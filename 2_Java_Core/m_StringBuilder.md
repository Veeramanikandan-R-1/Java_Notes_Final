# StringBuilder in Java

### Subtopic: Mutable Strings

---

# 1. Fundamentals

## What is StringBuilder?

`StringBuilder` is a mutable sequence of characters.

```java
StringBuilder sb = new StringBuilder();
sb.append("Java");
sb.append(" Backend");

System.out.println(sb.toString()); // Java Backend
```

Unlike `String`, a `StringBuilder` can change its internal content without creating a new object for every append.

---

## Why StringBuilder Exists

`String` is immutable.

```java
String result = "";

for (String item : items) {
    result += item;
}
```

This can create many temporary `String` objects.

`StringBuilder` solves this by keeping a resizable internal buffer.

```java
StringBuilder result = new StringBuilder();

for (String item : items) {
    result.append(item);
}
```

Use it when building strings step by step.

---

## String vs StringBuilder

| Feature | String | StringBuilder |
| ------- | ------ | ------------- |
| Mutability | Immutable | Mutable |
| Thread safety | Safe because immutable | Not synchronized |
| Best use | Fixed text, keys, values | Repeated string building |
| Modification | Creates new object | Updates same builder |
| Equality | Content-based `.equals()` | Reference-based `.equals()` |

---

# 2. Core StringBuilder APIs

## 2.1 Creating StringBuilder

```java
StringBuilder sb1 = new StringBuilder();
StringBuilder sb2 = new StringBuilder("Java");
StringBuilder sb3 = new StringBuilder(100);
```

Use initial capacity when you can estimate the final size.

```java
StringBuilder sql = new StringBuilder(256);
```

This reduces internal resizing.

---

## 2.2 append()

Adds content at the end.

```java
StringBuilder sb = new StringBuilder();

sb.append("Order ID: ");
sb.append(101);
sb.append(", status: ");
sb.append("FAILED");
```

`append()` returns the same builder, so chaining is common.

```java
String message = new StringBuilder()
        .append("User ")
        .append(userId)
        .append(" logged in")
        .toString();
```

---

## 2.3 insert()

Adds content at a specific index.

```java
StringBuilder sb = new StringBuilder("Java Backend");

sb.insert(5, "Senior ");

System.out.println(sb); // Java Senior Backend
```

Insertion may shift existing characters, so it is costlier than append.

---

## 2.4 delete() and deleteCharAt()

```java
StringBuilder sb = new StringBuilder("Java Backend");

sb.delete(4, 12);       // removes indexes 4 to 11
sb.deleteCharAt(0);     // removes first character
```

`delete(start, end)` includes `start` and excludes `end`.

---

## 2.5 replace()

```java
StringBuilder sb = new StringBuilder("Java UI");

sb.replace(5, 7, "Backend");

System.out.println(sb); // Java Backend
```

This replaces text between `start` and `end`.

---

## 2.6 reverse()

```java
StringBuilder sb = new StringBuilder("madam");

String reversed = sb.reverse().toString();
```

Useful for simple palindrome checks and coding problems.

---

## 2.7 length(), capacity(), and setLength()

```java
StringBuilder sb = new StringBuilder("Java");

sb.length();    // 4
sb.capacity();  // internal buffer size
```

`setLength()` can truncate or expand the builder.

```java
sb.setLength(2); // Ja
```

If expanded, extra positions are filled with null characters.

---

## 2.8 ensureCapacity()

```java
StringBuilder sb = new StringBuilder();
sb.ensureCapacity(1000);
```

Use this when building a large known-size result, such as CSV, JSON-like text, or generated SQL.

---

## 2.9 toString()

Convert the final builder into an immutable string.

```java
String finalValue = sb.toString();
```

Return `String` from public APIs unless callers specifically need a mutable builder.

---

# 3. Internal Working

## Mutable Internal Buffer

`StringBuilder` stores characters in an internal resizable buffer.

When you call:

```java
sb.append("Java");
```

