package com.revaturelabs.ask.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Tags. It has the default methods of a JPA repository.
 *
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
