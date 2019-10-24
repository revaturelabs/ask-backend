package com.revaturelabs.ask.response;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {
  
  @Autowired
  ResponseRepo responseRepo;

  @Override
  public List<Response> getAll() {
    return (List<Response>) this.responseRepo.findAll();
  }

  @Override
  public Response getById(int id) throws ResponseNotFoundException {
    Optional<Response> response = this.responseRepo.findById(Integer.valueOf(id));
    
    if(!response.isPresent()) {
      throw new ResponseNotFoundException("Response not found");
    }
    
    return response.get();
  }

}
