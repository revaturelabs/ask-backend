package com.revaturelabs.ask.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.HashSet;
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
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.user.User;

/**
 * @author Bryan Ritter, David Blitz, Efrain Vila
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionControllerTest {

	@Test
	public void contextLoads() {
	}

	@MockBean
	QuestionService questionServiceMock;

	@Autowired
	QuestionController questionControllerImpl;

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
		assertEquals(ResponseEntity.ok(null), questionControllerImpl.updateQuestion(exampleQuestion, 1));
	}
}
