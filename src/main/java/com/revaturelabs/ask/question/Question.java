package com.revaturelabs.ask.question;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

/**
 * Question class to represent a question. It holds the id of the user who submitted the question,
 * body, head, creation date.
 * 
 * @author Roy L. Brow De Jesús
 *
 */
@Entity
@Table(name = "questions")
public class Question {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Integer id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "head")
  private String head;

  @Column(name = "body")
  private String body;

  @Column(name = "creation_date")
  @CreatedDate
  private Date creationDate;

  /**
   * Auto-generated setter for id
   * 
   * @return a Integer that holds the questions id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Auto-generated setter for id
   * 
   * @param id an integer that holds the questions id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Auto-generated getter for user_Id
   * 
   * @return a Integer that holds the id of the user who submitted the question
   */
  public Integer getUser_Id() {
    return userId;
  }

  /**
   * Auto-generated setter for user_Id
   * 
   * @param user_Id an integer that holds the id of the user who submitted the question
   */
  public void setUser_Id(Integer userId) {
    this.userId = userId;
  }

  /**
   * Auto-generated getter for question-head
   * 
   * @return a String that contains a header of the question
   */
  public String getHead() {
    return head;
  }

  /**
   * Auto-generated setter for question-head
   * 
   * @param head a String that holds the header of the question
   */
  public void setHead(String head) {
    this.head = head;
  }

  /**
   * Auto-generated getter for question-body
   * 
   * @return a String that contains the question body
   */
  public String getBody() {
    return body;
  }

  /**
   * Auto-generated setter for question-body
   * 
   * @param body a String that holds the questions body content
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Auto-generated getter for creation_date
   * 
   * @return a Date of the question when created
   */
  public Date getCreation_date() {
    return creationDate;
  }

  /**
   * Auto-generated setter for creation_date
   * 
   * @param creation_Date a Date type variable that holds the questions date of creation
   */
  public void setCreation_date(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
    result = prime * result + ((head == null) ? 0 : head.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Question other = (Question) obj;
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
    if (head == null) {
      if (other.head != null)
        return false;
    } else if (!head.equals(other.head))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }

  /**
   * Auto-generated ToString for question class
   * 
   * @return A string to represent the question class
   */
  @Override
  public String toString() {
    return "Question [id=" + id + ", userId=" + userId + ", head=" + head + ", body=" + body
        + ", creationDate=" + creationDate + "]";
  }


}
