# Spring Boot

### Subtopic: Why Spring Boot - Need and Advantages

---

# 1. Fundamentals

Spring Boot makes Spring application development faster by reducing manual setup.

Before Spring Boot, developers had to configure many things explicitly:

* Servlet container
* Dependency versions
* Component scanning
* JSON conversion
* Web MVC configuration
* Logging defaults

Spring Boot provides opinionated defaults so teams can start with production-ready conventions.

---

# 2. Core Concepts

## Need for Spring Boot

Spring is powerful but configuration-heavy.

Spring Boot solves this by providing:

| Feature | Purpose |
| ------- | ------- |
| Starters | Dependency bundles |
| Auto configuration | Creates beans based on classpath and properties |
| Embedded server | Runs app as standalone JAR |
| Actuator | Production monitoring endpoints |
| Externalized config | Environment-specific values |

---

## Main Class

```java
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
```

`@SpringBootApplication` combines component scanning, auto configuration, and configuration support.

---

# 3. Internal Working

Spring Boot checks the classpath and configuration properties.

If `spring-boot-starter-web` is present, Boot configures Spring MVC, Jackson, validation support, and an embedded server.

This behavior is conditional. Boot creates defaults only when your application has not defined a replacement bean.

---

# 4. Common Mistakes

* Thinking Spring Boot replaces Spring Core.
* Adding many starters without understanding dependencies.
* Fighting auto configuration instead of customizing it.
* Hardcoding environment-specific values.
* Ignoring actuator and logging setup in production.

---

# 5. Best Practices

* Use starters intentionally.
* Keep the main class at the root package.
* Override Boot defaults only when required.
* Externalize config through properties, YAML, or environment variables.
* Add actuator and health checks for deployable services.

---

# 6. Code Example

```java
@RestController
class HealthController {
    @GetMapping("/ping")
    String ping() {
        return "pong";
    }
}
```

With Spring Boot web starter, this can run inside the embedded server without manual servlet container setup.

---

# 7. Real-world Scenarios

* Building REST APIs quickly.
* Creating microservices with embedded Tomcat.
* Standardizing dependency versions.
* Deploying executable JARs.
* Adding operational endpoints with actuator.

---

# Revision Notes

* Spring Boot simplifies Spring application setup.
* It uses starters, auto configuration, and embedded servers.
* `@SpringBootApplication` is the main entry annotation.
* Boot configures defaults based on classpath and properties.
* It does not replace Spring Core; it builds on top of it.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Main app | `@SpringBootApplication` |
| Run app | `SpringApplication.run()` |
| REST starter | `spring-boot-starter-web` |
| Monitoring | `spring-boot-starter-actuator` |
| Config | `application.properties` / `application.yml` |

---

# Interview Questions with Answers

### 1. Why use Spring Boot?

To reduce setup time and get production-friendly defaults for Spring applications.

### 2. What are starters?

Curated dependency bundles for common features like web, data, validation, and security.

### 3. What is auto configuration?

Conditional configuration that creates beans based on classpath, properties, and existing beans.

---

# Hands-on Exercises

### Exercise 1

Create a minimal Boot application class.

**Answer**

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

