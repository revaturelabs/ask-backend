package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
  
  @Autowired
  QuestionRepo questionRepo;

  
  public List<Question> getAll() {
    return (List<Question>) questionRepo.findAll();
  }

  @Override
  public Question getById(int id) throws QuestionNotFoundException {
    Optional<Question> question = questionRepo.findById(id);
    
    if(!question.isPresent()) {
      throw new QuestionNotFoundException("Question not found");
    }
    
    return question.get();
  }

}
