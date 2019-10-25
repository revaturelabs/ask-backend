package com.revaturelabs.ask.tags;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

  @Autowired
  TagRepository tagRepository;

  @Override
  public List<Tag> getAll() {
    return (List<Tag>) tagRepository.findAll();
  }

  @Override
  public Tag getById(int id) throws TagNotFoundException {
    Optional<Tag> tag = tagRepository.findById(id);
    if (!tag.isPresent()) {
      throw new TagNotFoundException("Tag Not Found");
    }
    return tag.get();
  }

  @Override
  public Tag create(Tag tag) {
    return tagRepository.save(tag);
  }

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

  @Override
  public void delete(int id) {
    boolean TagExists = tagRepository.existsById(id);

    if (!TagExists) {
      throw new TagNotFoundException("Unable to find Tag to delete");
    }
    tagRepository.deleteById(id);
  }

}
