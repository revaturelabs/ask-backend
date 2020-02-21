package com.revaturelabs.ask.ama;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AMASession")
public class AMASession {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "date")
  private int date;
  @ManyToMany
  @Column(name = "topic")
  private String topic;
  @OneToMany(mappedBy = "AMASession")
  @ManyToMany
  @Column(name = "expert")
  private String expert;

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

  public int getDate() {
    return date;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getExpert() {
    return expert;
  }

  public void setExpert(String expert) {
    this.expert = expert;
  }

  @Override
  public String toString() {
    return "AMASession [id=" + id + ", date=" + date + ", topic=" + topic + ", expert=" + expert
        + "]";
  }

  // generate toString

}
