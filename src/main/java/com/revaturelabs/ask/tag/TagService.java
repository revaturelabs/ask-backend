package com.revaturelabs.ask.tag;

import java.util.List;
import java.util.Set;

public interface TagService {
  List<Tag> getAll();

  Tag getById(int id) throws TagNotFoundException;

  Tag getTagByName(String name);

  Tag create(Tag tag);

  Tag update(Tag tag);

  Tag createOrUpdate(Tag tag);

  void delete(int id);

  Set<Tag> getValidTags(Set<Tag> associatedTags);
}
