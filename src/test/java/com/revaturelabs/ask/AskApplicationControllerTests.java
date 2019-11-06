package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserNotFoundException;
import com.revaturelabs.ask.user.UserService;

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
    
    //actual update
    System.out.println(exampleUser);
    exampleUser.setUsername("blahblahblah");
    exampleUser.setPassword("djfjgjofoo");
    exampleUser.setExpert(true);
    
    when((userServiceMock.update(exampleUser))).thenReturn(exampleUser);
    System.out.println(exampleUser);
    
    assertEquals(exampleUser, userControllerImpl.findById(index));
  }


  /**
   * testAddingUser tests if adding user
   * functions.
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

}
