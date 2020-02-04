package com.revaturelabs.ask.image;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionRepository;

/**
 * 
 * An implementation of service methods for images for Ask An Expert. Can add and retrieve images.
 * 
 * @author Cort Gerlach, Chris Allen
 *
 */
@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  ImageRepository imageRepository;

  @Autowired
  QuestionRepository questionRepository;

  /**
   * Method for adding an image to the database and attaching it to a question.
   * 
   * @param question The question to be attached to
   * @param request a Multipart Http Servlet Request that contains the image
   * @throws IOException exception that occurs if the image is inserted improperly
   * @throws ImageConflictException exception that occurs if there are no bytes passed in from the
   *         request
   * @return image The image object after being added to the database
   * 
   */
  @Override
  public Image addImage(Question question, MultipartHttpServletRequest request)
      throws IOException, ImageConflictException {
    MultipartFile mPF = request.getFile("image");
    byte[] bytes = mPF.getBytes();
    if (bytes == null) {
      throw new ImageConflictException("Invalid image");
    } else {
      Image image = new Image(0, bytes, question);
      return imageRepository.save(image);
    }
  }

  /**
   * Method for retrieving an image from the database based on ID.
   * 
   * @param id The id of the question of the image to be retrieved
   * @throws ImageNotFoundException Exception that is thrown when the given ID doesn't exist
   * @return image The image to be retrieved
   */
  @Override
  public Set<Image> getImages(int id) throws ImageNotFoundException {
    try {
      Set<Image> images = questionRepository.findById(id).get().getImages();
      if (images.isEmpty() || images == null) {
        throw new ImageNotFoundException("Image not found");
      }
      return images;
    } catch (Exception e) {
      throw new ImageNotFoundException("Image not found");
    }
  }
}
