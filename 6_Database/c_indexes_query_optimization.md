# Indexes

### Subtopic: Query Optimization

---

# 1. Fundamentals

An index is a data structure that helps the database find rows faster.

Without an index, the database may scan every row in a table.

With an index, it can locate matching rows using a smaller, ordered structure.

Common example:

```sql
CREATE INDEX idx_users_email ON users(email);
```

---

# 2. Core Concepts

## Primary Key Index

Primary keys are indexed automatically in most relational databases.

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);
```

## Unique Index

Ensures values are unique and helps lookup.

```sql
CREATE UNIQUE INDEX uq_users_email ON users(email);
```

## Composite Index

Uses multiple columns.

```sql
CREATE INDEX idx_orders_customer_status
ON orders(customer_id, status);
```

Useful query:

```sql
SELECT id, total_amount
FROM orders
WHERE customer_id = 42
  AND status = 'PAID';
```

## Index Column Order

For composite indexes, order matters.

`(customer_id, status)` helps queries filtering by `customer_id`, or by both `customer_id` and `status`.

It usually does not help much for filtering by `status` alone.

## Covering Index

An index can satisfy a query without reading the table if it contains all required columns.

PostgreSQL supports included columns:

```sql
CREATE INDEX idx_orders_customer_created_include_total
ON orders(customer_id, created_at)
INCLUDE (total_amount);
```

---

# 3. Internal Working

Most relational databases use B-tree indexes by default.

A B-tree keeps keys sorted and allows efficient:

* Equality lookup.
* Range lookup.
* Ordered scans.
* Prefix lookup on composite indexes.

Indexes are not free.

Every insert, update, and delete must also maintain related indexes. Too many indexes can slow writes and increase storage.

---

# 4. Common Mistakes

* Adding indexes without checking query patterns.
* Indexing low-selectivity columns blindly, such as a boolean flag.
* Creating duplicate or overlapping indexes.
* Using functions on indexed columns without functional indexes.
* Expecting an index to help when the query returns most rows.
* Ignoring write overhead.
* Forgetting indexes for foreign key joins.

Bad:

```sql
SELECT id
FROM users
WHERE LOWER(email) = LOWER(?);
```

This may not use a normal index on `email`.

Better:

```sql
CREATE INDEX idx_users_lower_email ON users(LOWER(email));
```

---

# 5. Best Practices

* Start from real slow queries, not guesses.
* Index columns used in `WHERE`, `JOIN`, `ORDER BY`, and selective filters.
* Use composite indexes for common multi-column filters.
* Put equality columns before range columns in many composite indexes.
* Remove unused indexes after measuring.
* Review index impact on write-heavy tables.
* Use `EXPLAIN` and production-like data before deciding.

---

# 6. Code Example

Query:

```sql
SELECT id, total_amount, created_at
FROM orders
WHERE customer_id = ?
  AND status = 'PAID'
ORDER BY created_at DESC
LIMIT 20;
```

Index:

```sql
CREATE INDEX idx_orders_customer_status_created
ON orders(customer_id, status, created_at DESC);
```

Java repository usage:

```java
String sql = """
        SELECT id, total_amount, created_at
        FROM orders
        WHERE customer_id = ?
          AND status = ?
        ORDER BY created_at DESC
        LIMIT ?
        """;
```

---

# 7. Real-world Scenarios

* Login lookup by email.
* Order history by customer and date.
* Job worker fetches pending tasks by status and priority.
* Admin searches records by external reference id.
* API endpoint sorts latest records with pagination.

---

# Revision Notes

* Indexes speed reads but slow writes.
* B-tree is the common default index type.
* Composite index column order matters.
* Unique indexes enforce data integrity.
* Indexes should match actual query patterns.
* Use execution plans to verify index usage.

---

# Cheat Sheet

| Need | SQL |
| ---- | --- |
| Basic index | `CREATE INDEX idx_name ON table(column)` |
| Unique index | `CREATE UNIQUE INDEX idx_name ON table(column)` |
| Composite index | `CREATE INDEX idx ON table(a, b)` |
| Functional index | `CREATE INDEX idx ON table(LOWER(email))` |
| Check plan | `EXPLAIN ANALYZE SELECT ...` |

---

# Interview Questions with Answers

### 1. What is an index?

An index is a database data structure that improves lookup speed by avoiding unnecessary full table scans.

### 2. Why not index every column?

Indexes consume storage and slow down writes because each data change must update the indexes too.

### 3. What is a composite index?

A composite index is an index over multiple columns. Its usefulness depends on the query filters and column order.

---

# Hands-on Exercises

### Exercise 1

Create an index for finding orders by customer and status.

**Answer**

```sql
CREATE INDEX idx_orders_customer_status
ON orders(customer_id, status);
```

### Exercise 2

Create a case-insensitive email lookup index.

**Answer**

```sql
CREATE INDEX idx_users_lower_email
ON users(LOWER(email));
```

