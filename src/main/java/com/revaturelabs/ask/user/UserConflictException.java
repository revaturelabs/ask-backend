package com.revaturelabs.ask.user;

public class UserConflictException extends Exception {
  public UserConflictException() {
    super("User already exists.");
  }
}
