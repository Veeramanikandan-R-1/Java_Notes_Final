# Revision Notes

* Indexes speed reads by reducing table scanning.
* Indexes slow writes because they must be maintained.
* B-tree is the common default index type.
* Composite index column order matters.
* Unique indexes enforce uniqueness.
* Match indexes to real query filters, joins, and sorts.
* Use `EXPLAIN ANALYZE` to verify.

---

# Cheat Sheet

| Need | SQL |
| ---- | --- |
| Basic index | `CREATE INDEX idx ON table(column)` |
| Unique | `CREATE UNIQUE INDEX idx ON table(column)` |
| Composite | `CREATE INDEX idx ON table(a, b)` |
| Functional | `CREATE INDEX idx ON table(LOWER(email))` |
| Plan | `EXPLAIN ANALYZE SELECT ...` |

---

# Interview Questions with Answers

### 1. What is an index?

A data structure that helps the database find rows faster.

### 2. Why not index every column?

Too many indexes increase storage and slow insert, update, and delete operations.

### 3. What is composite index order?

The order of columns in a multi-column index affects which queries can use it efficiently.

---

# Hands-on Exercises

### Exercise 1

Index order history by customer, status, and newest first.

**Answer**

```sql
CREATE INDEX idx_orders_customer_status_created
ON orders(customer_id, status, created_at DESC);
```

