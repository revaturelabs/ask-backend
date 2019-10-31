package com.revaturelabs.ask.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.user.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepo;

  /**
   * "findAll" basically gets all
   * the users from the H2 database.
   * 
   * @return List of all users in database.
   */
  public List<User> findAll() {
    return (List<User>) userRepo.findAll();
  }

  /**
   * "findById" finds the user by their ID.
   * 
   * @param id
   * @return
   * @throws UserNotFoundException
   */
  public User findById(int id) throws UserNotFoundException {
    Optional<User> user = userRepo.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException();
    }

    return user.get();
  }

  /**
   * "create" gets the user object and
   * creates a new row for it in the database.
   * 
   * @param user
   * @return
   */
  public User create(User user) {
    return userRepo.save(user);
  }

  /**
   * "update" checks if the user exists beforehand.
   * If so, it saves changes into the specified row
   * of the database, given it doesn't overwrite the ID.
   * 
   * If not, it throws the exception.
   * 
   * @param user
   * @return
   * @throws UserNotFoundException
   * @throws UserConflictException
   */
  public User update(User user) throws UserNotFoundException, UserConflictException {
    Optional<User> existingUser = userRepo.findById(user.getId());

    User updatedUser = null;
    if (existingUser.isPresent()) {
      try {
        updatedUser = userRepo.save(user);
      } catch (DataIntegrityViolationException e) {
        throw new UserConflictException();
      }
    } else {
      throw new UserNotFoundException("to update");
    }

    return updatedUser;
  }

  /**
   * "createOrUpdate" does the same function as
   * "update" except it also creates the user
   * if the user in question doesn't exist.
   * 
   * @param user
   * @return
   * @throws UserConflictException
   */
  public User createOrUpdate(User user) throws UserConflictException {
    User updatedUser = null;
    try {
      updatedUser = userRepo.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new UserConflictException();
    }

    return updatedUser;
  }

  /**
   * "delete" basically looks for the ID of the 
   * user and deletes the specified user with 
   * that ID.
   * 
   * @param id
   * @throws UserNotFoundException
   */
  public void delete(int id) throws UserNotFoundException {
    boolean userExists = userRepo.existsById(id);

    if (!userExists) {
      throw new UserNotFoundException("to delete");
    }

    userRepo.deleteById(id);
  }
}

