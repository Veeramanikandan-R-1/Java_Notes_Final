# Versioning

### Subtopic: URL and Header Versioning

---

# 1. Fundamentals

API versioning lets a backend evolve without breaking existing clients.

Versioning becomes necessary when a change is not backward compatible, such as removing a field, changing meaning of a field, or changing request/response structure.

---

# 2. Core Concepts

## URL Versioning

Version appears in the path.

```http
GET /api/v1/customers/10
GET /api/v2/customers/10
```

It is simple, visible, cache-friendly, and easy for teams to understand.

## Header Versioning

Version appears in a request header.

```http
GET /api/customers/10
Accept: application/vnd.company.customer-v2+json
```

Header versioning keeps resource URLs cleaner but is less obvious during manual debugging.

## Backward Compatible vs Breaking Changes

Usually safe:

* Adding optional response fields.
* Adding optional query parameters.
* Adding new endpoints.

Breaking:

* Removing fields.
* Renaming fields.
* Changing field type.
* Changing required validation rules.
* Changing error contract.

---

# 3. Internal Working

In Spring, URL versioning is usually routed by path.

```java
@RestController
@RequestMapping("/api/v1/customers")
class CustomerV1Controller {
}
```

Header versioning can be routed using `headers` or `produces`.

```java
@GetMapping(value = "/api/customers/{id}", headers = "X-API-Version=2")
public CustomerV2Response getV2(@PathVariable Long id) {
    return service.getV2(id);
}
```

The service layer should avoid duplicating all business logic. Controllers can map different DTO versions to shared domain operations where possible.

---

# 4. Common Mistakes

* Versioning every small additive change.
* Exposing entity classes directly and making versioning painful.
* Keeping old versions forever without deprecation policy.
* Breaking error formats between versions accidentally.
* Mixing URL and header versioning randomly.
* Sharing response DTOs between versions when contracts differ.

---

# 5. Best Practices

* Start with URL versioning for public or team-facing REST APIs unless there is a strong reason for headers.
* Version only breaking contract changes.
* Keep DTOs per API version.
* Document lifecycle: active, deprecated, sunset date.
* Add automated contract tests for old versions.
* Keep business logic in services and version mapping near the boundary.

---

# 6. Code Example

```java
record CustomerV1Response(Long id, String name) {}
record CustomerV2Response(Long id, String firstName, String lastName, String email) {}

@RestController
@RequestMapping("/api/v1/customers")
class CustomerV1Controller {
    private final CustomerService service;

    @GetMapping("/{id}")
    CustomerV1Response get(@PathVariable Long id) {
        Customer customer = service.getById(id);
        return new CustomerV1Response(customer.id(), customer.fullName());
    }
}

@RestController
@RequestMapping("/api/v2/customers")
class CustomerV2Controller {
    private final CustomerService service;

    @GetMapping("/{id}")
    CustomerV2Response get(@PathVariable Long id) {
        Customer customer = service.getById(id);
        return new CustomerV2Response(
                customer.id(),
                customer.firstName(),
                customer.lastName(),
                customer.email()
        );
    }
}
```

---

# 7. Real-world Scenarios

* Mobile apps that cannot update immediately.
* Public partner APIs with contractual stability.
* Migrating from `name` to `firstName` and `lastName`.
* Changing pagination response structure.
* Introducing a new error response format.

---

# Revision Notes

* Version APIs for breaking changes, not every change.
* URL versioning is visible and easy to route.
* Header versioning keeps URLs clean but is harder to inspect.
* Keep DTOs separate by version.
* Use deprecation and sunset policies.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Simple public API version | `/api/v1/...` |
| Media-type negotiation | `Accept: application/vnd.app-v2+json` |
| Custom header | `X-API-Version: 2` |
| Add optional field | Usually no new version |
| Remove/rename field | New version |
| Protect old clients | Contract tests |

---

# Interview Questions with Answers

### 1. When should an API be versioned?

When the external contract changes in a backward-incompatible way.

### 2. URL versioning vs header versioning?

URL versioning is explicit and simple. Header versioning is cleaner from a resource URL perspective but less visible and harder to test manually.

### 3. Why not expose JPA entities directly?

Because persistence models change for database reasons, while API contracts must remain stable for clients.

---

# Hands-on Exercises

### Exercise 1

Create two endpoint versions for customer response changes.

**Answer**

```text
GET /api/v1/customers/10 -> { "id": 10, "name": "Asha Rao" }
GET /api/v2/customers/10 -> { "id": 10, "firstName": "Asha", "lastName": "Rao" }
```

