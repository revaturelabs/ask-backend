package com.revaturelabs.ask.image;

import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image {
  
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "image")
  private byte[] image;

  public Image() {
    super();
  }

  public Image(int id, byte[] image) {
    super();
    this.id = id;
    this.image = image;
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
    return "Image [id=" + id + ", image=" + Arrays.toString(image) + "]";
  }


}
