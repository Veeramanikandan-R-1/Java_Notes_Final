# Spring Interview

### Subtopic: Spring, Spring Boot, JPA

---

# 1. Fundamentals

Spring interview preparation should focus on dependency injection, bean lifecycle, Boot auto-configuration, REST APIs, transactions, and JPA behavior.

---

# 2. Must-Know Topics

## Spring Core

* IoC container
* Dependency injection
* Bean lifecycle
* Bean scopes
* AOP

## Spring Boot

* Auto-configuration
* Starters
* Embedded server
* Profiles
* Actuator

## REST APIs

* Controller annotations
* Request/response DTOs
* Validation
* Global exception handling
* Status codes

## JPA

* Entity states
* Relationships
* Lazy vs eager
* Transactions
* N+1 problem

---

# 3. Common Interview Mistakes

* Saying Spring Boot removes configuration completely.
* Not knowing constructor injection benefits.
* Confusing `@Component`, `@Service`, `@Repository`.
* Ignoring transaction boundaries.
* Not explaining lazy loading problems.
* Returning entities directly from APIs.

---

# 4. Best Practices

* Prefer constructor injection.
* Keep entities separate from DTOs.
* Use global exception handling.
* Make transaction boundaries explicit.
* Use profiles for environment config.
* Use Actuator for operational visibility.

---

# 5. Interview Questions with Answers

### 1. What is IoC?

Inversion of Control means Spring creates and wires objects instead of application code manually creating dependencies.

### 2. Why constructor injection?

It makes dependencies mandatory, supports immutability, and improves testability.

### 3. What is auto-configuration?

Spring Boot configures beans based on classpath, properties, and existing beans.

### 4. What is N+1 problem?

One query loads parent records, then additional queries load children for each parent.

---

# Revision Notes

* Spring Core is about IoC and DI.
* Spring Boot simplifies setup through auto-configuration.
* REST APIs need DTOs, validation, and exception handling.
* JPA requires transaction and fetch strategy knowledge.
* Lazy loading can cause N+1.
* Constructor injection is preferred.

---

# Cheat Sheet

| Topic | Must Know |
| ----- | --------- |
| IoC | Spring manages objects |
| DI | Constructor/setter/field |
| Boot | Auto-config + starters |
| REST | Controller + DTO + validation |
| JPA | Entities + transactions |
| N+1 | Fetch optimization |

