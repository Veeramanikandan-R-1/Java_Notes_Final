package com.example;

// autowiring
public class StudentService {

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void getDetails() {
        student.display();
    }
}