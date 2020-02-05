package com.revaturelabs.ask.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.image.ImageController;
import com.revaturelabs.ask.image.ImageNotFoundException;
import com.revaturelabs.ask.image.ImageService;
import com.revaturelabs.ask.question.Question;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {

  @Test
  public void contextLoads() {}

  @MockBean
  ImageService imageServiceMock;

  @Autowired
  ImageController imageControllerImpl;

  @Test
  public void testGetImage() {
    Set<Image> images = new HashSet<Image>();
    Image testImage = new Image();
    Question imAQuestion = new Question();
    testImage.setId(10);
    byte[] byteArr = new byte[1];
    byteArr[0] = '1';
    testImage.setImage(byteArr);
    testImage.setQuestion(imAQuestion);
    images.add(testImage);

    when(imageServiceMock.getImages(10)).thenReturn(images);
    assertEquals(ResponseEntity.ok(images), imageControllerImpl.getImage(10));
  }

  @Test(expected = ResponseStatusException.class)
  public void testFailureGetImage() {
    when(imageServiceMock.getImages(10)).thenThrow(ImageNotFoundException.class);
    imageControllerImpl.getImage(10);
  }
}
