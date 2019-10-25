package com.revaturelabs.ask.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Questions.
 * It has the default methods of a JpaRepository.
 * 
 * @author Roy L. Brow De Jesús
 *
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
