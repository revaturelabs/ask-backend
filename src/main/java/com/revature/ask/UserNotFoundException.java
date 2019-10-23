package com.revature.ask;

public class UserNotFoundException extends Exception {
  public UserNotFoundException() {
    super("User not found");
  }
  public UserNotFoundException(String m) {
    super("User not found " + m);
  }
}
