package com.revaturelabs.ask.models;

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
		
		HashSet<Response> expertResponses = new HashSet<Response>();
		expertResponses.addAll(responseReturnList);

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

		testQuestion1PostCreate = new Question();
		testQuestion1PostCreate.setId(1);
		testQuestion1.setAssociatedTags(expertTags);
	}
}
