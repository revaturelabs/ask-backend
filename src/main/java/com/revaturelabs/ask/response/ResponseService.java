package com.revaturelabs.ask.response;

import java.util.List;

public interface ResponseService {
  
  List<Response> getAll();
  
  Response getById(int id) throws ResponseNotFoundException;

}
