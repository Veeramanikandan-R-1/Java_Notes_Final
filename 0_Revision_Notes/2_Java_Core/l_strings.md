# Revision Notes

* `String` is immutable.
* String literals are stored in the String Pool.
* Use `.equals()` for content comparison.
* Use `"CONST".equals(value)` to avoid `NullPointerException`.
* `substring(start, end)` includes `start` and excludes `end`.
* `split()` accepts regex, not plain text.
* Use `strip()` for Unicode-aware whitespace cleanup.
* Use `Locale.ROOT` for technical case normalization.
* Avoid repeated string concatenation inside large loops.
* Use `StringBuilder` for repeated string building.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Length | `s.length()` |
| Empty check | `s.isEmpty()` |
| Blank check | `s.isBlank()` |
| Character access | `s.charAt(i)` |
| Compare content | `s.equals(other)` |
| Ignore case | `s.equalsIgnoreCase(other)` |
| Search | `s.contains("x")` |
| Index | `s.indexOf("x")` |
| Prefix | `s.startsWith("pre")` |
| Suffix | `s.endsWith("txt")` |
| Extract | `s.substring(0, 3)` |
| Replace literal | `s.replace("a", "b")` |
| Replace regex | `s.replaceAll("\\s+", " ")` |
| Remove spaces | `s.strip()` |
| Split | `s.split(",")` |
| Join | `String.join(",", values)` |
| Convert | `String.valueOf(value)` |

---

# Interview Questions with Answers

### 1. Why is String immutable?

Because immutability makes strings safe to share, safe for String Pool reuse, reliable as `HashMap` keys, and safer for security-sensitive values.

---

### 2. Difference between `==` and `.equals()`?

`==` compares object references. `.equals()` compares actual string content.

---

### 3. What is the String Pool?

It is JVM-managed storage where string literals are reused to save memory.

---

### 4. Why avoid string concatenation in loops?

Repeated concatenation can create many temporary strings. Use `StringBuilder` for repeated appends.

---

### 5. Difference between `trim()` and `strip()`?

`trim()` removes older ASCII-style surrounding spaces. `strip()` is Unicode-aware.

---

### 6. Why can `split(".")` give wrong output?

Because `split()` uses regex and `.` means any character. Use `split("\\.")`.

---

# Hands-on Exercises

### Exercise 1

Validate username: not null, not blank, minimum 3 characters after trimming.

**Answer**

```java
public static boolean isValidUsername(String username) {
    return username != null && username.strip().length() >= 3;
}
```

---

### Exercise 2

Normalize status to uppercase.

**Answer**

```java
public static String normalizeStatus(String status) {
    if (status == null || status.isBlank()) {
        return "";
    }

    return status.strip().toUpperCase(Locale.ROOT);
}
```

---

### Exercise 3

Extract domain from email.

**Answer**

```java
public static String domainOf(String email) {
    if (email == null) {
        return "";
    }

    int atIndex = email.indexOf('@');

    if (atIndex == -1 || atIndex == email.length() - 1) {
        return "";
    }

    return email.substring(atIndex + 1);
}
```

---

### Exercise 4

Count vowels in a string.

**Answer**

```java
public static int countVowels(String input) {
    if (input == null) {
        return 0;
    }

    int count = 0;
    String text = input.toLowerCase(Locale.ROOT);

    for (int i = 0; i < text.length(); i++) {
        if ("aeiou".indexOf(text.charAt(i)) >= 0) {
            count++;
        }
    }

    return count;
}
```
