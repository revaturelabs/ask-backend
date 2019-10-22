package com.revaturelabs.ask.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class QuestionApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuestionApplication.class, args);
	}

}
