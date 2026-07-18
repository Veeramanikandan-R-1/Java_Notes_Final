# Revision Notes

* Joins combine related rows from multiple tables.
* `INNER JOIN` returns only matching rows.
* `LEFT JOIN` keeps all rows from the left table.
* `RIGHT JOIN` keeps all rows from the right table.
* Filtering right-side columns in `WHERE` can remove unmatched left rows.
* One-to-many joins can multiply result rows.
* Index join columns, especially foreign keys.

---

# Cheat Sheet

| Need | SQL |
| ---- | --- |
| Matches only | `INNER JOIN` |
| Keep left rows | `LEFT JOIN` |
| Keep right rows | `RIGHT JOIN` |
| Missing right rows | `WHERE right_table.id IS NULL` |
| Aggregate child rows | `GROUP BY parent.id` |

---

# Interview Questions with Answers

### 1. Difference between inner and left join?

Inner join returns matching rows only. Left join returns all left rows plus matching right rows.

### 2. Why can joins create duplicates?

A parent row appears once for each matching child row in a one-to-many relationship.

### 3. Why prefer explicit join syntax?

It makes relationships and filters easier to read and review.

---

# Hands-on Exercises

### Exercise 1

Find customers without orders.

**Answer**

```sql
SELECT c.id, c.name
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
WHERE o.id IS NULL;
```

