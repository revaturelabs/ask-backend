package com.revaturelabs.ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import com.revaturelabs.ask.user.*;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepo.class})
public class AskApplication {

  public static void main(String[] args) {
    SpringApplication.run(AskApplication.class, args);
  }

}
