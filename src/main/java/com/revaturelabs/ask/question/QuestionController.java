package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagService;

/**
 * The QuestionController is responsible for handling request about Questions. QuestionController
 * can return list of questions, a question by id, add question to database and update. Request and
 * responses are in JSON format.
 * 
 * @author Roy L. Brow De Jesús, Chris Allen
 *
 */
@RestController
@RequestMapping(path = "/questions")
public class QuestionController {

  @Autowired
  QuestionService questionService;

  @Autowired
  TagService tagService;


  /**
   * Accepts HTTP GET request. Returns a list of all questions on the database as a JSON object
   * 
   * @return a List of Question that contain all questions on the database
   */
  @GetMapping
  public ResponseEntity<List<Question>> getAllQuestions() {
    return ResponseEntity.ok(questionService.getAll());
  }

  /**
   * Accepts HTTP GET request Returns a Question instance as a JSON entity based on the given id.
   * 
   * @param id receives the id of a question
   * @return a question entity which has the same id as the given id.
   */

  @GetMapping("/{id}")
  public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
    try {
      return ResponseEntity.ok(questionService.getById(id));
    } catch (QuestionNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", e);
    }
  }

  /**
   * Accepts a HTTP POST request. Will take a question JSON with optional tag names and return a
   * valid question object with all specified tags as tag objects
   * 
   * @param a JSON
   * @return question object with correct wiring from JSON to Question object
   */
  @PostMapping("/create")
  public Question createQuestion(@RequestBody Question question) {

    question.setAssociatedTags(tagService.getValidTags(question.getAssociatedTags()));

    return questionService.create(question);
  }

  /**
   * Accepts HTTP PUT requests. Takes in a question and updates any matching question in the
   * database. If no question on the database has a matching id, then the given question is added to
   * the database.
   * 
   * @param question receives an object of question.
   * @param id receives an id of a question for update.
   */
  @PatchMapping("/{id}")
  public Question updateQuestion(@RequestBody Question question, @PathVariable int id) {
    question.setId(id);

    try {
      return questionService.update(question);
    } catch (QuestionConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Question already exists", e);
    } catch (QuestionNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", e);
    }
  }

  /**
   * Accepts HTTP PUT requests. Takes in a question and updates any matching question in the
   * database. If no question on the database has a matching id, then the given question is added to
   * the database.
   * 
   * @param question receives an object of question.
   * @param id receives an id of a question for update.
   */
  @PutMapping("/{id}")
  public Question createOrUpdate(@RequestBody Question question, @PathVariable int id) {
    question.setId(id);

    try {
      return questionService.createOrUpdate(question);
    } catch (QuestionConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Error during update/creation of question", e);
    }
  }


  /**
   * Accepts HTTP GET requests. Takes a boolean and a list of tag names to be searched for and
   * returns a set of questions that either contain all of the tags or contain at least one of the
   * tags (denoted by the boolean)
   * 
   * @author Chris Allen
   * @param requireAll A boolean that specifies whether to return questions with all the tags or at
   *        least one of the tags
   * @param tag The list of tag names to be searched for
   * @return Either a set of questions that match the specified criteria or a bad request exception
   */
  @GetMapping("/search")
  public ResponseEntity<Set<Question>> filterByTags(
      @RequestParam(required = true) boolean requireAll,
      @RequestParam(required = false) List<String> tag) {

    try {
      return ResponseEntity.ok(questionService.findAllByTagNames(requireAll, tag));
    } catch (TagNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A requested tag was not found!");
    }
  }
}
