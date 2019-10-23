package com.revaturelabs.ask.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.user.UserRepo;

@Service
public class UserServices {

  @Autowired
  private UserRepo userRepo;

  public List<User> findAll() {
    return (List<User>) userRepo.findAll();
  }

  public User findById(int id) throws UserNotFoundException {
    Optional<User> user = userRepo.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException();
    }

    return user.get();
  }

  public User create(User user) {
    return userRepo.save(user);
  }

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

  public User createOrUpdate(User user) throws UserConflictException {
    User updatedUser = null;
    try {
      updatedUser = userRepo.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new UserConflictException();
    }

    return updatedUser;
  }

  public void delete(int id) throws UserNotFoundException {
    boolean userExists = userRepo.existsById(id);

    if (!userExists) {
      throw new UserNotFoundException("to delete");
    }

    userRepo.deleteById(id);
  }
}

