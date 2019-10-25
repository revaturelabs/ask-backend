package com.revaturelabs.ask.tags;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service class for managing tags. It has the methods for obtaining a list of tags and getting an
 * individual tag as well as methods for creating, updating and deleting tags.
 *
 */
@Service
public class TagServiceImpl implements TagService {

  @Autowired
  TagRepository tagRepository;

  /**
   * Returns a list of tags in a database.
   * 
   * @return a List<Tag> of tags
   */
  @Override
  public List<Tag> getAll() {
    return (List<Tag>) tagRepository.findAll();
  }

  /**
   * Takes in a string and returns the Tab matching that string.
   * 
   * @param String name -The tag name of the tag being searched for
   * @return Tag tag - Returns the tag matching the string.
   * 
   */
  @Override
  public Tag getTagByName(String name) {
    Tag tag = null;
    List<Tag> list = (List<Tag>) tagRepository.findAll();
    for (Tag t : list) {
      if (t.getTagName().contentEquals(name)) {
        tag = t;
      }
    }
    if (tag.equals(null)) {
      throw new TagNotFoundException("Tag Not Found");
    }
    return tag;
  }

  /**
   * Returns an individual tag in a database.
   * 
   * @return Tag for a specific id.
   */
  @Override
  public Tag getById(int id) throws TagNotFoundException {
    Optional<Tag> tag = tagRepository.findById(id);
    if (!tag.isPresent()) {
      throw new TagNotFoundException("Tag Not Found");
    }
    return tag.get();
  }

  /**
   * Adds a new tag to the database.
   * 
   * @param tag - new tag to be created.
   * @return tag
   */
  @Override
  public Tag create(Tag tag) {
    return tagRepository.save(tag);
  }

  /**
   * Takes a Tag and updates the tag in a database if that tag id exists.
   * 
   * @param tag
   * @return tag
   */
  @Override
  public Tag update(Tag tag) {
    Optional<Tag> existingTag = tagRepository.findById(tag.getId());

    Tag updatedTag = null;
    if (existingTag.isPresent()) {
      try {
        updatedTag = tagRepository.save(tag);
      } catch (DataIntegrityViolationException e) {
        throw new TagConflictException("Tag fails to satisfy constraints");
      }
    } else {
      throw new TagNotFoundException("Unable to find tag to update");
    }
    return updatedTag;
  }

  /**
   * Takes in a tag creates or updates it if it exists in the database.
   * 
   * @param tag - New tag to be created.
   * @return tag - created/updated tag or throws an exception.
   */
  @Override
  public Tag createOrUpdate(Tag tag) {
    Tag updatedTag = null;
    try {
      updatedTag = tagRepository.save(tag);
    } catch (DataIntegrityViolationException e) {
      throw new TagConflictException("Tag fails to satisfy constraints");
    }
    return updatedTag;
  }

  /**
   * Deletes a tag with a specific id.
   * 
   * @param id -Id of tag to be deleted.
   */
  @Override
  public void delete(int id) {
    boolean tagExists = tagRepository.existsById(id);
    if (!tagExists) {
      throw new TagNotFoundException("Unable to find Tag to delete");
    }
    tagRepository.deleteById(id);
  }

}
