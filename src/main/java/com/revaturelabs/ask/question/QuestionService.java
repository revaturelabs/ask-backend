package com.revaturelabs.ask.question;

import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;

public interface QuestionService {

  Page<Question> getAll(int page, int size);

  Question getById(Integer id) throws QuestionNotFoundException;

  Question create(Question question);

  Question update(Question question) throws QuestionConflictException, QuestionNotFoundException;

  Question createOrUpdate(Question question) throws QuestionConflictException;

  Stream<Question> findAllByTagNames(boolean requireAll, List<String> tags, int page, int size);

  Set<Question> findAllByTagNames(boolean requireAll, List<String> tags);

  Question updateTags(Question question) throws QuestionNotFoundException;

  Question highlightResponse(int questionId, int highlightedResponseId) throws QuestionNotFoundException;

}
