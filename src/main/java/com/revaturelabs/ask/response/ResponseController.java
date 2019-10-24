package com.revaturelabs.ask.response;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/response")
public class ResponseController {

  @Autowired
  ResponseService responseService;
  
  @GetMapping
  public List<Response> getAllQuestions() {
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
}
