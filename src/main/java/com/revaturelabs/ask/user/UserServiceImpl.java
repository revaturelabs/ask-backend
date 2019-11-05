package com.revaturelabs.ask.user;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public Page<User> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return userRepo.findAll(pageable);
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

    User updatedUser = null;
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
  /**
   * Specialized function to update the tags of an existing user.
   * 
   * @param user the User object with a set of tags to use for updating
   * @return updatedUser The user after being updated in the repository
   */
  
  @Override
  public User updateTags(User user) {
    Optional<User> existingUser = userRepo.findById(user.getId());

    User updatedUser = null;
   
    if (existingUser.isPresent()) {
      try {
        updatedUser = existingUser.get();
        updatedUser.setExpertTags(user.getExpertTags());
        updatedUser = userRepo.save(updatedUser);
      } catch (DataIntegrityViolationException e) {
        throw new UserConflictException();
      }
    } else {
      throw new UserNotFoundException("to update");
    }

    return updatedUser;
  }
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
