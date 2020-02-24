package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revaturelabs.ask.user.User;

/**
 * 
 * @author Bianca/Updates to follow.
 *
 */
@Repository
public interface AMASessionRepository extends JpaRepository<AMASession, Integer> {
  
  public List<AMASession> getAMASessionsByExpert(User expert);

  public List<AMASession> findAll();

}