it writes into the existing buffer when capacity is available.

If capacity is not enough, it grows the buffer and copies existing content.

---

## Capacity Growth

Default capacity is usually enough for small strings.

For large strings, repeated growth causes extra allocation and copying.

```java
StringBuilder sb = new StringBuilder(5000);
```

This is useful when the expected output size is known.

---

## Not Thread-Safe

`StringBuilder` is not synchronized.

This is good for performance in single-threaded usage.

If multiple threads mutate the same character sequence, use proper synchronization or avoid shared mutation.

`StringBuffer` is synchronized, but in modern backend code it is less commonly needed because local builders are usually enough.

---

## Equality Behavior

`StringBuilder` does not compare content using `.equals()`.

```java
StringBuilder a = new StringBuilder("Java");
StringBuilder b = new StringBuilder("Java");

System.out.println(a.equals(b)); // false
```

Convert to `String` for content comparison.

```java
a.toString().equals(b.toString());
```

---

# 4. Common Mistakes

## 1. Using StringBuilder for Fixed Strings

```java
StringBuilder role = new StringBuilder("ADMIN");
```

Use `String` for fixed values.

---

## 2. Comparing Builders with equals()

```java
sb1.equals(sb2); // reference comparison
```

Use:

```java
sb1.toString().equals(sb2.toString());
```

---

## 3. Sharing One Builder Across Threads

Avoid shared mutable builders in services.

```java
class ReportService {
    private final StringBuilder sb = new StringBuilder(); // risky
}
```

Use method-local builders.

---

## 4. Forgetting to Clear Reused Builder

```java
StringBuilder sb = new StringBuilder();

sb.append("first");
sb.setLength(0);
sb.append("second");
```

Use `setLength(0)` to clear content.

---

## 5. Keeping Huge Capacity After Reuse

A builder that once held a huge string may keep a large internal buffer.

For long-lived objects, prefer creating a new builder instead of reusing a huge one forever.

---

# 5. Best Practices

## 1. Use Method-local Builders

```java
public String buildMessage(User user) {
    StringBuilder sb = new StringBuilder();
    sb.append("User: ").append(user.getName());
    return sb.toString();
}
```

Method-local builders avoid thread-safety problems.

---

## 2. Pre-size When Output is Predictable

```java
StringBuilder csv = new StringBuilder(rows.size() * 40);
```

This reduces resizing.

---

## 3. Return String, Not StringBuilder

```java
public String buildCsv(List<String> values) {
    StringBuilder sb = new StringBuilder();
    // build
    return sb.toString();
}
```

This keeps mutability internal.

---

## 4. Prefer String.join() for Simple Joining

```java
String tags = String.join(",", tagList);
```

Use `StringBuilder` when formatting logic is conditional or complex.

---

## 5. Avoid Overusing It

Simple concatenation is fine:

```java
String fullName = firstName + " " + lastName;
```

Use `StringBuilder` when there is repeated appending, usually inside loops or conditional assembly.

---

# 6. Code Examples

## Build CSV Line

```java
public static String toCsvLine(List<String> values) {
    StringBuilder sb = new StringBuilder(values.size() * 16);

    for (int i = 0; i < values.size(); i++) {
        if (i > 0) {
            sb.append(',');
        }
        sb.append(values.get(i));
    }

    return sb.toString();
}
```

---

## Build Error Message

```java
public static String buildValidationMessage(List<String> errors) {
    if (errors == null || errors.isEmpty()) {
        return "No validation errors";
    }

    StringBuilder sb = new StringBuilder("Validation failed: ");

    for (int i = 0; i < errors.size(); i++) {
        if (i > 0) {
            sb.append("; ");
        }
        sb.append(errors.get(i));
    }

    return sb.toString();
}
```

---

## Remove Consecutive Spaces

