package com.revaturelabs.ask.question;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.revaturelabs.ask.tags.Tag;
import com.revaturelabs.ask.tags.TagService;

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
          tag.setTagName(tagName);
          tag.setId(0);
          question.addTagToQuestion(tag);
        }
      }

      if (root.title != null) {
        question.setHead(root.title);
      }
      if (root.body != null) {
        question.setBody(root.body);
      }
      question.setId(0);

      if ((Integer) root.userId != null) {
        question.setUserId(root.userId);
      }
    }



    return question;

  }


  private static class Root {

    @JsonProperty("tags")
    public List<String> tagList;

    @JsonProperty("title")
    public String title;

    @JsonProperty("body")
    public String body;

    @JsonProperty("userId")
    public int userId;
  }
}
