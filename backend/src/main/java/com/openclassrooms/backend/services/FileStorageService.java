package com.openclassrooms.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

  // Ensure the uploads directory is present at the project root
  private static final String UPLOAD_DIR = "uploads/";

  public String saveFile(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("File is empty");
    }

    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
    Path filePath = Paths.get(UPLOAD_DIR, fileName);
    Files.createDirectories(filePath.getParent());
    Files.write(filePath, file.getBytes());

    return "/api/images/" + fileName;
  }
}
