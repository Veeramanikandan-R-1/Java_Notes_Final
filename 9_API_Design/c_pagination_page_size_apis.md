# Pagination

### Subtopic: Page and Size APIs

---

# 1. Fundamentals

Pagination splits large result sets into smaller chunks so APIs stay fast, predictable, and safe for clients.

Common request:

```http
GET /products?page=0&size=20
```

`page` identifies which slice is requested. `size` controls how many records are returned.

---

# 2. Core Concepts

## Page Numbering

Spring Data uses zero-based pages by default.

```text
page=0 -> first page
page=1 -> second page
```

Public APIs must document whether pages are zero-based or one-based.

## Response Shape

Good paginated responses include data and metadata.

```json
{
  "content": [],
  "page": 0,
  "size": 20,
  "totalElements": 145,
  "totalPages": 8,
  "last": false
}
```

## Limits

APIs should enforce maximum page size.

```text
size <= 100
```

This protects memory, database load, and response time.

## Offset Pagination

Page and size usually translate to SQL offset and limit.

```sql
SELECT * FROM products
ORDER BY id
LIMIT 20 OFFSET 40;
```

For very large datasets, offset pagination can become slow because the database still has to walk skipped rows.

---

# 3. Internal Working

In Spring Data JPA, `Pageable` carries page, size, and sort information.

```java
Page<Product> page = productRepository.findAll(PageRequest.of(0, 20));
```

`Page<T>` usually runs two queries:

* Main query for current content.
* Count query for total elements.

If total count is expensive and not needed, use `Slice<T>`.

---

# 4. Common Mistakes

* Returning all records from an endpoint.
* Not setting max page size.
* Forgetting stable sorting.
* Exposing database entities directly in page content.
* Using one-based API docs with zero-based backend behavior.
* Running expensive count queries unnecessarily.

---

# 5. Best Practices

* Always define default page and size.
* Enforce maximum size.
* Use stable sort, usually by `id` or creation time plus `id`.
* Return DTOs, not entities.
* Use `Slice` for infinite scrolling when total count is not required.
* Validate negative page numbers and invalid sizes.

---

# 6. Code Example

```java
@GetMapping("/products")
public Page<ProductResponse> listProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {

    int safeSize = Math.min(size, 100);
    Pageable pageable = PageRequest.of(page, safeSize, Sort.by("id").ascending());

    return productRepository.findAll(pageable)
            .map(product -> new ProductResponse(product.getId(), product.getName()));
}
```

```java
record ProductResponse(Long id, String name) {}
```

---

# 7. Real-world Scenarios

* Product catalog listing.
* Admin user search.
* Audit log browsing.
* Transaction history.
* Infinite scroll feed using `Slice`.

---

# Revision Notes

* Pagination avoids large unsafe responses.
* `page` and `size` represent result slice.
* Spring Data pages are zero-based by default.
* `Page<T>` includes total count metadata.
* `Slice<T>` avoids count query.
* Always enforce max page size and stable sort.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| First page | `page=0` |
| Page size | `size=20` |
| Max size | Validate server-side |
| Spring object | `Pageable` |
| With total count | `Page<T>` |
| Without total count | `Slice<T>` |

---

# Interview Questions with Answers

### 1. Why is pagination important?

It protects API performance, database load, network usage, and client memory.

### 2. What is the difference between `Page` and `Slice`?

`Page` includes total count information. `Slice` only tells whether another slice exists and avoids the count query.

### 3. Why is stable sorting required?

Without stable ordering, records can move between pages and clients may see duplicates or miss data.

---

# Hands-on Exercises

### Exercise 1

Write a paginated API for users with default size 25 and max size 100.

**Answer**

```java
@GetMapping("/users")
Page<UserResponse> users(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "25") int size) {
    Pageable pageable = PageRequest.of(page, Math.min(size, 100), Sort.by("id"));
    return userRepository.findAll(pageable).map(UserResponse::from);
}
```

