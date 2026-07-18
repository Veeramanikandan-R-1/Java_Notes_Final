# Spring Boot

### Subtopic: Spring Boot Basics - Auto Configuration

---

# 1. Fundamentals

Auto configuration is Spring Boot's ability to configure beans automatically based on dependencies, properties, and existing beans.

Example:

If `spring-boot-starter-web` is on the classpath, Boot configures Spring MVC, JSON serialization, validation integration, and an embedded web server.

---

# 2. Core Concepts

## Conditional Configuration

Boot auto configuration is conditional.

Common conditions:

| Condition | Meaning |
| --------- | ------- |
| Class present | Dependency exists |
| Bean missing | User has not defined custom bean |
| Property value | Config enables feature |
| Web app type | Servlet or reactive app |

---

## Overriding Defaults

You can customize Boot by defining your own bean.

```java
@Bean
ObjectMapper objectMapper() {
    return new ObjectMapper()
            .findAndRegisterModules();
}
```

Boot backs off when a user-defined bean replaces the default.

---

# 3. Internal Working

`@SpringBootApplication` enables auto configuration.

Boot imports auto-configuration classes declared by framework metadata. Each auto configuration class uses conditions to decide if it should create beans.

Debugging options:

```properties
debug=true
```

or use actuator condition report when available.

---

# 4. Common Mistakes

* Assuming auto configuration is magic and not inspectable.
* Overriding defaults unnecessarily.
* Adding dependencies that accidentally activate configuration.
* Defining duplicate beans of the same type.
* Not checking condition reports while debugging startup.

---

# 5. Best Practices

* Trust defaults first.
* Override with focused beans or properties.
* Keep custom configuration small.
* Use starters instead of manually assembling common dependencies.
* Inspect auto-configuration reports when behavior is unclear.

---

# 6. Code Example

```java
@Configuration
class WebConfig {
    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }
}
```

Adding a custom bean allows application services to depend on `Clock` consistently.

---

# 7. Real-world Scenarios

* Boot configures embedded Tomcat for web apps.
* Boot configures Jackson for JSON APIs.
* Boot configures datasource when JDBC dependencies and properties exist.
* Boot configures logging defaults.
* Teams override only selected infrastructure beans.

---

# Revision Notes

* Auto configuration creates beans conditionally.
* It is based on classpath, properties, web type, and existing beans.
* User-defined beans can override Boot defaults.
* `@SpringBootApplication` enables auto configuration.
* Use condition reports to debug.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable Boot app | `@SpringBootApplication` |
| Debug conditions | `debug=true` |
| Replace default | Define custom `@Bean` |
| Web defaults | `spring-boot-starter-web` |

---

# Interview Questions with Answers

### 1. What is auto configuration?

Spring Boot's conditional creation of default beans based on the application setup.

### 2. How do you override auto configuration?

Usually by defining your own bean or setting relevant properties.

### 3. Is auto configuration unconditional?

No. It uses conditions to decide when to apply.

---

# Hands-on Exercises

### Exercise 1

Enable Boot debug condition output.

**Answer**

```properties
debug=true
```

