package com.revaturelabs.ask.ama;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revaturelabs.ask.response.Response;

/**
 * 
 * @author Bianca/Updates to follow.
 *
 */
@Repository
public interface AMARepository extends JpaRepository<AMASession, Integer> {

}
