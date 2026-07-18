# SQL Basics

### Subtopic: CRUD

---

# 1. Fundamentals

SQL means Structured Query Language.

It is used to create, read, update, and delete data in relational databases.

CRUD means:

| Operation | SQL Command |
| --------- | ----------- |
| Create | `INSERT` |
| Read | `SELECT` |
| Update | `UPDATE` |
| Delete | `DELETE` |

Backend applications use CRUD in repositories, DAOs, ORM-generated queries, migrations, reports, and admin tools.

---

# 2. Core Concepts

## Tables, Rows, Columns

A table stores one type of entity.

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

Each row is one record. Each column stores one attribute.

## INSERT

```sql
INSERT INTO users (email, name, status)
VALUES ('a@example.com', 'Asha', 'ACTIVE');
```

Return generated values in PostgreSQL:

```sql
INSERT INTO users (email, name, status)
VALUES ('b@example.com', 'Bala', 'ACTIVE')
RETURNING id, created_at;
```

## SELECT

```sql
SELECT id, email, name
FROM users
WHERE status = 'ACTIVE'
ORDER BY created_at DESC
LIMIT 20;
```

Avoid `SELECT *` in production APIs unless you truly need every column.

## UPDATE

```sql
UPDATE users
SET status = 'BLOCKED'
WHERE id = 10;
```

Use a `WHERE` clause unless the whole table must change.

## DELETE

```sql
DELETE FROM users
WHERE id = 10;
```

Many production systems prefer soft delete:

```sql
UPDATE users
SET status = 'DELETED'
WHERE id = 10;
```

---

# 3. Internal Working

SQL is declarative.

You describe what result you want. The database optimizer decides how to execute it.

For a `SELECT`, the database may:

* Parse SQL.
* Validate table and column names.
* Choose an execution plan.
* Read table pages or index pages.
* Filter rows.
* Sort, aggregate, or join if needed.
* Return rows to the client.

For `INSERT`, `UPDATE`, and `DELETE`, the database also writes transaction logs so changes can be committed, rolled back, and recovered after failure.

---

# 4. Common Mistakes

* Running `UPDATE` or `DELETE` without `WHERE`.
* Using `SELECT *` in API paths.
* Trusting raw user input and causing SQL injection.
* Forgetting `ORDER BY` when pagination needs stable ordering.
* Storing numbers and dates as text.
* Ignoring constraints and doing all validation only in Java.
* Treating an empty result and an error as the same thing.

Bad:

```java
String sql = "SELECT * FROM users WHERE email = '" + email + "'";
```

Good:

```java
String sql = "SELECT id, email, name FROM users WHERE email = ?";
```

---

# 5. Best Practices

* Use explicit column names.
* Use prepared statements or ORM parameter binding.
* Add primary keys and meaningful constraints.
* Use transactions for multi-step writes.
* Paginate large result sets.
* Keep SQL readable and version schema changes with migrations.
* Validate important business invariants at both application and database level.

---

# 6. Code Example

```java
String sql = """
        SELECT id, email, name
        FROM users
        WHERE status = ?
        ORDER BY created_at DESC
        LIMIT ?
        """;

try (PreparedStatement ps = connection.prepareStatement(sql)) {
    ps.setString(1, "ACTIVE");
    ps.setInt(2, 20);

    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            long id = rs.getLong("id");
            String email = rs.getString("email");
            String name = rs.getString("name");
        }
    }
}
```

---

# 7. Real-world Scenarios

* User registration inserts a user row and returns generated id.
* Login reads user credentials by email.
* Admin dashboard updates account status.
* Data retention job soft-deletes old records.
* API endpoint reads a paginated list with filters.

---

# Revision Notes

* CRUD maps to `INSERT`, `SELECT`, `UPDATE`, and `DELETE`.
* SQL is declarative.
* Always use `WHERE` carefully for update and delete.
* Use prepared statements to prevent SQL injection.
* Use explicit columns and stable `ORDER BY` for pagination.
* Constraints protect data integrity.

---

# Cheat Sheet

| Need | SQL |
| ---- | --- |
| Create row | `INSERT INTO table (...) VALUES (...)` |
| Read rows | `SELECT columns FROM table WHERE condition` |
| Update rows | `UPDATE table SET column = value WHERE condition` |
| Delete rows | `DELETE FROM table WHERE condition` |
| Sort | `ORDER BY column DESC` |
| Limit result | `LIMIT 20` |

---

# Interview Questions with Answers

### 1. What is CRUD?

CRUD means Create, Read, Update, and Delete, the basic operations used to manage persistent data.

### 2. Why should backend code use prepared statements?

Prepared statements separate SQL structure from values, reducing SQL injection risk and helping the database reuse execution plans.

### 3. Why is `SELECT *` usually avoided?

It fetches unnecessary columns, makes API behavior depend on schema changes, and can increase network and memory cost.

---

# Hands-on Exercises

### Exercise 1

Create a query to fetch the latest 10 active users.

**Answer**

```sql
SELECT id, email, name
FROM users
WHERE status = 'ACTIVE'
ORDER BY created_at DESC
LIMIT 10;
```

### Exercise 2

Block a user by id.

**Answer**

```sql
UPDATE users
SET status = 'BLOCKED'
WHERE id = 101;
```

