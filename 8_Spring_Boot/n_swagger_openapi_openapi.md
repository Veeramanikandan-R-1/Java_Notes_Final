# Spring Boot

### Subtopic: Swagger/OpenAPI - OpenAPI

---

# 1. Fundamentals

OpenAPI describes REST APIs in a standard machine-readable format.

Swagger UI displays that API documentation interactively in the browser.

In Spring Boot, teams commonly use `springdoc-openapi` to generate OpenAPI docs from controller annotations.

---

# 2. Core Concepts

## OpenAPI Spec

OpenAPI describes:

* Endpoints
* HTTP methods
* Request bodies
* Response bodies
* Status codes
* Authentication schemes

---

## Swagger UI

Swagger UI lets developers inspect and test endpoints.

Typical local URL:

```text
/swagger-ui/index.html
```

OpenAPI JSON:

```text
/v3/api-docs
```

---

## Documentation Annotations

```java
@Operation(summary = "Get order by ID")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Order found"),
        @ApiResponse(responseCode = "404", description = "Order not found")
})
```

---

# 3. Internal Working

The OpenAPI library scans controllers, request mappings, DTOs, validation annotations, and documentation annotations.

It generates an OpenAPI document at runtime.

Swagger UI reads that document and renders the interactive page.

---

# 4. Common Mistakes

* Letting docs drift from actual API behavior.
* Missing error responses.
* Exposing Swagger UI publicly without controls.
* Using unclear DTO names.
* Not documenting authentication headers.

---

# 5. Best Practices

* Keep DTOs clean and descriptive.
* Document important status codes.
* Include validation constraints.
* Restrict Swagger UI in production if needed.
* Use OpenAPI as a contract for frontend and integration teams.

---

# 6. Code Example

```java
@GetMapping("/{id}")
@Operation(summary = "Find order by ID")
ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.findById(id));
}
```

---

# 7. Real-world Scenarios

* Frontend teams testing APIs.
* Generating API clients.
* Sharing backend contracts with partners.
* Reviewing error response contracts.
* Documenting secured endpoints.

---

# Revision Notes

* OpenAPI is the API specification.
* Swagger UI displays interactive docs.
* Spring Boot commonly uses `springdoc-openapi`.
* Generated docs come from controllers, DTOs, and annotations.
* Document status codes and error responses.
* Protect docs in production when needed.

---

# Cheat Sheet

| Need | Endpoint/Annotation |
| ---- | ------------------- |
| Swagger UI | `/swagger-ui/index.html` |
| OpenAPI JSON | `/v3/api-docs` |
| Operation summary | `@Operation` |
| Response docs | `@ApiResponse` |

---

# Interview Questions with Answers

### 1. What is OpenAPI?

A standard specification for describing REST APIs.

### 2. What is Swagger UI?

A browser UI that renders OpenAPI docs and lets users try API calls.

### 3. Why document error responses?

Clients need predictable failure contracts, not just successful examples.

---

# Hands-on Exercises

### Exercise 1

Add operation summary to a controller method.

**Answer**

```java
@Operation(summary = "Create user")
@PostMapping("/users")
ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
}
```

