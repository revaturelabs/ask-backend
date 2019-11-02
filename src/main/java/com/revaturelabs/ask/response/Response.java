package com.revaturelabs.ask.response;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.user.User;

/**
 * Holds the information about a response to a question. It holds the id of the responder, it's
 * title and body, as well as it's creation date.
 * 
 * @author Bryan Ritter, Chris Allen
 *
 */

@Entity
@Table(name = "responses")
@JsonDeserialize(using = ResponseJsonDeserializer.class)
public class Response {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "responder_id", nullable = false)
  private Integer responderId;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "creation_date", updatable = false)
  private Date creationDate;

  @Column(name = "question_id", nullable = false)
  private Integer questionId;

  @JsonIgnoreProperties({"responses", "user"})
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "question_id", insertable = false, updatable = false)
  private Question question;

  @JsonIgnoreProperties({"responses", "questions"})
  @JoinColumn(name = "responder_id", insertable = false, updatable = false)
  @ManyToOne(cascade = CascadeType.REFRESH)
  private User user;

  public Response() {

  }

  /**
   * Auto-generated getter for id.
   * 
   * @return an integer that is the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Auto-generated setter for id.
   * 
   * @param id the integer value to be set for the id
   */
  public void setId(Integer id) {
    this.id = id;
  }


  /**
   * Auto-generated getter for responder id.
   * 
   * @return an integer that is the responder id
   */
  public Integer getResponderId() {
    return this.responderId;
  }

  /**
   * Auto-generated setter for responder id.
   * 
   * @param responderId an Integer that is the responder's id
   */
  public void setResponderId(Integer responderId) {
    this.responderId = responderId;
  }

  /**
   * Auto-generated getter for the response body.
   * 
   * @return a String that contains the body of the response
   */
  public String getBody() {
    return this.body;
  }

  /**
   * Auto-generated setter for response body.
   * 
   * @param body a String that holds the response body content
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Auto-generated getter for creation date
   * 
   * @return a Date that is the object's creation time
   */
  public Date getCreationDate() {
    return this.creationDate;
  }


  /**
   * Auto-generated setter for creation date.
   * 
   * @param creationDate a Date to be set as the response's creation date
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Auto-generated getter for question id
   * 
   * @return an integer that is the source question id
   */
  public Integer getQuestionId() {
    return this.questionId;
  }

  /**
   * Auto-generated setter for question id.
   * 
   * @param questionId an integer that is the question ID to be set
   */
  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  /**
   * Auto-generated getter for the Question.
   * 
   * @return a Question object that is the response's question
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * Auto-generated setter for question
   * 
   * @param body a Question object that is the response's root question.
   */
  public void setQuestion(Question question) {
    this.question = question;
  }

  /**
   * Auto-generated getter for the user.
   * 
   * @return a User object that is the response's user
   */
  public User getUser() {
    return user;
  }

  /**
   * Auto-generated setter for question
   * 
   * @param body a User object that is the response's root user.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * 
   * Hashing function for response. Does NOT include question OR user attribute in hashing to avoid
   * potential infinite recursion problems (e.g., when attempting to serialize the responses within
   * a set, it will attempt to serialize the question or user. If the question or user hash attempts
   * to serialize its set of responses, an infinite loop will occur).
   */

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
    result = prime * result + ((responderId == null) ? 0 : responderId.hashCode());
    return result;
  }

  /**
   * Automatically generated equality function.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Response other = (Response) obj;
    if (body == null) {
      if (other.body != null)
        return false;
    } else if (!body.equals(other.body))
      return false;
    if (creationDate == null) {
      if (other.creationDate != null)
        return false;
    } else if (!creationDate.equals(other.creationDate))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (questionId == null) {
      if (other.questionId != null)
        return false;
    } else if (!questionId.equals(other.questionId))
      return false;
    if (responderId == null) {
      if (other.responderId != null)
        return false;
    } else if (!responderId.equals(other.responderId))
      return false;
    return true;
  }


  /**
   * Auto-generated toString method
   * 
   * @return String representation of Response
   */

  @Override
  public String toString() {
    return "Response [id=" + id + ", responderId=" + responderId + ", body=" + body
        + ", creationDate=" + creationDate + ", questionId=" + questionId + ", question=" + question
        + ", user=" + user + "]";
  }



}

