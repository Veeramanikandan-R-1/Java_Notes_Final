package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private Student student;

    @Autowired
    public StudentService(Student student) {
        this.student = student;
    }

    public void getStudentDetails() {
        student.display();
    }
}