package com.revaturelabs.ask.response;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service class for managing responses. It contains methods for finding all responses, finding a
 * response by id, creating a response, and updating.
 * 
 * @author Bryan_Ritter, Chris Allen
 *
 */
@Service
public class ResponseServiceImpl implements ResponseService {

  @Autowired
  ResponseRepository responseRepository;

  /**
   * Returns a list of all response on the database
   * 
   * @return a List of Responses that contains all responses on the database.
   */
  @Override
  public List<Response> getAll() {
    return (List<Response>) this.responseRepository.findAll();
  }


  /**
   * Returns responses that match the given id.
   * 
   * @param id
   * @return Response all response that match the given id. empty if no responses match
   */
  @Override
  public Response getById(int id) throws ResponseNotFoundException {
    Optional<Response> response = this.responseRepository.findById(Integer.valueOf(id));

    if (!response.isPresent()) {
      throw new ResponseNotFoundException("Response not found");
    }

    return response.get();
  }



  /**
   * Adds to the database an instance of Response, before adding to database the instance id is set
   * to zero to allow the database to auto-generate the id of the new added instance.
   * 
   * @param response receives a response object
   */
  @Override
  public Response create(Response response) {
    return this.responseRepository.save(response);
  }

  /**
   * Takes a Response object and updates any matching entity in the database if no entities match
   * the question will be created.
   * 
   * @param response receives a response object
   */
  @Override
  public Response update(Response response) {
    Optional<Response> existingResponse =
        this.responseRepository.findById(Integer.valueOf(response.getId()));

    Response updatedResponse = null;
    if (existingResponse.isPresent()) {
      try {
        updatedResponse = this.responseRepository.save(response);
      } catch (DataIntegrityViolationException e) {
        throw new ResponseConflictException("Error updating response");
      }
    } else {
      throw new ResponseNotFoundException("Unable to find response to update");
    }

    return updatedResponse;
  }

  /**
   * Takes in a Response object and either edits an existing Response object or creates a new
   * response object
   * 
   * @param response response to update or create
   */
  @Override
  public Response createOrUpdate(Response response) {
    Response updatedResponse = null;
    try {
      updatedResponse = this.responseRepository.save(response);
    } catch (DataIntegrityViolationException e) {
      throw new ResponseConflictException("Response fails to satisfy constraints");
    }

    return updatedResponse;
  }

  /**
   * Takes in an id of a response to delete and deletes it
   * 
   * @param id an id of a response to be deleted
   */
  @Override
  public void delete(int id) {
    boolean categoryExists = this.responseRepository.existsById(Integer.valueOf(id));

    if (!categoryExists) {
      throw new ResponseNotFoundException("Unable to find response to delete");
    }

    this.responseRepository.deleteById(Integer.valueOf(id));
  }


  /**
   * Takes in an id of a question and returns a list of responses associated with
   * that question (if any)
   * 
   * @param id the id of the target question
   * @author Chris Allen
   */
  @Override
  public List<Response> getAllByQuestionId(int id) {
    Optional<List<Response>> responses = responseRepository.findByQuestionId(id);
    
    if(!responses.isPresent()) {
      throw new ResponseNotFoundException("Responses not found");
    }
    
    return responses.get();
  }

}

