package com.revaturelabs.ask.questionTagsJunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revaturelabs.ask.tags.Tag;
import com.revaturelabs.ask.tags.TagService;
import com.revaturelabs.ask.tags.TagServiceImpl;

@Service
public class QuestionTagsJunctionServiceImp implements QuestionTagsJunctionService {
  
  @Autowired
  QuestionTagsJunctionRepository questiontagsjunctionrepository;

  @Override
  public void create(int questionId, String [] tagName) {
    QuestionTagsJunction questiontagsjunction = null;
    TagService tagservice = new TagServiceImpl();
    Tag tag = null;
    
    for (String string : tagName) {
      
      questiontagsjunction.setId(0);
      questiontagsjunction.setQuestionId(questionId);
      tag = tagservice.getTagByName(string);
      questiontagsjunction.setTagId(tag.getId());
      
      questiontagsjunctionrepository.save(questiontagsjunction);
    }
    
   
  }
}
