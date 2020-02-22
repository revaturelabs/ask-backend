package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.user.User;

@Service
public class AMASessionServiceImpl implements AMASessionService{

  @Autowired
  AMASessionRepository amaRepository;
  
  @Override
  public List<AMASession> getAllSessionsByExpert(User expertName) {
    // TODO Auto-generated method stub
    return amaRepository.getAMASessionsByExpert(expertName);
  }

  @Override
  public List<AMASession> getAllSessions() {
    // TODO Auto-generated method stub
    return amaRepository.findAll();
  }

}
