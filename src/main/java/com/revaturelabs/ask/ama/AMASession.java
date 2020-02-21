package com.revaturelabs.ask.ama;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.user.User;

@Entity
@Table(name = "AMASession")
public class AMASession {
  @Id
  @Column(name = "id")
  private int id;
  
  @Column(name = "date")
  private Date date;
  
  @JoinColumn(name="topic_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private Tag topic;
  
  @JoinColumn(name="expert_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private User expert;

  // java default constructor
  public AMASession() {

  }

  // generate getters and setters.

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Tag getTopic() {
    return topic;
  }

  public void setTopic(Tag topic) {
    this.topic = topic;
  }

  public User getExpert() {
    return expert;
  }

  public void setExpert(User expert) {
    this.expert = expert;
  }

  @Override
  public String toString() {
    return "AMASession [id=" + id + ", date=" + date + ", topic=" + topic + ", expert=" + expert
        + "]";
  }

  // generate toString

}
