package com.revaturelabs.ask.tags;

import java.util.List;

public interface TagService {
  List<Tag> getAll();

  Tag getById(int id) throws TagNotFoundException;

  Tag getTagByName(String name);

  Tag create(Tag tag);

  Tag update(Tag tag);

  Tag createOrUpdate(Tag tag);

  void delete(int id);
}
