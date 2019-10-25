package com.revaturelabs.ask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AskApplication {

  public static void main(String[] args) {
    SpringApplication.run(AskApplication.class, args);
  }

  /**
   * Produces the Swagger 2 Docket Bean for the apis in com.revaturelabs.ask
   */
  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.revaturelabs.ask")).build();

  }
}
