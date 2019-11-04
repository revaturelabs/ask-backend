package com.revaturelabs.ask.image;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.question.Question;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  ImageRepository imageRepository;

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

  @Override
  public Image getImage(int id) throws ImageNotFoundException {
    Optional<Image> image = imageRepository.findById(id);

    if (!image.isPresent()) {
      throw new ImageNotFoundException("Image not found");
    }
    return image.get();
  }

}
