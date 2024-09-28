package com.wohlgemuth.animalpictureapp.rest;


import com.wohlgemuth.animalpictureapp.model.AnimalPicture;
import com.wohlgemuth.animalpictureapp.service.AnimalPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "*")
public class AnimalPictureController {

  @Autowired
  private AnimalPictureService animalPictureService;

  @PostMapping("/fetch")
  public ResponseEntity<String> fetchAndSavePicture(@RequestParam String type, @RequestParam int amount) {
    try {
      animalPictureService.fetchAndSavePicture(type, amount);
      return ResponseEntity.ok("Picture fetched successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
  }

  @GetMapping("/last/{type}")
  public ResponseEntity<byte[]> getLastPicture(@PathVariable String type) {
    AnimalPicture lastPicture = animalPictureService.getLastPicture(type);

    if (lastPicture != null) {
      return ResponseEntity.ok()
              .contentType(MediaType.IMAGE_JPEG)
              .body(lastPicture.getImageData());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
