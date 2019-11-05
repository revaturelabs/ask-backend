package com.revaturelabs.ask.image;

import java.util.Arrays;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revaturelabs.ask.question.Question;

@Entity
@Table(name = "images")
public class Image {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "image")
  private byte[] image;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "question_id")
  @JsonIgnoreProperties({"images", "responses", "user", "associatedTags"})
  private Question question;


  public Image() {
    super();
  }

  public Image(int id, byte[] image, Question question) {
    super();
    this.id = id;
    this.image = image;
    this.question = question;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + Arrays.hashCode(image);
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
    Image other = (Image) obj;
    if (id != other.id)
      return false;
    if (!Arrays.equals(image, other.image))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Image [id=" + id + ", image=" + Arrays.toString(image) + ", question=" + question + "]";
  }
}
