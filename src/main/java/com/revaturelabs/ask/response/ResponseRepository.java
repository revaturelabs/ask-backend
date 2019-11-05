package com.revaturelabs.ask.response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Questions. It has the default methods of a JpaRepository.
 * 
 * @author Bryan Ritter, Chris Allen
 *
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {


}


