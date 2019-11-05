package com.revaturelabs.ask.tag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Tags. It has the default methods of a JPA repository.
 *
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

}
