package com.revaturelabs.ask.question;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuestionRepo extends CrudRepository<Question, Integer> {

}
