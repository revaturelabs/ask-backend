package com.revature.ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.revaturelabs.ask.tags.AppTagGetter;


@SpringBootApplication
@RestController
public class AskApplication {
	
	@Autowired
	AppTagGetter atg;

	public static void main(String[] args) {
		SpringApplication.run(AskApplication.class, args);
	}

}
