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

@RestController
@RequestMapping(path = "/response")
public class ResponseController {

  @Autowired
  ResponseService responseService;

  @GetMapping
  public List<Response> getAllResponses() {
    return this.responseService.getAll();
  }

  @GetMapping("/{id}")
  public Response getResponseById(@PathVariable int id) {
    try {
      return this.responseService.getById(id);
    } catch (ResponseNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found", e);
    }
  }

  @PostMapping
  public Response createResponse(@RequestBody Response response) {
    return this.responseService.create(response);
  }

  @PutMapping("/{id}")
  public Response createOrUpdate(@RequestBody Response response, @PathVariable int id) {
    response.setId(id);
    try {
      return this.responseService.createOrUpdate(response);
    } catch (ResponseConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Response already exists", e);
    }
  }

  @PatchMapping("/{id}")
  public Response updateResponse(@RequestBody Response response, @PathVariable int id) {
    response.setId(id);
    try {
      return this.responseService.update(response);
    } catch (ResponseConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Response already exists", e);
    } catch (ResponseNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Response not found", e);
    }
  }

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
