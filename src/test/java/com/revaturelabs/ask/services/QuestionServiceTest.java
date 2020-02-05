package com.revaturelabs.ask.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionRepository;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.question.QuestionServiceImpl;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.tag.TagServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {

	@Test
	public void contextLoads() {
	}

	static ServiceMockData mockData;

	@Mock
	TagRepository tagRepositoryMock;

	@Mock
	QuestionRepository questionRepositoryMock;

	@Mock
	TagService tagServiceMock;

	@Mock
	QuestionService questionServiceMock;

	@InjectMocks
	TagService tagServiceImpl = new TagServiceImpl();

	@InjectMocks
	QuestionService questionServiceImpl = new QuestionServiceImpl();

	@InjectMocks
	TagController tagControllerImpl = new TagController();

	@InjectMocks
	QuestionController questionControllerImpl = new QuestionController();

	/**
	 * Test for getting all questions
	 * 
	 */
	@Test
	public void questionsGetAllTest() {
		when(questionRepositoryMock.findAll(PageRequest.of(0, 5))).thenReturn(ServiceMockData.returnQuestionsPage);

		assertEquals(ServiceMockData.returnQuestionsPage, questionServiceImpl.getAll(0, 5));
	}

	/**
	 * Test for getting a question by ID
	 * 
	 */
	@Test
	public void questionsGetByIdTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));

		assertEquals(ServiceMockData.testQuestion1, questionServiceImpl.getById(1));
	}

	/**
	 * Test for getting a question accurately by ID
	 */
	@Test
	public void questionsGetAccuratelyByIdTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));

		assertNotEquals(ServiceMockData.testQuestion2, questionServiceImpl.getById(1));
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
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1)).thenReturn(ServiceMockData.testQuestion1PostCreate);

		assertEquals(ServiceMockData.testQuestion1PostCreate, questionServiceImpl.create(ServiceMockData.testQuestion1));
	}

	/**
	 * Test updating a question
	 */
	@Test
	public void questionUpdateTest() {
		Question testQuestion1UpdateInfo = new Question();
		testQuestion1UpdateInfo.setId(1);
		testQuestion1UpdateInfo.setBody("Test body update");

		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(questionRepositoryMock.save(testQuestion1UpdateInfo)).thenReturn(ServiceMockData.testQuestion1);

		assertEquals(ServiceMockData.testQuestion1, questionServiceImpl.update(testQuestion1UpdateInfo));
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
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1)).thenThrow(DataIntegrityViolationException.class);

		questionServiceImpl.update(ServiceMockData.testQuestion1);
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
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1)).thenThrow(DataIntegrityViolationException.class);

		questionServiceImpl.createOrUpdate(ServiceMockData.testQuestion1);
	}

	/**
	 * Testing updating tags for a question
	 * 
	 */
	@Test
	public void questionsUpdateTagsTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion2));
		when(questionRepositoryMock.save(ServiceMockData.testQuestion2)).thenReturn(ServiceMockData.testQuestion2);

		questionServiceImpl.updateTags(ServiceMockData.testQuestion1);

		assertEquals(ServiceMockData.testQuestion1.getAssociatedTags(), ServiceMockData.testQuestion2.getAssociatedTags());
	}

	/**
	 * Testing failure to find valid question
	 */
	@Test(expected = QuestionNotFoundException.class)
	public void questionsQuestionNotFoundWhenUpdatingTagsExceptionTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());
		questionServiceImpl.updateTags(ServiceMockData.testQuestion1);
	}

	/**
	 * Testing highlighting a response
	 */
	@Test
	public void questionsHighlightResponseTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1)).thenReturn(ServiceMockData.testQuestion1);

		assertEquals((Integer) 4, questionServiceImpl.highlightResponse(1, 4).getHighlightedResponseId());
	}

	/**
	 * Testing failure to find a question when highlighting a response
	 */
	@Test(expected = QuestionNotFoundException.class)
	public void questionsQuestionNotFoundWhenHighlightingResponseTest() {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.empty());

		questionServiceImpl.highlightResponse(1, 4);
	}
}
