package com.revaturelabs.ask.question;

import java.util.List;

public interface QuestionService {

  List<Question> getAll();

  Question getById(Integer id) throws QuestionNotFoundException;

  Question create(Question question);

  Question update(Question question) throws QuestionConflictException, QuestionNotFoundException;

  Question createOrUpdate(Question question) throws QuestionConflictException;

  List<Question> getByUserId(Integer id);

}
