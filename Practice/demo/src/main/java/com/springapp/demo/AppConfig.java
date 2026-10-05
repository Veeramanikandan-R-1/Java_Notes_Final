package com.springapp.demo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@ComponentScan("com.springapp.demo")
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig { 
    @Bean
    public EmailClient emailClient(){
        return new EmailClient();
    }

    @Bean
    public EmailService emailService(EmailClient emailClient){
        return new EmailService(emailClient);
    }
}
