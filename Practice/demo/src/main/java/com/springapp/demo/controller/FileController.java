package com.springapp.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final Path uploadDir = Paths.get("uploads")
            .toAbsolutePath().normalize();

    public FileController() throws IOException {
        Files.createDirectories(uploadDir);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "File is empty");
        }

        String originalName = file.getOriginalFilename();

        if (originalName == null || originalName.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid filename");
        }

        // Prevent user-supplied path components from being stored.
        String safeName = Paths.get(
                originalName.replace('\\', '/'))
                .getFileName().toString();

        String extension = "";
        int dot = safeName.lastIndexOf('.');
        if (dot > 0) {
            extension = safeName.substring(dot);
        }

        // Generate a unique storage name to avoid collisions.
        String storedName = UUID.randomUUID() + extension;
        Path destination = uploadDir.resolve(storedName).normalize();

        if (!destination.startsWith(uploadDir)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid filename");
        }

        file.transferTo(destination);

        return ResponseEntity.ok(
                "File uploaded successfully. Stored name: " + storedName);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String filename) throws IOException {

        // Only accept generated UUID-based storage names.
        if (!filename.matches(
                "[0-9a-fA-F-]{36}(\\.[a-zA-Z0-9]{1,10})?")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid filename");
        }

        Path path = uploadDir.resolve(filename).normalize();

        if (!path.startsWith(uploadDir) || !Files.isRegularFile(path)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "File not found");
        }

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(filename).build().toString())
                .body(resource);
    }
}