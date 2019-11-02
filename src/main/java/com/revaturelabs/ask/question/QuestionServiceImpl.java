package com.revaturelabs.ask.question;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagNotFoundException;
import com.revaturelabs.ask.tag.TagService;

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

  @Autowired
  TagService tagService;

  /**
   * Returns a list of all questions on the database
   * 
   * @return a List of Question that contains all questions on the database.
   */
  @Override
  public Page<Question> getAll(int page, int size) {

    Pageable pageable = (Pageable) PageRequest.of(page, size);
    return questionRepository.findAll(pageable);
  }

  /**
   * Returns questions that match the given id.
   * 
   * @param id
   * 
   * @return all questions that match the given id. empty if no question match
   */
  @Override
  public Question getById(Integer id) throws QuestionNotFoundException {
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
        throw new QuestionConflictException("Question already exists");
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

  /**
   * 
   * A method to search for questions by specified tags. Will allow for the results to return either
   * questions that have all of the specified tags or for questions that have at least one of the
   * specified tags.
   * 
   * @author Chris Allen
   * @param requireAll A boolean that specified whether all tags must be included or at least one
   *        tag is included in the results
   * @param tagNames A list of tag names to be searched for
   */
  @Override
  public Stream<Question> findAllByTagNames(boolean requireAll, List<String> tagNames, int page,
      int size) {

    Pageable pageable = (Pageable) PageRequest.of(page, size);
    Set<Tag> tagsToSearchWith = new HashSet<Tag>();

    for (String tagName : tagNames) {
      try {
        tagsToSearchWith.add(tagService.getTagByName(tagName));
      } catch (TagNotFoundException e) {
        throw new TagNotFoundException("A specified tag was not found!");
      }
    }

    if (requireAll) {
      return (questionRepository
          .findAllContainingAllTags(tagsToSearchWith, tagsToSearchWith.size(), pageable).get());
    } else {
      return (questionRepository.findAllContainingAtLeastOneTag(tagsToSearchWith, pageable).get());
    }
  }
}
