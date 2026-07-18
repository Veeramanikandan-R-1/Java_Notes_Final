# Spring Core

### Subtopic: Configuration - @Configuration, @Bean, @ConfigurationProperties

---

# 1. Fundamentals

Spring configuration tells the container which objects to create and how to configure them.

Annotations commonly used:

| Annotation | Purpose |
| ---------- | ------- |
| `@Configuration` | Declares a configuration class |
| `@Bean` | Registers a bean from a method |
| `@ConfigurationProperties` | Binds external properties to a typed class |

---

# 2. Core Concepts

## @Configuration

```java
@Configuration
class AppConfig {
}
```

It marks a class as a source of bean definitions.

---

## @Bean

Use `@Bean` when the object is from a third-party library or needs manual construction.

```java
@Bean
RestClient restClient() {
    return RestClient.create();
}
```

---

## @ConfigurationProperties

Use this for structured configuration.

```java
@ConfigurationProperties(prefix = "payment")
public record PaymentProperties(
        String baseUrl,
        int timeoutSeconds
) {
}
```

Example properties:

```properties
payment.base-url=https://payments.example.com
payment.timeout-seconds=5
```

---

# 3. Internal Working

Spring reads environment properties from sources such as `.properties`, `.yml`, environment variables, command-line arguments, and config servers.

`@ConfigurationProperties` binds matching keys to fields or constructor parameters.

`@Bean` methods inside `@Configuration` classes are managed by Spring so repeated calls can return the container-managed singleton.

---

# 4. Common Mistakes

* Putting secrets directly in committed config files.
* Using many scattered `@Value` fields instead of typed properties.
* Creating third-party clients with `new` inside services.
* Mixing business logic into configuration classes.
* Forgetting to validate configuration values.

---

# 5. Best Practices

* Use `@Bean` for library objects.
* Use `@ConfigurationProperties` for grouped configuration.
* Keep configuration classes infrastructure-focused.
* Validate required config.
* Keep sensitive values in environment variables or secret managers.

---

# 6. Code Example

```java
@ConfigurationProperties(prefix = "mail")
public record MailProperties(String host, int port, String username) {
}

@Configuration
class MailConfig {
    @Bean
    MailClient mailClient(MailProperties properties) {
        return new MailClient(properties.host(), properties.port());
    }
}
```

---

# 7. Real-world Scenarios

* Creating HTTP clients with base URL and timeouts.
* Configuring AWS, mail, payment, and cache clients.
* Switching behavior by environment.
* Binding many related values into one typed object.

---

# Revision Notes

* `@Configuration` declares bean configuration.
* `@Bean` registers manually created objects.
* `@ConfigurationProperties` binds external config into typed classes.
* Prefer typed config over scattered string values.
* Keep secrets out of source control.
* Configuration classes should not contain business workflows.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Java config class | `@Configuration` |
| Manual bean | `@Bean` |
| Grouped properties | `@ConfigurationProperties` |
| Property file | `application.properties` |
| YAML config | `application.yml` |

---

# Interview Questions with Answers

### 1. When do you use `@Bean`?

When Spring must manage an object that you construct manually, commonly third-party clients.

### 2. Why use `@ConfigurationProperties`?

It gives type-safe, grouped, maintainable configuration.

### 3. Difference between `@Value` and `@ConfigurationProperties`?

`@Value` injects individual values. `@ConfigurationProperties` binds a structured group of properties.

---

# Hands-on Exercises

### Exercise 1

Create typed properties for an SMS provider.

**Answer**

```java
@ConfigurationProperties(prefix = "sms")
public record SmsProperties(String apiKey, String senderId) {
}
```

