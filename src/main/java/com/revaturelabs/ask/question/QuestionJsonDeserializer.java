package com.revaturelabs.ask.question;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.revaturelabs.ask.tags.Tag;

public class QuestionJsonDeserializer extends JsonDeserializer<Question> {

  @Override
  public Question deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {

    Root root = p.readValueAs(Root.class);


    Question question = new Question();
    if (root != null) {
      if (root.tagList != null) {
        for (String tagName : root.tagList) {
          Tag tag = new Tag();
          tag.setName(tagName);
          tag.setId(0);
          question.addTagToQuestion(tag);
        }
      }

      if (root.head != null) {
        question.setHead(root.head);
      }
      if (root.body != null) {
        question.setBody(root.body);
      }
      question.setId(0);

      if (root.userId != null) {
        question.setQuestionerId(root.userId);
      }
      

      if (root.associatedTags != null) {
        question.setAssociatedTags(root.associatedTags);
      }

      if (root.creation_date != null) {
        try {
          Date currentDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(root.creation_date);
          question.setCreationDate(currentDate);
        } catch (ParseException e) {
        }
      } else {
        Date currentDate = new Date();
        question.setCreationDate(currentDate);
      }
    }



    return question;

  }


  private static class Root {

    @JsonProperty("tagList")
    public List<String> tagList;

    @JsonProperty("creation_date")
    public String creation_date;

    @JsonProperty("head")
    public String head;

    @JsonProperty("body")
    public String body;

    @JsonProperty("userId")
    public Integer userId;

    @JsonProperty("associatedTags")
    public Set<Tag> associatedTags;
  }
}
