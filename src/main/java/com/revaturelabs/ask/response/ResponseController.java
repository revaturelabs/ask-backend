package com.revaturelabs.ask.response;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * The ResponseController is responsible for handling responses to questions. Can return a list of
 * responses, a response by id, and add response to database and update a response. Responses are in
 * JSON format.
 * 
 * @author Bryan Ritter
 *
 */
@RestController
@RequestMapping(path = "/responses")
public class ResponseController {

  @Autowired
  ResponseService responseService;

  /**
   * Accepts HTTP GET request. Returns a list of all responses on the database as a JSON object
   * 
   * @return a List of Responses that contain all responses on the database
   */
  @GetMapping
  public List<Response> getAllResponses() {
    return this.responseService.getAll();
  }

  /**
   * Accepts HTTP GET request. Returns a Response instance as a JSON entity based on the given id.
   * 
   * @param id receives the id of a response
   * @return response entity which has the same id as the given id.
   */
  @GetMapping("/{id}")
  public Response getResponseById(@PathVariable int id) {
    // System.out.println("in getResponseById of ResponseController " + id); // testing
    try {
      return this.responseService.getById(id);
    } catch (ResponseNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found", e);
    }
  }

  /**
   * Accepts a HTTP POST request. Attempts to add a Response instance to the database
   * 
   * @param response receives a response object
   * @return a Response object
   */
  @PostMapping
  public Response createResponse(@RequestBody Response response) {
    return this.responseService.create(response);
  }

  /**
   * Accepts HTTP PUT requests. Takes in a response and updates any matching response in the
   * database. If no response on the database has a matching id, then the given response is added to
   * the database.
   * 
   * @param response the response to create
   * @param id the id of the response to create
   * @return
   */
  @PutMapping("/{id}")
  public Response createOrUpdate(@RequestBody Response response, @PathVariable int id) {
    response.setId(id);
    try {
      return this.responseService.createOrUpdate(response);
    } catch (ResponseConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Error during update/creation of response", e);
    }
  }

  /**
   * Accepts HTTP PUT requests. Takes in a response and updates any matching response in the
   * database. If no response on the database has a matching id, then the given response is added to
   * the database.
   * 
   * @param response the response to update
   * @param id the id of the response to update
   * @return ResponseService
   */
  @PatchMapping("/{id}")
  public Response updateResponse(@RequestBody Response response, @PathVariable int id) {
    response.setId(id);
    try {
      return this.responseService.update(response);
    } catch (ResponseConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Error during update of response", e);
    } catch (ResponseNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found", e);
    }
  }

  /**
   * Accepts HTTP DELETE requests. Takes in a response id, and deletes the corresponding response,
   * if it exists.
   * 
   * @param id
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteResponse(@PathVariable int id) {
    try {
      this.responseService.delete(id);
    } catch (ResponseNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found", e);
    }
  }
}


