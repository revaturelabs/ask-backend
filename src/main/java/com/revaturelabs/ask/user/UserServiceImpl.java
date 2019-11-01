package com.revaturelabs.ask.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepo;
  
  @Autowired
  private TagService tagService;

  @Override
  public List<User> findAll() {
    return (List<User>) userRepo.findAll();
  }

  @Override
  public User findById(int id) throws UserNotFoundException {
    Optional<User> user = userRepo.findById(id);

    if (!user.isPresent()) {
      throw new UserNotFoundException();
    }

    return user.get();
  }

  @Override
  public User create(User user) {
    return userRepo.save(user);
  }

  @Override
  public User update(User user) throws UserNotFoundException, UserConflictException {
    Optional<User> existingUser = userRepo.findById(user.getId());

    User updatedUser = existingUser.get();
    if (existingUser.isPresent()) {
      try {
        if (!(user.getUsername().equals(updatedUser.getUsername()) ||
            user.getPassword().equals(updatedUser.getPassword())))
          updatedUser = userRepo.save(user);
      } catch (DataIntegrityViolationException e) {
        throw new UserConflictException();
      }
    } else {
      throw new UserNotFoundException("to update");
    }

    return updatedUser;
  }

  @Override
  public User createOrUpdate(User user) throws UserConflictException {
    User updatedUser = userRepo.findById(user.getId()).get();
    //User presentUser
    try {
      if (!(user.getUsername().equals(updatedUser.getUsername()) ||
          user.getPassword().equals(updatedUser.getPassword())))
        updatedUser = userRepo.save(user);
    } catch (DataIntegrityViolationException e) {
      throw new UserConflictException();
    }

    return updatedUser;
  }

  @Override
  public void delete(int id) throws UserNotFoundException {
    boolean userExists = userRepo.existsById(id);

    if (!userExists) {
      throw new UserNotFoundException("to delete");
    }

    userRepo.deleteById(id);
  }

  @Override
  public User addUserTags(User user, Tag[] tags) throws UserNotFoundException, UserConflictException {
    Optional<User> existingUser = userRepo.findById(user.getId());
    Set<Tag> tagsReq = new HashSet<Tag>();
    
    User updatedUser = null;
    if (existingUser.isPresent() && tags != null && user.isExpert()) {
      try {
        for (Tag tag : tags) {
          tagsReq.add(tag);
        }
        user.setUserTags(tagsReq);
        updatedUser = userRepo.save(user);
      } catch (DataIntegrityViolationException e) {
        throw new UserConflictException();
      }
    } else {
      throw new UserNotFoundException("to update");
    }

    return updatedUser;
  }
}
