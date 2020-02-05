package com.revaturelabs.ask.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.user.User;

public class UserModelTest {

	static ModelMockData mockData;

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
		tags.add(ModelMockData.testTag1);
		testUser.setExpertTags(tags);
		assertEquals(testUser.getExpertTags(), tags);
		Set<Question> questions = new HashSet<Question>();
		questions.add(ModelMockData.testQuestion1);
		testUser.setQuestions(questions);
		assertEquals(testUser.getQuestions(), questions);
		Set<Response> responses = new HashSet<Response>();
		responses.add(ModelMockData.testResponse1);
		testUser.setResponses(responses);
		assertEquals(testUser.getResponses(), responses);

		User testUserSame = new User();
		testUserSame.setId(10);
		testUserSame.setUsername("username");
		testUserSame.setPassword("password");
		testUserSame.setExpert(true);
		testUserSame.addTagToUser(ModelMockData.testTag1);
		testUserSame.setQuestions(questions);
		testUserSame.setResponses(responses);

		User testUserAddTag = new User();
		testUserAddTag.addTagToUser(ModelMockData.testTag1);
		testUserAddTag.addTagToUser(ModelMockData.testTag2);

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
		tags2.add(ModelMockData.testTag2);
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
		questions2.add(ModelMockData.testQuestion3);
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
		responses2.add(ModelMockData.testResponse2);
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
		assertFalse(testUser.equals(ModelMockData.testImage1));
		assertFalse(testUserDiffId.equals(testUserNullFields) & testUserDiffId.equals(testUser));
		assertFalse(testUserDiffUsername.equals(testUserNullFields) & testUserDiffUsername.equals(testUser));
		assertFalse(testUserNullUsername.equals(testUserNullFields) & testUserNullUsername.equals(testUser)
				& testUserNullUsername.equals(testUserDiffUsername));
		assertFalse(testUserDiffPassword.equals(testUserNullFields) & testUserDiffPassword.equals(testUser));
		assertFalse(testUserNullPassword.equals(testUserNullFields) & testUserNullPassword.equals(testUser));
		assertFalse(testUserDiffExpert.equals(testUserNullFields) & testUserDiffExpert.equals(testUser));
		assertFalse(testUserDiffTags.equals(testUserNullFields) & testUserDiffTags.equals(testUser));
		assertFalse(testUserNullTags.equals(testUserNullFields) & testUserNullTags.equals(testUser));
		assertFalse(testUserDiffQuestions.equals(testUserNullFields) & testUserDiffQuestions.equals(testUser));
		assertFalse(testUserNullQuestions.equals(testUserNullFields) & testUserNullQuestions.equals(testUser));
		assertFalse(testUserDiffResponses.equals(testUserNullFields) & testUserDiffResponses.equals(testUser));
		assertFalse(testUserNullResponses.equals(testUserNullFields) & testUserNullResponses.equals(testUser));
		
		//.toString()
		String userString = "User [id="+testUser.getId()+", username="+testUser.getUsername()+", password="+testUser.getPassword()+
				", isExpert="+testUser.isExpert()+", expertTags="+testUser.getExpertTags()+", questions="
				+testUser.getQuestions().toString()+", responses="+testUser.getResponses().toString()+"]";
		assertEquals(testUser.toString(), userString);
	}
}
