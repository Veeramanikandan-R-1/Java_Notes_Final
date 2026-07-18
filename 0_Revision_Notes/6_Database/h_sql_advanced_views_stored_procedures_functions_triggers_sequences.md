# Revision Notes

* View is a stored query exposed like a table.
* Function returns a value or table and can be used in SQL.
* Procedure performs actions and is called with `CALL`.
* Trigger runs automatically on insert, update, or delete.
* Sequence generates numeric values.
* PostgreSQL sequence values can have gaps.
* Keep triggers small, predictable, and documented.
* Version database-side code through migrations.

---

# Cheat Sheet

| Feature | Main Use |
| ------- | -------- |
| View | Reusable read query |
| Function | Return a value/table |
| Procedure | Perform action |
| Trigger | Auto-run on data change |
| Sequence | Generate numbers |
| Call procedure | `CALL name(...)` |
| Next sequence | `nextval('seq')` |

---

# Interview Questions with Answers

### 1. What is a view?

A saved query that can be queried like a table.

### 2. Difference between procedure and function?

A function returns a value. A procedure performs actions and is called with `CALL`.

### 3. Are sequences gap-free?

No. PostgreSQL sequence values are not rolled back, so gaps are normal.

---

# Hands-on Exercises

### Exercise 1

Create a view for active users.

**Answer**

```sql
CREATE VIEW active_users AS
SELECT id, email, name
FROM users
WHERE status = 'ACTIVE';
```

