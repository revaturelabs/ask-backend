package com.revaturelabs.ask.response;

import java.io.IOException;
import java.util.List;
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
    
    if(root != null) {
      if((Integer) root.id != null) {
        response.setId(root.id);
      }
      if((Integer) root.responderId != null) {
        response.setResponderId(root.responderId);
      }
      if(root.body != null) {
        response.setBody(root.body);
      }
      if(root.creationDate != null) {
      }
    }
    return response;
  }


  private static class Root {

    @JsonProperty("id")
    public int id;
    
    @JsonProperty("responderId")
    public int responderId;
    
    @JsonProperty("body")
    public String body;
    
    @JsonProperty("creationDate")
    public String creationDate;
  }
}
