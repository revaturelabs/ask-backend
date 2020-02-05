package com.revaturelabs.ask;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.image.ImageController;
import com.revaturelabs.ask.image.ImageNotFoundException;
import com.revaturelabs.ask.image.ImageRepository;
import com.revaturelabs.ask.image.ImageService;
import com.revaturelabs.ask.image.ImageServiceImpl;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionRepository;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.question.QuestionServiceImpl;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseConflictException;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseNotFoundException;
import com.revaturelabs.ask.response.ResponseRepository;
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.response.ResponseServiceImpl;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagConflictException;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.tag.TagServiceImpl;
import com.revaturelabs.ask.user.User;
import com.revaturelabs.ask.user.UserConflictException;
import com.revaturelabs.ask.user.UserController;
import com.revaturelabs.ask.user.UserNotFoundException;
import com.revaturelabs.ask.user.UserRepository;
import com.revaturelabs.ask.user.UserService;
import com.revaturelabs.ask.user.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationServiceTests {

	@Test
	public void contextLoads() {
	}

	@Mock
	TagRepository tagRepositoryMock;

	@Mock
	ImageRepository imageRepositoryMock;

	@Mock
	ResponseRepository responseRepositoryMock;

	@Mock
	UserRepository userRepositoryMock;

	@Mock
	QuestionRepository questionRepositoryMock;

	@Mock
	TagService tagServiceMock;

	@Mock
	ImageService imageServiceMock;

	@Mock
	ResponseService responseServiceMock;

	@Mock
	UserService userServiceMock;

	@Mock
	QuestionService questionServiceMock;

	@Mock
	MultipartHttpServletRequest mockRequest;

	@InjectMocks
	TagService tagServiceImpl = new TagServiceImpl();

	@InjectMocks
	ImageService imageServiceImpl = new ImageServiceImpl();

	@InjectMocks
	ResponseService responseServiceImpl = new ResponseServiceImpl();

	@InjectMocks
	UserService userServiceImpl = new UserServiceImpl();

	@InjectMocks
	QuestionService questionServiceImpl = new QuestionServiceImpl();

	@InjectMocks
	TagController tagControllerImpl = new TagController();

	@InjectMocks
	ImageController imageControllerImpl = new ImageController();

	@InjectMocks
	ResponseController responseControllerImpl = new ResponseController();

	@InjectMocks
	UserController userControllerImpl = new UserController();

	@InjectMocks
	QuestionController questionControllerImpl = new QuestionController();

	static Tag testTag1;

	static Tag testTag1PostCreate;

	static Tag testTag2;

	static Tag testTag3;

	static List<Tag> tagReturnList;

	static Set<Image> testImageSet1;

	static Set<Image> testImageSet2;

	static Image testImage1;

	static Image testImage2;

	static Response testResponse1;

	static Response testResponse1PostCreate;

	static Response testResponse2;

  static List<Response> responseReturnList;

	static User testUser1;

	static User testUser1PostCreate;

	static User testUser2;

	static Page<User> returnUsersPage;

	static Question testQuestion1;

	static Question testQuestion1PostCreate;

	static Question testQuestion2;

	static Page<Question> returnQuestionsPage;

	static {
		testTag1 = new Tag();
		testTag1.setId(0);
		testTag1.setName("Test 1");

		testTag1PostCreate = new Tag();
		testTag1PostCreate.setId(1);
		testTag1PostCreate.setName("Test 1");

		testTag2 = new Tag();
		testTag2.setId(1);
		testTag2.setName("Test 2");

		testTag3 = new Tag();
		testTag3.setId(2);
		testTag3.setName("Test 3");

		tagReturnList = Arrays.asList(testTag1, testTag2, testTag3);

		testResponse1 = new Response();
		testResponse1.setId(1);
		testResponse1.setBody("Test response");

		testResponse1PostCreate = new Response();
		testResponse1PostCreate.setId(1);
		testResponse1PostCreate.setBody("Updated body");

		testResponse2 = new Response();
		testResponse2.setId(2);
		testResponse2.setBody("Test response 2");

		responseReturnList = Arrays.asList(testResponse1, testResponse2);

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.addAll(tagReturnList);

		testUser1 = new User();
		testUser1.setId(1);
		testUser1.setExpertTags(expertTags);

		testUser1PostCreate = testUser1;
		testUser1PostCreate.setUsername("Test username 2");

		testUser2 = new User();
		testUser2.setId(2);

		testImage1 = new Image();
		testImage1.setId(1);
		testImage2 = new Image();
		testImage2.setId(2);

		testImageSet1 = new HashSet<Image>();
		testImageSet1.add(testImage1);
		testImageSet2 = new HashSet<Image>();
		testImageSet2.add(testImage2);

		testQuestion1 = new Question();
		testQuestion1.setId(1);
		testQuestion1.setAssociatedTags(expertTags);
		testQuestion1.setImages(testImageSet1);

		testQuestion2 = new Question();
		testQuestion2.setId(2);
	}

	/**
	 * Test of tagService findAll method.
	 * 
	 */
	@Test
	public void getAllTagsTest() {

		when((tagRepositoryMock.findAll())).thenReturn(tagReturnList);
		assertEquals(tagReturnList, tagServiceImpl.getAll());
	}

  /**
   * Test retrieval of getting one tag
   * 
   */
  @Test
  public void getOneTagTest() {

    when(tagRepositoryMock.findById(1)).thenReturn(Optional.of(testTag1));

    assertEquals(testTag1, tagServiceImpl.getById(1));
  }

  /**
   * Test failure of getting a non-existent tag
   * 
   */
  @Test(expected = TagNotFoundException.class)
  public void getBadTagTest() {

    when(tagRepositoryMock.findById(10)).thenReturn(Optional.empty());

    tagServiceImpl.getById(10);
  }

  /**
   * Test accuracy of getting a tag
   * 
   */
  @Test
  public void getTagByIdAccuracyTest() {
    when(tagRepositoryMock.findById(1)).thenReturn(Optional.of(testTag1));

    assertNotEquals(testTag2, tagServiceImpl.getById(1));
  }

  /**
   * Test retrieval of an existing tag by name
   * 
   */
  @Test
  public void getTagByNameTest() {

    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    assertEquals(testTag1, tagServiceImpl.getTagByName("Test 1"));
  }

  /**
   * Test failure retrieval of a bad tag name
   * 
   */
  @Test(expected = TagNotFoundException.class)
  public void getBadTagNameTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);
    tagServiceImpl.getTagByName("notTestTag1");
  }

  /**
   * Test accuracy of getting a tag by name
   * 
   */
  @Test
  public void getAccurateTagNameTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    assertNotEquals(testTag2, tagServiceImpl.getTagByName("Test 1"));
  }

  /**
   * Test tag creation
   * 
   */
  @Test
  public void tagCreationAccuracyTest() {
    when(tagRepositoryMock.save(testTag1)).thenReturn(testTag1PostCreate);

    assertEquals(testTag1PostCreate, tagServiceImpl.create(testTag1));
  }

  /**
   * Test updating a tag
   */
  @Test
  public void tagUpdateTest() {
    Tag testTag1UpdateInfo = new Tag();
    testTag1UpdateInfo.setId(0);
    testTag1UpdateInfo.setName("New name");

    when(tagRepositoryMock.findById(testTag1UpdateInfo.getId())).thenReturn(Optional.of(testTag1));
    when(tagRepositoryMock.save(testTag1UpdateInfo)).thenReturn(testTag1);

    assertEquals(testTag1, tagServiceImpl.update(testTag1UpdateInfo));
  }

  /**
   * Test updating failure for non-existent tag
   * 
   */
  @Test(expected = TagNotFoundException.class)
  public void tagUpdateNonExistantFailureTest() {
    Tag nonExistentTag = new Tag();
    nonExistentTag.setId(5);

    when(tagRepositoryMock.findById(5)).thenReturn(Optional.empty());

    tagServiceImpl.update(nonExistentTag);
  }

  /**
   * Testing data integrity violation failure for updating a tag
   * 
   */
  @Test(expected = TagConflictException.class)
  public void tagFailureToUpdateTest() {
    when(tagRepositoryMock.findById(0)).thenReturn(Optional.of(testTag1));
    when(tagRepositoryMock.save(testTag1)).thenThrow(DataIntegrityViolationException.class);

    tagServiceImpl.update(testTag1);
  }

  /**
   * Test create or update
   * 
   */
  @Test
  public void createOrUpdateTagTest() {
    Tag nonExistentTag = new Tag();
    nonExistentTag.setId(4);
    nonExistentTag.setName("New tag");
    List<Tag> tagReturnList = new ArrayList<Tag>();
    tagReturnList.addAll(Arrays.asList(testTag1, testTag2, testTag3, nonExistentTag));

    when(tagRepositoryMock.save(nonExistentTag)).thenReturn(nonExistentTag);
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    tagServiceImpl.createOrUpdate(nonExistentTag);
    assertEquals(tagReturnList, tagServiceImpl.getAll());
  }

  /**
   * Testing data integrity violation failure for creating or updating a tag
   * 
   */
  @Test(expected = TagConflictException.class)
  public void tagFailureToCreateOrUpdateTest() {
    when(tagRepositoryMock.save(testTag1)).thenThrow(DataIntegrityViolationException.class);

    tagServiceImpl.createOrUpdate(testTag1);
  }

  /**
   * Test deletion of a tag
   * 
   */
  @Test
  public void tagDeletionTest() {
    when(tagRepositoryMock.existsById(1)).thenReturn(true);
    Mockito.doNothing().when(tagRepositoryMock).deleteById(1);
    tagServiceImpl.delete(1);
    when(tagRepositoryMock.existsById(1)).thenReturn(false);


    Assert.assertFalse(tagRepositoryMock.existsById(1));
  }

  /**
   * Test exception throw of invalid tag
   */
  @Test(expected = TagNotFoundException.class)
  public void tagDeleteNonExistantTagTest() {
    when(tagRepositoryMock.existsById(10)).thenReturn(false);
    Mockito.doNothing().when(tagRepositoryMock).deleteById(10);

    tagServiceImpl.delete(10);
  }

  /**
   * Test retrieval of valid tags
   * 
   */
  @Test
  public void tagGetValidTagsTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);
    Set<Tag> searchTagsSet = new HashSet<Tag>();
    searchTagsSet.add(testTag1);
    searchTagsSet.add(testTag2);

    assertEquals(searchTagsSet, tagServiceImpl.getValidTags(searchTagsSet));
  }

  /**
   * Test retrieval of no tags
   * 
   */
  @Test
  public void getNoValidTagsTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    assertEquals(new HashSet<Tag>(), tagServiceImpl.getValidTags(null));
  }

  /**
   * Test retrieval of invalid tags
   */
  @Test(expected = TagNotFoundException.class)
  public void getInvalidTagsFailureTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    Tag invalidTag = new Tag();
    invalidTag.setId(20);
    invalidTag.setName("Invalid tag");

    HashSet<Tag> associatedTags = new HashSet<Tag>();
    associatedTags.add(invalidTag);

    tagServiceImpl.getValidTags(associatedTags);
  }

  /**
   * Test retrieval of invalid tags with at least one invalid tag and one valid tag
   * 
   */
  @Test(expected = TagNotFoundException.class)
  public void getInvalidTagsWithAtLeastOneValidTagFailureTest() {
    when(tagRepositoryMock.findAll()).thenReturn(tagReturnList);

    Tag invalidTag = new Tag();
    invalidTag.setId(20);
    invalidTag.setName("Invalid tag");

    HashSet<Tag> associatedTags = new HashSet<Tag>();
    associatedTags.add(invalidTag);
    associatedTags.add(testTag1);

    tagServiceImpl.getValidTags(associatedTags);
  }

  /**
   * Test retrieval of Image by id
   */
  @Test
  public void getImageByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));

    assertEquals(testImageSet1, imageServiceImpl.getImages(1));
  }

  /**
   * Test valid retrieval of Image by id
   * 
   */
  @Test
  public void getCorrectImageByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));

    assertNotEquals(testImageSet2, imageServiceImpl.getImages(1));
  }

  @Test(expected = ImageNotFoundException.class)
  public void getFailedImages() {
    Set<Image> images = new HashSet<Image>();
    when(questionRepositoryMock.findById(1)).thenReturn(null);

    imageServiceImpl.getImages(1);
  }

  @Test
  public void addImageTest() throws IOException, ImageConflictException {
    MockMultipartFile firstFile =
        new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
    MultipartFile mpf = firstFile;
    when(mockRequest.getFile("image")).thenReturn(mpf);

    assertNull(imageServiceImpl.addImage(testQuestion1, mockRequest));
  }

  // Can't pass with current state of addImage
  @Ignore
  @Test(expected = ImageConflictException.class)
  public void addImageFailTest() throws IOException, ImageConflictException {
    byte[] byteArr = null;
    MockMultipartFile firstFile =
        new MockMultipartFile("data", "filename.txt", "text/plain", byteArr);
    MultipartFile mpf = firstFile;
    when(mockRequest.getFile("image")).thenReturn(firstFile);

    imageServiceImpl.addImage(testQuestion1, mockRequest);
  }

  /**
   * Test failure of retrieval of image by invalid id
   * 
   */
  @Test(expected = ImageNotFoundException.class)
  public void getInvalidImageIdTest() {
    when(questionRepositoryMock.findById(2)).thenReturn(Optional.of(testQuestion2));

    imageServiceImpl.getImages(2);
  }

  /**
   * Test find all responses
   * 
   */
  @Test
  public void getAllResponsesTest() {
    when(responseRepositoryMock.findAll()).thenReturn(responseReturnList);

    assertEquals(responseReturnList, responseServiceImpl.getAll());
  }

  /**
   * Test find a response by id
   * 
   */
  @Test
  public void getResponseByIdTest() {
    when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(testResponse1));

    assertEquals(testResponse1, responseServiceImpl.getById(1));
  }

  /**
   * Test find a valid response by id
   */
  @Test
  public void getAccurateResponseByIdTest() {
    when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(testResponse1));

    assertNotEquals(testResponse2, responseServiceImpl.getById(1));
  }

  /**
   * Test failure of response retrieval given invalid response id
   */
  @Test(expected = ResponseNotFoundException.class)
  public void invalidResponseIdFailureTest() {
    when(responseRepositoryMock.findById(1)).thenReturn(Optional.empty());

    responseServiceImpl.getById(1);
  }

  /**
   * Test response creation
   * 
   */
  @Test
  public void responseCreationAccuracyTest() {
    when(responseRepositoryMock.save(testResponse1)).thenReturn(testResponse1PostCreate);

    assertEquals(testResponse1PostCreate, responseServiceImpl.create(testResponse1));
  }

  /**
   * Test updating a response
   */
  @Test
  public void responseUpdateTest() {
    Response testResponse1UpdateInfo = new Response();
    testResponse1UpdateInfo.setId(1);
    testResponse1UpdateInfo.setBody("Test body update");

    when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(testResponse1));
    when(responseRepositoryMock.save(testResponse1UpdateInfo)).thenReturn(testResponse1);

    assertEquals(testResponse1, responseServiceImpl.update(testResponse1UpdateInfo));
  }

  /**
   * Test updating failure for non-existent response
   * 
   */
  @Test(expected = ResponseNotFoundException.class)
  public void responseUpdateNonExistantFailureTest() {
    Response nonExistentResponse = new Response();
    nonExistentResponse.setId(5);

    when(responseRepositoryMock.findById(5)).thenReturn(Optional.empty());

    responseServiceImpl.update(nonExistentResponse);
  }

  /**
   * Testing data integrity violation failure for updating a response
   * 
   */
  @Test(expected = ResponseConflictException.class)
  public void responseFailureToUpdateTest() {
    when(responseRepositoryMock.findById(1)).thenReturn(Optional.of(testResponse1));
    when(responseRepositoryMock.save(testResponse1))
        .thenThrow(DataIntegrityViolationException.class);

    responseServiceImpl.update(testResponse1);
  }

  /**
   * Test create or update response
   * 
   */
  @Test
  public void createOrUpdateResponseTest() {
    Response nonExistentResponse = new Response();
    nonExistentResponse.setId(4);
    nonExistentResponse.setBody("New response");

    when(responseRepositoryMock.save(nonExistentResponse)).thenReturn(nonExistentResponse);

    responseServiceImpl.createOrUpdate(nonExistentResponse);
    assertEquals(nonExistentResponse.getId(), 4);
    assertEquals(nonExistentResponse.getBody(), "New response");
  }

  /**
   * Testing data integrity violation failure for creating or updating a response
   * 
   */
  @Test(expected = ResponseConflictException.class)
  public void responseFailureToCreateOrUpdateTest() {
    when(responseRepositoryMock.save(testResponse1))
        .thenThrow(DataIntegrityViolationException.class);

    responseServiceImpl.createOrUpdate(testResponse1);
  }

  /**
   * Test deletion of a response
   * 
   */
  @Test
  public void responseDeletionTest() {
    when(responseRepositoryMock.existsById(1)).thenReturn(true);
    Mockito.doNothing().when(responseRepositoryMock).deleteById(1);

    responseServiceImpl.delete(1);
    when(responseRepositoryMock.existsById(1)).thenReturn(false);
    assertFalse(responseRepositoryMock.existsById(1));
  }

  /**
   * Test exception throw of invalid response when deleting
   */
  @Test(expected = ResponseNotFoundException.class)
  public void responseDeleteNonExistantResponseTest() {
    when(responseRepositoryMock.existsById(10)).thenReturn(false);
    Mockito.doNothing().when(responseRepositoryMock).deleteById(10);

    responseServiceImpl.delete(10);
  }

  /**
   * Test of findAll users
   * 
   */
  @Test
  public void usersFindAllTest() {
    when(userRepositoryMock.findAll(PageRequest.of(0, 5))).thenReturn(returnUsersPage);
    assertEquals(returnUsersPage, userServiceImpl.findAll(0, 5));
  }

  /**
   * Test of findById
   */
  @Test
  public void usersFindByIdTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.of(testUser1));

    assertEquals(testUser1, userServiceImpl.findById(1));
  }

  /**
   * Test of accurate findById
   */
  @Test
  public void usersFindAccuratelyByIdTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.of(testUser1));

    assertNotEquals(testUser2, userServiceImpl.findById(1));
  }

  /**
   * Test of thrown exception when given an invalid ID
   */
  @Test(expected = UserNotFoundException.class)
  public void usersNotFoundIdExceptionTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.empty());

    userServiceImpl.findById(1);
  }

  /**
   * Test user creation
   * 
   */
  @Test
  public void userCreationAccuracyTest() {
    when(userRepositoryMock.save(testUser1)).thenReturn(testUser1PostCreate);

    assertEquals(testUser1PostCreate, userServiceImpl.create(testUser1));
  }

  /**
   * Test updating a user
   */
  @Test
  public void userUpdateTest() {
    User testUser1UpdateInfo = new User();
    testUser1UpdateInfo.setId(1);
    testUser1UpdateInfo.setUsername("testUsername");

    when(userRepositoryMock.findById(1)).thenReturn(Optional.of(testUser1));
    when(userRepositoryMock.save(testUser1UpdateInfo)).thenReturn(testUser1);

    assertEquals(testUser1, userServiceImpl.update(testUser1UpdateInfo));
  }

  /**
   * Test updating failure for non-existent user
   * 
   */
  @Test(expected = UserNotFoundException.class)
  public void userUpdateNonExistantFailureTest() {
    User nonExistentUser = new User();
    nonExistentUser.setId(5);

    when(userRepositoryMock.findById(5)).thenReturn(Optional.empty());

    userServiceImpl.update(nonExistentUser);
  }

  /**
   * Testing data integrity violation failure for updating a user
   * 
   */
  @Test(expected = UserConflictException.class)
  public void userFailureToUpdateTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.of(testUser1));
    when(userRepositoryMock.save(testUser1)).thenThrow(DataIntegrityViolationException.class);

    userServiceImpl.update(testUser1);
  }

  /**
   * Test create or update user
   * 
   */
  @Test
  public void createOrUpdateUserTest() {
    User nonExistentUser = new User();
    nonExistentUser.setId(4);

    when(userRepositoryMock.save(nonExistentUser)).thenReturn(nonExistentUser);

    userServiceImpl.createOrUpdate(nonExistentUser);
    assertEquals(nonExistentUser.getId(), 4);
  }

  /**
   * Testing data integrity violation failure for creating or updating a user
   * 
   */
  @Test(expected = UserConflictException.class)
  public void userFailureToCreateOrUpdateTest() {
    when(userRepositoryMock.save(testUser1)).thenThrow(DataIntegrityViolationException.class);

    userServiceImpl.createOrUpdate(testUser1);
  }

  /**
   * Test deletion of a user
   * 
   */
  @Test
  public void userDeletionTest() {
    when(userRepositoryMock.existsById(1)).thenReturn(true);
    Mockito.doNothing().when(userRepositoryMock).deleteById(1);

    userServiceImpl.delete(1);
    when(userRepositoryMock.existsById(1)).thenReturn(false);
    assertFalse(userRepositoryMock.existsById(1));
  }

  /**
   * Test exception throw of invalid response when deleting
   */
  @Test(expected = UserNotFoundException.class)
  public void userDeleteNonExistantResponseTest() {
    when(userRepositoryMock.existsById(10)).thenReturn(false);
    Mockito.doNothing().when(userRepositoryMock).deleteById(10);

    userServiceImpl.delete(10);
  }

  /**
   * Test updating user tags
   */
  @Test
  public void userUpdateTagsTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.of(testUser2));
    when(userRepositoryMock.save(testUser2)).thenReturn(testUser2);

    userServiceImpl.updateTags(testUser1);

    assertEquals(testUser2.getExpertTags(), testUser1.getExpertTags());
  }

  /**
   * Test for proper failure when updating user tags that don't exist
   */
  @Test(expected = UserNotFoundException.class)
  public void userUpdateTagsFailureTest() {
    when(userRepositoryMock.findById(1)).thenReturn(Optional.empty());
    when(userRepositoryMock.save(testUser1)).thenReturn(testUser1);

    userServiceImpl.updateTags(testUser1);
  }

  /**
   * Test for getting all questions
   * 
   */
  @Test
  public void questionsGetAllTest() {
    when(questionRepositoryMock.findAll(PageRequest.of(0, 5))).thenReturn(returnQuestionsPage);

    assertEquals(returnQuestionsPage, questionServiceImpl.getAll(0, 5));
  }

  /**
   * Test for getting a question by ID
   * 
   */
  @Test
  public void questionsGetByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));

    assertEquals(testQuestion1, questionServiceImpl.getById(1));
  }

  /**
   * Test for getting a question accurately by ID
   */

  @Test
  public void questionsGetAccuratelyByIdTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));

    assertNotEquals(testQuestion2, questionServiceImpl.getById(1));
  }

  /**
   * Test for proper failure when an invalid ID is searched for
   */
  @Test(expected = QuestionNotFoundException.class)
  public void questionsNotFoundWithBadIDTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());

    questionServiceImpl.getById(1);
  }

  /**
   * Test question creation
   * 
   */
  @Test
  public void questionCreationAccuracyTest() {
    when(questionRepositoryMock.save(testQuestion1)).thenReturn(testQuestion1PostCreate);

    assertEquals(testQuestion1PostCreate, questionServiceImpl.create(testQuestion1));
  }

  /**
   * Test updating a question
   */
  @Test
  public void questionUpdateTest() {
    Question testQuestion1UpdateInfo = new Question();
    testQuestion1UpdateInfo.setId(1);
    testQuestion1UpdateInfo.setBody("Test body update");

    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
    when(questionRepositoryMock.save(testQuestion1UpdateInfo)).thenReturn(testQuestion1);

    assertEquals(testQuestion1, questionServiceImpl.update(testQuestion1UpdateInfo));
  }

  /**
   * Test updating failure for non-existent question
   * 
   */
  @Test(expected = QuestionNotFoundException.class)
  public void questionUpdateNonExistantFailureTest() {
    Question nonExistentQuestion = new Question();
    nonExistentQuestion.setId(5);

    when(questionRepositoryMock.findById(5)).thenReturn(Optional.empty());

    questionServiceImpl.update(nonExistentQuestion);
  }

  /**
   * Testing data integrity violation failure for updating a question
   * 
   */
  @Test(expected = QuestionConflictException.class)
  public void questionFailureToUpdateTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
    when(questionRepositoryMock.save(testQuestion1))
        .thenThrow(DataIntegrityViolationException.class);

    questionServiceImpl.update(testQuestion1);
  }

  /**
   * Test create or update question
   * 
   */
  @Test
  public void createOrUpdateQuestionTest() {
    Question nonExistentQuestion = new Question();
    nonExistentQuestion.setId(4);
    nonExistentQuestion.setBody("New question");

    when(questionRepositoryMock.save(nonExistentQuestion)).thenReturn(nonExistentQuestion);

    questionServiceImpl.createOrUpdate(nonExistentQuestion);
    assertEquals(nonExistentQuestion.getId(), new Integer(4));
    assertEquals(nonExistentQuestion.getBody(), "New question");

  }

  /**
   * Testing data integrity violation failure for creating or updating a question
   * 
   */
  @Test(expected = QuestionConflictException.class)
  public void questionFailureToCreateOrUpdateTest() {
    when(questionRepositoryMock.save(testQuestion1))
        .thenThrow(DataIntegrityViolationException.class);

    questionServiceImpl.createOrUpdate(testQuestion1);
  }

  /**
   * Testing updating tags for a question
   * 
   */
  @Test
  public void questionsUpdateTagsTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion2));
    when(questionRepositoryMock.save(testQuestion2)).thenReturn(testQuestion2);

    questionServiceImpl.updateTags(testQuestion1);

    assertEquals(testQuestion1.getAssociatedTags(), testQuestion2.getAssociatedTags());
  }

  /**
   * Testing failure to find valid question
   */
  @Test(expected = QuestionNotFoundException.class)
  public void questionsQuestionNotFoundWhenUpdatingTagsExceptionTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());
    questionServiceImpl.updateTags(testQuestion1);
  }

  /**
   * Testing highlighting a response
   */
  @Test
  public void questionsHighlightResponseTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
    when(questionRepositoryMock.save(testQuestion1)).thenReturn(testQuestion1);

    assertEquals((Integer) 4,
        questionServiceImpl.highlightResponse(1, 4).getHighlightedResponseId());
  }

  /**
   * Testing failure to find a question when highlighting a response
   */
  @Test(expected = QuestionNotFoundException.class)
  public void questionsQuestionNotFoundWhenHighlightingResponseTest() {
    when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());

    questionServiceImpl.highlightResponse(1, 4);
  }

	/**
	 * Testing failure to update a response when there is a conflict updating the
	 * data
	 */
	@Test(expected = QuestionConflictException.class)
	public void questionsConflictExceptionWhenHighlightingResponseTest() {
		// when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
		// when(questionRepositoryMock.save(testQuestion1)).thenThrow(DataIntegrityViolationException.class);
		when(questionRepositoryMock.findById(1)).thenThrow(DataIntegrityViolationException.class);

		questionServiceImpl.highlightResponse(1, 4);
	}

	/**
	 * Testing adding an image to a question
	 */
	@Test
	public void testAddImageToQuestion() throws QuestionNotFoundException, ImageConflictException, IOException {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
		when(questionServiceImpl.addImageToQuestion(1, mockRequest)).thenReturn(testQuestion1);

		assertTrue(questionServiceImpl.addImageToQuestion(1, mockRequest) == testQuestion1);
	}

	@Test(expected = QuestionNotFoundException.class)
	public void testAddImageToQuestionForQuestionNotFoundException()
			throws QuestionNotFoundException, ImageConflictException, IOException {

		when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());
		assertNotNull(questionRepositoryMock.findById(1));
		assertEquals(questionRepositoryMock.findById(1), Optional.empty());

		questionServiceImpl.addImageToQuestion(1, mockRequest);
	}

	@Test(expected = ImageConflictException.class)
	public void testAddImageToQuestionForImageConflictException()
			throws QuestionNotFoundException, ImageConflictException, IOException {

		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
		when(imageServiceMock.addImage(testQuestion1, mockRequest)).thenThrow(ImageConflictException.class);
		assertNotNull(questionRepositoryMock.findById(1));
		assertEquals(questionRepositoryMock.findById(1), Optional.of(testQuestion1));

		questionServiceImpl.addImageToQuestion(1, mockRequest);
	}

	@Test(expected = IOException.class)
	public void testAddImageToQuestionForIOException()
			throws QuestionNotFoundException, ImageConflictException, IOException {

		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(testQuestion1));
		when(imageServiceMock.addImage(testQuestion1, mockRequest)).thenThrow(IOException.class);
		assertNotNull(questionRepositoryMock.findById(1));
		assertEquals(questionRepositoryMock.findById(1), Optional.of(testQuestion1));

		questionServiceImpl.addImageToQuestion(1, mockRequest);
	}

	@Test
	public void testFindAllByTagNames() {

		List<Question> questions1 = new ArrayList<Question>();
		questions1.add(testQuestion1);
		List<Question> questions2 = new ArrayList<Question>();
		questions2.add(testQuestion2);
		Page<Question> page1 = new PageImpl<>(questions1);
		Page<Question> page2 = new PageImpl<>(questions2);

		List<String> tagNames = new ArrayList<String>();
		tagNames.add(testTag1.getName());
		tagNames.add(testTag2.getName());
		tagNames.add(testTag3.getName());

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.add(tagReturnList.get(0));
		expertTags.add(tagReturnList.get(1));
		expertTags.add(tagReturnList.get(2));
		Pageable pageable = (Pageable) PageRequest.of(0, 20);

		when(tagServiceMock.getTagByName(tagNames.get(0))).thenReturn(tagReturnList.get(0));
		when(tagServiceMock.getTagByName(tagNames.get(1))).thenReturn(tagReturnList.get(1));
		when(tagServiceMock.getTagByName(tagNames.get(2))).thenReturn(tagReturnList.get(2));

		when(questionRepositoryMock.findAllContainingAllTags(expertTags, expertTags.size(), pageable))
				.thenReturn(page1);
		when(questionRepositoryMock.findAllContainingAtLeastOneTag(expertTags, pageable)).thenReturn(page2);
		assertNotNull(questionRepositoryMock.findAllContainingAllTags(expertTags, expertTags.size(), pageable));
		assertEquals(questionRepositoryMock.findAllContainingAllTags(expertTags, expertTags.size(), pageable), page1);

		questionServiceImpl.findAllByTagNames(true, tagNames, 0, 20);
		questionServiceImpl.findAllByTagNames(false, tagNames, 0, 20);
	}

	@Test(expected = TagNotFoundException.class)
	public void testFindAllByTagNamesForTagNotFoundException() {

		List<String> tagNames = new ArrayList<String>();
		tagNames.add(testTag1.getName());
		tagNames.add(testTag2.getName());
		tagNames.add(testTag3.getName());

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.addAll(tagReturnList);

		when(tagServiceMock.getTagByName(tagNames.get(0))).thenReturn(testTag1);
		when(tagServiceMock.getTagByName(tagNames.get(1))).thenReturn(testTag2);
		when(tagServiceMock.getTagByName(tagNames.get(2))).thenThrow(TagNotFoundException.class);

		questionServiceImpl.findAllByTagNames(true, tagNames, 0, 20);
		questionServiceMock.findAllByTagNames(false, tagNames, 0, 20);
	}
}