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

/**
 * Image class for use within the database. Contains a relation to a question object, the id of the
 * image in the database, and the bytes for the image.
 * 
 * @author Chris Allen, Cort Gerlach
 *
 */
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

  /**
   * Auto-generated getter for id.
   * 
   * @return The id of the image
   */
  public int getId() {
    return id;
  }

  /**
   * Auto-generated setter for id.
   * 
   * @param id The id to be set for the image
   */
  public void setId(int id) {
    this.id = id;
  }


  /**
   * Auto-generated getter for the image byte array.
   * 
   * @return image The byte array of the image.
   */
  public byte[] getImage() {
    return image;
  }

  /**
   * Auto-generated setter for the byte array of images.
   * 
   * @param image The byte array of the image to be set
   */
  public void setImage(byte[] image) {
    this.image = image;
  }

  /**
   * Auto-generated getter for question
   *
   * @return question The question object associated with the image.
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * Auto-generated setter for question
   * 
   * @param question The question object to be associated with the image.
   */
  public void setQuestion(Question question) {
    this.question = question;
  }

  /**
   * Hashing function for images.
   * 
   * Does NOT include question to avoid an infinite recursion problem.
   * 
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + Arrays.hashCode(image);
    return result;
  }

  /**
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
    Image other = (Image) obj;
    if (id != other.id)
      return false;
    if (!Arrays.equals(image, other.image))
      return false;
    return true;
  }

  /**
   * Auto-generated toString function
   * 
   * @return The string format of the object
   * 
   */
  @Override
  public String toString() {
    return "Image [id=" + id + ", image=" + Arrays.toString(image) + ", question=" + question + "]";
  }
}
