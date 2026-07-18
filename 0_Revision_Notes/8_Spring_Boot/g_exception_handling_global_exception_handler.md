# Revision Notes

* Global exception handler centralizes API error responses.
* Use `@RestControllerAdvice`.
* Use `@ExceptionHandler` for specific exceptions.
* Return consistent error format.
* Do not expose internal stack traces to clients.
* Map domain errors to correct HTTP status codes.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Global handler | `@RestControllerAdvice` |
| Handle exception | `@ExceptionHandler` |
| Bad request | `400` |
| Unauthorized | `401` |
| Forbidden | `403` |
| Not found | `404` |
| Conflict | `409` |

---

# Interview Questions with Answers

### 1. Why global exception handler?

To keep error handling consistent and avoid duplicate try-catch in controllers.

### 2. What is @RestControllerAdvice?

Global advice that applies to REST controllers and returns response bodies.

### 3. What status for resource not found?

HTTP 404.

