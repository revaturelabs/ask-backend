package com.revaturelabs.ask.question;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.image.ImageConflictException;

public interface QuestionService {

  Page<Question> getAll(int page, int size);

  Question getById(Integer id) throws QuestionNotFoundException;

  Question create(Question question);

  Question update(Question question) throws QuestionConflictException, QuestionNotFoundException;

  Question createOrUpdate(Question question) throws QuestionConflictException;

  Stream<Question> findAllByTagNames(boolean requireAll, List<String> tags, int page, int size);

  Question updateTags(Question question) throws QuestionNotFoundException;

  Question highlightResponse(int questionId, int highlightedResponseId)
      throws QuestionNotFoundException, QuestionConflictException;

  Question addImageToQuestion(int id, MultipartHttpServletRequest request)
      throws QuestionNotFoundException, ImageConflictException, IOException;

}
