# Java 8+ Features

### Subtopic: Lambda, Functional Interfaces

---

# 1. Fundamentals

Java 8 introduced functional programming features that made collection processing and behavior passing cleaner.

Key features:

* Lambda expressions
* Functional interfaces
* Streams API
* Optional
* Default methods
* New Date/Time API

This note focuses on lambdas and functional interfaces.

---

# 2. Core Concepts

## Lambda Expression

A lambda is a short way to implement a functional interface.

```java
Runnable task = () -> System.out.println("Running");
```

Before lambda:

```java
Runnable task = new Runnable() {
    public void run() {
        System.out.println("Running");
    }
};
```

## Functional Interface

An interface with exactly one abstract method.

```java
@FunctionalInterface
interface Validator {
    boolean isValid(String value);
}
```

Usage:

```java
Validator notBlank = value -> value != null && !value.isBlank();
```

## Built-in Functional Interfaces

| Interface | Method | Use |
| --------- | ------ | --- |
| `Predicate<T>` | `test(T)` | Condition |
| `Function<T,R>` | `apply(T)` | Transform |
| `Consumer<T>` | `accept(T)` | Consume |
| `Supplier<T>` | `get()` | Supply value |

## Method Reference

```java
users.stream()
        .map(User::getEmail)
        .toList();
```

Method reference is shorthand for simple lambda.

---

# 3. Internal Working

Lambdas are not anonymous classes in the old direct sense. JVM uses invokedynamic-based mechanics to create efficient runtime behavior.

For day-to-day coding, understand that lambdas implement functional interfaces.

---

# 4. Common Mistakes

* Writing long complex lambdas.
* Using lambda when normal method is clearer.
* Mutating external variables inside lambdas.
* Forgetting captured local variables must be effectively final.
* Creating custom functional interfaces when built-in ones are enough.

---

# 5. Best Practices

* Keep lambdas short.
* Use method references when clear.
* Prefer built-in functional interfaces.
* Move complex logic to named methods.
* Avoid side effects in stream lambdas.
* Use `@FunctionalInterface` for custom contracts.

---

# 6. Code Example

```java
import java.util.function.Predicate;

class UserFilters {
    static Predicate<User> activeUsers() {
        return User::isActive;
    }

    static Predicate<User> byRole(String role) {
        return user -> role.equals(user.getRole());
    }
}
```

Usage:

```java
List<User> admins = users.stream()
        .filter(UserFilters.activeUsers())
        .filter(UserFilters.byRole("ADMIN"))
        .toList();
```

---

# 7. Real-world Scenarios

* Stream filtering and mapping.
* Callback behavior.
* Validation rules.
* Sorting with comparators.
* Retry policies.
* Event handling.

---

# Revision Notes

* Lambda is concise functional interface implementation.
* Functional interface has one abstract method.
* Use `@FunctionalInterface`.
* Predicate checks condition.
* Function transforms value.
* Consumer consumes value.
* Supplier provides value.
* Captured local variables must be effectively final.

---

# Cheat Sheet

| Need | Interface |
| ---- | --------- |
| Condition | `Predicate<T>` |
| Transform | `Function<T,R>` |
| Consume | `Consumer<T>` |
| Supply | `Supplier<T>` |
| No args task | `Runnable` |
| Method reference | `Class::method` |

---

# Interview Questions with Answers

### 1. What is lambda?

A concise implementation of a functional interface.

### 2. What is functional interface?

An interface with exactly one abstract method.

### 3. What is effectively final?

A local variable captured by lambda that is not modified after assignment.

---

# Hands-on Exercises

### Exercise 1

Create predicate for adult age.

**Answer**

```java
Predicate<Integer> isAdult = age -> age >= 18;
```

