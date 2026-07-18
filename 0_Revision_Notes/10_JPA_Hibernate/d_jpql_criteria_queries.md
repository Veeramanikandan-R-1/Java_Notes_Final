# Revision Notes

* JPQL queries entity names and fields.
* SQL queries tables and columns.
* Use parameter binding.
* Fetch join loads association in the same query.
* Criteria API builds dynamic queries programmatically.
* DTO projections are useful for read-only responses.
* Inspect generated SQL for important queries.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Simple query | Derived method |
| Fixed custom query | JPQL |
| Dynamic filters | Criteria or Specification |
| Load association | `join fetch` |
| Read-only shape | DTO projection |
| Avoid injection | Bound parameters |

---

# Interview Questions with Answers

### 1. JPQL vs SQL?

JPQL uses entities and fields. SQL uses tables and columns.

### 2. When use Criteria?

For dynamic queries with optional filters.

### 3. What is DTO projection?

A query result mapped directly into a DTO instead of a managed entity.

---

# Hands-on Exercises

### Exercise 1

Write JPQL for orders by status.

**Answer**

```java
@Query("select o from Order o where o.status = :status")
List<Order> findByStatus(OrderStatus status);
```

