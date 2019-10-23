package com.revaturelabs.ask.tags;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagNotFoundException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 7749497388328047729L;

  public TagNotFoundException(String message) {
    super(message);
  }
}
