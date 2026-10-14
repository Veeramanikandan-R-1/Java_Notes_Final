package com.springapp.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        // var context = SpringApplication.run(DemoApplication.class, args);


        // UserService userService1 = context.getBean(UserService.class);
        // userService1.createUser();
        // userService1.deleteUser();

        // EmailService emailService = context.getBean(EmailService.class);
        // emailService.sendEmail();

        // AppService appService = context.getBean(AppService.class);
        // appService.printConfig();   
        
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
            logger.info("mani");
        };
    }
}
