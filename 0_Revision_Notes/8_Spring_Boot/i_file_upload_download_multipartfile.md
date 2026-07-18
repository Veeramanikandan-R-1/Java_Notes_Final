# Revision Notes

* Spring handles uploads with `MultipartFile`.
* Validate file size and content type.
* Do not trust original filename blindly.
* Store files outside application code directory.
* For downloads, set content type and content disposition.
* Use streaming for large files.
* Store metadata in database if needed.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Upload file | `MultipartFile` |
| File bytes | `getBytes()` |
| Input stream | `getInputStream()` |
| Original name | `getOriginalFilename()` |
| Download response | `ResponseEntity<Resource>` |

---

# Interview Questions with Answers

### 1. What is MultipartFile?

Spring abstraction for uploaded file data in multipart request.

### 2. Why validate file upload?

To avoid malicious files, huge files, and wrong file types.

### 3. Where store files?

Filesystem, object storage like S3, or database only when justified.

