package com.revaturelabs.ask.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends Exception {

  
  /**
   * 
   */
  private static final long serialVersionUID = -2329992299469419407L;

  public QuestionNotFoundException(String message) {
    super(message);
  }
}
