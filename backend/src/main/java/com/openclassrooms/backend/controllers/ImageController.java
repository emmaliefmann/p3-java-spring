package com.openclassrooms.backend.controllers;
import com.openclassrooms.backend.services.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {


  private final ImageService imageService;

  @Value("${upload.dir}")
  private String uploadDir;

  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @GetMapping("/{filename}")
  public ResponseEntity<UrlResource> getImage(@PathVariable String filename) {
    UrlResource image = imageService.getImage(filename);
    return ResponseEntity.ok()
      .contentType(MediaType.IMAGE_JPEG)
      .body(image);
  }

}
