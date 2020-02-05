package com.revaturelabs.ask;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.springframework.data.domain.Page;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.user.User;

public class AskApplicationModelTests {

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

	static Question testQuestion2;

	static Question testQuestion3;

	static Question testQuestion4;

	static Question testQuestion5;

	static Question testQuestion6;

	static Question testQuestion7;

	static Question testQuestion8;

	static Question testQuestion9;

	static Question testQuestion10;

	static Question testQuestion11;

	static Question testQuestion12;

	static Question testQuestion13;

	static Question testQuestion14;

	static Question emptyQuestion;

	static Question nullQuestion;

	static Question nullQuestion2;

	static Page<Question> returnQuestionsPage;

	static Date testDate;

	static {
		testTag1 = new Tag();
		testTag1.setId(0);
		testTag1.setName("Test 1");

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

		testResponse2 = new Response();
		testResponse2.setId(2);
		testResponse2.setBody("Test response 2");

		responseReturnList = Arrays.asList(testResponse1, testResponse2);

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.addAll(tagReturnList);

		HashSet<Response> expertResponses = new HashSet<Response>();
		expertResponses.addAll(responseReturnList);

		testUser1 = new User();
		testUser1.setId(1);
		testUser1.setExpertTags(expertTags);

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

		testDate = new Date();

		nullQuestion = new Question();

		nullQuestion2 = new Question();

		testQuestion1 = new Question();
		testQuestion1.setId(1);
		testQuestion1.setBody("This is the body");
		testQuestion1.setHead("This is the head");
		testQuestion1.setQuestionerId(1);
		testQuestion1.setResponses(expertResponses);
		testQuestion1.setHighlightedResponseId(1);
		testQuestion1.setAssociatedTags(expertTags);
		testQuestion1.setImages(testImageSet1);
		testQuestion1.setCreationDate(testDate);

		testQuestion2 = new Question();
		testQuestion2.setId(1);
		testQuestion2.setBody("This is the body");
		testQuestion2.setHead("This is the head");
		testQuestion2.setQuestionerId(1);
		testQuestion2.setResponses(expertResponses);
		testQuestion2.setHighlightedResponseId(1);
		testQuestion2.setAssociatedTags(expertTags);
		testQuestion2.setImages(testImageSet1);
		testQuestion2.setCreationDate(testDate);

		testQuestion3 = new Question();
		testQuestion3.setId(null);
		testQuestion3.setBody("This is the body");
		testQuestion3.setHead("This is the head");
		testQuestion3.setQuestionerId(1);
		testQuestion3.setResponses(expertResponses);
		testQuestion3.setHighlightedResponseId(1);
		testQuestion3.setAssociatedTags(expertTags);
		testQuestion3.setImages(testImageSet1);
		testQuestion3.setCreationDate(testDate);

		testQuestion4 = new Question();
		testQuestion4.setId(1);
		testQuestion4.setBody(null);
		testQuestion4.setHead("This is the head");
		testQuestion4.setQuestionerId(1);
		testQuestion4.setResponses(expertResponses);
		testQuestion4.setHighlightedResponseId(1);
		testQuestion4.setAssociatedTags(expertTags);
		testQuestion4.setImages(testImageSet1);
		testQuestion4.setCreationDate(testDate);

		testQuestion5 = new Question();
		testQuestion5.setId(1);
		testQuestion5.setBody("This is the body");
		testQuestion5.setHead(null);
		testQuestion5.setQuestionerId(1);
		testQuestion5.setResponses(expertResponses);
		testQuestion5.setHighlightedResponseId(1);
		testQuestion5.setAssociatedTags(expertTags);
		testQuestion5.setImages(testImageSet1);
		testQuestion5.setCreationDate(testDate);

		testQuestion6 = new Question();
		testQuestion6.setId(1);
		testQuestion6.setBody("This is the body");
		testQuestion6.setHead("This is the head");
		testQuestion6.setQuestionerId(null);
		testQuestion6.setResponses(expertResponses);
		testQuestion6.setHighlightedResponseId(1);
		testQuestion6.setAssociatedTags(expertTags);
		testQuestion6.setImages(testImageSet1);
		testQuestion6.setCreationDate(testDate);

		testQuestion7 = new Question();
		testQuestion7.setId(1);
		testQuestion7.setBody("This is the body");
		testQuestion7.setHead("This is the head");
		testQuestion7.setQuestionerId(1);
		testQuestion7.setResponses(null);
		testQuestion7.setHighlightedResponseId(1);
		testQuestion7.setAssociatedTags(expertTags);
		testQuestion7.setImages(testImageSet1);
		testQuestion7.setCreationDate(testDate);

		testQuestion8 = new Question();
		testQuestion8.setId(1);
		testQuestion8.setBody("This is the body");
		testQuestion8.setHead("This is the head");
		testQuestion8.setQuestionerId(1);
		testQuestion8.setResponses(expertResponses);
		testQuestion8.setHighlightedResponseId(null);
		testQuestion8.setAssociatedTags(expertTags);
		testQuestion8.setImages(testImageSet1);
		testQuestion8.setCreationDate(testDate);

		testQuestion9 = new Question();
		testQuestion9.setId(1);
		testQuestion9.setBody("This is the body");
		testQuestion9.setHead("This is the head");
		testQuestion9.setQuestionerId(1);
		testQuestion9.setResponses(expertResponses);
		testQuestion9.setHighlightedResponseId(1);
		testQuestion9.setAssociatedTags(null);
		testQuestion9.setImages(testImageSet1);
		testQuestion9.setCreationDate(testDate);

		testQuestion10 = new Question();
		testQuestion10.setId(1);
		testQuestion10.setBody("This is the body");
		testQuestion10.setHead("This is the head");
		testQuestion10.setQuestionerId(1);
		testQuestion10.setResponses(expertResponses);
		testQuestion10.setHighlightedResponseId(1);
		testQuestion10.setAssociatedTags(expertTags);
		testQuestion10.setImages(null);
		testQuestion10.setCreationDate(testDate);

		testQuestion11 = new Question();
		testQuestion11.setId(1);
		testQuestion11.setBody("This is the body");
		testQuestion11.setHead("This is the head");
		testQuestion11.setQuestionerId(1);
		testQuestion11.setResponses(expertResponses);
		testQuestion11.setHighlightedResponseId(1);
		testQuestion11.setAssociatedTags(expertTags);
		testQuestion11.setImages(testImageSet1);
		testQuestion11.setCreationDate(null);

	}

