# PostgreSQL

### Subtopic: Setup and Usage

---

# 1. Fundamentals

PostgreSQL is an open-source relational database widely used in production backend systems.

It supports:

* SQL.
* Transactions.
* ACID guarantees.
* Indexes.
* JSON data.
* Stored procedures and functions.
* Extensions.
* Strong concurrency through MVCC.

---

# 2. Core Concepts

## Installation

Common options:

```bash
docker run --name postgres-dev \
  -e POSTGRES_USER=app \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=appdb \
  -p 5432:5432 \
  -d postgres:16
```

Check container:

```bash
docker ps
```

Connect:

```bash
psql -h localhost -p 5432 -U app -d appdb
```

## Basic psql Commands

| Command | Meaning |
| ------- | ------- |
| `\l` | List databases |
| `\c appdb` | Connect to database |
| `\dt` | List tables |
| `\d users` | Describe table |
| `\q` | Quit |

## Users and Databases

```sql
CREATE USER app_user WITH PASSWORD 'strong_password';
CREATE DATABASE app_db OWNER app_user;
GRANT ALL PRIVILEGES ON DATABASE app_db TO app_user;
```

## Schema

A schema is a namespace inside a database.

```sql
CREATE SCHEMA app;
CREATE TABLE app.users (
    id BIGSERIAL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE
);
```

---

# 3. Internal Working

PostgreSQL stores data in pages, uses WAL for durability, and uses MVCC for concurrency.

Important pieces:

| Term | Meaning |
| ---- | ------- |
| WAL | Write-Ahead Log for crash recovery |
| MVCC | Multiple row versions for concurrent transactions |
| Vacuum | Cleans old row versions |
| Analyze | Updates statistics for optimizer |
| Connection | Database session from app or tool |

Backend apps should usually connect through a connection pool, not create a new connection per request.

---

# 4. Common Mistakes

* Using the superuser account from application code.
* Running production with weak passwords.
* Opening too many connections instead of pooling.
* Forgetting migrations and changing schema manually.
* Ignoring `VACUUM` and statistics on busy systems.
* Storing local timestamps without a clear timezone policy.
* Logging SQL with sensitive values.

---

# 5. Best Practices

* Use least-privilege database users.
* Use migration tools such as Flyway or Liquibase.
* Use a connection pool such as HikariCP.
* Keep credentials in environment variables or secret managers.
* Use `TIMESTAMPTZ` for absolute timestamps.
* Back up and test restore procedures.
* Monitor slow queries, locks, connection count, and disk usage.

---

# 6. Code Example

Spring Boot configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/appdb
spring.datasource.username=app
spring.datasource.password=secret
spring.datasource.hikari.maximum-pool-size=10
spring.jpa.hibernate.ddl-auto=validate
```

JDBC dependency:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

Health query:

```sql
SELECT 1;
```

---

# 7. Real-world Scenarios

* Local development database using Docker.
* Spring Boot service connects through HikariCP.
* Flyway runs schema migrations on startup or deployment.
* Admin uses `psql` to inspect tables and indexes.
* Production monitoring checks slow queries and locks.

---

# Revision Notes

* PostgreSQL is a production-grade relational database.
* `psql` is the command-line client.
* Use app-specific users, not superuser accounts.
* Use migrations for schema changes.
* Use connection pooling from Java apps.
* WAL provides durability and crash recovery.
* MVCC supports concurrent reads and writes.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Run with Docker | `docker run ... postgres:16` |
| Connect | `psql -h localhost -U app -d appdb` |
| List databases | `\l` |
| List tables | `\dt` |
| Describe table | `\d table_name` |
| Health check | `SELECT 1;` |

---

# Interview Questions with Answers

### 1. Why use PostgreSQL for backend systems?

It provides relational modeling, transactions, indexes, concurrency, extensions, and reliable production behavior.

### 2. What is WAL?

Write-Ahead Log records changes before data pages are written, allowing crash recovery.

### 3. Why use a connection pool?

Opening database connections is expensive. A pool reuses connections and controls load on the database.

---

# Hands-on Exercises

### Exercise 1

Start PostgreSQL locally with Docker.

**Answer**

```bash
docker run --name postgres-dev \
  -e POSTGRES_USER=app \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_DB=appdb \
  -p 5432:5432 \
  -d postgres:16
```

### Exercise 2

List tables after connecting with `psql`.

**Answer**

```sql
\dt
```

