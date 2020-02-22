package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.user.User;

@Service
public class AMASessionServiceImpl implements AMASessionService{

  @Autowired
  AMASessionRepository amaRepository;
  
  public List<AMASession> getAllSessionsByExpert(User expertName) {
    return amaRepository.getAMASessionsByExpert(expertName);
  }

  public List<AMASession> getAllSessions() {
    return amaRepository.findAll();
  }

  public AMASession postNewSession(AMASession newSession) {
    return amaRepository.save(newSession);
  }

}
