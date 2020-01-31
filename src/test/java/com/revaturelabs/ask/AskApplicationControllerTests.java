package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.base.Optional;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserNotFoundException;
import com.revaturelabs.ask.user.UserService;

import io.micrometer.core.ipc.http.HttpSender.Request;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationControllerTests {

  @Test
  public void contextLoads() {}

  @MockBean
  TagRepository tagRepositoryMock;

  @MockBean
  UserService userServiceMock;

  @MockBean
  ResponseService responseServiceMock;

  @MockBean
  TagService tagServiceMock;

  @MockBean
  QuestionService questionServiceMock;

  @Autowired
  UserController userControllerImpl;

  @Autowired
  ResponseController responseControllerImpl;

  @Autowired
  TagController tagControllerImpl;

  @Autowired
  TagService tagServiceImpl;

  @Autowired
  QuestionController questionControllerImpl;

  @Mock
  MultipartHttpServletRequest mockRequest;

  @Test
  public void testGetResponseById() {

    Response exampleResponse = new Response();
    org.mockito.Mockito.when((this.responseServiceMock.getById(-999))).thenReturn(exampleResponse);
    assertEquals(ResponseEntity.ok(exampleResponse),
        this.responseControllerImpl.getResponseById(-999));
  }

  /**
   * Tests if creating a response returns the same response
   */
  @Test
  public void testCreateResponse() {
    Response exampleResponse = new Response();
    org.mockito.Mockito.when(this.responseServiceMock.create(exampleResponse))
        .thenReturn(exampleResponse);
    assertEquals(exampleResponse, this.responseControllerImpl.createResponse(exampleResponse));
  }

  /**
   * Tests updating a response if it exists
   */

  @Test
  public void testUpdateExistingResponse() {

    Response exampleResponse1 = new Response();
    exampleResponse1.setBody("I'm here 1");
    exampleResponse1.setId(-11);
    Response exampleResponse2 = new Response();
    exampleResponse2.setBody("I'm here 2");
    exampleResponse2.setId(-22);

    org.mockito.Mockito.when(this.responseServiceMock.update(exampleResponse2))
        .thenReturn(exampleResponse2);
    assertEquals(exampleResponse2,
        this.responseControllerImpl.updateResponse(exampleResponse2, -11));
    System.out.println("exampleResponse1: " + exampleResponse1);
    System.out.println("exampleResponse2: " + exampleResponse2); // Should match number above
  }

  /**
   * Makes sure attempts on a non-existent id results in an error
   * 
   * @throws ResponseNotFoundException
   */
  @Test
  public void testUpdateNonexistingResponse() throws ResponseNotFoundException {

    Response exampleResponse3 = new Response();
    exampleResponse3.setBody("I'm here 3");
    exampleResponse3.setId(-33);

    org.mockito.Mockito.when(this.responseServiceMock.getById(-33))
        .thenThrow(new ResponseNotFoundException(null));
    assertEquals(null, this.responseControllerImpl.updateResponse(exampleResponse3, -33));
  }

  /**
   * Tests if CreateOrUpdate results in a response being created or updated
   */
  @Test
  public void TestCreateOrUpdateResponse() {
    Response exampleResponse4 = new Response();
    exampleResponse4.setBody("I'm here 4");
    exampleResponse4.setId(-44);

    org.mockito.Mockito.when(this.responseServiceMock.createOrUpdate(exampleResponse4))
        .thenReturn(exampleResponse4);
    assertEquals(exampleResponse4,
        this.responseControllerImpl.createOrUpdate(exampleResponse4, -44));
  }

  @Test
  public void testGetAllResponses() {
    Response exampleResponse5 = new Response();
    exampleResponse5.setBody("I'm here 5");
    exampleResponse5.setId(-55);
    Response exampleResponse6 = new Response();
    exampleResponse6.setBody("I'm here 6");
    exampleResponse6.setId(-66);
    List<Response> listResponses = new ArrayList<>();
    listResponses.add(exampleResponse5);
    listResponses.add(exampleResponse6);
    org.mockito.Mockito.when(this.responseServiceMock.getAll()).thenReturn(listResponses);
    assertEquals(ResponseEntity.ok(listResponses), this.responseControllerImpl.getAllResponses());
  }

  /**
   * Tests for tags.
   */

  /**
   * Test getTagById returns a tag from the service layer
   */
  @Test
  public void testGetTagById() {
    Tag exampleTag = new Tag();
    exampleTag.setId(1);
    exampleTag.setName("Java");
    when((tagServiceMock.getById(1))).thenReturn(exampleTag);
    assertEquals(ResponseEntity.ok(exampleTag), tagControllerImpl.getTagById(1));
  }

  /**
   * Test that getAllTags in the controller returns a list of tags
   */

  @Test
  public void testGetAllTags() {
    Tag javaScriptTag = new Tag();
    javaScriptTag.setName("JavaScript");
    Tag javaTag = new Tag();
    javaTag.setName("Java");
    List<Tag> exampleTags = new ArrayList<Tag>();
    exampleTags.add(javaScriptTag);
    exampleTags.add(javaTag);
    when((tagServiceMock.getAll())).thenReturn(exampleTags);
    assertEquals(ResponseEntity.ok(exampleTags), tagControllerImpl.getAllTags());
  }

  /**
   * That that the createTag method returns the new tag created
   */
  @Test
  public void testCreateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createTag(exampleTag));
  }

  /**
   * Test that updateTag returns the updated tag
   */
  @Test
  public void testUpdateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setId(1);
    exampleTag.setName("JavaScript");

    when((tagServiceMock.update(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.updateTag(exampleTag, 1));
  }

  /**
   * Test deleting tag.
   */
  // @Test
  // public void testDeleteTag() {
  // Tag exampleTag = new Tag();
  // exampleTag.setId(1);
  // exampleTag.setName("JavaScript");
  //
  // when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
  // tagServiceMock.create(exampleTag);
  // tagServiceMock.delete(1)
  // when((tagServiceMock.getById(exampleTag.getId()))).thenReturn(null);
  // assertEquals(null, tagControllerImpl.getTagById(1), null);
  //
  // }

  /**
   * Test that createOrUpdate returns the tag to be created/updated
   */
  @Test
  public void testCreateUpdateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.createOrUpdate(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createOrUpdate(exampleTag, 1));
  }

  /**
   * Test a failed tag update would return a null tag.
   */
  @Test
  public void testUpdateTagFails() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    exampleTag.setId(1);
    Tag exampleTag2 = new Tag();
    exampleTag2.setName("Java");
    when(this.tagServiceMock.update(exampleTag)).thenReturn(null);
    assertEquals(null, tagControllerImpl.updateTag(exampleTag, 5));
  }

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

  @Test(expected = ResponseStatusException.class)
  public void testGetQuestionByIdForResponseStatusException() {
    Question exampleQuestion = new Question();
    try {
      when((questionServiceMock.getById(1))).thenThrow(QuestionNotFoundException.class);
    } catch (Exception e) {
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.getQuestionById(1));
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

  @Test
  public void testQuestionHashCodeAndEquals() {
    Question exampleQuesiton = new Question();
    Question exampleQuastion2 = new Question();
    Question exampleQuestion3 = new Question();
    Question exampleQuesiton4 = new Question();
    Question exampleQuesiton5 = new Question();
    Question exampleQuesiton6 = new Question();
    Question exampleQuesiton7 = new Question();
    Question exampleQuesiton8 = new Question();
    Question exampleQuesiton9 = new Question();
    Question exampleQuesiton10 = new Question();
    Question exampleQuesiton11 = new Question();
    Question exampleQuesiton12 = new Question();
    Question exampleQuesiton13 = new Question();
    Question exampleQuesiton14 = new Question();
    Question emptyQuestion = null;

    Tag javaTag = new Tag();
    javaTag.setName("Java");
    Tag jsTag = new Tag();
    jsTag.setName("JavaScript");

    Set<Tag> setTag = new HashSet<Tag>();
    setTag.add(javaTag);
    setTag.add(jsTag);

    Set<Tag> setTag2 = new HashSet<Tag>();
    setTag2.add(javaTag);

    Image image = new Image();
    Image image2 = new Image();
    image2.setId(1);
    Set<Image> imageSet = new HashSet<Image>();
    imageSet.add(image);

    Response resp1 = new Response();
    Response resp2 = new Response();
    Set<Response> setResponse = new HashSet<Response>();
    Set<Response> setResponse2 = new HashSet<Response>();
    setResponse.add(resp1);
    setResponse2.add(resp2);

    User user = new User();
    User user2 = new User();
    user2.setId(1);
    user2.setExpert(true);

    Date date = new Date();
    Date date2 = new Date();
    date2.setDate(02162001);

    assertTrue(
        exampleQuesiton.equals(exampleQuastion2) && exampleQuastion2.equals(exampleQuesiton));
    assertFalse(exampleQuesiton.equals(emptyQuestion) && emptyQuestion.equals(exampleQuesiton));
    assertTrue(exampleQuesiton.hashCode() == exampleQuastion2.hashCode());
    assertTrue(emptyQuestion == null);

    assertNotNull(exampleQuesiton.toString());

    exampleQuesiton.setAssociatedTags(setTag);
    exampleQuesiton.setBody("This is the body");
    exampleQuesiton.setHead("This is the head");
    exampleQuesiton.setId(1);
    exampleQuesiton.setQuestionerId(1);
    exampleQuesiton.setUser(user);
    exampleQuesiton.setResponses(setResponse);
    exampleQuesiton.setHighlightedResponseId(1);
    exampleQuesiton.setCreationDate(date);
    exampleQuesiton.addTagToQuestion(javaTag);
    exampleQuesiton.addImageToImages(image);
    exampleQuesiton.setImages(imageSet);

    exampleQuastion2.setAssociatedTags(null);
    exampleQuastion2.setBody(null);
    exampleQuastion2.setHead(null);
    exampleQuastion2.setId(null);
    exampleQuastion2.setQuestionerId(null);
    exampleQuastion2.setUser(null);
    exampleQuastion2.setResponses(null);
    exampleQuastion2.setHighlightedResponseId(null);
    exampleQuastion2.setCreationDate(null);
    exampleQuastion2.addTagToQuestion(null);
    exampleQuastion2.addImageToImages(null);

    exampleQuestion3.setAssociatedTags(null);
    exampleQuestion3.setBody(null);
    exampleQuestion3.setHead(null);
    exampleQuestion3.setId(null);
    exampleQuestion3.setQuestionerId(null);
    exampleQuestion3.setUser(null);
    exampleQuestion3.setResponses(null);
    exampleQuestion3.setHighlightedResponseId(null);
    exampleQuestion3.setCreationDate(null);
    exampleQuestion3.addTagToQuestion(null);
    exampleQuestion3.addImageToImages(null);

    exampleQuesiton4.setAssociatedTags(setTag2);
    exampleQuesiton4.setBody("This is the body");
    exampleQuesiton4.setHead("This is the head");
    exampleQuesiton4.setId(1);
    exampleQuesiton4.setQuestionerId(1);
    exampleQuesiton4.setUser(user);
    exampleQuesiton4.setResponses(setResponse);
    exampleQuesiton4.setHighlightedResponseId(1);
    exampleQuesiton4.setCreationDate(date);
    exampleQuesiton4.addTagToQuestion(javaTag);
    exampleQuesiton4.addImageToImages(image);
    exampleQuesiton4.setImages(imageSet);

    exampleQuesiton5.setAssociatedTags(setTag);
    exampleQuesiton5.setBody(null);
    exampleQuesiton5.setHead("This is the head");
    exampleQuesiton5.setId(1);
    exampleQuesiton5.setQuestionerId(1);
    exampleQuesiton5.setUser(user);
    exampleQuesiton5.setResponses(setResponse);
    exampleQuesiton5.setHighlightedResponseId(1);
    exampleQuesiton5.setCreationDate(date);
    exampleQuesiton5.addTagToQuestion(javaTag);
    exampleQuesiton5.addImageToImages(image);
    exampleQuesiton5.setImages(imageSet);

    exampleQuesiton6.setAssociatedTags(setTag);
    exampleQuesiton6.setBody("This is the body");
    exampleQuesiton6.setHead(null);
    exampleQuesiton6.setId(1);
    exampleQuesiton6.setQuestionerId(1);
    exampleQuesiton6.setUser(user);
    exampleQuesiton6.setResponses(setResponse);
    exampleQuesiton6.setHighlightedResponseId(1);
    exampleQuesiton6.setCreationDate(date);
    exampleQuesiton6.addTagToQuestion(javaTag);
    exampleQuesiton6.addImageToImages(image);
    exampleQuesiton6.setImages(imageSet);

    exampleQuesiton7.setAssociatedTags(setTag);
    exampleQuesiton7.setBody("This is the body");
    exampleQuesiton7.setHead("This is the head");
    exampleQuesiton7.setId(null);
    exampleQuesiton7.setQuestionerId(1);
    exampleQuesiton7.setUser(user);
    exampleQuesiton7.setResponses(setResponse);
    exampleQuesiton7.setHighlightedResponseId(1);
    exampleQuesiton7.setCreationDate(date);
    exampleQuesiton7.addTagToQuestion(javaTag);
    exampleQuesiton7.addImageToImages(image);
    exampleQuesiton7.setImages(imageSet);

    exampleQuesiton8.setAssociatedTags(setTag);
    exampleQuesiton8.setBody("This is the body");
    exampleQuesiton8.setHead("This is the head");
    exampleQuesiton8.setId(1);
    exampleQuesiton8.setQuestionerId(null);
    exampleQuesiton8.setUser(user);
    exampleQuesiton8.setResponses(setResponse);
    exampleQuesiton8.setHighlightedResponseId(1);
    exampleQuesiton8.setCreationDate(date);
    exampleQuesiton8.addTagToQuestion(javaTag);
    exampleQuesiton8.addImageToImages(image);
    exampleQuesiton8.setImages(imageSet);

    exampleQuesiton9.setAssociatedTags(setTag);
    exampleQuesiton9.setBody("This is the body");
    exampleQuesiton9.setHead("This is the head");
    exampleQuesiton9.setId(1);
    exampleQuesiton9.setQuestionerId(1);
    // exampleQuesiton9.setUser(user2);
    exampleQuesiton9.setResponses(setResponse);
    exampleQuesiton9.setHighlightedResponseId(1);
    exampleQuesiton9.setCreationDate(date);
    exampleQuesiton9.addTagToQuestion(javaTag);
    exampleQuesiton9.addImageToImages(image);
    exampleQuesiton9.setImages(imageSet);

    exampleQuesiton10.setAssociatedTags(setTag);
    exampleQuesiton10.setBody("This is the body");
    exampleQuesiton10.setHead("This is the head");
    exampleQuesiton10.setId(1);
    exampleQuesiton10.setQuestionerId(1);
    exampleQuesiton10.setUser(user);
    exampleQuesiton10.setResponses(null);
    exampleQuesiton10.setHighlightedResponseId(1);
    exampleQuesiton10.setCreationDate(date);
    exampleQuesiton10.addTagToQuestion(javaTag);
    exampleQuesiton10.addImageToImages(image);
    exampleQuesiton10.setImages(imageSet);

    exampleQuesiton11.setAssociatedTags(setTag);
    exampleQuesiton11.setBody("This is the body");
    exampleQuesiton11.setHead("This is the head");
    exampleQuesiton11.setId(1);
    exampleQuesiton11.setQuestionerId(1);
    exampleQuesiton11.setUser(user);
    exampleQuesiton11.setResponses(setResponse);
    exampleQuesiton11.setHighlightedResponseId(null);
    exampleQuesiton11.setCreationDate(date);
    exampleQuesiton11.addTagToQuestion(javaTag);
    exampleQuesiton11.addImageToImages(image);
    exampleQuesiton11.setImages(imageSet);

    exampleQuesiton12.setAssociatedTags(setTag);
    exampleQuesiton12.setBody("This is the body");
    exampleQuesiton12.setHead("This is the head");
    exampleQuesiton12.setId(1);
    exampleQuesiton12.setQuestionerId(1);
    exampleQuesiton12.setUser(user);
    exampleQuesiton12.setResponses(setResponse);
    exampleQuesiton12.setHighlightedResponseId(1);
    exampleQuesiton12.setCreationDate(null);
    exampleQuesiton12.addTagToQuestion(javaTag);
    exampleQuesiton12.addImageToImages(image);
    exampleQuesiton12.setImages(imageSet);

    exampleQuesiton13.setAssociatedTags(setTag);
    exampleQuesiton13.setBody("This is the body");
    exampleQuesiton13.setHead("This is the head");
    exampleQuesiton13.setId(1);
    exampleQuesiton13.setQuestionerId(1);
    exampleQuesiton13.setUser(user);
    exampleQuesiton13.setResponses(setResponse);
    exampleQuesiton13.setHighlightedResponseId(1);
    exampleQuesiton13.setCreationDate(date);
    exampleQuesiton13.addTagToQuestion(jsTag);
    // exampleQuesiton13.addImageToImages(null);
    exampleQuesiton13.setImages(imageSet);


    exampleQuesiton14.setAssociatedTags(setTag);
    exampleQuesiton14.setBody("This is the body");
    exampleQuesiton14.setHead("This is the head");
    exampleQuesiton14.setId(1);
    exampleQuesiton14.setQuestionerId(1);
    exampleQuesiton14.setUser(user);
    exampleQuesiton14.setResponses(setResponse);
    exampleQuesiton14.setHighlightedResponseId(1);
    exampleQuesiton14.setCreationDate(date);
    exampleQuesiton14.addTagToQuestion(javaTag);
    exampleQuesiton14.addImageToImages(image);
    exampleQuesiton14.setImages(null);

    assertFalse(
        exampleQuesiton.equals(exampleQuastion2) & exampleQuastion2.equals(exampleQuesiton));
    // assertTrue(exampleQuestion3.equals(exampleQuastion2) &
    // exampleQuastion2.equals(exampleQuestion3));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton4) & exampleQuesiton4.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton5) & exampleQuesiton5.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton6) & exampleQuesiton6.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton7) & exampleQuesiton7.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton8) & exampleQuesiton8.equals(exampleQuesiton));
    // assertFalse(exampleQuesiton.equals(exampleQuesiton9) &
    // exampleQuesiton9.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton10) & exampleQuesiton10.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton11) & exampleQuesiton11.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton12) & exampleQuesiton12.equals(exampleQuesiton));
    // assertFalse(exampleQuesiton.equals(exampleQuesiton13) &
    // exampleQuesiton13.equals(exampleQuesiton));
    assertFalse(
        exampleQuesiton.equals(exampleQuesiton14) & exampleQuesiton14.equals(exampleQuesiton));
    assertFalse(exampleQuesiton.equals(user));

    assertFalse(exampleQuesiton.hashCode() == exampleQuastion2.hashCode());

    assertTrue(
        exampleQuesiton.getAssociatedTags() != null & exampleQuastion2.getAssociatedTags() != null);
    assertTrue(exampleQuesiton.getBody() != null & exampleQuastion2.getBody() == null);
    assertTrue(exampleQuesiton.getHead() == exampleQuesiton4.getHead()
        & exampleQuesiton.getHead() != null);
    assertTrue(exampleQuesiton.getHighlightedResponseId() == 1
        & exampleQuastion2.getHighlightedResponseId() == null);
    assertTrue(exampleQuesiton.getId() == 1 & exampleQuastion2.getId() == null);
    assertTrue(exampleQuesiton.getQuestionerId() == 1 & exampleQuastion2.getQuestionerId() == null);
    assertTrue(exampleQuesiton.getResponses() != null & exampleQuastion2.getResponses() == null);
    assertTrue(exampleQuesiton.getUser() != null & exampleQuastion2.getUser() == null);
    assertTrue(exampleQuesiton.getCreationDate() != exampleQuastion2.getCreationDate()
        & exampleQuastion2.getCreationDate() == null);
    assertTrue(exampleQuesiton.getImages() != null & exampleQuastion2.getImages() != null);
    assertTrue(exampleQuesiton.getClass() == exampleQuastion2.getClass());
  }

  /**
   * This test creating or updating a question qc 157
   */

  @Test
  public void testCreateUpdateQuestion() throws QuestionConflictException {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");

    when((questionServiceMock.createOrUpdate(exampleQuestion))).thenReturn(null);
    assertEquals(null, questionControllerImpl.createOrUpdate(exampleQuestion, 1));
  }

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

  @Test
  public void testGettingUserById() {
    int index = 4;
    User exampleUser = new User();

    when((userServiceMock.findById(index))).thenReturn(exampleUser);

    assertEquals(ResponseEntity.ok(exampleUser), userControllerImpl.findById(index));
  }

  /**
   * testUpdatingUser checks against submitting the same data.
   */
  @Test(expected = UserConflictException.class)
  public void testUpdatingUser() {
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();

    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);
    userServiceMock.create(exampleUser);

    when((userServiceMock.findById(index))).thenReturn(exampleUser);

    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);

    when((userServiceMock.update(exampleUser))).thenThrow(UserConflictException.class);

    // actual update
    System.out.println(exampleUser);
    exampleUser.setUsername("blahblahblah");
    exampleUser.setPassword("djfjgjofoo");
    exampleUser.setExpert(true);

    when((userServiceMock.update(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);

    assertEquals(exampleUser, userControllerImpl.findById(index));
  }

  /**
   * testAddingUser tests if adding user functions.
   */
  @Test
  public void testAddingUser() {
    int index = 1;
    User exampleUser = new User();
    exampleUser.setId(index);
    exampleUser.setUsername("blah");
    exampleUser.setPassword("dsafjawjf");

    when((userServiceMock.create(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);

    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
  }

  /**
   * testCreatingOrUpdatingUser_Create checks the creation functionality of createOrUpdate.
   */
  @Test(expected = UserNotFoundException.class)
  public void testCreatingOrUpdateUser_Create() {

    // updating
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();

    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);

    when((userServiceMock.createOrUpdate(exampleUser))).thenThrow(UserNotFoundException.class);

    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));

  }

  /**
   * testCreatingOrUpdatingUser_Update checks the updating functionality of createOrUpdate.
   * 
   * It checks against a duplicate new user if the user with the same data already exists.
   */
  @Test(expected = UserConflictException.class)
  public void testCreatingOrUpdatingUser_Update() {
    // updating
    int index = 1; // the last object in the database so far.
    User exampleUser = new User();

    exampleUser.setId(index);
    exampleUser.setUsername("retweet");
    exampleUser.setPassword("weguiawej");
    exampleUser.setExpert(false);

    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    assertEquals(exampleUser, userServiceMock.createOrUpdate(exampleUser));

    System.out.println(exampleUser);
    User anotherExampleUser = new User();

    anotherExampleUser.setId(index);
    anotherExampleUser.setUsername("retweet");
    anotherExampleUser.setPassword("weguiawej");
    anotherExampleUser.setExpert(false);

    when((userServiceMock.createOrUpdate(anotherExampleUser)))
        .thenThrow(UserConflictException.class);
    assertEquals(anotherExampleUser, userServiceMock.createOrUpdate(anotherExampleUser));
    System.out.println(anotherExampleUser);

    when((userServiceMock.findById(index))).thenReturn(exampleUser);
    // creating
    exampleUser.setId(index);
    exampleUser.setUsername("retweqwgwe");
    exampleUser.setPassword("weguiawgwgwj");
    exampleUser.setExpert(false);

    when((userServiceMock.createOrUpdate(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);

    assertEquals(exampleUser, userControllerImpl.createUser(exampleUser));
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
  public void testHighlightResponseForDataIntegrityViolationException() {

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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(question), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForImageConflictEeption() {

    Question question = new Question();
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest))
          .thenThrow(ImageConflictException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForQuestionNotFoundException() {

    Question question = new Question();
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest))
          .thenThrow(QuestionNotFoundException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }

  @Test(expected = ResponseStatusException.class)
  public void testAddImageForIOException() {

    Question question = new Question();
    try {
      when(questionServiceMock.addImageToQuestion(1, mockRequest)).thenThrow(IOException.class);
    } catch (QuestionNotFoundException | ImageConflictException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertEquals(ResponseEntity.ok(null), questionControllerImpl.addImage(1, mockRequest));
  }

}
