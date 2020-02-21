package com.revaturelabs.ask.ama;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Bianca/Updates to follow
 *
 */
@Entity
@Table(name = "MessageTable")
public class MessageTable {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "user_id")
  private int user_id;
  @Column(name = "message_body")
  private String message_body;
  @ManyToOne
  @Column(name = "AMA_id")
  private int AMA_id;
  @Column(name = "time_stamp")
  private Date time_stamp;

  // java default constructor
  public MessageTable() {

  }

  // generate getters and setters.
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getMessage_body() {
    return message_body;
  }

  public void setMessage_body(String message_body) {
    this.message_body = message_body;
  }

  public Date getTime_stamp() {
    return time_stamp;
  }

  public void setTime_stamp(Date time_stamp) {
    this.time_stamp = time_stamp;
  }

  public int getAMA_id() {
    return AMA_id;
  }

  public void setAMA_id(int aMA_id) {
    AMA_id = aMA_id;
  }

  // generate toString
  @Override
  public String toString() {
    return "MessageTable [id=" + id + ", user_id=" + user_id + ", message_body=" + message_body
        + ", AMA_id=" + AMA_id + ", time_stamp=" + time_stamp + "]";
  }


}
