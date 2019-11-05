package com.revaturelabs.ask.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.tag.Tag;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  /**
   * "findById" obtains a GET request for one user
   * by the ID column. Throws UserNotFoundException if it fails.
   * 
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable int id) {
    try {
      return userService.findById(id);
    } catch (UserNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  /**
   * "updateUser" obtains a PATCH request and updates the user,
   * in the respective affected columns.
   * It throws a UserNotFoundException if the userID isn't present
   * or a UserConflictException if user's info is intact.
   * 
   * @param user
   * @param id
   * @return
   */
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

  /**
   * "createUser" obtains a POST request and creates
   * a new row on the table for the new User object.
   * @param user
   * @return
   */
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.create(user);
  }
  
  /**
   * "createOrUpdate" obtains a PUT request, and either 
   * updates the User or creates one if it doesn't exist.
   * 
   * Otherwise, it throws a UserConflictException if
   * the user, with all information intact, already exists.
   * 
   * @param user
   * @param id
   * @return
   */
  @PutMapping("/{id}")
  public User createOrUpdate(@RequestBody User user, @PathVariable int id) {
    user.setId(id);
    try {
      
      return userService.createOrUpdate(user);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
    }
  }

  /**
   * "deleteUser" obtains a DELETE request with a userID
   * and deletes the user row in the DB if it exists.
   * Throws UserNotFoundException if it finds nothing to delete.
   * @param id
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable int id) {
    try {
      userService.delete(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }
  
  /**
   * "addUserTags"
   * 
   * 
   * @param user
   * @param id
   * @param tags
   * @return
   */
  @PatchMapping("/{id}/tags")
  public User addUserTags(@RequestBody User user, @PathVariable int id, @PathVariable Tag[] tags) {
    user.setId(id);
    try {
      return userService.addUserTags(user, tags);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User is not an expert", e);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }
}


