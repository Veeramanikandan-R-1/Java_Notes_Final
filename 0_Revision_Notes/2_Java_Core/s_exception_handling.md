# Revision Notes

* Exception is abnormal flow.
* Checked exceptions are checked by compiler.
* Unchecked exceptions extend `RuntimeException`.
* `try-catch` handles exceptions.
* `finally` is used for cleanup.
* try-with-resources closes `AutoCloseable` resources.
* `throw` throws an exception.
* `throws` declares exceptions.
* Catch specific exceptions.
* Preserve original cause when wrapping.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Handle | `try { } catch (Exception ex) { }` |
| Cleanup | `finally { }` |
| Auto close | `try (var r = resource) { }` |
| Throw | `throw new RuntimeException()` |
| Declare | `throws IOException` |
| Custom | `class X extends RuntimeException` |

---

# Interview Questions with Answers

### 1. What is checked exception?

An exception the compiler forces you to handle or declare.

### 2. What is unchecked exception?

A runtime exception not enforced by compiler.

### 3. Why not catch generic Exception everywhere?

It hides intent and can accidentally swallow errors the code cannot handle properly.

---

# Hands-on Exercises

### Exercise 1

Create custom exception for invalid order state.

**Answer**

```java
class InvalidOrderStateException extends RuntimeException {
    InvalidOrderStateException(String message) {
        super(message);
    }
}
```

