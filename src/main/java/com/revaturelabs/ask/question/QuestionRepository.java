package com.revaturelabs.ask.question;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revaturelabs.ask.tag.Tag;

/**
 * A JPA repository for Questions. It has the default methods of a JpaRepository.
 * 
 * @author Roy L. Brow De Jesús, Chris Allen
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

  /**
   * Custom query that will return all questions provided that all the specified tags are present
   * 
   * @param tags The set of tags to be used
   * @param tagsSize The size of the set of tags. Is used to ensure that all tags are present
   * @return An optional set of questions with specified tags
   */
  @Query(
      value = "SELECT q FROM Question q join q.associatedTags t WHERE t in :tags GROUP BY q HAVING count(q) = :tagsSize")
  Page<Question> findAllContainingAllTags(@Param("tags") Set<Tag> tags, long tagsSize, Pageable pageable);

  /**
   * Custom query that will return all questions provided that at least one of the specified tags
   * 
   * @param tagSet The set of tags to be used for searching
   * @return An optional set of questions with at least one of the specified tags
   */
  @Query(value = "SELECT distinct q from Question q join q.associatedTags t where t in ?1")
  Page<Question> findAllContainingAtLeastOneTag(Set<Tag> tagSet, Pageable pageable);
}
