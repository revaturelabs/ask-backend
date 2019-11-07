package com.revaturelabs.ask.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Exception that occurs if a requested Image is not found 
 * @author Cort Gerlach
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 2767333166284362795L;

  public ImageNotFoundException(String message) {
    super(message);
  }
}
