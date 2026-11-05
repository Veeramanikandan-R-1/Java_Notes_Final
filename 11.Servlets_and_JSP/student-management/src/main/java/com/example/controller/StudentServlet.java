package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if ("new".equals(action)) {

                request.getRequestDispatcher(
                    "/WEB-INF/views/student-form.jsp"
                ).forward(request, response);

            } else if ("edit".equals(action)) {

                int id = Integer.parseInt(
                    request.getParameter("id")
                );

                Student student = studentDAO.findById(id);

                if (student == null) {
                    response.sendError(404, "Student not found");
                    return;
                }

                request.setAttribute("student", student);

                request.getRequestDispatcher(
                    "/WEB-INF/views/student-form.jsp"
                ).forward(request, response);

            } else if ("summary".equals(action)) {

                request.setAttribute(
                    "message", "Data received from StudentServlet"
                );

                request.getRequestDispatcher(
                    "/student-summary"
                ).forward(request, response);

            } else {

                request.setAttribute(
                    "students", studentDAO.findAll()
                );

                request.getRequestDispatcher(
                    "/WEB-INF/views/students.jsp"
                ).forward(request, response);
            }

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if ("create".equals(action)) {

                Student student = new Student(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("course")
                );

                studentDAO.save(student);

            } else if ("update".equals(action)) {

                Student student = new Student(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("course")
                );

                studentDAO.update(student);

            } else if ("delete".equals(action)) {

                int id = Integer.parseInt(
                    request.getParameter("id")
                );

                studentDAO.delete(id);
            }

            response.sendRedirect(
                request.getContextPath() + "/students"
            );

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }
}
