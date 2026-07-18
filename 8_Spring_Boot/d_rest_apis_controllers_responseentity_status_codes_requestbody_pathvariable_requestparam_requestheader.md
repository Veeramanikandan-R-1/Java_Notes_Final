# Spring Boot

### Subtopic: REST APIs - Controllers, ResponseEntity, Status Codes, RequestBody, PathVariable, RequestParam, RequestHeader

---

# 1. Fundamentals

REST APIs expose resources over HTTP.

Spring Boot uses Spring MVC annotations to map requests to controller methods.

```java
@RestController
@RequestMapping("/api/orders")
class OrderController {
}
```

`@RestController` returns response bodies directly, usually JSON.

---

# 2. Core Concepts

## Request Mapping

```java
@GetMapping("/{id}")
ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
    return ResponseEntity.ok(new OrderResponse(id, "CREATED"));
}
```

Common annotations:

| Annotation | Purpose |
| ---------- | ------- |
| `@RequestBody` | JSON body to Java object |
| `@PathVariable` | Value from URL path |
| `@RequestParam` | Query parameter |
| `@RequestHeader` | HTTP header |
| `ResponseEntity` | Body, status, headers |

---

## Status Codes

| Code | Meaning |
| ---- | ------- |
| 200 | Success |
| 201 | Created |
| 204 | Success with no body |
| 400 | Bad request |
| 404 | Not found |
| 409 | Conflict |
| 500 | Server error |

---

# 3. Internal Working

Spring MVC uses `DispatcherServlet` as the front controller.

It maps the request to a controller method, converts JSON using Jackson, validates arguments when configured, invokes the method, and writes the response.

---

# 4. Common Mistakes

* Returning 200 for every result.
* Exposing entity classes directly.
* Using POST for read operations.
* Ignoring pagination for list APIs.
* Putting business logic inside controllers.
* Not validating request bodies.

---

# 5. Best Practices

* Keep controllers thin.
* Use DTOs for request and response.
* Return correct HTTP status codes.
* Use `ResponseEntity` when status or headers matter.
* Keep URLs resource-oriented.
* Delegate business logic to services.

---

# 6. Code Example

```java
@PostMapping
ResponseEntity<OrderResponse> create(
        @RequestHeader("X-Correlation-Id") String correlationId,
        @RequestBody CreateOrderRequest request) {

    OrderResponse response = orderService.create(request, correlationId);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

@GetMapping
List<OrderResponse> search(@RequestParam String status) {
    return orderService.findByStatus(status);
}
```

---

# 7. Real-world Scenarios

* Creating orders with POST.
* Reading resource by ID with GET.
* Passing filters through query parameters.
* Passing correlation IDs through headers.
* Returning 404 when resource does not exist.

---

# Revision Notes

* `@RestController` returns response body.
* `@PathVariable` reads URL path values.
* `@RequestParam` reads query parameters.
* `@RequestHeader` reads headers.
* `@RequestBody` maps JSON body to Java object.
* Use `ResponseEntity` for status, headers, and body.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| GET | `@GetMapping` |
| POST | `@PostMapping` |
| Body | `@RequestBody` |
| Path value | `@PathVariable` |
| Query value | `@RequestParam` |
| Header | `@RequestHeader` |
| Status control | `ResponseEntity` |

---

# Interview Questions with Answers

### 1. What is `ResponseEntity`?

A wrapper that lets you control HTTP status, headers, and response body.

### 2. Difference between `@PathVariable` and `@RequestParam`?

`@PathVariable` comes from URL path. `@RequestParam` comes from query string.

### 3. Why keep controllers thin?

Controllers should handle HTTP concerns and delegate business rules to services.

---

# Hands-on Exercises

### Exercise 1

Create a GET API for `/api/users/{id}`.

**Answer**

```java
@GetMapping("/api/users/{id}")
ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getById(id));
}
```

