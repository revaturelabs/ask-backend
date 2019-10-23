package com.revaturelabs.ask.tags;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
  
 @Autowired
 TagRepo tagRepo; 
 
  @Override
  public List<Tag> getAll() {
    return (List<Tag>)tagRepo.findAll();
  }

  @Override
  public Tag getById(int i) throws TagNotFoundException {
    Optional<Tag> tag= tagRepo.findById(i);
    if (!tag.isPresent()) {
      throw new TagNotFoundException("Tag Not Found");
    }
    return tag.get();
  }

//  @Override
//  public Tag create(Tag tag) {
//    //return TagRepo.save(tag);
//    return null;
//  }
//
//  @Override
//  public Tag update(Tag tag) {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//  @Override
//  public Tag createOrUpdate(Tag tag) {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//  @Override
//  public void delete(int id) {
//    // TODO Auto-generated method stub
//
//  }

}
