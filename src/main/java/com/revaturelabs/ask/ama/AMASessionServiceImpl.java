package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AMASessionServiceImpl implements AMASessionService{

  @Autowired
  AMASessionRepository amaRepository;
  
  @Override
  public List<AMASession> getAllSessionsByExpert(String expertName) {
    // TODO Auto-generated method stub
    return amaRepository.getAMASessionsByExpert(expertName);
  }

  @Override
  public List<AMASession> getAllSessions() {
    // TODO Auto-generated method stub
    return amaRepository.findAll();
  }

}
