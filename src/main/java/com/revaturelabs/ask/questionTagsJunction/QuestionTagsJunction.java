package com.revaturelabs.ask.questionTagsJunction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_tags_junction")
public class QuestionTagsJunction {
  
  @Id
  @Column(name = "id")
  @GeneratedValue
  private Integer id;
  
  @Column(name = "question_id")
  private Integer questionId;
  
  @Column(name = "tag_id")
  private Integer tagId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public Integer getTagId() {
    return tagId;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
    result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    QuestionTagsJunction other = (QuestionTagsJunction) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (questionId == null) {
      if (other.questionId != null)
        return false;
    } else if (!questionId.equals(other.questionId))
      return false;
    if (tagId == null) {
      if (other.tagId != null)
        return false;
    } else if (!tagId.equals(other.tagId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "QuestionTagsJunction [id=" + id + ", questionId=" + questionId + ", tagId=" + tagId
        + "]";
  }
  
  
}
