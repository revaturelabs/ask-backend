package com.revaturelabs.ask.ama;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Bianca/Updates to follow.
 *
 */
@Repository
public interface AMASessionRepository extends JpaRepository<AMASession, Integer> {
  
  public List<AMASession> getAMASessionsByExpert(String expertName);

  public List<AMASession> findAll();

}
