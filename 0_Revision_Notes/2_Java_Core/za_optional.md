# Revision Notes

* Optional represents a possibly absent value.
* Use `Optional.of()` for non-null values.
* Use `Optional.ofNullable()` for nullable values.
* Use `Optional.empty()` for absence.
* Avoid direct `get()`.
* Use `orElseThrow()` when value is required.
* Use `orElseGet()` for expensive default values.
* Optional is best as a return type.
* Avoid Optional fields and parameters in most cases.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Non-null | `Optional.of(v)` |
| Nullable | `Optional.ofNullable(v)` |
| Empty | `Optional.empty()` |
| Map | `.map(...)` |
| Flat map | `.flatMap(...)` |
| Default | `.orElse(v)` |
| Lazy default | `.orElseGet(...)` |
| Throw | `.orElseThrow(...)` |

---

# Interview Questions with Answers

### 1. Why was Optional introduced?

To make absence explicit and reduce null-related mistakes.

### 2. Why avoid Optional.get()?

It throws `NoSuchElementException` when value is absent.

### 3. Should repository find method return Optional?

Yes, it is a good use case when a record may not exist.

---

# Hands-on Exercises

### Exercise 1

Get email or default.

**Answer**

```java
String email = optionalUser
        .map(User::getEmail)
        .orElse("unknown@example.com");
```

