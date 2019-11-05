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
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.tag.TagService;

/**
 * 
 * Custom JSON Deserializer for the question class. In addition to standard Question JSONs, will
 * handle other edge cases when data is submitted (e.g. in a form)
 * 
 * @author Chris Allen
 *
 */

public class QuestionJsonDeserializer extends JsonDeserializer<Question> {

  @Override
  /**
   * Overridden deserialize method. The most notable functionality added is the ability to convert a
   * passed-in string array of tag names into valid tag objects.
   * 
   * Currently, the deserializer does not explicitly require any fields be passed in to be
   * recognized as a valid JSON, so any arbitrary JSON passed in will be treated as a question JSON.
   * 
   * This is beneficial in the event that a front-end application wants to send a question object
   * without knowing all necessary details (e.g. the automatically generated ID of the question).
   * 
   * @author Chris Allen
   */
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

      if ((Integer) root.userId != null) {
        question.setQuestionerId(root.userId);
      }

      if (root.associatedTags != null) {
        question.setAssociatedTags(root.associatedTags);
      }
    }



    return question;

  }


  /**
   * 
   * Utility class for QuestionJsonDeserializer. Acts as the representation of the JSON that is
   * passed in to the application.
   * 
   * @author Chris Allen
   *
   */
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
    public int userId;

    @JsonProperty("associatedTags")
    public Set<Tag> associatedTags;
  }
}
