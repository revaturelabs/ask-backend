package com.revaturelabs.ask.question;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Questions.
 * It has the default methods of a JpaRepository.
 * 
 * @author Roy L. Brow De Jesús, Chris Allen
 *
 */
@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {

  Optional<List<Question>> findByUserId(int id);

}
