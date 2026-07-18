# Strings in Java

### Subtopic: String APIs

---

# 1. Fundamentals

## What is a String?

A `String` is an immutable sequence of characters.

```java
String name = "Java";
```

Once a `String` object is created, its content cannot be changed. Any operation that looks like modification creates a new `String`.

```java
String s = "Java";
s = s + " Backend";   // new String is created
```

---

## Why Strings are Important

Backend applications use strings everywhere:

* API request and response fields
* JSON keys and values
* Database values
* Log messages
* Config properties
* User input validation
* File paths and URLs

Because strings are so common, small mistakes can create performance bugs, security issues, or incorrect business behavior.

---

## String Literal vs new String()

```java
String a = "Java";
String b = "Java";
String c = new String("Java");
```

`a` and `b` point to the same object from the String Pool.

`c` creates a separate object on the heap.

```java
System.out.println(a == b);      // true
System.out.println(a == c);      // false
System.out.println(a.equals(c)); // true
```

Use `.equals()` for content comparison.

---

# 2. Core String APIs

## 2.1 Length and Empty Checks

```java
String value = "Java";

value.length();   // 4
value.isEmpty();  // false
```

`isEmpty()` checks length only.

```java
"   ".isEmpty();  // false
```

Use `isBlank()` when whitespace-only text should be treated as empty.

```java
"   ".isBlank();  // true
```

---

## 2.2 Character Access

```java
String s = "Java";

char first = s.charAt(0);  // J
char last = s.charAt(s.length() - 1);
```

Use this when parsing tokens, validating input, or scanning characters.

Common risk:

```java
s.charAt(s.length()); // StringIndexOutOfBoundsException
```

---

## 2.3 Comparing Strings

```java
String a = "java";
String b = "JAVA";

a.equals(b);             // false
a.equalsIgnoreCase(b);   // true
a.compareTo(b);          // lexicographic comparison
```

Production rule:

```java
"ACTIVE".equals(status)
```

This avoids `NullPointerException` when `status` is null.

---

## 2.4 Searching

```java
String email = "user@example.com";

email.contains("@");        // true
email.startsWith("user");   // true
email.endsWith(".com");     // true
email.indexOf("@");         // 4
email.lastIndexOf(".");
```

Use these APIs for simple checks. For complex validation, use proper parsers or regex carefully.

---

## 2.5 Extracting Text

```java
String text = "order-12345";

String prefix = text.substring(0, 5); // order
String id = text.substring(6);        // 12345
```

`substring(start, end)` includes `start` and excludes `end`.

```java
text.substring(0, 5);
```

Means characters from index `0` to `4`.

---

## 2.6 Replacing Text

```java
String input = "java backend java";

input.replace("java", "Java");
input.replaceFirst("java", "Java");
input.replaceAll("\\s+", " ");
```

Important difference:

| API            | Meaning                     |
| -------------- | --------------------------- |
| `replace()`    | Literal replacement         |
| `replaceFirst()` | Regex, first match only   |
| `replaceAll()` | Regex, all matches         |

Use `replace()` when you do not need regex.

---

## 2.7 Trimming and Stripping

```java
String value = "  Java  ";

value.trim();   // removes older ASCII-style surrounding spaces
value.strip();  // Unicode-aware whitespace removal
```

Prefer `strip()` in modern Java when user input can contain Unicode whitespace.

Related APIs:

```java
value.stripLeading();
value.stripTrailing();
```

---

## 2.8 Case Conversion

```java
String city = "Delhi";

city.toLowerCase();
city.toUpperCase();
```

For production systems, use `Locale.ROOT` when converting values for technical comparison.

```java
String normalized = city.toLowerCase(Locale.ROOT);
```

This avoids locale-specific surprises.

---

## 2.9 Splitting and Joining

```java
String csv = "java,spring,mysql";

String[] parts = csv.split(",");
```

`split()` uses regex.

```java
"a.b.c".split(".");   // wrong: . means any character in regex
"a.b.c".split("\\."); // correct
```

Join values:

```java
String result = String.join(",", "java", "spring", "mysql");
```

---

## 2.10 Conversion APIs

```java
String.valueOf(100);      // "100"
String.valueOf(true);     // "true"
Integer.parseInt("123");  // 123
```

Use `String.valueOf(obj)` instead of `obj.toString()` when `obj` may be null.

```java
Object obj = null;

String.valueOf(obj); // "null"
obj.toString();      // NullPointerException
```

---

## 2.11 Formatting

```java
String message = String.format("Order %s failed", orderId);
```

Modern Java also supports:

```java
String message = "Order %s failed".formatted(orderId);
```

Use formatting for logs, error messages, and generated text where readability matters.

---

# 3. Internal Working

## Immutability

`String` is immutable.

```java
String s = "Java";
s.concat(" Backend");

System.out.println(s); // Java
```

The original string is unchanged.

Why immutability matters:

* Safe sharing between threads
* Safe use as `HashMap` keys
* Enables String Pool reuse
* Prevents accidental changes in security-sensitive values

---

## String Pool

String literals are stored in the String Pool.

```java
String a = "Java";
String b = "Java";
```

Both references can reuse the same pooled object.

This saves memory because common literals appear many times in applications.

---

## Hash Code Caching

`String` is commonly used as a key in maps.

```java
Map<String, User> users = new HashMap<>();
```

Because strings are immutable, Java can safely cache the hash code after computing it. This makes repeated map lookups efficient.

---

## Concatenation

```java
String fullName = firstName + " " + lastName;
```

For simple expressions, this is fine. The compiler optimizes many cases.

