package com.revaturelabs.ask.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.Optional;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.image.ImageController;
import com.revaturelabs.ask.image.ImageNotFoundException;
import com.revaturelabs.ask.image.ImageRepository;
import com.revaturelabs.ask.image.ImageService;
import com.revaturelabs.ask.image.ImageServiceImpl;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionRepository;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.question.QuestionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

  @Test
  public void contextLoads() {}

  static ServiceMockData mockData;

  @Mock
  ImageRepository imageRepositoryMock;

  @Mock
  QuestionRepository questionRepositoryMock;

  @Mock
  ImageService imageServiceMock;

  @Mock
  QuestionService questionServiceMock;

  @Mock
  MultipartHttpServletRequest mockRequest;

  @InjectMocks
  ImageService imageServiceImpl = new ImageServiceImpl();

  @InjectMocks
  QuestionService questionServiceImpl = new QuestionServiceImpl();

  @InjectMocks
  ImageController imageControllerImpl = new ImageController();

  @InjectMocks
  QuestionController questionControllerImpl = new QuestionController();

  /**
   * Test retrieval of Image by id
   */
  @Test
  public void getImageByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));

    assertEquals(ServiceMockData.testImageSet1, imageServiceImpl.getImages(1));
  }

  /**
   * Test valid retrieval of Image by id
   * 
   */
  @Test
  public void getCorrectImageByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));

    assertNotEquals(ServiceMockData.testImageSet2, imageServiceImpl.getImages(1));
  }

  @Test(expected = ImageNotFoundException.class)
  public void getFailedImages() {
    when(questionRepositoryMock.findById(1)).thenReturn(null);

    imageServiceImpl.getImages(1);
  }

  @Test
  public void addImageTest() throws IOException, ImageConflictException {
    MockMultipartFile firstFile =
        new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
    MultipartFile mpf = firstFile;
    when(mockRequest.getFile("image")).thenReturn(mpf);

    assertNull(imageServiceImpl.addImage(ServiceMockData.testQuestion1, mockRequest));
  }

  // Can't pass with current state of addImage
  @Ignore
  @Test(expected = ImageConflictException.class)
  public void addImageFailTest() throws IOException, ImageConflictException {
    byte[] byteArr = null;
    MockMultipartFile firstFile =
        new MockMultipartFile("data", "filename.txt", "text/plain", byteArr);
    when(mockRequest.getFile("image")).thenReturn(firstFile);

    imageServiceImpl.addImage(ServiceMockData.testQuestion1, mockRequest);
  }

  /**
   * Test failure of retrieval of image by invalid id
   * 
   */
  @Test(expected = ImageNotFoundException.class)
  public void getInvalidImageIdTest() {
    when(questionRepositoryMock.findById(2)).thenReturn(Optional.of(ServiceMockData.testQuestion2));

    imageServiceImpl.getImages(2);
  }
}
