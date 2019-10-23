package com.revaturelabs.ask.tags;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TagRepo extends CrudRepository<Tag, Integer> {

}
