# Revision Notes

* Filtering narrows a collection.
* Sorting defines deterministic order.
* Query parameters are best for collection filtering and sorting.
* Use user-facing parameter names, not database internals.
* Validate enum values, ranges, and unsupported fields.
* Allowlist sortable fields.
* Add default stable sort.

---

# Cheat Sheet

| Need | Example |
| ---- | ------- |
| Status filter | `?status=ACTIVE` |
| Range filter | `?minPrice=100&maxPrice=500` |
| Asc sort | `?sort=name,asc` |
| Desc sort | `?sort=createdAt,desc` |
| Stable ordering | Add `id` sort |
| Safety | Allowlist fields |

---

# Interview Questions with Answers

### 1. Why avoid arbitrary sort fields?

They can expose internals and trigger expensive or unsafe queries.

### 2. Should invalid filters be ignored?

Usually no. Return a clear validation error so clients can fix the request.

### 3. Why use query parameters?

They naturally refine collection resources without changing the resource identity.

---

# Hands-on Exercises

### Exercise 1

Write a URL for paid orders sorted newest first.

**Answer**

```http
GET /orders?status=PAID&sort=createdAt,desc
```

