package com.revaturelabs.ask.response;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Questions. It has the default methods of a JpaRepository.
 * 
 * @author Bryan Ritter, Chris Allen
 *
 */
@Repository
public interface ResponseRepository extends CrudRepository<Response, Integer> {

  Optional<List<Response>> findByQuestionId(Integer id);

}


