package com.revaturelabs.ask.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/images")
public class ImageController {

  @Autowired
  ImageService imageService;

//  @PostMapping
//  public ResponseEntity<Image> addImage(MultipartHttpServletRequest request)
//      throws IOException, ImageConflictException {
//    try {
//      return ResponseEntity.ok(imageService.addImage(null, request));
//    } catch (ImageConflictException e) {
//      throw new ResponseStatusException(HttpStatus.CONFLICT, "There was an issue adding an image!",
//          e);
//    }
//  }

  @GetMapping("/{id}")
  public ResponseEntity<Image> getImage(@PathVariable int id) throws ImageNotFoundException {

    try {
      return ResponseEntity.ok(imageService.getImage(id));
    } catch (ImageNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found!", e);
    }
  }
}
