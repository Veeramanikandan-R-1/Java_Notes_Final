# Spring Boot

### Subtopic: Validation - Bean Validation

---

# 1. Fundamentals

Bean Validation checks input data before business logic runs.

Spring Boot commonly uses Jakarta Bean Validation with Hibernate Validator.

```java
public record CreateUserRequest(
        @NotBlank String name,
        @Email String email
) {
}
```

---

# 2. Core Concepts

## Common Annotations

| Annotation | Meaning |
| ---------- | ------- |
| `@NotNull` | Must not be null |
| `@NotBlank` | Text must contain non-space characters |
| `@Email` | Must be email format |
| `@Size` | Length or collection size |
| `@Min` / `@Max` | Numeric bounds |
| `@Pattern` | Regex rule |
| `@Valid` | Trigger nested validation |

---

## Controller Validation

```java
@PostMapping
ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
}
```

Invalid input usually raises `MethodArgumentNotValidException`.

---

# 3. Internal Working

Spring deserializes JSON into DTOs first.

Then validation runs when `@Valid` or `@Validated` is present.

If validation fails, controller method is not called.

Global exception handling can convert validation errors into a consistent API response.

---

# 4. Common Mistakes

* Forgetting `@Valid` on request body.
* Using `@NotNull` on strings when `@NotBlank` is required.
* Returning raw validation errors without a clean error contract.
* Validating business uniqueness only with annotations.
* Not validating nested objects.

---

# 5. Best Practices

* Validate request DTOs at API boundary.
* Use clear validation messages.
* Use custom validators only for reusable structural rules.
* Keep business validation in service layer.
* Return field-level errors consistently.

---

# 6. Code Example

```java
public record CreateOrderRequest(
        @NotNull Long productId,
        @Min(1) int quantity,
        @NotBlank String deliveryAddress
) {
}
```

---

# 7. Real-world Scenarios

* Rejecting invalid emails.
* Enforcing password length.
* Preventing negative quantities.
* Validating nested address objects.
* Returning form-level errors to frontend clients.

---

# Revision Notes

* Bean Validation validates DTO fields.
* Use `@Valid` to trigger request body validation.
* `@NotBlank` is better for required strings.
* Validation failure stops controller execution.
* Business validation belongs in services.
* Global exception handlers should format errors.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Required object | `@NotNull` |
| Required string | `@NotBlank` |
| Email | `@Email` |
| Length | `@Size` |
| Minimum | `@Min` |
| Nested object | `@Valid` |

---

# Interview Questions with Answers

### 1. What does `@Valid` do?

It triggers validation of the annotated request object or nested field.

### 2. Difference between `@NotNull` and `@NotBlank`?

`@NotNull` rejects null only. `@NotBlank` also rejects empty or whitespace-only strings.

### 3. Where should business validation go?

In service layer, because it usually needs database or domain context.

---

# Hands-on Exercises

### Exercise 1

Validate a product creation request.

**Answer**

```java
public record CreateProductRequest(
        @NotBlank String name,
        @Min(1) int price
) {
}
```

