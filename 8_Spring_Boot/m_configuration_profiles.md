# Spring Boot

### Subtopic: Configuration - Profiles

---

# 1. Fundamentals

Profiles let a Spring Boot app use different configuration for different environments.

Examples:

* `dev`
* `test`
* `qa`
* `prod`

---

# 2. Core Concepts

## Profile-Specific Files

```text
application.yml
application-dev.yml
application-prod.yml
```

Default values go in `application.yml`.

Environment-specific overrides go in profile files.

---

## Activate Profile

```properties
spring.profiles.active=dev
```

Or through environment:

```bash
SPRING_PROFILES_ACTIVE=prod
```

---

## Profile-Specific Beans

```java
@Bean
@Profile("dev")
PaymentGateway fakePaymentGateway() {
    return new FakePaymentGateway();
}
```

---

# 3. Internal Working

Spring builds an environment from property sources.

When profiles are active, profile-specific files and beans are included.

Profile-specific values override default configuration.

---

# 4. Common Mistakes

* Committing production secrets in profile files.
* Setting active profile inside the artifact for all environments.
* Creating too many profiles with unclear differences.
* Using profiles for business feature flags.
* Forgetting default values.

---

# 5. Best Practices

* Keep common config in `application.yml`.
* Activate profiles externally.
* Store secrets outside source control.
* Use profiles for environment differences.
* Use feature flags for business behavior toggles.

---

# 6. Code Example

```yaml
# application.yml
spring:
  application:
    name: payment-service

# application-prod.yml
logging:
  level:
    com.example.payment: INFO
```

---

# 7. Real-world Scenarios

* Local database in dev.
* Cloud database in prod.
* Fake payment gateway in test.
* Different logging levels by environment.
* Disabling scheduling during tests.

---

# Revision Notes

* Profiles separate environment-specific config.
* Use `application-{profile}.yml`.
* Activate with `SPRING_PROFILES_ACTIVE`.
* `@Profile` controls bean registration.
* Do not commit secrets.
* Use profiles for environment differences, not business flags.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Dev config | `application-dev.yml` |
| Prod config | `application-prod.yml` |
| Active profile | `spring.profiles.active` |
| Env activation | `SPRING_PROFILES_ACTIVE` |
| Profile bean | `@Profile` |

---

# Interview Questions with Answers

### 1. What are profiles?

Named environment configurations that change properties and beans.

### 2. How do you activate prod profile?

Set `SPRING_PROFILES_ACTIVE=prod` or equivalent deployment configuration.

### 3. Should secrets be in profile files?

No. Use environment variables or secret management.

---

# Hands-on Exercises

### Exercise 1

Create a bean only for test profile.

**Answer**

```java
@Bean
@Profile("test")
PaymentGateway fakeGateway() {
    return new FakePaymentGateway();
}
```

