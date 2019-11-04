package com.revaturelabs.ask.question;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.revaturelabs.ask.image.ImageConflictException;
import com.revaturelabs.ask.image.ImageService;
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
  
  @Autowired
  ImageService imageService;

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


  /**
   * Specialized function to update the tags of an existing question.
   * 
   * @param question the Question object with a set of tags to use for updating
   * @return updatedQuestion The question after being updated in the repository
   * @throws QuestionNotFoundException
   */

  @Override
  public Question updateTags(Question question) throws QuestionNotFoundException {
    Optional<Question> existingQuestion = questionRepository.findById(question.getId());

    Question updatedQuestion = null;

    if (existingQuestion.isPresent()) {
      updatedQuestion = existingQuestion.get();
      updatedQuestion.setAssociatedTags(question.getAssociatedTags());
      updatedQuestion = questionRepository.save(updatedQuestion);
    } else {
      throw new QuestionNotFoundException("No such question found!");
    }

    return updatedQuestion;
  }

  /**
   * 
   * Takes a question ID and a response ID and specifies the given response ID as the highlighted
   * response ID.
   * 
   * @param questionId the ID of the question to be updated
   * @param highlightedResponseId the ID of the response that should be updated
   * @return question the Question to be returned
   * @throws QuestionNotFoundException
   * 
   */
  @Override
  public Question highlightResponse(int questionId, int highlightedResponseId)
      throws QuestionNotFoundException {
    Question question = null;
    try {
      question = getById(questionId);
      question.setHighlightedResponseId(highlightedResponseId);
      return questionRepository.save(question);
    } catch (QuestionNotFoundException e) {
      throw new QuestionNotFoundException("The specified question was not found!");
    }
  }

  /**
   * Takes an id and a Multipart Http request and returns a modified question, which has had
   * an image added to its set of images.
   * 
   * @param id The id of the question to be modified
   * @param request The Multipart HttpRequest containing the image
   * @author Chris Allen
   */
  @Override
  public Question addImageToQuestion(int id, MultipartHttpServletRequest request)
      throws QuestionNotFoundException, ImageConflictException, IOException {
    Question question = null;
    try {
      question = getById(id);
      question.addImageToImages(imageService.addImage(request));
      return questionRepository.save(question);
    }
    catch(QuestionNotFoundException e) {
      throw new QuestionNotFoundException("The specified question was not found!");
    } catch (IOException e) {
      throw new IOException("There was an I/O Exception!");
    } catch (ImageConflictException e) {
       throw new ImageConflictException("There was an issue when uploading the image!");
    }
  }
}
