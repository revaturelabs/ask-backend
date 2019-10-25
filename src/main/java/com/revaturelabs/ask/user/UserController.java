package com.revaturelabs.ask.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  UserServices userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable int id) {
    try {
      return ResponseEntity.ok(userService.findById(id));
    } catch (UserNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  @PutMapping("/{id}")
  public User createOrUpdate(@RequestBody User user, @PathVariable int id) {
    user.setId(id);
    try {
      return userService.createOrUpdate(user);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
    }
  }

  @PatchMapping("/{id}")
  public User updateUser(@RequestBody User user, @PathVariable int id) {
    user.setId(id);
    try {
      return userService.update(user);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User name already exists", e);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.create(user);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable int id) {
    try {
      userService.delete(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }
}


