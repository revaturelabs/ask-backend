package com.revaturelabs.ask.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * An exception that's thrown in the event that a response hasn't been found.
 * 
 * @author Bryan Ritter
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 6510033927498496397L;

  public ResponseNotFoundException(String message) {
    super(message);
  }
}
