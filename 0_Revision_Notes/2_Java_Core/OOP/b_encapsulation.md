# Revision Notes

* Encapsulation binds data and behavior together.
* It hides internal state from uncontrolled external access.
* Use private fields.
* Use methods to enforce validation and business rules.
* Avoid public mutable fields.
* Avoid blind setters when behavior methods are clearer.
* Constructors should create valid objects.
* Return copies or unmodifiable views for mutable collections.

---

# Cheat Sheet

| Requirement | Code/Approach |
| ----------- | ------------- |
| Hide field | `private` |
| Controlled change | `deposit(amount)` |
| Validate state | Constructor/method check |
| Read-only access | Getter |
| Protect collection | `List.copyOf()` |

---

# Interview Questions with Answers

### 1. What is encapsulation?

Protecting internal data and allowing access through controlled methods.

### 2. How does Java support encapsulation?

Through classes, access modifiers, constructors, and methods.

### 3. Why are blind setters risky?

They can allow invalid state and bypass business rules.

---

# Hands-on Exercises

### Exercise 1

Create `ProductStock` that does not allow negative stock.

**Answer**

```java
class ProductStock {
    private int quantity;

    void add(int value) {
        if (value <= 0) throw new IllegalArgumentException();
        quantity += value;
    }

    void remove(int value) {
        if (value <= 0 || value > quantity) throw new IllegalArgumentException();
        quantity -= value;
    }
}
```

