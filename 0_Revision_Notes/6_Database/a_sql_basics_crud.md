# Revision Notes

* CRUD maps to `INSERT`, `SELECT`, `UPDATE`, and `DELETE`.
* SQL is declarative: describe the result, database chooses the plan.
* Use explicit columns instead of `SELECT *` in backend APIs.
* Always review `WHERE` before `UPDATE` or `DELETE`.
* Use prepared statements or ORM parameter binding.
* Use constraints for data integrity.

---

# Cheat Sheet

| Need | SQL |
| ---- | --- |
| Insert | `INSERT INTO users (...) VALUES (...)` |
| Read | `SELECT id, email FROM users WHERE id = ?` |
| Update | `UPDATE users SET status = ? WHERE id = ?` |
| Delete | `DELETE FROM users WHERE id = ?` |
| Sort | `ORDER BY created_at DESC` |
| Page | `LIMIT 20 OFFSET 0` |

---

# Interview Questions with Answers

### 1. What is CRUD?

Create, Read, Update, and Delete operations used to manage persistent data.

### 2. Why use prepared statements?

They prevent SQL injection by separating SQL structure from input values.

### 3. Why is stable ordering important for pagination?

Without `ORDER BY`, the database does not guarantee row order across requests.

---

# Hands-on Exercises

### Exercise 1

Fetch latest active users.

**Answer**

```sql
SELECT id, email, name
FROM users
WHERE status = 'ACTIVE'
ORDER BY created_at DESC
LIMIT 10;
```

