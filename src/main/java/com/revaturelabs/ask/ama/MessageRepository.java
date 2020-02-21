package com.revaturelabs.ask.ama;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Bianca/Updates to follow.
 *
 */

@Repository
public interface MessageRepository extends JpaRepository<MessageRepository, Integer> {

}