```java
public static String normalizeSpaces(String input) {
    if (input == null || input.isEmpty()) {
        return "";
    }

    StringBuilder sb = new StringBuilder(input.length());
    boolean previousSpace = false;

    for (int i = 0; i < input.length(); i++) {
        char ch = input.charAt(i);

        if (Character.isWhitespace(ch)) {
            if (!previousSpace) {
                sb.append(' ');
                previousSpace = true;
            }
        } else {
            sb.append(ch);
            previousSpace = false;
        }
    }

    return sb.toString().strip();
}
```

---

# 7. Real-world Scenarios

## 1. Building Dynamic SQL Fragments

Use `StringBuilder` for assembling optional filters. Still use prepared statements for values.

---

## 2. Creating CSV or Report Lines

Repeated append operations are common when generating export data.

---

## 3. Generating Log or Audit Messages

Useful when message parts are conditional.

---

## 4. Serialization Helpers

StringBuilder can build lightweight text output when a full JSON library is not needed.

---

# Revision Notes

* `StringBuilder` is mutable.
* It is useful for repeated string modifications.
* `append()` is usually cheaper than repeated `String` concatenation in loops.
* `StringBuilder` is not synchronized.
* Prefer method-local builders.
* Use initial capacity when final size is predictable.
* `delete(start, end)` and `replace(start, end, value)` exclude `end`.
* `.equals()` does not compare builder content.
* Convert to `String` using `toString()` before returning from APIs.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Create | `new StringBuilder()` |
| Pre-size | `new StringBuilder(capacity)` |
| Add at end | `append(value)` |
| Add at index | `insert(index, value)` |
| Delete range | `delete(start, end)` |
| Delete char | `deleteCharAt(index)` |
| Replace range | `replace(start, end, value)` |
| Reverse | `reverse()` |
| Current size | `length()` |
| Buffer size | `capacity()` |
| Clear | `setLength(0)` |
| Convert | `toString()` |

---

# Interview Questions with Answers

### 1. Why use StringBuilder instead of String concatenation in loops?

Because repeated string concatenation can create many temporary objects. `StringBuilder` mutates one internal buffer.

---

### 2. Is StringBuilder thread-safe?

No. It is not synchronized. Use method-local builders or external synchronization if shared mutation is unavoidable.

---

### 3. Difference between StringBuilder and StringBuffer?

`StringBuilder` is not synchronized and is faster for local use. `StringBuffer` is synchronized and safer for shared mutable access, but less common in modern code.

---

### 4. What happens when capacity is exceeded?

The builder grows its internal buffer and copies existing content into the new buffer.

---

### 5. Does StringBuilder override equals()?

No. `.equals()` checks reference equality. Convert to `String` for content comparison.

---

# Hands-on Exercises

### Exercise 1

Build a comma-separated string from a list.

**Answer**

```java
public static String joinWithComma(List<String> values) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < values.size(); i++) {
        if (i > 0) {
            sb.append(',');
        }
        sb.append(values.get(i));
    }

    return sb.toString();
}
```

---

### Exercise 2

Reverse a string using `StringBuilder`.

**Answer**

```java
public static String reverse(String input) {
    if (input == null) {
        return "";
    }

    return new StringBuilder(input).reverse().toString();
}
```

---

### Exercise 3

Remove all digits from a string.

**Answer**

```java
public static String removeDigits(String input) {
    if (input == null) {
        return "";
    }

    StringBuilder sb = new StringBuilder(input.length());

    for (int i = 0; i < input.length(); i++) {
        char ch = input.charAt(i);
        if (!Character.isDigit(ch)) {
            sb.append(ch);
        }
    }

    return sb.toString();
}
```

---

### Exercise 4

Build a URL query string from key-value pairs.

**Answer**

```java
public static String buildQuery(Map<String, String> params) {
    StringBuilder sb = new StringBuilder();

    for (Map.Entry<String, String> entry : params.entrySet()) {
        if (sb.length() > 0) {
            sb.append('&');
        }
        sb.append(entry.getKey()).append('=').append(entry.getValue());
    }

    return sb.toString();
}
```