	@Test
	public void testModelEqualsAndHashCode() {
		assertTrue(testQuestion1.hashCode() == testQuestion2.hashCode());
		assertTrue(testQuestion1.hashCode() != nullQuestion.hashCode());
		assertTrue(emptyQuestion == null);

		assertNotNull(testQuestion1.toString());

		assertTrue(testQuestion1.equals(testQuestion2) & testQuestion2.equals(testQuestion1));
		assertTrue(nullQuestion.equals(nullQuestion2) & nullQuestion2.equals(nullQuestion));
		assertFalse(testQuestion1.equals(testQuestion3) & testQuestion3.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion4) & testQuestion4.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion5) & testQuestion5.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion6) & testQuestion6.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion7) & testQuestion7.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion8) & testQuestion8.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion9) & testQuestion9.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion10) & testQuestion10.equals(testQuestion1));
		assertFalse(testQuestion1.equals(testQuestion11) & testQuestion11.equals(testQuestion1));
		assertFalse(testQuestion1.equals(nullQuestion) & nullQuestion.equals(testQuestion1));

		assertTrue(testQuestion1.getAssociatedTags() != null & testQuestion2.getAssociatedTags() != null);
		assertTrue(testQuestion1.getBody() != null & testQuestion4.getBody() == null);
		assertTrue(testQuestion1.getHead() == testQuestion4.getHead() & testQuestion1.getHead() != null);
		assertTrue(testQuestion1.getHighlightedResponseId() == 1 & testQuestion8.getHighlightedResponseId() == null);
		assertTrue(testQuestion1.getId() == 1 & testQuestion3.getId() == null);
		assertTrue(testQuestion1.getQuestionerId() == 1 & testQuestion6.getQuestionerId() == null);
		assertTrue(testQuestion1.getResponses() != null & testQuestion7.getResponses() == null);
		assertTrue(testQuestion1.getCreationDate() != null & testQuestion11.getCreationDate() == null);
		assertTrue(testQuestion1.getImages() != null & testQuestion10.getImages() == null);
		assertTrue(testQuestion1.getUser() == null);
		assertTrue(testQuestion1.getClass() == nullQuestion.getClass());
		assertFalse(testQuestion1.equals(emptyQuestion));
		assertFalse(testQuestion1.equals(testDate));

		Tag newTag = new Tag();

		nullQuestion.addImageToImages(testImage1);
		nullQuestion.setAssociatedTags(null);
		nullQuestion.addTagToQuestion(newTag);

		Image newImage = new Image();
		testQuestion1.addImageToImages(newImage);
		nullQuestion.addTagToQuestion(newTag);

		assertTrue(nullQuestion.getImages() != null);
		assertTrue(nullQuestion.getAssociatedTags() != null);
	}
  
  @Test
  public void tagHashcodeEqualsStringTest() {
    Tag testTag = new Tag();
    testTag.setId(10);
    testTag.setName("tagName");

    Tag testTagSame = new Tag();
    testTagSame.setId(10);
    testTagSame.setName("tagName");

    Tag testTagDiffId = new Tag();
    testTagDiffId.setId(11);
    testTagDiffId.setName("tagName");

    Tag testTagDiffName = new Tag();
    testTagDiffName.setId(10);
    testTagDiffName.setName("tagName2");

    Tag testTagNullId = new Tag();
    testTagNullId.setId(null);
    testTagNullId.setName("tagName");

    Tag testTagNullIdSame = new Tag();
    testTagNullIdSame.setId(null);
    testTagNullIdSame.setName("tagName");

    Tag testTagNullName = new Tag();
    testTagNullName.setId(10);
    testTagNullName.setName(null);

    Tag testTagNullNameSame = new Tag();
    testTagNullNameSame.setId(10);
    testTagNullNameSame.setName(null);

    Tag testTagNullFields = new Tag();
    testTagNullFields.setId(null);
    testTagNullFields.setName(null);

    Tag testTagNullObj = null;

    Image imageNotTag = new Image();

    // .equals()
    assertTrue(testTag.equals(testTagSame) & testTagSame.equals(testTag));
    assertFalse(testTag.equals(testTagNullId) & testTagNullId.equals(testTag));
    assertFalse(testTag.equals(testTagNullName) & testTagNullName.equals(testTag));
    assertFalse(testTag.equals(testTagNullObj));
    assertFalse(testTag.equals(imageNotTag));
    assertFalse(testTag.equals(testTagDiffName) & testTagDiffName.equals(testTag));
    assertFalse(testTag.equals(testTagDiffId) & testTagDiffId.equals(testTag));
    assertTrue(testTagNullId.equals(testTagNullIdSame));
    assertTrue(testTagNullName.equals(testTagNullNameSame));

    // .hashCode()
    assertTrue(testTag.hashCode() == testTagSame.hashCode());
    assertFalse(testTag.hashCode() == testTagNullFields.hashCode());

    // .toString()
    assertEquals(testTag.toString(), "Tag [id=10, name=tagName]");
  }

  @Test
  public void imageJavaBeanTest() {
    Image testImage = new Image();
    Question imAQuestion = new Question();
    testImage.setId(10);
    byte[] byteArr = new byte[1];
    byteArr[0] = '1';
    testImage.setImage(byteArr);
    testImage.setQuestion(imAQuestion);
    assertNotNull(testImage);
    Image testImageSame = new Image(10, byteArr, imAQuestion);

    // getters
    assertEquals(testImageSame, testImage);
    assertEquals(testImage, new Image(10, byteArr, imAQuestion));
    assertEquals(testImage.getId(), 10);
    assertEquals(testImage.getImage(), byteArr);
    assertEquals(testImage.getQuestion(), imAQuestion);

    // .equals()
    Image testImageNull = null;
    Tag testTag = new Tag();
    Image testImageDiffId = new Image(11, byteArr, imAQuestion);
    byte[] byteArr2 = new byte[1];
    byteArr2[0] = '2';
    Image testImageDiffImage = new Image(10, byteArr2, imAQuestion);
    assertTrue(testImage.equals(testImage));
    assertFalse(testImage.equals(testImageNull));
    assertFalse(testImage.equals(testTag));
    assertFalse(testImage.equals(testImageDiffId));
    assertFalse(testImage.equals(testImageDiffImage));

    // .toString()
    assertEquals(testImageSame.toString(),
        "Image [id=10, image=[49], question=" + testImageSame.getQuestion().toString() + "]");
  }

  @Test
  public void responseJavaBeanTest() {
    Response testResponse = new Response();
    testResponse.setId(10);
    testResponse.setBody("Response Body");
    testResponse.setResponderId(10);
    Date date = new Date();
    testResponse.setCreationDate(date);
    testResponse.setQuestionId(10);
    testResponse.setQuestion(testQuestion1);
    testResponse.setUser(testUser1);

    Response testResponseSame = new Response();
    testResponseSame.setId(10);
    testResponseSame.setBody("Response Body");
    testResponseSame.setResponderId(10);
    testResponseSame.setCreationDate(date);
    testResponseSame.setQuestionId(10);
    testResponseSame.setQuestion(testQuestion1);
    testResponseSame.setUser(testUser1);

    Response testResponseNullFields = new Response();
    testResponseNullFields.setId(null);
    testResponseNullFields.setBody(null);
    testResponseNullFields.setResponderId(null);
    testResponseNullFields.setCreationDate(null);
    testResponseNullFields.setQuestionId(null);
    testResponseNullFields.setQuestion(testQuestion1);
    testResponseNullFields.setUser(testUser1);

    Response testResponseNullFieldsSame = new Response();
    testResponseNullFieldsSame.setId(null);
    testResponseNullFieldsSame.setBody(null);
    testResponseNullFieldsSame.setResponderId(null);
    testResponseNullFieldsSame.setCreationDate(null);
    testResponseNullFieldsSame.setQuestionId(null);
    testResponseNullFieldsSame.setQuestion(testQuestion1);
    testResponseNullFieldsSame.setUser(testUser1);

    Response testResponseDiffId = new Response();
    testResponseDiffId.setId(11);
    testResponseDiffId.setBody("Response Body");
    testResponseDiffId.setResponderId(10);
    testResponseDiffId.setCreationDate(date);
    testResponseDiffId.setQuestionId(10);
    testResponseDiffId.setQuestion(testQuestion1);
    testResponseDiffId.setUser(testUser1);

    Response testResponseNullId = new Response();
    testResponseNullId.setId(null);
    testResponseNullId.setBody("Response Body");
    testResponseNullId.setResponderId(10);
    testResponseNullId.setCreationDate(date);
    testResponseNullId.setQuestionId(10);
    testResponseNullId.setQuestion(testQuestion1);
    testResponseNullId.setUser(testUser1);

    Response testResponseDiffBody = new Response();
    testResponseDiffBody.setId(10);
    testResponseDiffBody.setBody("Different Response Body");
    testResponseDiffBody.setResponderId(10);
    testResponseDiffBody.setCreationDate(date);
    testResponseDiffBody.setQuestionId(10);
    testResponseDiffBody.setQuestion(testQuestion1);
    testResponseDiffBody.setUser(testUser1);

    Response testResponseNullBody = new Response();
    testResponseNullBody.setId(10);
    testResponseNullBody.setBody(null);
    testResponseNullBody.setResponderId(10);
    testResponseNullBody.setCreationDate(date);
    testResponseNullBody.setQuestionId(10);
    testResponseNullBody.setQuestion(testQuestion1);
    testResponseNullBody.setUser(testUser1);

    Response testResponseDiffResponderId = new Response();
    testResponseDiffResponderId.setId(10);
    testResponseDiffResponderId.setBody("Response Body");
    testResponseDiffResponderId.setResponderId(11);
    testResponseDiffResponderId.setCreationDate(date);
    testResponseDiffResponderId.setQuestionId(10);
    testResponseDiffResponderId.setQuestion(testQuestion1);
    testResponseDiffResponderId.setUser(testUser1);

    Response testResponseNullResponderId = new Response();
    testResponseNullResponderId.setId(10);
    testResponseNullResponderId.setBody("Response Body");
    testResponseNullResponderId.setResponderId(null);
    testResponseNullResponderId.setCreationDate(date);
    testResponseNullResponderId.setQuestionId(10);
    testResponseNullResponderId.setQuestion(testQuestion1);
    testResponseNullResponderId.setUser(testUser1);

    Response testResponseDiffCreationDate = new Response();
    testResponseDiffCreationDate.setId(10);
    testResponseDiffCreationDate.setBody("Response Body");
    testResponseDiffCreationDate.setResponderId(10);
    testResponseDiffCreationDate.setCreationDate(new Date(111L));
    testResponseDiffCreationDate.setQuestionId(10);
    testResponseDiffCreationDate.setQuestion(testQuestion1);
    testResponseDiffCreationDate.setUser(testUser1);

    Response testResponseNullCreationDate = new Response();
    testResponseNullCreationDate.setId(10);
    testResponseNullCreationDate.setBody("Response Body");
    testResponseNullCreationDate.setResponderId(10);
    testResponseNullCreationDate.setCreationDate(null);
    testResponseNullCreationDate.setQuestionId(10);
    testResponseNullCreationDate.setQuestion(testQuestion1);
    testResponseNullCreationDate.setUser(testUser1);

    Response testResponseDiffQuestionId = new Response();
    testResponseDiffQuestionId.setId(10);
    testResponseDiffQuestionId.setBody("Response Body");
    testResponseDiffQuestionId.setResponderId(10);
    testResponseDiffQuestionId.setCreationDate(date);
    testResponseDiffQuestionId.setQuestionId(11);
    testResponseDiffQuestionId.setQuestion(testQuestion1);
    testResponseDiffQuestionId.setUser(testUser1);

    Response testResponseNullQuestionId = new Response();
    testResponseNullQuestionId.setId(10);
    testResponseNullQuestionId.setBody("Response Body");
    testResponseNullQuestionId.setResponderId(10);
    testResponseNullQuestionId.setCreationDate(date);
    testResponseNullQuestionId.setQuestionId(null);
    testResponseNullQuestionId.setQuestion(testQuestion1);
    testResponseNullQuestionId.setUser(testUser1);

    // Response Exists
    assertNotNull(testResponse);

    // Test Getters and Setters
    assertEquals(testResponse.getId(), 10);
    assertEquals(testResponse.getBody(), "Response Body");
    assertEquals(testResponse.getResponderId(), (Integer) 10);
    assertEquals(testResponse.getCreationDate(), date);
    assertEquals(testResponse.getQuestionId(), (Integer) 10);
    assertEquals(testResponse.getQuestion(), testQuestion1);
    assertEquals(testResponse.getUser(), testUser1);

    // .hashcode()
    assertTrue(testResponse.hashCode() == testResponseSame.hashCode());
    assertFalse(testResponse.hashCode() == testResponseNullFields.hashCode());

    // .equals()
    assertTrue(testResponse.equals(testResponseSame));
    assertTrue(testResponseNullFields.equals(testResponseNullFieldsSame));
    assertFalse(testResponse.equals(null));
    assertFalse(testResponse.equals(testTag1));
    assertFalse(testResponseDiffBody.equals(testResponseNullFields)
        & testResponseDiffBody.equals(testResponse));
    assertFalse(testResponseNullBody.equals(testResponseNullFields)
        & testResponseNullBody.equals(testResponse));
    assertFalse(testResponseNullId.equals(testResponseNullFields)
        & testResponseNullId.equals(testResponse));
    assertFalse(testResponseDiffId.equals(testResponseNullFields)
        & testResponseDiffId.equals(testResponse));
    assertFalse(testResponseNullId.equals(testResponseNullFields)
        & testResponseNullId.equals(testResponse));
    assertFalse(testResponseDiffCreationDate.equals(testResponseNullFields)
        & testResponseDiffCreationDate.equals(testResponse));
    assertFalse(testResponseNullCreationDate.equals(testResponseNullFields)
        & testResponseNullCreationDate.equals(testResponse));
    assertFalse(testResponseDiffQuestionId.equals(testResponseNullFields)
        & testResponseDiffQuestionId.equals(testResponse));
    assertFalse(testResponseNullQuestionId.equals(testResponseNullFields)
        & testResponseNullQuestionId.equals(testResponse));
    assertFalse(testResponseDiffResponderId.equals(testResponseNullFields)
        & testResponseDiffResponderId.equals(testResponse));
    assertFalse(testResponseNullResponderId.equals(testResponseNullFields)
        & testResponseNullResponderId.equals(testResponse));

  }

  @Test
  public void userJavaBeanTest() {
    User testUser = new User();
    testUser.setId(10);
    assertEquals(testUser.getId(), 10);
    testUser.setUsername("username");
    assertEquals(testUser.getUsername(), "username");
    testUser.setPassword("password");
    assertEquals(testUser.getPassword(), "password");
    assertEquals(testUser, new User(10, "username", "password"));
    testUser.setExpert(true);
    assertEquals(testUser.isExpert(), true);
    Set<Tag> tags = new HashSet<Tag>();
    tags.add(testTag1);
    testUser.setExpertTags(tags);
    assertEquals(testUser.getExpertTags(), tags);
    Set<Question> questions = new HashSet<Question>();
    questions.add(testQuestion1);
    testUser.setQuestions(questions);
    assertEquals(testUser.getQuestions(), questions);
    Set<Response> responses = new HashSet<Response>();
    responses.add(testResponse1);
    testUser.setResponses(responses);
    assertEquals(testUser.getResponses(), responses);

    User testUserSame = new User();
    testUserSame.setId(10);
    testUserSame.setUsername("username");
    testUserSame.setPassword("password");
    testUserSame.setExpert(true);
    testUserSame.addTagToUser(testTag1);
    testUserSame.setQuestions(questions);
    testUserSame.setResponses(responses);

    User testUserAddTag = new User();
    testUserAddTag.addTagToUser(testTag1);
    testUserAddTag.addTagToUser(testTag2);

    User testUserNullFields = new User();
    testUserNullFields.setId(10);
    testUserNullFields.setUsername(null);
    testUserNullFields.setPassword(null);
    testUserNullFields.setExpert(false);
    testUserNullFields.setExpertTags(null);
    testUserNullFields.setQuestions(null);
    testUserNullFields.setResponses(null);

    User testUserNullFieldsSame = new User();
    testUserNullFieldsSame.setId(10);
    testUserNullFieldsSame.setUsername(null);
    testUserNullFieldsSame.setPassword(null);
    testUserNullFieldsSame.setExpert(false);
    testUserNullFieldsSame.setExpertTags(null);
    testUserNullFieldsSame.setQuestions(null);
    testUserNullFieldsSame.setResponses(null);

    User testUserDiffId = new User();
    testUserDiffId.setId(11);
    testUserDiffId.setUsername("username");
    testUserDiffId.setPassword("password");
    testUserDiffId.setExpert(true);
    testUserDiffId.setExpertTags(tags);
    testUserDiffId.setQuestions(questions);
    testUserDiffId.setResponses(responses);

    User testUserDiffUsername = new User();
    testUserDiffUsername.setId(10);
    testUserDiffUsername.setUsername("Different Username");
    testUserDiffUsername.setPassword("password");
    testUserDiffUsername.setExpert(true);
    testUserDiffUsername.setExpertTags(tags);
    testUserDiffUsername.setQuestions(questions);
    testUserDiffUsername.setResponses(responses);

    User testUserNullUsername = new User();
    testUserNullUsername.setId(10);
    testUserNullUsername.setUsername(null);
    testUserNullUsername.setPassword("password");
    testUserNullUsername.setExpert(true);
    testUserNullUsername.setExpertTags(tags);
    testUserNullUsername.setQuestions(questions);
    testUserNullUsername.setResponses(responses);

    User testUserDiffPassword = new User();
    testUserDiffPassword.setId(10);
    testUserDiffPassword.setUsername("username");
    testUserDiffPassword.setPassword("Different Password");
    testUserDiffPassword.setExpert(true);
    testUserDiffPassword.setExpertTags(tags);
    testUserDiffPassword.setQuestions(questions);
    testUserDiffPassword.setResponses(responses);

    User testUserNullPassword = new User();
    testUserNullPassword.setId(10);
    testUserNullPassword.setUsername("username");
    testUserNullPassword.setPassword(null);
    testUserNullPassword.setExpert(true);
    testUserNullPassword.setExpertTags(tags);
    testUserNullPassword.setQuestions(questions);
    testUserNullPassword.setResponses(responses);

    User testUserDiffExpert = new User();
    testUserDiffExpert.setId(10);
    testUserDiffExpert.setUsername("username");
    testUserDiffExpert.setPassword("password");
    testUserDiffExpert.setExpert(false);
    testUserDiffExpert.setExpertTags(tags);
    testUserDiffExpert.setQuestions(questions);
    testUserDiffExpert.setResponses(responses);

    User testUserDiffTags = new User();
    testUserDiffTags.setId(10);
    testUserDiffTags.setUsername("username");
    testUserDiffTags.setPassword("password");
    testUserDiffTags.setExpert(true);
    Set<Tag> tags2 = new HashSet<Tag>();
    tags2.add(testTag2);
    testUserDiffTags.setExpertTags(tags2);
    testUserDiffTags.setQuestions(questions);
    testUserDiffTags.setResponses(responses);

    User testUserNullTags = new User();
    testUserNullTags.setId(10);
    testUserNullTags.setUsername("username");
    testUserNullTags.setPassword("password");
    testUserNullTags.setExpert(true);
    testUserNullTags.setExpertTags(null);
    testUserNullTags.setQuestions(questions);
    testUserNullTags.setResponses(responses);

    User testUserDiffQuestions = new User();
    testUserDiffQuestions.setId(10);
    testUserDiffQuestions.setUsername("username");
    testUserDiffQuestions.setPassword("password");
    testUserDiffQuestions.setExpert(true);
    testUserDiffQuestions.setExpertTags(tags);
    Set<Question> questions2 = new HashSet<Question>();
    questions2.add(testQuestion2);
    testUserDiffQuestions.setQuestions(questions2);
    testUserDiffQuestions.setResponses(responses);

    User testUserNullQuestions = new User();
    testUserNullQuestions.setId(10);
    testUserNullQuestions.setUsername("username");
    testUserNullQuestions.setPassword("password");
    testUserNullQuestions.setExpert(true);
    testUserNullQuestions.setExpertTags(tags);
    testUserNullQuestions.setQuestions(null);
    testUserNullQuestions.setResponses(responses);

    User testUserDiffResponses = new User();
    testUserDiffResponses.setId(10);
    testUserDiffResponses.setUsername("username");
    testUserDiffResponses.setPassword("password");
    testUserDiffResponses.setExpert(true);
    testUserDiffResponses.setExpertTags(tags);
    testUserDiffResponses.setQuestions(questions);
    Set<Response> responses2 = new HashSet<Response>();
    responses2.add(testResponse2);
    testUserDiffResponses.setResponses(responses2);

    User testUserNullResponses = new User();
    testUserNullResponses.setId(10);
    testUserNullResponses.setUsername("username");
    testUserNullResponses.setPassword("password");
    testUserNullResponses.setExpert(true);
    testUserNullResponses.setExpertTags(tags);
    testUserNullResponses.setQuestions(questions);
    testUserNullResponses.setResponses(null);

    assertNotEquals(testUser.getExpertTags(), testUserAddTag.getExpertTags());
    assertNotEquals(testUser.getUsername(), "Not the username");
    assertNotEquals(testUserNullUsername.getUsername(), "Not the username");
    assertEquals(testUserNullUsername.getUsername(), null);

    // .hashcode()
    assertTrue(testUser.hashCode() == testUserSame.hashCode());
    assertFalse(testUser.hashCode() == testUserNullFields.hashCode());

    // .equals()
    assertTrue(testUser.equals(testUserSame));
    assertTrue(testUserNullFields.equals(testUserNullFieldsSame));
    assertFalse(testUser.equals(null));
    assertFalse(testUser.equals(testImage1));
    assertFalse(testUserDiffId.equals(testUserNullFields) & testUserDiffId.equals(testUser));
    assertFalse(
        testUserDiffUsername.equals(testUserNullFields) & testUserDiffUsername.equals(testUser));
    assertFalse(
        testUserNullUsername.equals(testUserNullFields) & testUserNullUsername.equals(testUser)
            & testUserNullUsername.equals(testUserDiffUsername));
    assertFalse(
        testUserDiffPassword.equals(testUserNullFields) & testUserDiffPassword.equals(testUser));
    assertFalse(
        testUserNullPassword.equals(testUserNullFields) & testUserNullPassword.equals(testUser));
    assertFalse(
        testUserDiffExpert.equals(testUserNullFields) & testUserDiffExpert.equals(testUser));
    assertFalse(testUserDiffTags.equals(testUserNullFields) & testUserDiffTags.equals(testUser));
    assertFalse(testUserNullTags.equals(testUserNullFields) & testUserNullTags.equals(testUser));
    assertFalse(
        testUserDiffQuestions.equals(testUserNullFields) & testUserDiffQuestions.equals(testUser));
    assertFalse(
        testUserNullQuestions.equals(testUserNullFields) & testUserNullQuestions.equals(testUser));
    assertFalse(
        testUserDiffResponses.equals(testUserNullFields) & testUserDiffResponses.equals(testUser));
    assertFalse(
        testUserNullResponses.equals(testUserNullFields) & testUserNullResponses.equals(testUser));
  }
}