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
  public Response deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    Response response = new Response();
    Root root = p.readValueAs(Root.class);

    if (root != null) {
      if ((Integer) root.id != null) {
        response.setId(root.id);
      } else {
        response.setId(0);
      }
      if ((Integer) root.responderId != null) {
        response.setResponderId(root.responderId);
      } else {
        response.setResponderId(1);
      }
      if (root.body != null) {
        response.setBody(root.body);
      }
      if (root.questionId != null) {
        response.setQuestionId(root.questionId);
      } else {
        response.setQuestionId(1);
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
