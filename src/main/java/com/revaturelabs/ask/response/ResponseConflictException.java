package com.revaturelabs.ask.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResponseConflictException extends RuntimeException {
  
  private static final long serialVersionUID = -979634931350991437L;

  public ResponseConflictException(String message) {
    super(message);
  }
}
