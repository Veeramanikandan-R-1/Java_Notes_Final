# Spring Boot

### Subtopic: DTO Pattern - Request Response DTOs

---

# 1. Fundamentals

DTO means Data Transfer Object.

DTOs define what data enters and leaves your API.

They protect internal domain models from being directly exposed to clients.

---

# 2. Core Concepts

## Request DTO

```java
public record CreateUserRequest(
        String name,
        String email
) {
}
```

Represents client input.

---

## Response DTO

```java
public record UserResponse(
        Long id,
        String name,
        String email
) {
}
```

Represents API output.

---

## Mapping

```java
UserResponse toResponse(User user) {
    return new UserResponse(user.getId(), user.getName(), user.getEmail());
}
```

Mapping can be manual or library-based. Manual mapping is clear and often enough.

---

# 3. Internal Working

Spring uses Jackson to deserialize request JSON into request DTOs and serialize response DTOs into JSON.

Records work well for immutable DTOs.

Validation annotations can be placed on request DTO fields.

---

# 4. Common Mistakes

* Returning JPA entities directly from controllers.
* Using the same DTO for create, update, and response when fields differ.
* Exposing internal fields like password hashes or flags.
* Putting business logic inside DTOs.
* Allowing clients to set server-owned fields like `id` or `createdAt`.

---

# 5. Best Practices

* Use separate request and response DTOs.
* Keep DTOs simple and immutable where possible.
* Map server-owned fields inside service layer.
* Validate request DTOs.
* Avoid leaking domain or database design through API contracts.

---

# 6. Code Example

```java
public record CreateProductRequest(String name, BigDecimal price) {
}

public record ProductResponse(Long id, String name, BigDecimal price) {
}

@Service
class ProductMapper {
    ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
```

---

# 7. Real-world Scenarios

* Hiding password fields.
* Returning computed display values.
* Versioning external API contracts.
* Accepting different fields for create and update.
* Avoiding lazy-loading issues from entity serialization.

---

# Revision Notes

* DTOs define API input and output shape.
* Request DTOs represent client input.
* Response DTOs represent server output.
* Do not expose entities directly.
* Records are useful for immutable DTOs.
* Keep mapping explicit and testable.

---

# Cheat Sheet

| Need | DTO |
| ---- | --- |
| Create input | `CreateXRequest` |
| Update input | `UpdateXRequest` |
| API output | `XResponse` |
| List item | `XSummaryResponse` |
| Mapping | Mapper method/class |

---

# Interview Questions with Answers

### 1. Why use DTOs?

To separate API contracts from internal domain and persistence models.

### 2. Why not return entity directly?

It can leak internal fields, create serialization problems, and tightly couple API to database structure.

### 3. Where should mapping happen?

Usually in service or mapper classes, not inside controllers.

---

# Hands-on Exercises

### Exercise 1

Create request and response DTOs for user registration.

**Answer**

```java
public record RegisterUserRequest(String name, String email, String password) {
}

public record UserResponse(Long id, String name, String email) {
}
```

