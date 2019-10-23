package com.revaturelabs.ask.question;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "questions")
public class Question {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Integer id;

  @Column(name = "user_Id")
  private Integer user_Id;

  @Column(name = "head")
  private String head;

  @Column(name = "body")
  private String body;

  @Column(name = "creation_date")
  @CreatedDate
  private Date creation_date;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUser_Id() {
    return user_Id;
  }

  public void setUser_Id(Integer user_Id) {
    this.user_Id = user_Id;
  }

  public String getHead() {
    return head;
  }

  public void setHead(String head) {
    this.head = head;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getCreation_date() {
    return creation_date;
  }

  public void setCreation_date(Date creation_date) {
    this.creation_date = creation_date;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((creation_date == null) ? 0 : creation_date.hashCode());
    result = prime * result + ((head == null) ? 0 : head.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((user_Id == null) ? 0 : user_Id.hashCode());
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
    if (creation_date == null) {
      if (other.creation_date != null)
        return false;
    } else if (!creation_date.equals(other.creation_date))
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
    if (user_Id == null) {
      if (other.user_Id != null)
        return false;
    } else if (!user_Id.equals(other.user_Id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Question [id=" + id + ", user_Id=" + user_Id + ", head=" + head + ", body=" + body
        + ", creation_date=" + creation_date + "]";
  }


}
