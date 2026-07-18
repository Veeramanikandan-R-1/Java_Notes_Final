# Revision Notes

* Execution plans show how the database runs a query.
* `EXPLAIN` shows estimated plan.
* `EXPLAIN ANALYZE` executes and measures actual runtime.
* Sequential scan reads many or all table rows.
* Index scan uses an index to locate rows.
* Compare estimated rows with actual rows.
* Deep `OFFSET` pagination can become slow.
* Fix high-impact slow queries first.

---

# Cheat Sheet

| Need | Command/Concept |
| ---- | --------------- |
| Estimated plan | `EXPLAIN SELECT ...` |
| Actual plan | `EXPLAIN ANALYZE SELECT ...` |
| Full read | Sequential scan |
| Indexed lookup | Index scan |
| Index-only lookup | Index only scan |
| Refresh stats | `ANALYZE table_name` |
| Better pagination | Keyset pagination |

---

# Interview Questions with Answers

### 1. What is an execution plan?

The database's selected strategy for scanning, joining, filtering, sorting, and returning rows.

### 2. Is sequential scan always a problem?

No. It can be the best choice for small tables or queries returning many rows.

### 3. Why compare estimated and actual rows?

Large differences can reveal stale statistics, skewed data, or poor plan choices.

---

# Hands-on Exercises

### Exercise 1

Measure a query plan for email lookup.

**Answer**

```sql
EXPLAIN ANALYZE
SELECT id, email
FROM users
WHERE email = 'a@example.com';
```

