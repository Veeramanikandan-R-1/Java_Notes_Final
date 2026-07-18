# Factory Design Pattern

### Subtopic: Factory

---

# 1. Fundamentals

Factory pattern centralizes object creation logic.

Instead of callers using `new` everywhere, they ask a factory to create the right object.

---

# 2. Core Concepts

## Problem

```java
PaymentProcessor processor = new UpiProcessor();
```

Client code becomes tied to concrete classes.

## Factory Solution

```java
interface PaymentProcessor {
    void process(double amount);
}

class UpiProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("UPI payment");
    }
}

class CardProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("Card payment");
    }
}

class PaymentProcessorFactory {
    static PaymentProcessor create(String type) {
        return switch (type) {
            case "UPI" -> new UpiProcessor();
            case "CARD" -> new CardProcessor();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
```

---

# 3. Internal Working

Factory hides object creation and returns abstraction.

Client depends on interface:

```java
PaymentProcessor processor = PaymentProcessorFactory.create("UPI");
```

This improves maintainability when creation logic changes.

---

# 4. Common Mistakes

* Creating factory for every simple object.
* Returning concrete classes instead of abstraction.
* Putting too much business logic in factory.
* Using string types everywhere without enum.
* Giant factory with many unrelated object types.

---

# 5. Best Practices

* Use factory when creation logic varies.
* Return interface or abstract type.
* Use enum for type-safe selection.
* Keep factory focused.
* In Spring, prefer dependency injection for known beans.

---

# 6. Real-world Scenarios

* Payment processor selection.
* Notification sender creation.
* Parser selection by file type.
* Report exporter selection.
* Cloud storage provider selection.

---

# Revision Notes

* Factory centralizes object creation.
* Client code avoids direct dependency on concrete classes.
* Factory should return abstraction.
* Use when creation logic varies.
* Avoid overusing for simple constructors.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Hide object creation | Factory |
| Multiple implementations | Return interface |
| Type-safe choice | Enum |
| Spring apps | DI may replace factory |

---

# Interview Questions with Answers

### 1. What problem does Factory solve?

It hides object creation and reduces coupling to concrete classes.

### 2. Factory vs constructor?

Constructor creates one class directly. Factory can decide which implementation to create.

### 3. When avoid factory?

When object creation is simple and has no variation.

