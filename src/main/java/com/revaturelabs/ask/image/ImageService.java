package com.revaturelabs.ask.image;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.question.Question;

@Service
public interface ImageService {

  Image getImage(int id) throws ImageNotFoundException;

  Image addImage(Question question, MultipartHttpServletRequest request)
      throws IOException, ImageConflictException;

}
