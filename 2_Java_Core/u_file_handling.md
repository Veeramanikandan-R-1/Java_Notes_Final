# File Handling in Java

### Subtopic: NIO, Files API

---

# 1. Fundamentals

File handling means reading, writing, creating, moving, and deleting files or directories.

Modern Java prefers `java.nio.file` APIs.

```java
Path path = Path.of("data.txt");
String content = Files.readString(path);
```

---

# 2. Core Concepts

## Path

`Path` represents a file or directory path.

```java
Path path = Path.of("logs", "app.log");
```

## Files

`Files` provides utility methods.

```java
Files.exists(path);
Files.createFile(path);
Files.delete(path);
```

## Read File

```java
String content = Files.readString(Path.of("input.txt"));
```

For lines:

```java
List<String> lines = Files.readAllLines(Path.of("input.txt"));
```

For large files:

```java
try (Stream<String> lines = Files.lines(Path.of("input.txt"))) {
    lines.forEach(System.out::println);
}
```

## Write File

```java
Files.writeString(Path.of("out.txt"), "Hello");
```

Append:

```java
Files.writeString(path, "new line\n", StandardOpenOption.APPEND);
```

## Copy, Move, Delete

```java
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
Files.deleteIfExists(path);
```

## Directory Operations

```java
Files.createDirectories(Path.of("data/reports"));
```

List directory:

```java
try (Stream<Path> paths = Files.list(Path.of("data"))) {
    paths.forEach(System.out::println);
}
```

---

# 3. Internal Working

File APIs interact with the operating system. Most operations can fail because of:

* Missing file
* Permission issue
* Locked file
* Disk full
* Invalid path

That is why many methods throw `IOException`.

---

# 4. Common Mistakes

* Loading huge files fully into memory.
* Not closing streams.
* Ignoring `IOException`.
* Hardcoding OS-specific separators.
* Using string concatenation for paths.
* Not validating uploaded file names.

Bad:

```java
Path path = Path.of("data/" + fileName);
```

Better:

```java
Path path = Path.of("data").resolve(fileName).normalize();
```

---

# 5. Best Practices

* Use `Path` and `Files`.
* Use try-with-resources for streams.
* Stream large files instead of reading all at once.
* Validate file names from users.
* Use `createDirectories()` before writing nested files.
* Handle `IOException` with useful context.
* Avoid exposing server paths in API errors.

---

# 6. Code Example

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class AuditWriter {
    void writeEvent(String event) {
        Path path = Path.of("logs", "audit.log");

        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, event + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to write audit log", ex);
        }
    }
}
```

---

# 7. Real-world Scenarios

* Uploading files.
* Reading CSV reports.
* Writing audit logs.
* Exporting invoices.
* Processing batch jobs.
* Moving files between processing folders.

---

# Revision Notes

* Use `Path` to represent file paths.
* Use `Files` for file operations.
* Use `readString()` for small files.
* Use `Files.lines()` for large files.
* Use try-with-resources for streams.
* File operations commonly throw `IOException`.
* Validate user-controlled file paths.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Path | `Path.of("a.txt")` |
| Read string | `Files.readString(path)` |
| Read lines | `Files.readAllLines(path)` |
| Stream lines | `Files.lines(path)` |
| Write | `Files.writeString(path, text)` |
| Copy | `Files.copy(src, dest)` |
| Move | `Files.move(src, dest)` |
| Delete | `Files.deleteIfExists(path)` |
| Create dirs | `Files.createDirectories(path)` |

---

# Interview Questions with Answers

### 1. Why prefer NIO over old File API?

NIO provides richer, cleaner, and more modern file operations with `Path` and `Files`.

### 2. How to read large file safely?

Use streaming APIs like `Files.lines()` with try-with-resources.

### 3. Why does file handling throw IOException?

External file operations can fail due to OS, permission, disk, or path issues.

---

# Hands-on Exercises

### Exercise 1

Append a line to a file.

**Answer**

```java
public static void appendLine(Path path, String line) throws IOException {
    Files.writeString(path, line + System.lineSeparator(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND);
}
```