Avoid repeated concatenation inside loops:

```java
String result = "";

for (String item : items) {
    result += item; // creates many temporary objects
}
```

Use `StringBuilder` for repeated modification.

---

# 4. Common Mistakes

## 1. Using == for Content Comparison

```java
if (name == "admin") { } // wrong
```

Use:

```java
if ("admin".equals(name)) { }
```

---

## 2. Ignoring Null

```java
if (status.equals("ACTIVE")) { } // can throw NPE
```

Safer:

```java
if ("ACTIVE".equals(status)) { }
```

---

## 3. Concatenating in Large Loops

Use `StringBuilder` when building large strings repeatedly.

---

## 4. Misusing split()

`split()` accepts regex, not plain text.

```java
"a|b|c".split("|");    // wrong
"a|b|c".split("\\|");  // correct
```

---

## 5. Forgetting Locale in Case Conversion

Use `Locale.ROOT` for technical normalization.

```java
value.toLowerCase(Locale.ROOT);
```

---

# 5. Best Practices

## 1. Use equals() for Content

```java
if ("SUCCESS".equals(result)) { }
```

---

## 2. Normalize External Input

```java
String username = input.strip().toLowerCase(Locale.ROOT);
```

Normalize before validation, comparison, or persistence.

---

## 3. Prefer Clear APIs

Use `contains()`, `startsWith()`, and `endsWith()` for simple checks instead of regex.

---

## 4. Use StringBuilder for Repeated Appends

```java
StringBuilder sb = new StringBuilder();

for (String line : lines) {
    sb.append(line).append('\n');
}
```

---

## 5. Be Careful with Sensitive Strings

Passwords and secrets stored as `String` stay in memory until garbage collected.

For highly sensitive data, prefer `char[]` where practical because it can be cleared manually.

---

# 6. Code Examples

## Validate and Normalize Email

```java
public static String normalizeEmail(String email) {
    if (email == null || email.isBlank()) {
        throw new IllegalArgumentException("Email is required");
    }

    String normalized = email.strip().toLowerCase(Locale.ROOT);

    if (!normalized.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }

    return normalized;
}
```

---

## Parse File Extension

```java
public static String extensionOf(String fileName) {
    if (fileName == null || fileName.isBlank()) {
        return "";
    }

    int dotIndex = fileName.lastIndexOf('.');

    if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
        return "";
    }

    return fileName.substring(dotIndex + 1);
}
```

---

## Mask Mobile Number

```java
public static String maskMobile(String mobile) {
    if (mobile == null || mobile.length() < 4) {
        return "****";
    }

    String lastFour = mobile.substring(mobile.length() - 4);
    return "******" + lastFour;
}
```

---

# 7. Real-world Scenarios

## 1. Request Validation

APIs receive strings from clients. Use `strip()`, `isBlank()`, length checks, and clear validation before business logic.

---

## 2. Database Lookups

Normalize values like email, status, country codes, and usernames before lookup to avoid duplicate or missed records.

---

## 3. Logging

Use formatted strings carefully and avoid logging secrets.

---

## 4. Routing and Parsing

String APIs are often used for path variables, file names, IDs, and simple protocol parsing.

---

# Revision Notes

* `String` is immutable.
* String literals are stored in the String Pool.
* Use `.equals()` for content comparison.
* Use `"CONST".equals(value)` to avoid null issues.
* `substring(start, end)` excludes `end`.
* `split()` uses regex.
* Use `strip()` for Unicode-aware whitespace removal.
* Use `Locale.ROOT` for technical case conversion.
* Avoid string concatenation in large loops.
* Use `StringBuilder` for repeated appends.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Length | `s.length()` |
| Empty | `s.isEmpty()` |
| Blank | `s.isBlank()` |
| Character | `s.charAt(i)` |
| Compare | `s.equals(other)` |
| Ignore case | `s.equalsIgnoreCase(other)` |
| Search | `contains`, `indexOf`, `lastIndexOf` |
| Prefix/Suffix | `startsWith`, `endsWith` |
| Extract | `substring` |
| Replace literal | `replace` |
| Replace regex | `replaceAll` |
| Trim whitespace | `strip`, `trim` |
| Split | `split(regex)` |
| Join | `String.join(delimiter, values)` |
| Convert | `String.valueOf(value)` |

---

# Interview Questions with Answers

### 1. Why is String immutable?

Immutability makes strings safe to share, safe as map keys, usable in the String Pool, and reliable in security-sensitive code.

---

### 2. Difference between `==` and `.equals()`?

`==` compares references. `.equals()` compares content.

---

### 3. What is the String Pool?

It is a JVM-managed area that stores string literals so identical literals can reuse the same object.

---

### 4. Why is string concatenation in loops bad?

Each modification can create a new string, producing many temporary objects. Use `StringBuilder`.

---

### 5. Difference between `trim()` and `strip()`?

`trim()` removes older ASCII-style surrounding spaces. `strip()` is Unicode-aware and preferred in modern Java.

---

### 6. Why can `split(".")` fail?

Because `split()` takes regex and `.` means any character. Use `split("\\.")`.

---

# Hands-on Exercises

### Exercise 1

Check whether a username is valid: not null, not blank, and at least 3 characters after trimming.

**Answer**

```java
public static boolean isValidUsername(String username) {
    return username != null && username.strip().length() >= 3;
}
```

---

### Exercise 2

Normalize a status value to uppercase.

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
        char ch = text.charAt(i);
        if ("aeiou".indexOf(ch) >= 0) {
            count++;
        }
    }

    return count;
}
```

---

### Exercise 4

Extract domain from an email.

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
