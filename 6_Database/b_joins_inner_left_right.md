# Joins

### Subtopic: Inner, Left, Right

---

# 1. Fundamentals

A join combines rows from multiple tables using related columns.

Backend systems use joins to load related business data without storing everything in one table.

Example tables:

```sql
customers(id, name)
orders(id, customer_id, total_amount, created_at)
```

`orders.customer_id` references `customers.id`.

---

# 2. Core Concepts

## INNER JOIN

Returns only matching rows from both tables.

```sql
SELECT c.id, c.name, o.id AS order_id, o.total_amount
FROM customers c
INNER JOIN orders o ON o.customer_id = c.id;
```

Customers without orders are not returned.

## LEFT JOIN

Returns all rows from the left table and matching rows from the right table.

```sql
SELECT c.id, c.name, o.id AS order_id
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id;
```

Customers without orders are returned with `NULL` order columns.

## RIGHT JOIN

Returns all rows from the right table and matching rows from the left table.

```sql
SELECT c.id, c.name, o.id AS order_id
FROM customers c
RIGHT JOIN orders o ON o.customer_id = c.id;
```

In practice, many teams rewrite `RIGHT JOIN` as `LEFT JOIN` by changing table order because it is easier to read.

## Filtering with Joins

Be careful where you put conditions.

```sql
SELECT c.id, c.name, o.id AS order_id
FROM customers c
LEFT JOIN orders o
    ON o.customer_id = c.id
   AND o.created_at >= CURRENT_DATE - INTERVAL '30 days';
```

This keeps customers even if they have no recent orders.

---

# 3. Internal Working

Databases use different join algorithms:

| Algorithm | Good For |
| --------- | -------- |
| Nested loop join | Small outer input, indexed inner lookup |
| Hash join | Large equality joins |
| Merge join | Sorted inputs or useful indexes |

The optimizer chooses based on statistics, indexes, filters, and estimated row counts.

Join performance depends heavily on:

* Foreign key columns.
* Indexes on join columns.
* Number of rows after filtering.
* Whether the join condition is selective.

---

# 4. Common Mistakes

* Confusing `LEFT JOIN` with `INNER JOIN`.
* Putting right-table filters in `WHERE` and accidentally removing unmatched rows.
* Joining on wrong columns.
* Creating duplicate rows by joining one-to-many tables without aggregation.
* Selecting many columns from many tables in hot API paths.
* Missing indexes on foreign key columns.

Bad:

```sql
SELECT c.id, o.id
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
WHERE o.status = 'PAID';
```

This behaves like an inner join for paid orders.

Good:

```sql
SELECT c.id, o.id
FROM customers c
LEFT JOIN orders o
    ON o.customer_id = c.id
   AND o.status = 'PAID';
```

---

# 5. Best Practices

* Prefer explicit `JOIN ... ON` syntax.
* Use table aliases that are short but readable.
* Index foreign key columns used in joins.
* Filter early when possible.
* Verify row counts when joining one-to-many relations.
* Use `LEFT JOIN` when missing related data is valid.
* Keep joins in repositories tuned to specific use cases.

---

# 6. Code Example

Find customers and their order counts:

```sql
SELECT c.id, c.name, COUNT(o.id) AS order_count
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
GROUP BY c.id, c.name
ORDER BY order_count DESC;
```

Load order details:

```sql
SELECT o.id,
       o.total_amount,
       c.name AS customer_name
FROM orders o
INNER JOIN customers c ON c.id = o.customer_id
WHERE o.id = ?;
```

---

# 7. Real-world Scenarios

* Order API joins orders with customer profile.
* Admin report left joins users with last login.
* Billing system joins invoice rows with payments.
* Audit screen joins events with actor user data.
* Data quality report finds customers without orders.

---

# Revision Notes

* `INNER JOIN` returns only matches.
* `LEFT JOIN` keeps all rows from the left table.
* `RIGHT JOIN` keeps all rows from the right table.
* Conditions in `WHERE` can change outer join behavior.
* One-to-many joins can multiply rows.
* Join columns usually need indexes.

---

# Cheat Sheet

| Join | Result |
| ---- | ------ |
| `INNER JOIN` | Matching rows only |
| `LEFT JOIN` | All left rows plus matches |
| `RIGHT JOIN` | All right rows plus matches |
| `ON` | Defines join relationship |
| `GROUP BY` | Aggregates joined rows |

---

# Interview Questions with Answers

### 1. Difference between inner join and left join?

Inner join returns only matching rows. Left join returns all rows from the left table and matching rows from the right table, using `NULL` when no match exists.

### 2. Why can a left join become an inner join?

If a filter on the right table is placed in `WHERE`, rows with `NULL` right-side values are removed.

### 3. Why are indexes important for joins?

Indexes help the database find matching rows faster, especially on foreign key and primary key columns.

---

# Hands-on Exercises

### Exercise 1

Find customers who have no orders.

**Answer**

```sql
SELECT c.id, c.name
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
WHERE o.id IS NULL;
```

### Exercise 2

Find paid orders with customer names.

**Answer**

```sql
SELECT o.id, c.name, o.total_amount
FROM orders o
INNER JOIN customers c ON c.id = o.customer_id
WHERE o.status = 'PAID';
```

