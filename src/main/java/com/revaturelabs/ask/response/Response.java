package com.revaturelabs.ask.response;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="responses")
public class Response {

  @Id
  @Column(name = "id")
  @GeneratedValue
  private int id;
  
  @Column(name = "responder_id")
  private Integer responderId;
  
  @Column(name = "title")
  private String title;
  
  @Column(name = "body")
  private String body;
  
  @Column(name = "creation_date", updatable = false)
  @CreatedDate
  private Date creation_date;

//  IDEA for later iteration... also store/use last edited date
//  @Column(name = "updated_on")
//  @LastModifiedDate
//  private Date updatedOn;

  public Response() {
    
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getPostStamp() {
    return this.creation_date;
  }
  
  public Integer getUserId() {
    return this.responderId;
  }

  public void setUserId(Integer userId) {
    this.responderId = userId;
  }

  public void setPostStamp(Date postStamp) {
    this.creation_date = postStamp;
  }

  @Override
  public String toString() {
    return "Response [id=" + this.id + ", title=" + this.title + ", body=" + this.body + ", postStamp=" + this.creation_date
        + "]";
  }

}

