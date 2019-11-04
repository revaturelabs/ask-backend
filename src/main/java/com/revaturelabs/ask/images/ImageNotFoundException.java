package com.revaturelabs.ask.images;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends Exception {

  private static final long serialVersionUID = 2767333166284362795L;

  public ImageNotFoundException(String message) {
    super(message);
  }
}
