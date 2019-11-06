package com.revaturelabs.ask.response;

import java.util.List;

/**
 * An interface for the response service methods.
 * 
 * @author Bryan Ritter, Chris Allen
 *
 */
public interface ResponseService {

  List<Response> getAll();

  Response getById(int id) throws ResponseNotFoundException;

  Response create(Response response);

  Response update(Response response);

  Response createOrUpdate(Response category);

  void delete(int id);


}
