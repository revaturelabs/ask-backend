package com.revaturelabs.ask.image;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public interface ImageService {

  Image addImage(MultipartHttpServletRequest request) throws IOException, ImageConflictException;

  Image getImage(int id) throws ImageNotFoundException;

}
