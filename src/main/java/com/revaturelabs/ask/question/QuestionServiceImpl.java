package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.user.UserNotFoundException;

/**
 * Service class for managing questions. It contains methods for finding all questions, finding a
 * question by id, creating a question, and updating.
 * 
 * @author Roy L.Brow De Jesús
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  QuestionRepository questionRepository;

  /**
   * Returns a list of all questions on the database
   * 
   * @return a List of Question that contains all questions on the database.
   */
  @Override
  public List<Question> getAll() {
    return (List<Question>) questionRepository.findAll();
  }

  /**
   * Returns questions that match the given id.
   * 
   * @param id
   * 
   * @return all questions that match the given id. empty if no question match
   */
  @Override
  public Question getById(int id) throws QuestionNotFoundException {
    Optional<Question> question = questionRepository.findById(id);

    if (!question.isPresent()) {
      throw new QuestionNotFoundException("Question not found");
    }

    return question.get();
  }

  /**
   * Adds to the database an instance of Question, before adding to database the instance id is set
   * to zero to allow the database to auto-generate the id of the new added instance.
   * 
   * @param question receives a question object
   */
  @Override
  public Question create(Question question) {
    return questionRepository.save(question);
  }

  /**
   * Takes a Question object and updates any matching entity in the database if no entities match
   * the question will be created.
   * 
   * @param question receives a question object
   */
  @Override
  public Question update(Question question)
      throws QuestionConflictException, QuestionNotFoundException {
    Optional<Question> existingQuestion = questionRepository.findById(question.getId());

    Question updateQuestion = null;
    if (existingQuestion.isPresent()) {
      try {
        updateQuestion = questionRepository.save(question);
      } catch (DataIntegrityViolationException e) {
        throw new QuestionConflictException("Question already exist");
      }
    } else {
      throw new QuestionNotFoundException("Unable to find question to update.");
    }
    return updateQuestion;
  }

  @Override
  public Question createOrUpdate(Question question) throws QuestionConflictException {
    Question updateQuestion = null;

    try {
      updateQuestion = questionRepository.save(question);
    } catch (DataIntegrityViolationException e) {
      throw new QuestionConflictException("Question already exists");
    }
    return updateQuestion;
  }

  @Override
  public List<Question> getByUserId(int id) {
    Optional<List<Question>> questionList = questionRepository.findByQuestionerId(id);

    if (!questionList.isPresent()) {
      throw new UserNotFoundException("No questions found for that user");
    }

    return questionList.get();
  }

}
