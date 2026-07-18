# Spring Core

### Subtopic: Dependency Injection - Constructor, Setter, Field Injection

---

# 1. Fundamentals

Dependency Injection supplies a class with the objects it needs.

Spring supports constructor, setter, and field injection.

The choice affects immutability, testability, readability, and runtime safety.

---

# 2. Core Concepts

## Constructor Injection

```java
@Service
class PaymentService {
    private final PaymentClient paymentClient;

    PaymentService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }
}
```

Use for required dependencies.

Benefits:

* Dependencies are explicit.
* Fields can be `final`.
* Object cannot be created in invalid state.
* Unit testing is simple.

---

## Setter Injection

```java
@Service
class AuditService {
    private AlertService alertService;

    @Autowired
    void setAlertService(AlertService alertService) {
        this.alertService = alertService;
    }
}
```

Use only for optional or reconfigurable dependencies.

---

## Field Injection

```java
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;
}
```

Field injection is concise but weaker for production code because dependencies are hidden and harder to test.

---

# 3. Internal Working

Spring resolves beans by type first.

If exactly one matching bean exists, injection succeeds.

If multiple candidates exist, Spring needs disambiguation using:

```java
@Qualifier("stripePaymentClient")
```

or:

```java
@Primary
```

---

# 4. Common Mistakes

* Using field injection in services that need unit tests.
* Making dependencies optional when they are required.
* Ignoring multiple implementation ambiguity.
* Creating circular dependencies between services.
* Injecting too many dependencies into one class.

---

# 5. Best Practices

* Prefer constructor injection.
* Keep required dependencies `final`.
* Use setter injection only for optional dependencies.
* Use `@Qualifier` with clear bean names.
* If a constructor has too many dependencies, split responsibilities.

---

# 6. Code Example

```java
interface SmsSender {
    void send(String phone, String text);
}

@Service("twilioSmsSender")
class TwilioSmsSender implements SmsSender {
    public void send(String phone, String text) {
        System.out.println("Sending SMS");
    }
}

@Service
class OtpService {
    private final SmsSender smsSender;

    OtpService(@Qualifier("twilioSmsSender") SmsSender smsSender) {
        this.smsSender = smsSender;
    }
}
```

---

# 7. Real-world Scenarios

* Constructor injection for repositories and clients.
* Setter injection for optional metrics or alerts.
* Qualifiers for multiple payment gateways.
* Unit tests using constructor-created services with mocks.

---

# Revision Notes

* Constructor injection is preferred.
* Setter injection fits optional dependencies.
* Field injection hides dependencies and hurts testability.
* Spring injects by type by default.
* Use `@Qualifier` or `@Primary` for multiple candidates.
* Too many constructor arguments can signal poor class design.

---

# Cheat Sheet

| Injection Type | Use Case |
| -------------- | -------- |
| Constructor | Required dependency |
| Setter | Optional dependency |
| Field | Avoid in production services |
| `@Qualifier` | Choose specific bean |
| `@Primary` | Default bean among candidates |

---

# Interview Questions with Answers

### 1. Why prefer constructor injection?

It makes dependencies mandatory, visible, immutable, and easy to test.

### 2. When is setter injection acceptable?

For optional dependencies or values that can be configured later.

### 3. What is the problem with field injection?

It hides dependencies and makes plain unit testing harder.

---

# Hands-on Exercises

### Exercise 1

Rewrite field injection as constructor injection.

**Answer**

```java
@Service
class ProductService {
    private final ProductRepository repository;

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }
}
```

