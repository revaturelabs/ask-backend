package com.revaturelabs.ask.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 6510033927498496397L;

  public ResponseNotFoundException(String message) {
    super(message);
  }
}
