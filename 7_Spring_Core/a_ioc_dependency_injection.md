# Spring Core

### Subtopic: IoC - Dependency Injection

---

# 1. Fundamentals

IoC means Inversion of Control.

Instead of an object creating its own dependencies, Spring creates objects and injects the dependencies into them.

Without IoC:

```java
class OrderService {
    private final PaymentService paymentService = new PaymentService();
}
```

With IoC:

```java
class OrderService {
    private final PaymentService paymentService;

    OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

The class depends on an abstraction or dependency, but does not control how it is created.

---

# 2. Core Concepts

## IoC Container

The Spring IoC container creates, wires, configures, and manages beans.

Common container interfaces:

| Interface | Purpose |
| --------- | ------- |
| `BeanFactory` | Basic bean container |
| `ApplicationContext` | Full-featured container used in real applications |

---

## Dependency Injection

Dependency Injection is the practical technique used to implement IoC.

Types:

| Type | Meaning |
| ---- | ------- |
| Constructor injection | Required dependencies are passed through constructor |
| Setter injection | Optional dependencies are set through setters |
| Field injection | Spring injects directly into fields |

Production code usually prefers constructor injection.

---

## Bean

A bean is an object managed by Spring.

```java
@Service
class InvoiceService {
}
```

Spring detects it through component scanning and registers it in the application context.

---

# 3. Internal Working

At startup, Spring scans configuration classes and component packages.

It creates bean definitions first, then instantiates beans, resolves dependencies, injects them, and stores singleton beans in the context.

When `OrderService` needs `PaymentService`, Spring searches the context for a matching bean.

If multiple beans match, Spring needs help using `@Qualifier`, `@Primary`, or a more specific type.

---

# 4. Common Mistakes

* Creating dependencies manually using `new` inside Spring-managed services.
* Using field injection everywhere.
* Injecting concrete classes when an interface would make testing easier.
* Having multiple beans of same type without `@Qualifier` or `@Primary`.
* Putting business logic into configuration classes.
* Making bean dependencies circular.

---

# 5. Best Practices

* Prefer constructor injection for required dependencies.
* Keep beans small and focused.
* Depend on interfaces when multiple implementations are likely.
* Use `@Qualifier` only when needed.
* Keep object creation centralized in Spring configuration.
* Avoid circular dependencies by redesigning responsibilities.

---

# 6. Code Example

```java
interface PaymentGateway {
    void charge(String orderId);
}

@Service
class RazorpayGateway implements PaymentGateway {
    public void charge(String orderId) {
        System.out.println("Charging order " + orderId);
    }
}

@Service
class OrderService {
    private final PaymentGateway paymentGateway;

    OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    void placeOrder(String orderId) {
        paymentGateway.charge(orderId);
    }
}
```

---

# 7. Real-world Scenarios

* Injecting repositories into services.
* Injecting HTTP clients into integration services.
* Swapping payment provider implementations.
* Mocking dependencies in unit tests.
* Managing environment-specific infrastructure beans.

---

# Revision Notes

* IoC means Spring controls object creation and wiring.
* Dependency Injection is how dependencies are supplied.
* `ApplicationContext` is the main Spring container.
* A bean is an object managed by Spring.
* Constructor injection is preferred for required dependencies.
* Avoid manual `new` for Spring-managed dependencies.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Register service | `@Service` |
| Register component | `@Component` |
| Inject dependency | Constructor |
| Resolve duplicate beans | `@Qualifier`, `@Primary` |
| Container | `ApplicationContext` |

---

# Interview Questions with Answers

### 1. What is IoC?

IoC means the framework controls object creation and dependency wiring instead of application classes doing it themselves.

### 2. Why is DI useful?

It reduces tight coupling, improves testability, and makes configuration easier.

### 3. Why prefer constructor injection?

It makes required dependencies explicit and allows immutable fields.

---

# Hands-on Exercises

### Exercise 1

Create `NotificationService` and inject it into `UserService`.

**Answer**

```java
@Service
class NotificationService {
    void sendWelcomeEmail(String email) {
        System.out.println("Welcome " + email);
    }
}

@Service
class UserService {
    private final NotificationService notificationService;

    UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
```

