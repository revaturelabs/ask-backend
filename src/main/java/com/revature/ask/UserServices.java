package com.revature.ask;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

  @Autowired
  private UserRepo userRepo;

  public List<User> findAll() {
    return (List<User>) userRepo.findAll();
  }

  public User findById(int id) throws UserNotFoundException {
    Optional<User> user = userRepo.findById(id);

    if(!user.isPresent()) {
        throw new UserNotFoundException("User not found");
    }

    return user.get();
  }
  
  
}
