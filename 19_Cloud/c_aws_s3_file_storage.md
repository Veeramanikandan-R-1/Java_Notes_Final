# AWS S3

### Subtopic: File Storage

---

# 1. Fundamentals

Amazon S3 is object storage.

Java backend services use S3 to store files such as invoices, exports, profile images, reports, logs, backups, and user uploads.

S3 is not a filesystem and not a relational database. It stores objects in buckets using keys.

---

# 2. Core Concepts

## Bucket, Object, Key

| Concept | Meaning |
| ------- | ------- |
| Bucket | Top-level storage container |
| Object | Stored file plus metadata |
| Key | Unique object path inside bucket |
| Metadata | Additional object attributes |
| Presigned URL | Temporary URL for upload/download |

Example key:

```text
orders/2026/07/invoice-1001.pdf
```

---

## Access Control

Prefer private buckets. Grant access using IAM policies, roles, bucket policies, and presigned URLs.

Backend services running on EC2, ECS, Lambda, or Kubernetes should use IAM roles instead of hardcoded AWS keys.

---

## Presigned URLs

A presigned URL allows temporary direct access to a specific object operation.

Common pattern:

```text
Client asks API for upload URL
API validates permission
API returns presigned PUT URL
Client uploads directly to S3
API stores object key in database
```

This avoids sending large files through the Java API server.

---

# 3. Internal Working

S3 stores objects by key in a bucket and serves them through regional endpoints.

Applications commonly store object metadata in a relational database:

```text
order_attachment table -> object_key, bucket, content_type, size, owner_id
```

The database keeps business ownership and queryability. S3 keeps durable file content.

---

# 4. Common Mistakes

* Making buckets public by accident.
* Storing object content in the database when S3 is better.
* Using predictable keys for sensitive files.
* Not validating file type and size before upload.
* Returning permanent public URLs.
* Forgetting lifecycle rules for temporary exports.
* Treating S3 as a low-latency local disk.

---

# 5. Best Practices

* Keep buckets private by default.
* Use IAM roles and least privilege.
* Use presigned URLs for controlled direct upload/download.
* Store object keys and metadata in the database.
* Validate content type, file size, and ownership.
* Use lifecycle policies for retention and cleanup.
* Enable encryption according to security requirements.
* Use versioning for important or user-managed documents.
* Avoid exposing raw bucket structure unnecessarily.

---

# 6. Code Example

```java
public URL createDownloadUrl(String bucket, String key) {
    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .build();

    GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(10))
            .getObjectRequest(getObjectRequest)
            .build();

    return s3Presigner.presignGetObject(presignRequest).url();
}
```

Store the key, not the full temporary URL:

```text
bucket=orders-prod-files
key=orders/1001/invoice.pdf
```

---

# 7. Real-world Scenarios

* Uploading user documents without overloading the API.
* Generating invoice PDFs and storing them for download.
* Exporting CSV reports asynchronously.
* Keeping product images private until authorized.
* Cleaning old temporary files using lifecycle rules.

---

# Revision Notes

* S3 is object storage.
* Buckets contain objects addressed by keys.
* Keep buckets private by default.
* Use IAM roles instead of static keys.
* Use presigned URLs for temporary controlled access.
* Store metadata in a database and content in S3.
* Use lifecycle rules for cleanup.

---

# Cheat Sheet

| Need | S3 Concept |
| ---- | ---------- |
| Storage container | Bucket |
| Stored file | Object |
| Path/identifier | Key |
| Temporary access | Presigned URL |
| Permissions | IAM policy |
| Cleanup | Lifecycle rule |
| Durability feature | Versioning |

---

# Interview Questions with Answers

### 1. Why use S3 for files instead of a database?

S3 is designed for durable object storage, while the database should store metadata and business relationships.

### 2. What is a presigned URL?

A temporary signed URL that allows a specific S3 operation without making the bucket public.

### 3. Why avoid public buckets?

Accidental public exposure can leak sensitive files and create security incidents.

---

# Hands-on Exercises

### Exercise 1

Describe the direct upload flow using S3.

**Answer**

```text
Client -> API asks for upload URL
API -> validates user and creates presigned PUT URL
Client -> uploads file directly to S3
API/database -> stores bucket, key, size, content type, owner
```

