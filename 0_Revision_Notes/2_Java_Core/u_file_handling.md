# Revision Notes

* Modern file handling uses `java.nio.file`.
* `Path` represents file/directory paths.
* `Files` provides read, write, copy, move, delete APIs.
* Use `readString()` for small files.
* Use `Files.lines()` for large files.
* Use try-with-resources to close streams.
* Most file operations throw `IOException`.
* Validate user-controlled paths and file names.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Path | `Path.of("data.txt")` |
| Read | `Files.readString(path)` |
| Read all lines | `Files.readAllLines(path)` |
| Stream | `Files.lines(path)` |
| Write | `Files.writeString(path, text)` |
| Append | `StandardOpenOption.APPEND` |
| Copy | `Files.copy(src, dest)` |
| Move | `Files.move(src, dest)` |
| Delete | `Files.deleteIfExists(path)` |

---

# Interview Questions with Answers

### 1. What is Path?

An object representation of a file or directory path.

### 2. Why use try-with-resources?

To close file streams automatically.

### 3. How do you avoid loading large files into memory?

Use streaming APIs like `Files.lines()`.

---

# Hands-on Exercises

### Exercise 1

Read all lines from a file.

**Answer**

```java
public static List<String> readLines(Path path) throws IOException {
    return Files.readAllLines(path);
}
```

