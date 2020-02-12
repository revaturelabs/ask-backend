package com.revaturelabs.ask.user;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.tag.Tag;


/**
 * 
 * @author Carlos Santos, Chris Allen
 *
 */
@Entity
@Table(name = "users")
@JsonDeserialize(using = UserJsonDeserializer.class)
public class User {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "username")
  private String username;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "expert")
  private boolean isExpert;
  
  @Column(name = "picture")
  private String picture;

  @ManyToMany
  @JoinTable(name = "users_tags", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> expertTags;


  @JsonIgnoreProperties({"user", "responses", "associatedTags", "images"})
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Question> questions;

  @JsonIgnoreProperties({"user", "question"})
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Response> responses;

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

  public Set<Tag> getExpertTags() {
    return expertTags;
  }

  public void setExpertTags(Set<Tag> expertTags) {
    this.expertTags = expertTags;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  /**
   * Auto-generated getter for questions.
   * 
   * @return Returns a set of questions
   */
  public Set<Question> getQuestions() {
    return questions;
  }

  /**
   * Auto-generated setter for questions.
   * 
   * @param questions A set of questions to be set for the questions attribute
   */
  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

  /**
   * 
   * Method for adding tags to the set of user tags.
   * 
   * @param tag The tag to be added to the set.
   */
  public void addTagToUser(Tag tag) {
    if(this.expertTags == null) {
      this.expertTags = new HashSet<Tag>();
    }
    this.expertTags.add(tag);
  }

  /**
   * Auto-generated getter for responses.
   * 
   * @return Returns a set of responses
   */
  public Set<Response> getResponses() {
    return responses;
  }

  /**
   * Auto-generated setter for responses.
   * 
   * @param questions A set of responses to be set for the responses attribute
   */
  public void setResponses(Set<Response> responses) {
    this.responses = responses;
  }

  /**
   * 
   * Hashing function for response. DOES include questions and responses attributes in hashing
   * function. If the questions or the responses hashing function is changed to include its User,
   * there may be an infinite recursion error if a corresponding change is not made on the User
   * object.
   */


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((expertTags == null) ? 0 : expertTags.hashCode());
    result = prime * result + id;
    result = prime * result + (isExpert ? 1231 : 1237);
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((questions == null) ? 0 : questions.hashCode());
    result = prime * result + ((responses == null) ? 0 : responses.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  /**
   * 
   * Auto-generated equals function
   * 
   * @param obj The object to be compared
   * 
   */

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (expertTags == null) {
      if (other.expertTags != null)
        return false;
    } else if (!expertTags.equals(other.expertTags))
      return false;
    if (id != other.id)
      return false;
    if (isExpert != other.isExpert)
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (questions == null) {
      if (other.questions != null)
        return false;
    } else if (!questions.equals(other.questions))
      return false;
    if (responses == null) {
      if (other.responses != null)
        return false;
    } else if (!responses.equals(other.responses))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

  /**
   * 
   * Automatically generated toString method.
   * 
   * @return A string representation of the User object
   * 
   */

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", isExpert="
        + isExpert + ", expertTags=" + expertTags + ", questions=" + questions + ", responses="
        + responses + "]";
  }



}
