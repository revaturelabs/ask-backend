package com.revaturelabs.ask.ama;

import java.util.List;
import com.revaturelabs.ask.user.User;

public interface AMASessionService {

  List<AMASession> getAllSessionsByExpert(User expertName);
  
  List<AMASession> getAllSessions();
  
}
