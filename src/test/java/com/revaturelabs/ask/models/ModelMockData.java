package com.revaturelabs.ask.models;

import java.util.Arrays;
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

public class ModelMockData {
	
	@Test
	public void contextLoads() {
	}

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

		testQuestion1PostCreate = new Question();
		testQuestion1PostCreate.setId(1);
		testQuestion1.setAssociatedTags(expertTags);

		testQuestion2 = new Question();
		testQuestion2.setId(2);
	}
}
