# Testcontainers

### Subtopic: Postgres Containers

---

# 1. Fundamentals

Testcontainers lets Java tests start real Docker containers during test execution.

For backend testing, it is especially useful for running PostgreSQL, Redis, Kafka, RabbitMQ, Elasticsearch, and other infrastructure in a production-like way.

PostgreSQL Testcontainers helps avoid false confidence from in-memory databases.

Use it when you need to test:

* Real SQL syntax
* JPA mappings
* Migrations
* Database constraints
* JSONB, arrays, enums, UUIDs, timestamps, and indexes
* Transaction behavior close to production

---

# 2. Basic PostgreSQL Container

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class OrderRepositoryPostgresTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void contextLoads() {
    }
}
```

The container starts before the Spring context needs the datasource.

---

# 3. Dependencies

Typical Maven dependencies:

```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

Spring Boot dependency management usually controls compatible versions.

---

# 4. Internal Working

Testcontainers uses the Docker daemon.

At test runtime:

1. Java test starts.
2. Testcontainers requests Docker to pull or reuse the image.
3. Docker starts the PostgreSQL container.
4. Testcontainers waits until PostgreSQL is ready.
5. Spring receives JDBC URL, username, and password.
6. Tests run against the containerized database.
7. Container stops after the test class or JVM lifecycle.

The JDBC port is dynamically mapped. Do not hardcode `localhost:5432`.

---

# 5. Spring Boot 3.1+ Service Connection

Spring Boot can integrate directly with Testcontainers using service connections.

```java
@Testcontainers
@SpringBootTest
class OrderRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
}
```

This reduces manual datasource property registration when supported by the project setup.

---

# 6. Testing Migrations

Testcontainers is valuable with Flyway or Liquibase.

```java
@SpringBootTest
class MigrationTest {

    @Test
    void shouldStartApplicationWithRealPostgresSchema() {
    }
}
```

If migrations fail, the Spring context fails to start. That is useful feedback.

---

# 7. Common Mistakes

* Hardcoding database ports.
* Running tests without Docker available.
* Using different PostgreSQL major version than production.
* Recreating containers for every test method unnecessarily.
* Leaving test data shared between tests.
* Testing too many business rules through slow container tests.
* Using H2 for custom PostgreSQL SQL and assuming it is safe.

---

# 8. Best Practices

* Use a static container per test class for speed.
* Match production PostgreSQL major version when possible.
* Keep container tests focused on persistence and integration behavior.
* Clean test data between tests.
* Let Spring Boot manage dependency versions.
* Use dynamic properties or `@ServiceConnection`.
* Run Testcontainers in CI with Docker support.
* Keep fast unit tests separate from heavier integration tests.

---

# 9. Production Backend Example

```java
@DataJpaTest
@Testcontainers
class CustomerRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    CustomerRepository repository;

    @Test
    void shouldFindActiveCustomersByEmailDomain() {
        repository.save(new CustomerEntity("a@company.com", true));
        repository.save(new CustomerEntity("b@test.com", true));

        List<CustomerEntity> result = repository.findActiveByEmailDomain("company.com");

        assertEquals(1, result.size());
    }
}
```

---

# 10. Real-world Scenarios

* Validate Flyway migrations before deployment.
* Test native PostgreSQL queries.
* Verify unique constraints.
* Test JSONB columns.
* Reproduce production database bugs locally.
* Run repository tests in CI with the same database engine as production.

---

# Revision Notes

* Testcontainers starts real Docker containers for tests.
* PostgreSQL containers are better than H2 for PostgreSQL-specific behavior.
* Use dynamic datasource properties because ports are mapped dynamically.
* `@Container` manages container lifecycle with JUnit.
* `@DynamicPropertySource` injects runtime container connection details.
* Spring Boot `@ServiceConnection` can simplify container configuration.
* Container tests are slower than unit tests, so keep them focused.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Enable Testcontainers | `@Testcontainers` |
| Define container | `@Container` |
| PostgreSQL image | `PostgreSQLContainer<?>` |
| Register datasource | `@DynamicPropertySource` |
| Spring Boot shortcut | `@ServiceConnection` |
| Avoid hardcoded port | `postgres.getJdbcUrl()` |
| Common command check | `docker ps` |

---

# Interview Questions with Answers

### 1. Why use Testcontainers instead of H2?

It tests against the real database engine, reducing differences in SQL behavior, data types, constraints, and transactions.

### 2. Why should database port not be hardcoded?

Docker maps container ports dynamically, so tests should use the JDBC URL provided by Testcontainers.

### 3. What does `@DynamicPropertySource` do?

It supplies runtime properties, such as datasource URL and credentials, to the Spring test context.

### 4. What is a drawback of Testcontainers?

It requires Docker and is slower than pure unit tests.

---

# Hands-on Exercises

### Exercise 1

Create a PostgreSQL container and configure Spring datasource properties from it.

### Exercise 2

Write a repository test that verifies a unique constraint violation.

