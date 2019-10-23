package com.revaturelabs.ask.tags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TagApplication {

  public static void main(String[] args) {    
    SpringApplication.run(TagApplication.class, args);
  }

}
