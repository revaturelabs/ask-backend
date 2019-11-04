package com.revaturelabs.ask.image;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping(path = "/image")
public class ImageController {

  @Autowired
  ImageService imageService;

  @PostMapping("/add")
  public ResponseEntity<Boolean> addImage(MultipartHttpServletRequest request) throws IOException, ImageConflictException {
    Boolean b = imageService.addImage(request);
    if (b == true)
      return ResponseEntity.status(HttpStatus.OK).body(b);
    else
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(b);
  }

  @GetMapping("/getImage/{id}")
  public ResponseEntity<Image> getImage(@PathVariable int id) throws ImageNotFoundException {

    Image p = imageService.getImage(id);
    if (p == null)
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(p);
    else
      return ResponseEntity.status(HttpStatus.OK).body(p);
  }
}
