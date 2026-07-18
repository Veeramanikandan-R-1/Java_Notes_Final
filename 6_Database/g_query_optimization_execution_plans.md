# Query Optimization

### Subtopic: Execution Plans

---

# 1. Fundamentals

Query optimization is the process of making SQL execute efficiently.

An execution plan shows how the database intends to run a query.

In PostgreSQL:

```sql
EXPLAIN
SELECT id, email
FROM users
WHERE email = 'a@example.com';
```

To actually run and measure:

```sql
EXPLAIN ANALYZE
SELECT id, email
FROM users
WHERE email = 'a@example.com';
```

---

# 2. Core Concepts

## Sequential Scan

Reads many or all table rows.

Good for small tables or queries returning a large portion of data.

## Index Scan

Uses an index to find matching rows, then reads table rows.

## Index Only Scan

Reads from the index without reading the table when visibility and selected columns allow it.

## Bitmap Index Scan

Collects matching row locations first, then fetches table pages efficiently.

Useful when many rows match but not enough to justify full sequential scan.

## Cost

PostgreSQL plans show estimated cost, not money or time directly.

The optimizer uses table statistics to estimate rows and choose plans.

## Rows

Compare estimated rows with actual rows in `EXPLAIN ANALYZE`.

Large differences often mean stale statistics, skewed data, or missing indexes.

---

# 3. Internal Working

The optimizer evaluates possible plans and picks the cheapest estimated plan.

It considers:

* Table size.
* Indexes.
* Column statistics.
* Join order.
* Filter selectivity.
* Sort and aggregation cost.
* Work memory.
* Parallel execution.

Execution plan analysis usually starts from the slowest or most repeated operation.

---

# 4. Common Mistakes

* Optimizing queries without measuring.
* Reading only estimated cost and ignoring actual time.
* Missing indexes on filtered or joined columns.
* Using functions in `WHERE` that prevent normal index usage.
* Sorting huge result sets unnecessarily.
* Paginating deeply with large `OFFSET`.
* Fetching more rows or columns than the API needs.
* Ignoring N+1 query behavior from ORMs.

Bad:

```sql
SELECT *
FROM orders
ORDER BY created_at DESC;
```

Better for an API:

```sql
SELECT id, total_amount, created_at
FROM orders
ORDER BY created_at DESC
LIMIT 50;
```

---

# 5. Best Practices

* Use `EXPLAIN ANALYZE` on representative data.
* Check actual rows versus estimated rows.
* Add indexes that match query filters and sort order.
* Prefer keyset pagination for large ordered lists.
* Select only required columns.
* Avoid N+1 queries by batching or joining intentionally.
* Keep statistics fresh with autovacuum and analyze.
* Optimize the highest-impact queries first.

---

# 6. Code Example

Problem query:

```sql
SELECT id, customer_id, created_at
FROM orders
WHERE customer_id = 42
ORDER BY created_at DESC
LIMIT 20;
```

Check plan:

```sql
EXPLAIN ANALYZE
SELECT id, customer_id, created_at
FROM orders
WHERE customer_id = 42
ORDER BY created_at DESC
LIMIT 20;
```

Potential index:

```sql
CREATE INDEX idx_orders_customer_created
ON orders(customer_id, created_at DESC);
```

Keyset pagination:

```sql
SELECT id, customer_id, created_at
FROM orders
WHERE customer_id = ?
  AND created_at < ?
ORDER BY created_at DESC
LIMIT 20;
```

---

# 7. Real-world Scenarios

* Slow customer order history API.
* Admin search timing out on large tables.
* Dashboard query aggregating millions of rows.
* ORM causing N+1 queries for parent and child records.
* Job queue worker scanning too many pending rows.

---

# Revision Notes

* Execution plans show how a query runs.
* `EXPLAIN` estimates.
* `EXPLAIN ANALYZE` runs and measures.
* Sequential scans are not always bad.
* Compare estimated rows and actual rows.
* Indexes should match filters, joins, and ordering.
* Keyset pagination beats deep offset for large data.

---

# Cheat Sheet

| Need | Command/Concept |
| ---- | --------------- |
| See estimated plan | `EXPLAIN SELECT ...` |
| Measure actual plan | `EXPLAIN ANALYZE SELECT ...` |
| Full table read | Sequential scan |
| Index lookup | Index scan |
| Index-only read | Index only scan |
| Stale stats fix | `ANALYZE table_name` |

---

# Interview Questions with Answers

### 1. What is an execution plan?

It is the database's chosen strategy for reading, joining, filtering, sorting, and returning query results.

### 2. Is a sequential scan always bad?

No. It can be efficient for small tables or queries that return a large percentage of rows.

### 3. Why is deep offset pagination slow?

The database still has to walk and discard earlier rows before returning the requested page.

---

# Hands-on Exercises

### Exercise 1

Write a command to measure the actual plan for a query.

**Answer**

```sql
EXPLAIN ANALYZE
SELECT id, email
FROM users
WHERE email = 'a@example.com';
```

### Exercise 2

Create an index for customer order history sorted newest first.

**Answer**

```sql
CREATE INDEX idx_orders_customer_created
ON orders(customer_id, created_at DESC);
```

