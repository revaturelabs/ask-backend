package com.revaturelabs.ask.questionTagsJunction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTagsJunctionRepository extends CrudRepository<QuestionTagsJunction, Integer> {

}
