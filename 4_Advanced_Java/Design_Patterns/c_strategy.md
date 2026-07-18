# Strategy Design Pattern

### Subtopic: Strategy

---

# 1. Fundamentals

Strategy pattern defines a family of algorithms and makes them interchangeable.

Use it when behavior varies but caller should stay stable.

---

# 2. Core Concepts

## Strategy Interface

```java
interface DiscountStrategy {
    double discount(double amount);
}
```

## Concrete Strategies

```java
class RegularDiscount implements DiscountStrategy {
    public double discount(double amount) {
        return amount * 0.05;
    }
}

class PremiumDiscount implements DiscountStrategy {
    public double discount(double amount) {
        return amount * 0.15;
    }
}
```

## Context

```java
class BillingService {
    double finalAmount(double amount, DiscountStrategy strategy) {
        return amount - strategy.discount(amount);
    }
}
```

---

# 3. Internal Working

Strategy replaces conditional logic with polymorphism.

Bad:

```java
if ("PREMIUM".equals(type)) { }
else if ("REGULAR".equals(type)) { }
```

Better:

```java
billingService.finalAmount(amount, strategy);
```

---

# 4. Common Mistakes

* Creating strategy when only one behavior exists.
* Making strategy interface too broad.
* Letting strategies depend on unrelated state.
* Choosing strategy using scattered if-else blocks.
* Not registering strategies cleanly in Spring.

---

# 5. Best Practices

* Use for replaceable algorithms.
* Keep strategy interface small.
* Use map/registry for selecting strategy.
* In Spring, inject `Map<String, Strategy>`.
* Make strategies stateless where possible.

---

# 6. Real-world Scenarios

* Payment method processing.
* Discount calculation.
* Shipping cost calculation.
* Notification channel selection.
* File parser selection.

---

# Revision Notes

* Strategy makes algorithms interchangeable.
* It replaces conditional behavior with polymorphism.
* Context uses strategy interface.
* Concrete strategies implement behavior.
* Useful when behavior varies at runtime.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Strategy | Interface |
| Concrete strategy | Algorithm implementation |
| Context | Uses strategy |
| Registry | Selects strategy |

---

# Interview Questions with Answers

### 1. What problem does Strategy solve?

It avoids large conditional blocks when behavior varies.

### 2. Strategy vs Factory?

Factory creates objects. Strategy represents interchangeable behavior.

### 3. Where is Strategy used?

Payments, discounts, validations, and notification channels.

