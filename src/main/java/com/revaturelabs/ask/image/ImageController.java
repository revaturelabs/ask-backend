package com.revaturelabs.ask.image;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * 
 * Controller class for images
 * 
 * @author Cort Gerlach, Chris Allen
 *
 */
@RestController
@RequestMapping(path = "/images")
public class ImageController {

  @Autowired
  ImageService imageService;

  /**
   * Accepts HTTP GET requests. Method to retrieve an existing image by its id
   * 
   * @param questionId The id of the image's question in the database
   * @return ResponseEntity<Image> An image in the response entity
   * @throws ImageNotFoundException Exception that occurs if the given ID doesn't exist in the
   *         database
   */
  @GetMapping("/{questionId}")
  public ResponseEntity<Set<Image>> getImage(@PathVariable int questionId) throws ImageNotFoundException {

    try {
      return ResponseEntity.ok(imageService.getImages(questionId));
    } catch (ImageNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found!", e);
    }
  }
}
