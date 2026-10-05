package com.springapp.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DemoApplication.class, args);


        UserService userService1 = context.getBean(UserService.class);
        userService1.createUser();

        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendEmail();

        AppService appService = context.getBean(AppService.class);
        appService.printConfig();
    }
}
