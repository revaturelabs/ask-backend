package com.revaturelabs.ask.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ImageConflictException extends Exception {

  private static final long serialVersionUID = -2907715669402016388L;

  public ImageConflictException(String message) {
    super(message);
  }
}
