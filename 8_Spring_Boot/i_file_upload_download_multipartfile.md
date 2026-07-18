# Spring Boot

### Subtopic: File Upload & Download - MultipartFile

---

# 1. Fundamentals

Spring Boot supports file upload using multipart requests.

Uploaded files are represented by `MultipartFile`.

```java
@PostMapping("/upload")
ResponseEntity<Void> upload(@RequestParam MultipartFile file) {
    return ResponseEntity.ok().build();
}
```

---

# 2. Core Concepts

## MultipartFile

Important methods:

| Method | Purpose |
| ------ | ------- |
| `getOriginalFilename()` | Client-provided filename |
| `getContentType()` | MIME type |
| `getSize()` | File size |
| `getInputStream()` | Read content |
| `transferTo()` | Save to target |

Do not trust original filename blindly.

---

## Download Response

```java
return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.pdf\"")
        .contentType(MediaType.APPLICATION_PDF)
        .body(resource);
```

---

# 3. Internal Working

Spring parses multipart form data before calling the controller.

Files may be stored temporarily in memory or disk depending on size and server configuration.

Upload limits are configured through properties.

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

# 4. Common Mistakes

* Trusting user-provided file names.
* Not limiting file size.
* Saving files directly under public paths.
* Not validating file type.
* Loading large files fully into memory.
* Returning files without correct content type.

---

# 5. Best Practices

* Validate file size and type.
* Generate server-side stored filenames.
* Store metadata in database.
* Stream large downloads.
* Keep files outside source directories.
* Scan files if security requirements demand it.

---

# 6. Code Example

```java
@PostMapping("/documents")
ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    Path target = Path.of("uploads", UUID.randomUUID() + ".bin");
    file.transferTo(target);
    return ResponseEntity.status(HttpStatus.CREATED).build();
}
```

---

# 7. Real-world Scenarios

* Uploading profile images.
* Downloading invoices.
* Importing CSV files.
* Storing documents in S3 or Azure Blob.
* Generating reports for users.

---

# Revision Notes

* Use `MultipartFile` for uploads.
* Configure max file and request size.
* Do not trust original filename.
* Validate content type and size.
* Use correct `Content-Disposition` for downloads.
* Stream large files where possible.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Upload param | `@RequestParam MultipartFile file` |
| File size | `file.getSize()` |
| Save file | `file.transferTo(path)` |
| Download header | `Content-Disposition` |
| Limit size | `spring.servlet.multipart.max-file-size` |

---

# Interview Questions with Answers

### 1. What is `MultipartFile`?

Spring's abstraction for an uploaded file in a multipart request.

### 2. Why not trust original filename?

It comes from the client and may contain unsafe or misleading values.

### 3. How do you return downloadable files?

Use `ResponseEntity` with content type, content disposition, and a resource or byte stream.

---

# Hands-on Exercises

### Exercise 1

Configure upload limit to 5 MB.

**Answer**

```properties
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
```

