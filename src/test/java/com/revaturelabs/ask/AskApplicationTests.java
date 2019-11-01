package com.revaturelabs.ask;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.question.QuestionConflictException;
import com.revaturelabs.ask.question.QuestionController;
import com.revaturelabs.ask.question.QuestionNotFoundException;
import com.revaturelabs.ask.question.QuestionService;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.response.ResponseController;
import com.revaturelabs.ask.response.ResponseService;
import com.revaturelabs.ask.tags.Tag;
import com.revaturelabs.ask.tags.TagController;
import com.revaturelabs.ask.tags.TagService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AskApplicationTests {

  @Test
  public void contextLoads() {}


  @MockBean
  ResponseService responseServiceMock;

  @MockBean
  TagService tagServiceMock;

  @MockBean
  QuestionService questionServiceMock;


  @Autowired
  ResponseController responseControllerImpl;

  @Autowired
  TagController tagControllerImpl;

  @Autowired
  QuestionController questionControllerImpl;

  @Test
  public void testGetResponseById() {

    Response exampleResponse = new Response();
    when((responseServiceMock.getById(1))).thenReturn(exampleResponse);

    assertEquals(exampleResponse, responseControllerImpl.getResponseById(1));
  }

  /**
   * Tests for tags.
   */
  @Test
  public void testGetTagById() {
    Tag exampleTag = new Tag();
    when((tagServiceMock.getById(1))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.getTagById(1));
  }


  @Test
  public void testGetAllTags() {
    Tag javaScriptTag = new Tag();
    javaScriptTag.setName("JavaScript");
    Tag javaTag = new Tag();
    javaTag.setName("Java");
    List<Tag> exampleTags = new ArrayList();
    exampleTags.add(javaScriptTag);
    exampleTags.add(javaTag);
    when((tagServiceMock.getAll())).thenReturn(exampleTags);
    assertEquals(exampleTags, tagControllerImpl.getAllTags());
  }

  @Test
  public void testCreateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.create(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createTag(exampleTag));
  }

  @Test
  public void testUpdateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setId(1);
    exampleTag.setName("JavaScript");

    when((tagServiceMock.update(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.updateTag(exampleTag, 1));
  }


  @Test
  public void testCreateUpdateTag() {
    Tag exampleTag = new Tag();
    exampleTag.setName("JavaScript");
    when((tagServiceMock.createOrUpdate(exampleTag))).thenReturn(exampleTag);
    assertEquals(exampleTag, tagControllerImpl.createOrUpdate(exampleTag, 1));
  }


  /**
   * Tests related to Questions
   */
  @Test
  public void testGetQuestionById() {
    Question exampleQuestion = new Question();
    try {
      when((questionServiceMock.getById(1))).thenReturn(exampleQuestion);
    } catch (Exception e) {
    }
    assertEquals(exampleQuestion, questionControllerImpl.getQuestionById(1));
  }

  @Test
  public void testGetAllQuestions() {
    Question javaScriptQuestion = new Question();
    javaScriptQuestion.setHead("A JavaScript Question");
    javaScriptQuestion.setBody("A JavaScript Question Body");

    Question htmlQuestion = new Question();
    htmlQuestion.setHead("An HTML Question");
    htmlQuestion.setBody("An HTML Question Body");

    List<Question> questionList = new ArrayList();
    questionList.add(javaScriptQuestion);
    questionList.add(htmlQuestion);

    when((questionServiceMock.getAll())).thenReturn(questionList);
    assertEquals(questionList, questionControllerImpl.getAllQuestions());
  }

  @Test
  public void testCreateQuestion() {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    when((questionServiceMock.create(exampleQuestion))).thenReturn(exampleQuestion);
    assertEquals(exampleQuestion, questionControllerImpl.createQuestion(exampleQuestion));
  }

  @Test
  public void testCreateUpdateQuestion() throws QuestionConflictException {
    Question exampleQuestion = new Question();
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");

    when((questionServiceMock.createOrUpdate(exampleQuestion))).thenReturn(exampleQuestion);
    assertEquals(exampleQuestion, questionControllerImpl.createOrUpdate(exampleQuestion, 1));
  }

  @Test
  public void testUpdateQuestion() throws QuestionConflictException, QuestionNotFoundException {
    Question exampleQuestion = new Question();
    exampleQuestion.setId(1);
    exampleQuestion.setHead("JavaScript Question Head");
    exampleQuestion.setBody("JavaScript Question Body");
    when((questionServiceMock.update(exampleQuestion))).thenReturn(exampleQuestion);
    assertEquals(exampleQuestion, questionControllerImpl.updateQuestion(exampleQuestion, 1));
  }
}
