package com.revaturelabs.ask.user;

/**
 * Here is an exception for when user already exists.
 * 
 * @author csantos777
 *
 */
public class UserConflictException extends RuntimeException {
  public UserConflictException() {
    super("User already exists.");
  }
}
