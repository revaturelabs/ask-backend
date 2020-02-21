package com.revaturelabs.ask.ama;

import java.util.List;

public interface AMASessionService {

  List<AMASession> getAllSessionsByExpert(String expertName);
  
  List<AMASession> getAllSessions();
  
}
