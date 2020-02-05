package com.revaturelabs.ask.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.user.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.image.ImageConflictException;
import java.util.stream.Stream;
import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionControllerTest {

  @Test
  public void contextLoads() {}

  @MockBean
  QuestionService questionServiceMock;

  @Autowired
  QuestionController questionControllerImpl;

  @Mock
  MultipartHttpServletRequest mockRequest;

  /**
   * Tests related to Questions
   * 
   * This will test getting question by Id : qc 79
   */
  @Test
  public void testGetQuestionById() {
    Question exampleQuestion = new Question();
    try {
      when((questionServiceMock.getById(1))).thenReturn(exampleQuestion);
    } catch (Exception e) {
    }
    assertEquals(ResponseEntity.ok(exampleQuestion), questionControllerImpl.getQuestionById(1));
  }

  /**
   * This tests creating a question qc 94
   */

  @Test
  public void testCreateQuestion() {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    User user = new User();
    user.setId(1);
    Set<Tag> associatedTags = new HashSet<Tag>();
    exampleQuestion.setAssociatedTags(associatedTags);
    exampleQuestion.setUser(user);
    exampleQuestion.setQuestionerId(1);
    exampleQuestion.setAssociatedTags(associatedTags);
    when((questionServiceMock.create(exampleQuestion))).thenReturn(exampleQuestion);
    assertEquals(exampleQuestion, questionControllerImpl.createQuestion(exampleQuestion));
  }

  /**
   * This test creating or updating a question qc 131
   */

  @Test
  public void testCreateUpdateQuestion() throws QuestionConflictException {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");

    when((questionServiceMock.createOrUpdate(exampleQuestion))).thenReturn(null);
    assertEquals(null, questionControllerImpl.createOrUpdate(exampleQuestion, 1));
  }

  /**
   * This test updating a question qc 110
   * 
   * @throws QuestionConflictException
   * @throws QuestionNotFoundException
   */

  @Test
  public void testUpdateQuestion() throws QuestionConflictException, QuestionNotFoundException {
    Question exampleQuestion = new Question();
    exampleQuestion.setId(1);
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    when((questionServiceMock.update(exampleQuestion))).thenReturn(null);
    assertEquals(ResponseEntity.ok(null),
        questionControllerImpl.updateQuestion(exampleQuestion, 1));
  }

  // Tests added below this line---------------------------------

  /**
   * This will test getting question by Id while checking for exception thrown
   */
  @Test(expected = ResponseStatusException.class)
  public void testGetQuestionByIdForResponseStatusException() {
    try {
      when((questionServiceMock.getById(1))).thenThrow(QuestionNotFoundException.class);
    } catch (Exception e) {
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.getQuestionById(1));
  }

  /**
   * This test creating or updating a question while checking for exception thrown
   */
  @Test(expected = ResponseStatusException.class)
  public void testCreateUpdateQuestionForResponseStatusException()
      throws QuestionConflictException {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");

    when((questionServiceMock.createOrUpdate(exampleQuestion)))
        .thenThrow(QuestionConflictException.class);
    assertEquals(null, questionControllerImpl.createOrUpdate(exampleQuestion, 1));
  }

  /**
   * This will test updating a question when QuestionConflictException thrown
   */
  @Test(expected = ResponseStatusException.class)
  public void testUpdateQuestionForQuestionConflictException()
      throws QuestionConflictException, QuestionNotFoundException {
    Question exampleQuestion = new Question();
    exampleQuestion.setId(1);
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    when((questionServiceMock.update(exampleQuestion))).thenThrow(QuestionConflictException.class);
    assertEquals(ResponseEntity.ok(null),
        questionControllerImpl.updateQuestion(exampleQuestion, 1));
  }

  /**
   * This will test updating a question when QuestionNotFoundException thrown
   */
  @Test(expected = ResponseStatusException.class)
  public void testUpdateQuestionForQuestionNotFoundException()
      throws QuestionConflictException, QuestionNotFoundException {
    Question exampleQuestion = new Question();
    exampleQuestion.setId(1);
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    when((questionServiceMock.update(exampleQuestion))).thenThrow(QuestionNotFoundException.class);
    assertEquals(ResponseEntity.ok(null),
        questionControllerImpl.updateQuestion(exampleQuestion, 1));
  }

  @Test
  public void testGetImages() {
    Set<Image> images = new HashSet<Image>();
    Image i1 = new Image();
    Image i2 = new Image();
    images.add(i1);
    images.add(i2);

    Question question = new Question();

    when(questionServiceMock.getById(1)).thenReturn(question);
    assertEquals(ResponseEntity.ok(questionServiceMock.getById(1).getImages()),
        questionControllerImpl.getImages(1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testGetImagesForResponseStatusException() {
    when(questionServiceMock.getById(1)).thenThrow(QuestionNotFoundException.class);
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.getImages(1));
  }

  @Test
  public void testGetResponses() {
    Question question = new Question();

    when(questionServiceMock.getById(1)).thenReturn(question);
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.getResponses(1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testGetResponsesForResponseStatusException() {
    when(questionServiceMock.getById(1)).thenThrow(QuestionNotFoundException.class);
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.getResponses(1));
  }

  @Test
  public void testGetAllQuestions() {
    List<Question> questions = new ArrayList<Question>();
    Page<Question> page = new PageImpl<>(questions);
    when(questionServiceMock.getAll(0, 20)).thenReturn(page);
    assertEquals(ResponseEntity.ok(page.getContent()),
        questionControllerImpl.getAllQuestions(0, 20));
    assertEquals(ResponseEntity.ok(page.getContent()),
        questionControllerImpl.getAllQuestions(null, null));
  }

  @Test
  public void testHighlightResponse() {
    Question question = new Question();

    when(questionServiceMock.highlightResponse(1, 1)).thenReturn(question);
    assertEquals(ResponseEntity.ok(questionControllerImpl.highlightResponse(1, 1).getBody()),
        questionControllerImpl.highlightResponse(1, 1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testHighlightResponseForQuestionNotFoundException() {
    when(questionServiceMock.highlightResponse(1, 1)).thenThrow(QuestionNotFoundException.class);
    assertEquals(ResponseEntity.ok(questionControllerImpl.highlightResponse(1, 1).getBody()),
        questionControllerImpl.highlightResponse(1, 1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testHighlightResponseForQuestionConflictException() {
    when(questionServiceMock.highlightResponse(1, 1)).thenThrow(QuestionConflictException.class);
    assertEquals(ResponseEntity.ok(questionControllerImpl.highlightResponse(1, 1).getBody()),
        questionControllerImpl.highlightResponse(1, 1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testHighlightResponseFor() {
    when(questionServiceMock.highlightResponse(1, 1))
        .thenThrow(DataIntegrityViolationException.class);
    assertEquals(ResponseEntity.ok(questionControllerImpl.highlightResponse(1, 1).getBody()),
        questionControllerImpl.highlightResponse(1, 1));
  }

  @Test
  public void testFilterByTags() {
    List<Question> questions = new ArrayList<Question>();
    Page<Question> page = new PageImpl<>(questions);
    Stream<Question> question = page.get();
    List<String> tags = new ArrayList<String>();
    tags.add("Tag 1");
    tags.add("Tag 2");

    when(questionServiceMock.findAllByTagNames(false, tags, 0, 20)).thenReturn(question);
    assertEquals(ResponseEntity.ok(question).getBody(),
        questionControllerImpl.filterByTags(0, 20, false, tags).getBody());
    assertEquals(ResponseEntity.ok(question).getBody(),
        questionControllerImpl.filterByTags(null, null, null, tags).getBody());
  }

  @Test(expected = ResponseStatusException.class)
  public void testFilterByTagsForTagNotFoundException() {
    List<String> tags = new ArrayList<String>();
    tags.add("Tag 1");
    tags.add("Tag 2");

    when(questionServiceMock.findAllByTagNames(true, null, 0, 20))
        .thenThrow(TagNotFoundException.class);

    assertEquals(ResponseEntity.ok(null), questionControllerImpl.filterByTags(0, 20, true, null));
  }

  @Test
  public void testSetTags() {
    Question question = new Question();
    question.setId(1);

    when(questionServiceMock.updateTags(question)).thenReturn(question);
    assertEquals(ResponseEntity.ok(question), questionControllerImpl.setTags(question, 1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testSetTagsForTagNotFoundException() {
    Question question = new Question();
    question.setId(1);

    when(questionServiceMock.updateTags(question)).thenThrow(TagNotFoundException.class);
    assertEquals(ResponseEntity.ok(question), questionControllerImpl.setTags(question, 1));
  }

  @Test(expected = ResponseStatusException.class)
  public void testSetTagsForQuestionNotFound() {
    Question question = new Question();
    question.setId(1);

    when(questionServiceMock.updateTags(question)).thenThrow(QuestionNotFoundException.class);
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.setTags(question, 1));
  }

  @Test
  public void testAddImage() {
    Question question = new Question();
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest)).thenReturn(question);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(question), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForImageConflictEeption() {
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest))
          .thenThrow(ImageConflictException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForQuestionNotFoundException() {
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest))
          .thenThrow(QuestionNotFoundException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForIOException() {
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest)).thenThrow(IOException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }
}
