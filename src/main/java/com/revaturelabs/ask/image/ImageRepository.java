package com.revaturelabs.ask.image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Images. Has default methods of JpaRepository
 * 
 * @author Cort Gerlach
 *
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}
