package com.revature.ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepo.class})
public class AskApplication {

  @Autowired
  UserRepo userRep;

  public static void main(String[] args) {
    SpringApplication.run(AskApplication.class, args);
  }

}
