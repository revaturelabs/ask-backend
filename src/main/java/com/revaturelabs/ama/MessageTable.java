package com.revaturelabs.ama;

public class MessageTable {
  private int id;
  private int user_id;
  private String message_body;
  private String time_stamp;

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

  public String getTime_stamp() {
    return time_stamp;
  }

  public void setTime_stamp(String time_stamp) {
    this.time_stamp = time_stamp;
  }

  // generate toString
  @Override
  public String toString() {
    return "MessageTable [id=" + id + ", user_id=" + user_id + ", message_body=" + message_body
        + ", time_stamp=" + time_stamp + "]";
  }

}
