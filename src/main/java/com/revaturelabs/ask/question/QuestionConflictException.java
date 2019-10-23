package com.revaturelabs.ask.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class QuestionConflictException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public QuestionConflictException(String message) {
    super(message);
  }
}
