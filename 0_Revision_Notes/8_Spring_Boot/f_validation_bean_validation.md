# Revision Notes

* Bean Validation validates request data declaratively.
* Use `@Valid` to trigger validation.
* Common annotations: `@NotNull`, `@NotBlank`, `@Size`, `@Email`, `@Min`, `@Max`.
* Validation belongs on DTOs.
* Global exception handler can format validation errors.
* Custom validators handle business-specific rules.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Trigger | `@Valid` |
| Not null | `@NotNull` |
| Not blank | `@NotBlank` |
| Email | `@Email` |
| Size | `@Size` |
| Range | `@Min`, `@Max` |

---

# Interview Questions with Answers

### 1. What is Bean Validation?

Standard annotation-based validation for Java objects.

### 2. Where place validation annotations?

Usually on request DTO fields.

### 3. How handle validation errors?

Use global exception handler for `MethodArgumentNotValidException`.

