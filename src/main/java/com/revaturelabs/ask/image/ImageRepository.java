package com.revaturelabs.ask.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Images. Has default methods of JpaRepository
 * 
 * @author Cort Gerlach
 *
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
