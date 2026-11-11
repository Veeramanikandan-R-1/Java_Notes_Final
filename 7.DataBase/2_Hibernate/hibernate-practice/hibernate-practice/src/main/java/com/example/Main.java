
package com.example;

import com.example.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Create SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()) {

            // CREATE
            Student student = new Student(
                    "Arun",
                    "arun@gmail.com"
            );

            factory.inTransaction(session -> {
                session.persist(student);
            });

            Long studentId = student.getId();

            System.out.println("Student created: " + studentId);

            // READ BY ID
            factory.inTransaction(session -> {
                Student result = session.find(Student.class, studentId);
                System.out.println("Fetched: " + result);
            });

            // READ ALL
            factory.inTransaction(session -> {
                List<Student> students =
                        session.createSelectionQuery(
                                "FROM Student", Student.class
                        ).getResultList();

                System.out.println("All students:");

                students.forEach(System.out::println);
            });

            // UPDATE
            factory.inTransaction(session -> {
                Student result = session.find(Student.class, studentId);

                if (result != null) {
                    result.setName("Arun Kumar");
                    result.setEmail("arunkumar@gmail.com");
                }
            });

            System.out.println("Student updated");

            // DELETE
            factory.inTransaction(session -> {
                Student result = session.find(Student.class, studentId);

                if (result != null) {
                    session.remove(result);
                }
            });

            System.out.println("Student deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
