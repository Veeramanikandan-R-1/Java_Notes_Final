# Filtering and Sorting

### Subtopic: Query Parameters

---

# 1. Fundamentals

Filtering narrows results. Sorting controls result order.

REST APIs usually express both through query parameters because they refine a collection resource.

```http
GET /orders?status=PAID&customerId=10&sort=createdAt,desc
```

---

# 2. Core Concepts

## Filtering

Filters should map to business fields clients understand.

```http
GET /products?category=BOOKS&minPrice=100&maxPrice=500
```

Avoid exposing internal database column names unless they are already part of the API contract.

## Sorting

Sorting can be represented as:

```http
GET /orders?sort=createdAt,desc
GET /orders?sort=status,asc&sort=createdAt,desc
```

Multiple sort fields should be documented clearly.

## Validation

Filtering and sorting parameters must be allowlisted.

Clients should not be able to sort by sensitive or expensive fields accidentally.

---

# 3. Internal Working

In Spring Data, `Sort` and `Pageable` can handle sorting.

Dynamic filtering can be implemented using:

* Derived repository methods for simple cases.
* JPQL for fixed queries.
* Specifications or Criteria API for dynamic filters.

The API layer should parse user-facing parameters, then map them to safe persistence fields.

---

# 4. Common Mistakes

* Allowing arbitrary sort properties.
* Mixing filter names with database internals.
* Ignoring invalid filters silently.
* Returning unstable results without default sort.
* Building query strings by concatenation.
* Letting null filters create confusing behavior.

---

# 5. Best Practices

* Use query parameters for collection filtering.
* Define default sort.
* Allowlist sortable and filterable fields.
* Validate enum values and ranges.
* Keep filters composable and predictable.
* Use parameter binding, Specifications, or Criteria API instead of string concatenation.

---

# 6. Code Example

```java
@GetMapping("/orders")
public Page<OrderResponse> searchOrders(
        @RequestParam(required = false) OrderStatus status,
        @RequestParam(required = false) Long customerId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "createdAt,desc") String sort) {

    Sort safeSort = parseSort(sort);
    Pageable pageable = PageRequest.of(page, Math.min(size, 100), safeSort);
    return orderService.search(status, customerId, pageable);
}

private Sort parseSort(String sort) {
    Map<String, String> allowed = Map.of(
            "createdAt", "createdAt",
            "status", "status",
            "totalAmount", "totalAmount"
    );

    String[] parts = sort.split(",");
    String field = allowed.get(parts[0]);
    if (field == null) {
        throw new IllegalArgumentException("Unsupported sort field");
    }

    Sort.Direction direction = parts.length > 1
            ? Sort.Direction.fromString(parts[1])
            : Sort.Direction.ASC;

    return Sort.by(direction, field).and(Sort.by("id"));
}
```

---

# 7. Real-world Scenarios

* Searching orders by status, date range, and customer.
* Sorting products by price or rating.
* Filtering employees by department.
* Admin dashboards with multiple filter controls.
* Audit log pages sorted by newest event first.

---

# Revision Notes

* Filters narrow collection results.
* Sort controls deterministic order.
* Query parameters are natural for collection refinement.
* Always validate and allowlist filter/sort fields.
* Use default stable sort.
* Avoid dynamic string query concatenation.

---

# Cheat Sheet

| Need | Example |
| ---- | ------- |
| Filter by status | `?status=PAID` |
| Filter by range | `?minPrice=100&maxPrice=500` |
| Sort ascending | `?sort=name,asc` |
| Sort descending | `?sort=createdAt,desc` |
| Multiple sort fields | Repeated `sort` params |
| Safety | Allowlist fields |

---

# Interview Questions with Answers

### 1. Why use query parameters for filters?

Because filters refine a collection resource without identifying a different resource hierarchy.

### 2. Why allowlist sort fields?

To avoid exposing internals, expensive operations, and accidental sorting on unsafe fields.

### 3. Why add `id` as a secondary sort?

It makes ordering stable when multiple rows have the same primary sort value.

---

# Hands-on Exercises

### Exercise 1

Design a product search URL that filters by category and price, sorted by newest first.

**Answer**

```http
GET /products?category=LAPTOP&minPrice=50000&maxPrice=150000&sort=createdAt,desc
```

