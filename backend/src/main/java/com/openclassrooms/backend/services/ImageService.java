package com.openclassrooms.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
  @Value("${upload.dir}") // Read from application.properties
  private String uploadDir;

  public UrlResource getImage(String filename) {
    try {
      Path filePath = Paths.get(uploadDir).resolve(filename);
      UrlResource resource = new UrlResource(filePath.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new FileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException | FileNotFoundException e) {
      throw new RuntimeException("Error handling file: " + filename, e);
    }
  }

}
