package com.wohlgemuth.animalpictureapp.model;

import jakarta.persistence.*;

@Entity
public class AnimalPicture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String animalType;

  @Lob
  @Column(name = "image_data", columnDefinition = "BLOB")
  private byte[] imageData;


  public AnimalPicture(String animalType, byte[] imageData) {
    this.animalType = animalType;
    this.imageData = imageData;
  }

  public AnimalPicture() {}

  public Long getId() {
    return id;
  }

  public String getAnimalType() {
    return animalType;
  }

  public byte[] getImageData() {
    return imageData;
  }
}
