package com.revaturelabs.ask.user;

/**
 * Here is an exception for when user already exists.
 * 
 * @author csantos777
 *
 */
public class UserConflictException extends RuntimeException {
  public UserConflictException() {
    super("Error: 1. User already exists\n" 
        + " 2. Can't overwrite user data at this point.");
  }
}
