package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

  List<Question> getAll();

  Question getById(int id) throws QuestionNotFoundException;

  Question create(Question question);

  Question update(Question question) throws QuestionConflictException, QuestionNotFoundException;

  Question createOrUpdate(Question question) throws QuestionConflictException;

  Set<Question> findAllByTagNames(boolean requireAll, List<String> tags);

}
