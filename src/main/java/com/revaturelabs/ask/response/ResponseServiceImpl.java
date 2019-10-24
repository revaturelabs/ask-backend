package com.revaturelabs.ask.response;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

  @Autowired
  ResponseRepository responseRepository;

  @Override
  public List<Response> getAll() {
    return (List<Response>) this.responseRepository.findAll();
  }

  @Override
  public Response getById(int id) throws ResponseNotFoundException {
    Optional<Response> response = this.responseRepository.findById(Integer.valueOf(id));

    if (!response.isPresent()) {
      throw new ResponseNotFoundException("Response not found");
    }

    return response.get();
  }

  @Override
  public Response create(Response response) {
    return this.responseRepository.save(response);
  }

  @Override
  public Response update(Response response) {
    Optional<Response> existingResponse =
        this.responseRepository.findById(Integer.valueOf(response.getId()));

    Response updatedResponse = null;
    if (existingResponse.isPresent()) {
      try {
        updatedResponse = this.responseRepository.save(response);
      } catch (DataIntegrityViolationException e) {
        throw new ResponseConflictException("Response name already exists");
      }
    } else {
      throw new ResponseNotFoundException("Unable to find response to update");
    }

    return updatedResponse;
  }

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

  @Override
  public void delete(int id) {
    boolean categoryExists = this.responseRepository.existsById(Integer.valueOf(id));

    if (!categoryExists) {
      throw new ResponseNotFoundException("Unable to find response to delete");
    }

    this.responseRepository.deleteById(Integer.valueOf(id));
  }

}

