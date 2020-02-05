package com.revaturelabs.ask.services;

import static org.junit.Assert.*;
import java.io.IOException;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionRepository;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.question.QuestionServiceImpl;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagController;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagRepository;
import com.revaturelabs.ask.tag.TagService;
import com.revaturelabs.ask.tag.TagServiceImpl;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.image.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {

	@Test
	public void contextLoads() {
	}

	static ServiceMockData mockData;
	
	@Mock
	ImageService imageServiceMock;

	@Mock
	TagRepository tagRepositoryMock;

	@Mock
	QuestionRepository questionRepositoryMock;

	@Mock
	TagService tagServiceMock;

	@Mock
	QuestionService questionServiceMock;
	
	@Mock
	MultipartHttpServletRequest mockRequest;

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
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1))
				.thenReturn(ServiceMockData.testQuestion1PostCreate);

		assertEquals(ServiceMockData.testQuestion1PostCreate,
				questionServiceImpl.create(ServiceMockData.testQuestion1));
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
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1))
				.thenThrow(DataIntegrityViolationException.class);

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
		when(questionRepositoryMock.save(ServiceMockData.testQuestion1))
				.thenThrow(DataIntegrityViolationException.class);

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

		assertEquals(ServiceMockData.testQuestion1.getAssociatedTags(),
				ServiceMockData.testQuestion2.getAssociatedTags());
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
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(questionServiceImpl.addImageToQuestion(1, mockRequest)).thenReturn(ServiceMockData.testQuestion1);

		assertTrue(questionServiceImpl.addImageToQuestion(1, mockRequest) == ServiceMockData.testQuestion1);
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
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(imageServiceMock.addImage(ServiceMockData.testQuestion1, mockRequest)).thenThrow(ImageConflictException.class);
		assertNotNull(questionRepositoryMock.findById(1));
		assertEquals(questionRepositoryMock.findById(1), Optional.of(ServiceMockData.testQuestion1));

		questionServiceImpl.addImageToQuestion(1, mockRequest);
	}

	@Test(expected = IOException.class)
	public void testAddImageToQuestionForIOException()
			throws QuestionNotFoundException, ImageConflictException, IOException {
		when(questionRepositoryMock.findById(1)).thenReturn(Optional.of(ServiceMockData.testQuestion1));
		when(imageServiceMock.addImage(ServiceMockData.testQuestion1, mockRequest)).thenThrow(IOException.class);
		assertNotNull(questionRepositoryMock.findById(1));
		assertEquals(questionRepositoryMock.findById(1), Optional.of(ServiceMockData.testQuestion1));

		questionServiceImpl.addImageToQuestion(1, mockRequest);
	}
	
	@Test
	public void testFindAllByTagNames() {
		List<Question> questions1 = new ArrayList<Question>();
		questions1.add(ServiceMockData.testQuestion1);
		List<Question> questions2 = new ArrayList<Question>();
		questions2.add(ServiceMockData.testQuestion2);
		Page<Question> page1 = new PageImpl<>(questions1);
		Page<Question> page2 = new PageImpl<>(questions2);

		List<String> tagNames = new ArrayList<String>();
		tagNames.add(ServiceMockData.testTag1.getName());
		tagNames.add(ServiceMockData.testTag2.getName());
		tagNames.add(ServiceMockData.testTag3.getName());

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.add(ServiceMockData.tagReturnList.get(0));
		expertTags.add(ServiceMockData.tagReturnList.get(1));
		expertTags.add(ServiceMockData.tagReturnList.get(2));
		Pageable pageable = (Pageable) PageRequest.of(0, 20);

		when(tagServiceMock.getTagByName(tagNames.get(0))).thenReturn(ServiceMockData.tagReturnList.get(0));
		when(tagServiceMock.getTagByName(tagNames.get(1))).thenReturn(ServiceMockData.tagReturnList.get(1));
		when(tagServiceMock.getTagByName(tagNames.get(2))).thenReturn(ServiceMockData.tagReturnList.get(2));

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
		tagNames.add(ServiceMockData.testTag1.getName());
		tagNames.add(ServiceMockData.testTag2.getName());
		tagNames.add(ServiceMockData.testTag3.getName());

		HashSet<Tag> expertTags = new HashSet<Tag>();
		expertTags.addAll(ServiceMockData.tagReturnList);

		when(tagServiceMock.getTagByName(tagNames.get(0))).thenReturn(ServiceMockData.testTag1);
		when(tagServiceMock.getTagByName(tagNames.get(1))).thenReturn(ServiceMockData.testTag2);
		when(tagServiceMock.getTagByName(tagNames.get(2))).thenThrow(TagNotFoundException.class);

		questionServiceImpl.findAllByTagNames(true, tagNames, 0, 20);
		questionServiceMock.findAllByTagNames(false, tagNames, 0, 20);
	}
}
