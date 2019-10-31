package com.revaturelabs.ask.user;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.revaturelabs.ask.tag.Tag;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column
  @GeneratedValue
  private int id;

  @Column
  private String username;

  @Column
  private String password;
  
  @Column(name = "expert")
  private boolean isExpert;
  
  @ManyToMany
  @JoinTable(name = "users_tags", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> userTags;
  
  public User() {
    super();
  }

  public User(int id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isExpert() {
    return isExpert;
  }

  public void setExpert(boolean isExpert) {
    this.isExpert = isExpert;
  }

  public Set<Tag> getUserTags() {
    return userTags;
  }

  public void setUserTags(Set<Tag> userTags) {
    this.userTags = userTags;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username 
        + ", password=" + password + ", subjects=" + userTags + "]";
  }



}
