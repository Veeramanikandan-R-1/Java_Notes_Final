# Exception Handling in Java

### Subtopic: Checked and Unchecked

---

# 1. Fundamentals

An exception is an abnormal condition that interrupts normal program flow.

```java
int result = 10 / 0; // ArithmeticException
```

Exception handling lets code recover, report, or fail cleanly.

---

# 2. Core Concepts

## try-catch

```java
try {
    int value = Integer.parseInt("abc");
} catch (NumberFormatException ex) {
    System.out.println("Invalid number");
}
```

## finally

`finally` runs whether exception occurs or not.

```java
try {
    process();
} finally {
    cleanup();
}
```

## try-with-resources

Use it for resources that must be closed.

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    return reader.readLine();
}
```

The resource must implement `AutoCloseable`.

## Checked Exceptions

Checked exceptions are verified by compiler.

```java
void readFile() throws IOException {
    Files.readString(Path.of("a.txt"));
}
```

Examples:

* `IOException`
* `SQLException`

## Unchecked Exceptions

Unchecked exceptions extend `RuntimeException`.

Examples:

* `NullPointerException`
* `IllegalArgumentException`
* `IllegalStateException`
* `NumberFormatException`

They usually indicate programming errors or invalid runtime state.

## throw vs throws

`throw` actually throws an exception.

```java
throw new IllegalArgumentException("Invalid id");
```

`throws` declares that a method may throw.

```java
void load() throws IOException {
}
```

---

# 3. Internal Working

When an exception is thrown, JVM searches the call stack for a matching catch block.

If no handler is found, the thread terminates and stack trace is printed.

```text
methodC throws
methodB does not catch
methodA catches
```

This is exception propagation.

---

# 4. Common Mistakes

* Catching `Exception` too broadly.
* Swallowing exceptions silently.
* Losing root cause while wrapping exceptions.
* Using exceptions for normal control flow.
* Returning null instead of throwing clear domain exception.
* Not using try-with-resources for files or streams.

Bad:

```java
catch (Exception ex) {
}
```

Good:

```java
catch (IOException ex) {
    throw new FileProcessingException("Failed to read file", ex);
}
```

---

# 5. Best Practices

* Catch only exceptions you can handle.
* Add useful context when wrapping.
* Preserve original cause.
* Use unchecked exceptions for validation and business rule failures.
* Use checked exceptions when caller is expected to recover.
* Use try-with-resources for closeable resources.
* Do not expose internal exception details in API responses.

---

# 6. Code Example

```java
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(long userId) {
        super("User not found: " + userId);
    }
}

class UserService {
    User findById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user id");
        }

        throw new UserNotFoundException(id);
    }
}
```

---

# 7. Real-world Scenarios

* API validation failure.
* Missing database record.
* File read/write failure.
* External service timeout.
* Payment failure.
* Authentication error.

---

# Revision Notes

* Exceptions interrupt normal flow.
* Checked exceptions are compiler-checked.
* Unchecked exceptions extend `RuntimeException`.
* `throw` throws an exception.
* `throws` declares possible exception.
* `finally` runs for cleanup.
* try-with-resources closes resources automatically.
* Preserve root cause when wrapping exceptions.

---

# Cheat Sheet

| Need | Code |
| ---- | ---- |
| Catch | `try { } catch (IOException ex) { }` |
| Cleanup | `finally { }` |
| Auto-close | `try (Resource r = ...) { }` |
| Throw | `throw new IllegalArgumentException()` |
| Declare | `throws IOException` |
| Custom runtime | `extends RuntimeException` |

---

# Interview Questions with Answers

### 1. Checked vs unchecked exception?

Checked exceptions are enforced by compiler. Unchecked exceptions extend `RuntimeException` and are not compiler-enforced.

### 2. Difference between throw and throws?

`throw` throws an exception object. `throws` declares method-level exception possibility.

### 3. Why use try-with-resources?

It automatically closes resources and reduces cleanup bugs.

---

# Hands-on Exercises

### Exercise 1

Create a method that validates age.

**Answer**

```java
public static void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be at least 18");
    }
}
```

