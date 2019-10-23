package com.revaturelabs.ask.tags;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

  @Autowired
  TagRepo tagRepo;

  @Override
  public List<Tag> getAll() {
    return (List<Tag>) tagRepo.findAll();
  }

  @Override
  public Tag getById(int i) throws TagNotFoundException {
    Optional<Tag> tag = tagRepo.findById(i);
    if (!tag.isPresent()) {
      throw new TagNotFoundException("Tag Not Found");
    }
    return tag.get();
  }

  @Override
  public Tag create(Tag tag) {
    return tagRepo.save(tag);

  }

  @Override
  public Tag update(Tag tag) {
    Optional<Tag> existingTag = tagRepo.findById(tag.getId());

    Tag updatedTag = null;
    if (existingTag.isPresent()) {
      try {
        updatedTag = tagRepo.save(tag);
      } catch (DataIntegrityViolationException e) {
        throw new TagConflictException("Tag name already exists");
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
        updatedTag = tagRepo.save(tag);
    } catch (DataIntegrityViolationException e) {
        throw new TagConflictException("Category name already exists");
    }

    return updatedTag;
  }

  @Override
  public void delete(int id) {
    boolean TagExists = tagRepo.existsById(id);

    if (!TagExists) {
        throw new TagNotFoundException("Unable to find Tag to delete");
    }

    tagRepo.deleteById(id);

  }

}
