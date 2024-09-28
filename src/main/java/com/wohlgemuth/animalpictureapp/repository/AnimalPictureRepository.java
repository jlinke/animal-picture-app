package com.wohlgemuth.animalpictureapp.repository;

import com.wohlgemuth.animalpictureapp.model.AnimalPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalPictureRepository extends JpaRepository<AnimalPicture, Long> {

  AnimalPicture findFirstByAnimalTypeOrderByIdDesc(String animalType);

}
