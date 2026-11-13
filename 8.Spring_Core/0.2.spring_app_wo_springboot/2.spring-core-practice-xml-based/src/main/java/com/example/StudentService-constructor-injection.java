package com.example;

@Service
public class StudentService {

    private Student student;

    public StudentService(Student student) {
        this.student = student;
    }

    public void getStudentDetails() {
        student.display();
    }
}