package com.revaturelabs.ama;

public class AMASession {
  private int id;
  private int Date;
  private String Topic;
  private String Expert;

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
    return Date;
  }

  public void setDate(int date) {
    Date = date;
  }

  public String getTopic() {
    return Topic;
  }

  public void setTopic(String topic) {
    Topic = topic;
  }

  public String getExpert() {
    return Expert;
  }

  public void setExpert(String expert) {
    Expert = expert;
  }

  // generate toString
  @Override
  public String toString() {
    return "AMASession [id=" + id + ", Date=" + Date + ", Topic=" + Topic + ", Expert=" + Expert
        + "]";
  }


}
