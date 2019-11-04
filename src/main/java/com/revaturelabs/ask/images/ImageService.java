package com.revaturelabs.ask.images;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public interface ImageService {

  Boolean addImage(MultipartHttpServletRequest request) throws IOException, ImageConflictException;

  Image getImage(int id) throws ImageNotFoundException;

}
