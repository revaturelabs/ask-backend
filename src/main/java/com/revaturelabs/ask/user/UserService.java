package com.revaturelabs.ask.user;

import javax.servlet.http.Part;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


/**
 * The user service interface for Ask-An-Expert
 * 
 * @author Carlos Santos, Chris Allen
 *
 */
public interface UserService {

  /**
   * "findAll" basically gets all
   * the users from the H2 database based on the size.
   * 
   * @return Page of all users in database.
   */
  Page<User> findAll(int page, int size);

  /**
   * "findById" finds the user by their ID.
   * 
   * @param id
   * @return
   * @throws UserNotFoundException
   */
  User findById(int id) throws UserNotFoundException;

  /**
   * "create" gets the user object and
   * creates a new row for it in the database.
   * 
   * @param user
   * @return
   */
  User create(User user);

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
  User update(User user) throws UserNotFoundException, UserConflictException;

  /**
   * "createOrUpdate" does the same function as
   * "update" except it also creates the user
   * if the user in question doesn't exist.
   * 
   * @param user
   * @return
   * @throws UserConflictException
   */
  User createOrUpdate(User user) throws UserConflictException;

  /**
   * "delete" basically looks for the ID of the 
   * user and deletes the specified user with 
   * that ID.
   * 
   * @param id
   * @throws UserNotFoundException
   */
  void delete(int id) throws UserNotFoundException;

  /**
   * "updateTags" specialized function to update an existing user's tags
   * @param user a user object with the set of tags to be updated
   * @return The updated user object
   */
  User updateTags(User user);
  
  /**
   * "updateUserInfo" is used to update user info
   * @param A user object
   * @return The updated User with new information
   */
  User updateUserInfo(User user);
  
  /**
   * uploadProfilePicture handles uploading the image to an S3 bucket
   * @param A Part of a Multipart File referencing the new Image
   * @return An S3 key to the image file
   */
  String uploadProfilePicture(MultipartFile image, String username);

}
