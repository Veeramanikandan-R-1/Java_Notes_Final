# Spring Boot

### Subtopic: Exception Handling - Global Exception Handler

---

# 1. Fundamentals

Exception handling converts Java exceptions into meaningful HTTP responses.

In Spring Boot REST APIs, global exception handling is usually done with `@RestControllerAdvice`.

---

# 2. Core Concepts

## Custom Exception

```java
class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException(String message) {
        super(message);
    }
}
```

---

## Error Response DTO

```java
public record ApiError(
        String code,
        String message
) {
}
```

---

## Global Handler

```java
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError("NOT_FOUND", ex.getMessage()));
    }
}
```

---

# 3. Internal Working

When a controller throws an exception, Spring searches for a matching `@ExceptionHandler`.

The handler creates the response body and status.

If no handler matches, Spring falls back to default error handling.

---

# 4. Common Mistakes

* Returning stack traces to clients.
* Handling every exception as 500.
* Catching exceptions in each controller method.
* Using inconsistent error response shapes.
* Logging expected 4xx exceptions as critical errors.

---

# 5. Best Practices

* Create a consistent error response DTO.
* Map domain exceptions to correct HTTP status codes.
* Keep technical details out of client responses.
* Include correlation ID when available.
* Log unexpected 5xx errors with enough context.

---

# 6. Code Example

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new LinkedHashMap<>();
    ex.getBindingResult().getFieldErrors()
            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
}
```

---

# 7. Real-world Scenarios

* Returning 404 for missing resources.
* Returning 409 for duplicate email.
* Returning 400 for invalid input.
* Returning 403 for forbidden actions.
* Returning 500 for unexpected system failures.

---

# Revision Notes

* Use `@RestControllerAdvice` for global REST exception handling.
* Use `@ExceptionHandler` for exception-specific responses.
* Do not expose stack traces to clients.
* Use consistent error DTOs.
* Map exceptions to correct HTTP statuses.
* Validation errors should be field-level.

---

# Cheat Sheet

| Exception | Status |
| --------- | ------ |
| Validation failure | 400 |
| Not found | 404 |
| Conflict | 409 |
| Unauthorized | 401 |
| Forbidden | 403 |
| Unexpected error | 500 |

---

# Interview Questions with Answers

### 1. What is `@RestControllerAdvice`?

A global component that applies exception handling and response advice to REST controllers.

### 2. Why not catch exceptions in every controller?

It duplicates code and creates inconsistent responses.

### 3. What should clients receive for internal errors?

A safe, generic error response with a useful code, not stack traces.

---

# Hands-on Exercises

### Exercise 1

Handle `ResourceNotFoundException` globally.

**Answer**

```java
@ExceptionHandler(ResourceNotFoundException.class)
ResponseEntity<ApiError> handle(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiError("NOT_FOUND", ex.getMessage()));
}
```

