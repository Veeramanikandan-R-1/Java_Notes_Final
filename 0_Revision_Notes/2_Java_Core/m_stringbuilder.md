# Revision Notes

* `StringBuilder` is a mutable character sequence.
* It is used for repeated string modification.
* `append()` updates the same builder when capacity is available.
* Capacity grows when the internal buffer becomes full.
* It is not synchronized and not thread-safe for shared mutation.
* Prefer method-local builders in backend services.
* Pre-size the builder when final output size is predictable.
* `delete(start, end)` and `replace(start, end, value)` exclude `end`.
* `.equals()` does not compare builder content.
* Convert to `String` using `toString()` before returning from public APIs.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Create | `new StringBuilder()` |
| Create with text | `new StringBuilder("Java")` |
| Pre-size | `new StringBuilder(256)` |
| Append | `sb.append(value)` |
| Insert | `sb.insert(index, value)` |
| Delete range | `sb.delete(start, end)` |
| Delete char | `sb.deleteCharAt(index)` |
| Replace | `sb.replace(start, end, value)` |
| Reverse | `sb.reverse()` |
| Length | `sb.length()` |
| Capacity | `sb.capacity()` |
| Clear | `sb.setLength(0)` |
| Convert | `sb.toString()` |

---

# Interview Questions with Answers

### 1. Why use StringBuilder instead of String in loops?

Because repeated string concatenation can create many temporary objects. `StringBuilder` modifies one internal buffer.

---

### 2. Is StringBuilder thread-safe?

No. It is not synchronized. Use it locally inside methods or protect shared access.

---

### 3. Difference between StringBuilder and StringBuffer?

`StringBuilder` is faster and not synchronized. `StringBuffer` is synchronized but usually unnecessary for method-local string building.

---

### 4. What happens when capacity is exceeded?

The builder creates a larger internal buffer and copies existing content into it.

---

### 5. Does StringBuilder `.equals()` compare content?

No. It uses reference equality. Convert both builders to `String` for content comparison.

---

# Hands-on Exercises

### Exercise 1

Build comma-separated text from a list.

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

Reverse a string.

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

Build query string from key-value pairs.

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
