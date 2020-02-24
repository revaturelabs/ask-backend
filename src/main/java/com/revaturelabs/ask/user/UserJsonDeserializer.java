package com.revaturelabs.ask.user;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.tag.Tag;

public class UserJsonDeserializer extends JsonDeserializer<User> {

  /**
   * Overridden deserialize method. The most notable functionality added is the ability to convert a
   * passed-in string array of tag names into valid tag objects.
   * 
   * Currently, the deserializer does not explicitly require any fields be passed in to be
   * recognized as a valid JSON, so any arbitrary JSON passed in will be treated as a user JSON.
   * 
   * This is beneficial in the event that a front-end application wants to send a user object
   * without knowing all necessary details (e.g. the automatically generated ID of the user).
   * 
   * @author Chris Allen
   */
  @Override
  public User deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    User user = new User();
    Root root = p.readValueAs(Root.class);

    if (root != null) {
      if (root.id != null) {
        user.setId(root.id);
      } else {
        user.setId(0);
      }
      if (root.username != null) {
        user.setUsername(root.username);
      }
      if (root.password != null) {
        user.setPassword(root.password);
      }
      if (root.email != null) {
        user.setEmail(root.email);
      }
      if (root.bio != null) {
        user.setBio(root.bio);
      }
      if (root.expertTags != null) {
        user.setExpertTags(root.expertTags);
      }

      if (root.expertTagList != null) {
        for (String tagName : root.expertTagList) {
          Tag tag = new Tag();
          tag.setName(tagName);
          tag.setId(0);
          user.addTagToUser(tag);
        }
      }

      if (root.questions != null) {
        user.setQuestions(root.questions);
      }

      if ((Boolean) root.expert != null) {
        user.setExpert(root.expert);
      }
      
      if (root.profilePic != null) {
        user.setProfilePic(root.profilePic);
      }
    }
    return user;
  }

  /**
   * Utility class for the custom User JSON deserializer.
   * 
   * @author Chris Allen
   *
   */
  private static class Root {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
    
    @JsonProperty("email")
    public String email;
    
    @JsonProperty("bio")
    public String bio;
    
    @JsonProperty("profilePic")
    public String profilePic;

    @JsonProperty("expertTags")
    public Set<Tag> expertTags;

    @JsonProperty("expertTagList")
    public List<String> expertTagList;

    @JsonProperty("questions")
    public Set<Question> questions;

    @JsonProperty("expert")
    public boolean expert;
  }
}

