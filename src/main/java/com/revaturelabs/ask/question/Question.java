package com.revaturelabs.ask.question;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.revaturelabs.ask.image.Image;
import com.revaturelabs.ask.response.Response;
import com.revaturelabs.ask.tag.Tag;
import com.revaturelabs.ask.user.User;

/**
 * Question class to represent a question. It holds the id of the user who submitted the question,
 * body, head, creation date.
 * 
 * @author Roy L. Brow De Jesús, Chris Allen
 *
 */
@Entity
@Table(name = "questions")
@JsonDeserialize(using = QuestionJsonDeserializer.class)
public class Question {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "questioner_id", nullable = false)
  private Integer questionerId;

  @Column(name = "highlighted_response_id")
  private Integer highlightedResponseId;

  @Column(name = "head", nullable = false)
  private String head;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "creation_date", nullable = false)
  private Date creationDate;

  @ManyToMany
  @JoinTable(name = "questions_tags", joinColumns = @JoinColumn(name = "question_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> associatedTags;

  @JsonIgnoreProperties({"question", "user"})
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private Set<Response> responses;

  @JoinColumn(name = "questioner_id", insertable = false, updatable = false)
  @ManyToOne(cascade = CascadeType.REFRESH)
  @JsonIgnoreProperties({"questions", "responses"})
  private User user;

  @OneToMany(mappedBy = "question")
  @JsonIgnoreProperties({"question", "image"})
  private Set<Image> images;

  /**
   * Auto-generated setter for id.
   * 
   * @return a Integer that holds the questions id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Auto-generated setter for id.
   * 
   * @param id an integer that holds the questions id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  public Set<Tag> getAssociatedTags() {
    return associatedTags;
  }

  public void setAssociatedTags(Set<Tag> associatedTags) {
    this.associatedTags = associatedTags;
  }

  /**
   * Auto-generated getter for questionerId.
   * 
   * @return a Integer that holds the id of the user who submitted the question
   */
  public Integer getQuestionerId() {
    return questionerId;
  }

  /**
   * Auto-generated setter for questionerId.
   * 
   * @param questionerId an integer that holds the id of the user who submitted the question
   */
  public void setQuestionerId(Integer questionerId) {
    this.questionerId = questionerId;
  }

  /**
   * Auto-generated getter for highlightedResponseId.
   * 
   * @return the id of the response highlighted as correct by the questioner
   */
  public Integer getHighlightedResponseId() {
    return highlightedResponseId;
  }

  /**
   * Auto-generated setter for highlightedResponseId.
   * 
   * @param highlightedResponseId the id of a response to highlight for this question
   */
  public void setHighlightedResponseId(Integer highlightedResponseId) {
    this.highlightedResponseId = highlightedResponseId;
  }

  /**
   * Auto-generated getter for question-head.
   * 
   * @return a String that contains a header of the question
   */
  public String getHead() {
    return head;
  }

  /**
   * Auto-generated setter for question-head.
   * 
   * @param head a String that holds the header of the question
   */
  public void setHead(String head) {
    this.head = head;
  }

  /**
   * Auto-generated getter for question-body.
   * 
   * @return a String that contains the question body
   */
  public String getBody() {
    return body;
  }

  /**
   * Auto-generated setter for question-body.
   * 
   * @param body a String that holds the questions body content
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Auto-generated getter for creationDate.
   * 
   * @return a Date of the question when created
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Auto-generated setter for creationDate.
   * 
   * @param creationDate a Date type variable that holds the questions date of creation
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }


  /**
   * Auto-generated getter for user.
   * 
   * @return a User object that is the question's user
   */
  public User getUser() {
    return user;
  }

  /**
   * Auto-generated setter for user.
   * 
   * @param the user to be treated as the question's user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Add a tag to the associated tags set for this question and creates the set if necessary.
   * 
   * @param tag the tag to add
   */
  public void addTagToQuestion(Tag tag) {
    if (this.associatedTags == null) {
      this.associatedTags = new HashSet<Tag>();
    }
    associatedTags.add(tag);
  }

  /**
   * Auto-generated getter for responses
   * 
   * @return a set of responses for the question
   */
  public Set<Response> getResponses() {
    return responses;
  }

  /**
   * Auto-generated setter for responses
   * 
   * @param The set of responses to be used
   */
  public void setResponses(Set<Response> responses) {
    this.responses = responses;
  }

  /**
   * Auto-generated getter for images
   * 
   * @return a set of images for the question
   */
  public Set<Image> getImages() {
    return images;
  }

  /**
   * Auto-generated setter for images
   * 
   * @param The set of images to be used
   */
  public void setImages(Set<Image> images) {
    this.images = images;
  }

  /**
   * Add an image to the images set for this question and creates the set if necessary.
   * 
   * @param image the image to add
   */
  public void addImageToImages(Image image) {
    if(this.images == null) {
      this.images = new HashSet<Image>();
    }
    this.images.add(image);
  }
  
  /**
   * 
   * Automatically generated hashing function for question.
   * 
   * Note that, if used in conjunction with responses, images or users, if the response, image or
   * user hash function includes questions, there may be an infinite recursion problem. This
   * currently assumes that Response and Image do NOT use their question attribute in their hashing
   * function and that user DOES use its question attribute in hashing.
   * 
   */

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((associatedTags == null) ? 0 : associatedTags.hashCode());
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
    result = prime * result + ((head == null) ? 0 : head.hashCode());
    result =
        prime * result + ((highlightedResponseId == null) ? 0 : highlightedResponseId.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((images == null) ? 0 : images.hashCode());
    result = prime * result + ((questionerId == null) ? 0 : questionerId.hashCode());
    result = prime * result + ((responses == null) ? 0 : responses.hashCode());
    return result;
  }

  /**
   * 
   * Automatically generated equals function
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
    Question other = (Question) obj;
    if (associatedTags == null) {
      if (other.associatedTags != null)
        return false;
    } else if (!associatedTags.equals(other.associatedTags))
      return false;
    if (body == null) {
      if (other.body != null)
        return false;
    } else if (!body.equals(other.body))
      return false;
    if (creationDate == null) {
      if (other.creationDate != null)
        return false;
    } else if (!creationDate.equals(other.creationDate))
      return false;
    if (head == null) {
      if (other.head != null)
        return false;
    } else if (!head.equals(other.head))
      return false;
    if (highlightedResponseId == null) {
      if (other.highlightedResponseId != null)
        return false;
    } else if (!highlightedResponseId.equals(other.highlightedResponseId))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (images == null) {
      if (other.images != null)
        return false;
    } else if (!images.equals(other.images))
      return false;
    if (questionerId == null) {
      if (other.questionerId != null)
        return false;
    } else if (!questionerId.equals(other.questionerId))
      return false;
    if (responses == null) {
      if (other.responses != null)
        return false;
    } else if (!responses.equals(other.responses))
      return false;
    return true;
  }

  /**
   * 
   * Auto-generated toString method.
   * 
   */
  @Override
  public String toString() {
    return "Question [id=" + id + ", questionerId=" + questionerId + ", highlightedResponseId="
        + highlightedResponseId + ", head=" + head + ", body=" + body + ", creationDate="
        + creationDate + ", associatedTags=" + associatedTags + ", responses=" + responses
        + ", user=" + user + ", images=" + images + "]";
  }



}
