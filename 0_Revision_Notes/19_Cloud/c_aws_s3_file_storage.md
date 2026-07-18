# Revision Notes

* S3 is object storage, not a filesystem or database.
* Buckets contain objects.
* Objects are addressed by keys.
* Keep buckets private by default.
* Use IAM roles and least privilege.
* Presigned URLs provide temporary controlled upload or download.
* Store object metadata in a database.
* Use lifecycle rules to clean up temporary files.
* Validate file size, type, and ownership.

---

# Cheat Sheet

| Need | S3 Concept |
| ---- | ---------- |
| Container | Bucket |
| File | Object |
| Identifier | Key |
| Temporary access | Presigned URL |
| Authorization | IAM policy |
| Cleanup | Lifecycle rule |
| Change history | Versioning |

---

# Interview Questions with Answers

### 1. What is an S3 key?

The unique object identifier inside a bucket.

### 2. Why use presigned URLs?

They allow temporary direct access without making objects public or routing large files through the backend.

### 3. What should the database store for uploaded files?

Bucket, key, owner, content type, size, status, and business relationship metadata.

---

# Hands-on Exercises

### Exercise 1

What should be saved after a successful upload?

**Answer**

```text
bucket, object key, owner id, content type, size, upload status
```

