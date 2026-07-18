# Revision Notes

* Testcontainers starts real Docker containers during tests.
* PostgreSQL containers test real PostgreSQL behavior.
* Use Testcontainers for migrations, native queries, constraints, and database-specific types.
* `@Container` controls container lifecycle.
* `@DynamicPropertySource` registers runtime connection properties.
* Do not hardcode ports; use `postgres.getJdbcUrl()`.
* Tests need Docker support locally and in CI.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable Testcontainers | `@Testcontainers` |
| Container field | `@Container` |
| PostgreSQL | `PostgreSQLContainer<?>` |
| Runtime properties | `@DynamicPropertySource` |
| Spring Boot shortcut | `@ServiceConnection` |
| JDBC URL | `postgres.getJdbcUrl()` |

---

# Interview Q&A

### 1. Why Testcontainers?

It gives production-like infrastructure in tests and avoids differences from in-memory substitutes.

### 2. Why not hardcode database port?

Docker maps ports dynamically, so the mapped JDBC URL must come from the container.

### 3. What is the main tradeoff?

Container tests are more realistic but slower and require Docker.

---

# Exercises

### Exercise 1

Configure Spring datasource properties from a PostgreSQL container.

### Exercise 2

Test a unique constraint violation using real PostgreSQL.

