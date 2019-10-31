package com.revaturelabs.ask.response;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Holds the information about a response to a question. It holds the id of the responder, it's
 * title and body, as well as it's creation date.
 * 
 * @author Bryan Ritter, Chris Allen
 *
 */

@Entity
@Table(name = "responses")
@JsonDeserialize(using = ResponseJSonDeserializer.class)
public class Response {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private int id;

  @Column(name = "responder_id")
  private Integer responderId;

  @Column(name = "body")
  private String body;

  @Column(name = "creation_date", updatable = false)
  private Date creationDate;

  @Column(name = "question_id")
  private Integer questionId;

  public Response() {

  }

  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the responderId
   */
  public Integer getResponderId() {
    return this.responderId;
  }

  /**
   * @param responderId the responderId to set
   */
  public void setResponderId(Integer responderId) {
    this.responderId = responderId;
  }

  /**
   * @return the body
   */
  public String getBody() {
    return this.body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * @return the creationDate
   */
  public Date getCreationDate() {
    return this.creationDate;
  }

  /**
   * @param creationDate the creationDate to set
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * @return the questionId
   */
  public Integer getQuestionId() {
    return this.questionId;
  }

  /**
   * @param questionId the questionId to set
   */
  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  @Override
  public String toString() {
    return "Response [id=" + this.id + ", responderId=" + this.responderId + ", body=" + this.body
        + ", creationDate=" + this.creationDate + ", questionId=" + this.questionId + "]";
  }

}

