package com.revaturelabs.ask.question;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

  @Autowired
  QuestionService questionService;

  @GetMapping
  public List<Question> getAllQuestions() {
    return questionService.getAll();
  }

  @GetMapping("/{id}")
  public Question getQuestionById(@PathVariable int id) {
    try {
      return questionService.getById(id);
    } catch (QuestionNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", e);
    }
  }
  
  @PostMapping
  public Question createQuestion(@RequestBody Question question) {
    return questionService.create(question);
  }

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

  @PutMapping("/{id}")
  public Question createOrUpdate(@RequestBody Question question, @PathVariable int id) {
    question.setId(id);

    try {
      return questionService.createOrUpdate(question);
    } catch (QuestionConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Question already exists", e);
    }
  }
}
