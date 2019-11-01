package com.revaturelabs.ask.user;

public class UserNotFoundException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 7935151869324561571L;

  public UserNotFoundException() {
    super("User not found");
  }

  public UserNotFoundException(String m) {
    super("User not found " + m);
  }
}
