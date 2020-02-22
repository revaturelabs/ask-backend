package com.revaturelabs.ask.ama;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.revaturelabs.ask.user.User;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @JoinColumn(name = "user_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private User user;
  
  @Column(name = "message_body")
  private String messageBody;
  
  @Column(name = "time_stamp")
  private Date timeStamp;
  
  @JoinColumn(name = "session_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private AMASession session;

  // java default constructor
  public MessageTable() {

    
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessageBody() {
    return messageBody;
  }

  public void setMessageBody(String messageBody) {
    this.messageBody = messageBody;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public AMASession getSession() {
    return session;
  }

  public void setSession(AMASession session) {
    this.session = session;
  }

  @Override
  public String toString() {
    return "MessageTable [id=" + id + ", user=" + user + ", messageBody=" + messageBody
        + ", timeStamp=" + timeStamp + ", session=" + session + "]";
  }


}
