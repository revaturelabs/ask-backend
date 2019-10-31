package com.revaturelabs.ask.response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ResponseJSonDeserializer extends JsonDeserializer<Response> {

  @Override
  /**
   * Overridden deserialize method.
   * 
   * Currently, the deserializer does not explicitly require any fields be passed in to be
   * recognized as a valid JSON, so any arbitrary JSON passed in will be treated as a response JSON.
   * 
   * This is beneficial in the event that a front-end application wants to send a response object
   * without knowing all necessary details (e.g. the automatically generated ID of the response).
   * 
   * @author Chris Allen
   */
  public Response deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    Response response = new Response();
    Root root = p.readValueAs(Root.class);

    if (root != null) {
      if (root.id != null) {
        response.setId(root.id);
      } else {
        response.setId(0);
      }
      if ((Integer) root.responderId != null) {
        response.setResponderId(root.responderId);
      }
      if (root.body != null) {
        response.setBody(root.body);
      }
      if (root.questionId != null) {
        response.setQuestionId(root.questionId);
      }
      if (root.creationDate != null) {
        try {
          Date currentDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(root.creationDate);
          response.setCreationDate(currentDate);
        } catch (ParseException e) {
          
        }
      } else {
        Date currentDate = new Date();
        response.setCreationDate(currentDate);
      }
    }
    return response;
  }

  /**
   * 
   * Utility class for ResponseJsonDeserializer. Acts as the representation of the JSON that is
   * passed in to the application.
   * 
   * @author Chris Allen
   *
   */
  private static class Root {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("responderId")
    public Integer responderId;
    
    @JsonProperty("questionId")
    public Integer questionId;


    @JsonProperty("body")
    public String body;

    @JsonProperty("creationDate")
    public String creationDate;
  }
}
