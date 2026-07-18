# Revision Notes

* Strategy makes algorithms interchangeable.
* It replaces large conditional behavior with polymorphism.
* Strategy interface defines behavior.
* Concrete strategies implement behavior.
* Context uses the strategy.
* Useful when behavior varies at runtime.
* Keep strategies small and focused.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Strategy | Interface |
| Concrete strategy | Algorithm |
| Context | Uses strategy |
| Registry | Selects strategy |

---

# Interview Questions with Answers

### 1. What problem does Strategy solve?

It avoids long conditional blocks when behavior changes by type.

### 2. Where use Strategy?

Payments, discounts, validation, notification, and file parsing.

### 3. Strategy vs Template Method?

Strategy uses composition. Template Method uses inheritance.

