package com.revaturelabs.ask.response;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Questions. It has the default methods of a JpaRepository.
 * 
 * @author Bryan Ritter
 *
 */
@Repository
public interface ResponseRepository extends CrudRepository<Response, Integer> {

}


