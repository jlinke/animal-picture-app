package com.wohlgemuth.animalpictureapp.service;

import com.wohlgemuth.animalpictureapp.model.AnimalPicture;
import com.wohlgemuth.animalpictureapp.repository.AnimalPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

@Service
  public class AnimalPictureService {

    @Autowired
    private AnimalPictureRepository animalPictureRepository;

    private static final String CAT_API = "https://placecats.com/400/300";
    private static final String DOG_API = "https://place.dog/400/300";
    private static final String BEAR_API = "https://placebear.com/400/300";

    public void fetchAndSavePicture(String animalType, int amount) throws Exception {
      for (int i = 0; i < amount; i++) {
        String apiUrl = getApiUrlForAnimal(animalType);
        AnimalPicture animalPicture = new AnimalPicture(animalType, downloadImageAsBytes(apiUrl));
        animalPictureRepository.save(animalPicture);
      }
    }

    public AnimalPicture getLastPicture(String animalType) {
      return animalPictureRepository.findFirstByAnimalTypeOrderByIdDesc(animalType);
    }

    private String getApiUrlForAnimal(String animalType) {
      switch (animalType.toLowerCase()) {
        case "cat":
          return CAT_API;
        case "dog":
          return DOG_API;
        case "bear":
          return BEAR_API;
        default:
          throw new IllegalArgumentException("Unknown animal type: " + animalType);
      }
    }

  private byte[] downloadImageAsBytes(String imageUrl) throws Exception {
    URL url = new URL(imageUrl);
    try (InputStream inputStream = url.openStream()) {
      return inputStream.readAllBytes();
    }
  }


}
