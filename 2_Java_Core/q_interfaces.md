# Interfaces in Java

### Subtopic: Basics, Default, Static, Functional

---

# 1. Fundamentals

An interface defines a contract that classes can implement.

```java
interface PaymentGateway {
    void pay(double amount);
}

class UpiGateway implements PaymentGateway {
    public void pay(double amount) {
        System.out.println("Paid by UPI: " + amount);
    }
}
```

Interfaces help code depend on behavior instead of concrete classes.

---

# 2. Core Concepts

## Basic Interface

Methods are public and abstract by default.

```java
interface ReportExporter {
    byte[] export(long reportId);
}
```

A class uses `implements`.

```java
class PdfExporter implements ReportExporter {
    public byte[] export(long reportId) {
        return new byte[0];
    }
}
```

## Multiple Interfaces

Java allows a class to implement multiple interfaces.

```java
class UserService implements ReadService, WriteService {
}
```

## Default Methods

Default methods provide implementation inside an interface.

```java
interface Logger {
    default void info(String message) {
        System.out.println(message);
    }
}
```

They help evolve interfaces without breaking all existing implementations.

## Static Methods

Static methods belong to the interface.

```java
interface EmailValidator {
    static boolean isValid(String email) {
        return email != null && email.contains("@");
    }
}
```

Call using:

```java
EmailValidator.isValid("a@test.com");
```

## Functional Interface

A functional interface has exactly one abstract method.

```java
@FunctionalInterface
interface DiscountPolicy {
    double discount(double amount);
}
```

It can be implemented using lambda.

```java
DiscountPolicy policy = amount -> amount * 0.10;
```

---

# 3. Internal Working

Interfaces enable runtime polymorphism.

```java
NotificationSender sender = new EmailSender();
sender.send("Welcome");
```

The JVM invokes the actual implementation at runtime.

In frameworks like Spring, interfaces make dependency injection and testing easier.

---

# 4. Common Mistakes

* Creating interfaces for every class without real need.
* Adding too many methods to one interface.
* Using default methods for business logic that belongs in classes.
* Forgetting `@FunctionalInterface` for lambda-oriented contracts.
* Exposing implementation-specific details in interface methods.

---

# 5. Best Practices

* Use interfaces when multiple implementations exist or are likely.
* Keep interfaces small and capability-focused.
* Prefer names based on behavior: `PaymentProcessor`, `TokenGenerator`.
* Use default methods carefully for backward compatibility.
* Use functional interfaces for small behavior parameters.

---

# 6. Code Example

```java
@FunctionalInterface
interface RetryPolicy {
    boolean shouldRetry(int attempt, Exception exception);
}

class ApiClient {
    void call(RetryPolicy retryPolicy) {
        // simplified example
        boolean retry = retryPolicy.shouldRetry(1, new RuntimeException("timeout"));
        System.out.println(retry);
    }
}
```

Usage:

```java
ApiClient client = new ApiClient();
client.call((attempt, ex) -> attempt < 3);
```

---

# 7. Real-world Scenarios

* Service contracts.
* Repository abstractions.
* Payment provider integrations.
* Strategy pattern.
* Lambda callbacks.
* Test mocks.

---

# Revision Notes

* Interface defines a contract.
* Class implements interface using `implements`.
* A class can implement multiple interfaces.
* Default methods have method body.
* Static methods are called using interface name.
* Functional interface has one abstract method.
* Functional interfaces support lambdas.

---

# Cheat Sheet

| Need | Syntax |
| ---- | ------ |
| Define interface | `interface A {}` |
| Implement | `class B implements A` |
| Default method | `default void run() {}` |
| Static method | `static boolean ok() {}` |
| Functional interface | `@FunctionalInterface` |
| Lambda | `x -> x + 1` |

---

# Interview Questions with Answers

### 1. What is an interface?

A contract that defines behavior a class must provide.

### 2. Can interface have implementation?

Yes. It can have default and static methods.

### 3. What is a functional interface?

An interface with exactly one abstract method.

### 4. Why use interfaces in backend applications?

They reduce coupling, support polymorphism, improve testing, and allow multiple implementations.

---

# Hands-on Exercises

### Exercise 1

Create a functional interface for validating strings.

**Answer**

```java
@FunctionalInterface
interface StringValidator {
    boolean isValid(String value);
}

StringValidator notBlank = value -> value != null && !value.isBlank();
```

