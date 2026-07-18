# Revision Notes

* A transaction is one unit of database work.
* ACID means Atomicity, Consistency, Isolation, Durability.
* `COMMIT` makes changes permanent.
* `ROLLBACK` cancels uncommitted changes.
* PostgreSQL uses MVCC for concurrent reads and writes.
* Keep transactions short.
* Retry deadlocks and serialization failures when operation is safe to retry.

---

# Cheat Sheet

| Need | SQL/Java |
| ---- | -------- |
| Start | `BEGIN` |
| Commit | `COMMIT` |
| Cancel | `ROLLBACK` |
| JDBC | `connection.setAutoCommit(false)` |
| Spring | `@Transactional` |
| Strongest isolation | `SERIALIZABLE` |

---

# Interview Questions with Answers

### 1. What does atomicity mean?

All operations in a transaction succeed together or fail together.

### 2. What does isolation control?

How concurrent transactions see and affect each other's changes.

### 3. Why keep transactions short?

To reduce locks, contention, deadlocks, and cleanup pressure.

---

# Hands-on Exercises

### Exercise 1

Transfer money between accounts in SQL.

**Answer**

```sql
BEGIN;
UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;
```

