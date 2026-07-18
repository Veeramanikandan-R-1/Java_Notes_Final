# Spring Boot

### Subtopic: Spring Boot Project Structure - src/main, resources, application.properties, application.yml

---

# 1. Fundamentals

Spring Boot follows the standard Maven and Gradle Java project structure.

```text
src/main/java
src/main/resources
src/test/java
src/test/resources
```

Following this structure lets build tools and Spring Boot find code, tests, templates, static files, and configuration consistently.

---

# 2. Core Concepts

## src/main/java

Contains production Java code:

```text
com.example.order
  OrderApplication.java
  controller
  service
  repository
  dto
  exception
  config
```

Place the main application class in the root package so component scanning covers subpackages.

---

## src/main/resources

Contains non-Java resources:

```text
application.properties
application.yml
static/
templates/
db/migration/
```

---

## application.properties

```properties
server.port=8081
spring.application.name=order-service
```

Properties format is simple and flat.

---

## application.yml

```yaml
server:
  port: 8081
spring:
  application:
    name: order-service
```

YAML is better for nested configuration.

---

# 3. Internal Working

Spring Boot loads config from predictable locations and maps values into the environment.

Configuration is later used by auto configuration and your own beans.

Profile-specific files like `application-dev.yml` override default values when the profile is active.

---

# 4. Common Mistakes

* Placing the main class too deep in the package tree.
* Mixing unrelated responsibilities in one package.
* Committing secrets in application files.
* Duplicating config between `.properties` and `.yml`.
* Keeping DTOs, entities, and controllers in the same package without structure.

---

# 5. Best Practices

* Keep main class at root package.
* Use clear package boundaries.
* Prefer YAML for grouped config.
* Keep secrets in environment variables or vault tools.
* Separate controller, service, repository, DTO, config, and exception packages.

---

# 6. Code Example

```text
src/main/java/com/example/order
  OrderApplication.java
  controller/OrderController.java
  service/OrderService.java
  dto/CreateOrderRequest.java
  exception/GlobalExceptionHandler.java
src/main/resources
  application.yml
```

---

# 7. Real-world Scenarios

* Organizing medium-sized REST services.
* Keeping config environment-neutral.
* Adding database migration files.
* Supporting static assets for simple admin pages.
* Separating test fixtures under `src/test/resources`.

---

# Revision Notes

* Java code goes under `src/main/java`.
* Config and resources go under `src/main/resources`.
* Tests go under `src/test/java`.
* Main class should be in root package.
* Use either properties or YAML consistently.
* Do not commit secrets.

---

# Cheat Sheet

| Path | Purpose |
| ---- | ------- |
| `src/main/java` | Production code |
| `src/main/resources` | Config/resources |
| `src/test/java` | Test code |
| `application.properties` | Flat config |
| `application.yml` | Nested config |

---

# Interview Questions with Answers

### 1. Why should main class be in root package?

So component scanning naturally discovers controllers, services, repositories, and configuration classes in subpackages.

### 2. Difference between properties and YAML?

Properties is flat key-value format. YAML supports hierarchical structure.

### 3. Where should static files go?

Usually under `src/main/resources/static`.

---

# Hands-on Exercises

### Exercise 1

Write YAML config for application name and port.

**Answer**

```yaml
spring:
  application:
    name: user-service
server:
  port: 8082
```

