package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        // Create Spring IoC Container
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Bean from Spring Container
        StudentService service =
                context.getBean(StudentService.class);

        // Execute business logic
        service.getStudentDetails();

        // Close container
        context.close();
    }
}