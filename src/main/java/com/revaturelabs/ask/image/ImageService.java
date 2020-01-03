package com.revaturelabs.ask.image;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.question.Question;


/**
 * Service class for image objects for Ask An Expert.
 * 
 * @author Cort Gerlach, Chris Allen
 *
 */
@Service
public interface ImageService {

  Set<Image> getImages(int id) throws ImageNotFoundException;

  Image addImage(Question question, MultipartHttpServletRequest request)
      throws IOException, ImageConflictException;

}
