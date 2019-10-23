package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
  
  @Autowired
  QuestionRepository questionRepository;

  @Override
  public List<Question> getAll() {
    return (List<Question>) questionRepository.findAll();
  }

  @Override
  public Question getById(int id) throws QuestionNotFoundException {
    Optional<Question> question = questionRepository.findById(id);
    
    if(!question.isPresent()) {
      throw new QuestionNotFoundException("Question not found");
    }
    
    return question.get();
  }

  @Override
  public Question create(Question question) {
    question.setId(0);
    return questionRepository.save(question);
  }

  @Override
  public Question update(Question question) throws QuestionConflictException, QuestionNotFoundException {
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

}
