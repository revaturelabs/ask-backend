package com.revaturelabs.ask.response;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

/**
 * Holds the information about a response to a question. It holds the id of the responder, it's
 * title and body, as well as it's creation date.
 * 
 * @author Bryan Ritter
 *
 */

@Entity
@Table(name = "responses")
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
  @CreatedDate
  private Date creationDate;

  @Column(name = "Question_id")
  private Integer questionId;

  public Response() {

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

  /**
   * Auto-generated getter for id.
   * 
   * @return the response's id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Auto-generated setter for id.
   * 
   * @param id An Integer of the response's id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Auto-generated getter for the responder's id
   * 
   * @return the responderId
   */
  public Integer getResponderId() {
    return this.responderId;
  }

  /**
   * Auto-generated setter for the responder's id
   * 
   * @param responderId the responderId to set
   */
  public void setResponderId(Integer responderId) {
    this.responderId = responderId;
  }

  /**
   * Auto-generated getter for the body's text
   * 
   * @return the body
   */
  public String getBody() {
    return this.body;
  }

  /**
   * Auto-generated setter for the body's text
   * 
   * @param body the body to set
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Auto-generated getter for the creation date
   * 
   * @return the creationDate
   */
  public Date getCreationDate() {
    return this.creationDate;
  }

  /**
   * Auto-generated setter for the creation date
   * 
   * @param creationDate the creationDate to set
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Auto-generated toString
   */
  @Override
  public String toString() {
    return "Response [id=" + this.id + ", responderId=" + this.responderId + ", body=" + this.body
        + ", creationDate=" + this.creationDate + ", questionId=" + this.questionId + "]";
  }

}

