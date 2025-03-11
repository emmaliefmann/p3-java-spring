package com.openclassrooms.backend.controllers;
import com.openclassrooms.backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/images")
public class ImageController {

  @Autowired
  ImageService imageService;

  @Value("${upload.dir}") // Read from application.properties
  private String uploadDir;

  @GetMapping("/{filename}")
  public ResponseEntity<UrlResource> getImage(@PathVariable String filename) {
    UrlResource image = imageService.getImage(filename);
    return ResponseEntity.ok()
      .contentType(MediaType.IMAGE_JPEG)
      .body(image);
  }

}
