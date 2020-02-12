package com.revaturelabs.ask.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.user.UserRepository;



@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepo;

  /**
   * "findAll" basically gets all
   * the users from the H2 database.
   * 
   * @return List of all users in database.
   */
  @Override
  public Page<User> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return userRepo.findAll(pageable);
  }

  /**
   * "findById" finds the user by their ID.
   * 
   * @param id
   * @return
   * @throws UserNotFoundException
   */
  @Override
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
  @Override
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
  @Override
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
  @Override
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

  @Override
  public User updateUserInfo(User user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String uploadProfilePicture(MultipartFile image) {
    AWSCredentials credentials = new BasicAWSCredentials(
        System.getenv("s3_access_key"), 
        System.getenv("s3_secret_key")
      );
    
    AmazonS3 s3client = AmazonS3ClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(Regions.US_EAST_1)
        .build();
   
    //EMERGENCY DELETE WEIRD OBJECTS ON BUCKET
    //s3client.deleteObject(System.getenv("s3_bucket_name"),"/Users/alexmao/Documents/Revature/ask-backend/src/main/java/com/revaturelabs/ask/user/TestFile.txt");
    
    try {
      byte[] bytes = image.getBytes();
      if (bytes == null) {
        throw new ImageConflictException("Invalid image");
      } else {
        String key = "TestFolder/" + image.getName();
        System.out.println(key);
        
        s3client.putObject(
            System.getenv("s3_bucket_name"), 
            key, 
            new File("/Users/alexmao/Documents/Revature/ask-backend/src/main/java/com/revaturelabs/ask/user/TestFile.txt")
          );
      }   
    }catch(IOException e) {
        e.printStackTrace();
    } catch (ImageConflictException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    
    ObjectListing objectListing = s3client.listObjects(System.getenv("s3_bucket_name"));
    for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
        System.out.println(os);
    }
    
    return null;
  }
}

