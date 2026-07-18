# Revision Notes

* PostgreSQL is a production-grade relational database.
* `psql` is the PostgreSQL command-line client.
* Use app-specific users instead of superuser accounts.
* Use migrations for schema changes.
* Java services should use connection pooling.
* WAL supports durability and crash recovery.
* MVCC allows concurrent reads and writes with row versions.
* Monitor slow queries, locks, connections, and disk.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Connect | `psql -h localhost -U app -d appdb` |
| List databases | `\l` |
| Switch database | `\c appdb` |
| List tables | `\dt` |
| Describe table | `\d users` |
| Health query | `SELECT 1;` |
| Docker image | `postgres:16` |

---

# Interview Questions with Answers

### 1. Why use connection pooling?

Connections are expensive. A pool reuses them and limits database load.

### 2. What is WAL?

Write-Ahead Log records changes before data pages are persisted, helping recovery after crashes.

### 3. Why avoid superuser from application code?

Least privilege limits damage from bugs, leaks, and compromised application credentials.

---

# Hands-on Exercises

### Exercise 1

Connect to local PostgreSQL database `appdb` as user `app`.

**Answer**

```bash
psql -h localhost -p 5432 -U app -d appdb
```

