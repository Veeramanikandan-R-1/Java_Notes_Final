package com.example.spring_app_jdbc_h2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.spring_app_jdbc_h2db.model.Alien;
import com.example.spring_app_jdbc_h2db.repo.AlienRepo;

@SpringBootApplication
public class SpringAppJdbcH2dbApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringAppJdbcH2dbApplication.class, args);
		AlienRepo repo = context.getBean(AlienRepo.class);
		Alien alien = context.getBean(Alien.class);
		alien.setId(105);
		alien.setName("Ravi");
		alien.setTech("Java");
		repo.save(alien);
		// fetch all aliens from the database
		repo.findAll().forEach(a -> System.out.println(a));	
	}

}
