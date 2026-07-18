# OOP in Java

### Subtopic: Encapsulation

---

# 1. Fundamentals

Encapsulation means binding data and behavior together while protecting internal state from uncontrolled access.

```java
class Account {
    private double balance;

    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }
}
```

The field is private. The method controls how it changes.

---

# 2. Core Concepts

## Private Fields

Fields should usually be private.

```java
private String email;
```

This prevents direct modification from outside the class.

## Public Methods

Expose controlled behavior.

```java
public void changeEmail(String email) {
    if (email == null || !email.contains("@")) {
        throw new IllegalArgumentException("Invalid email");
    }
    this.email = email;
}
```

## Invariants

An invariant is a rule that must always remain true.

Example:

* Balance cannot be negative.
* Email must be valid.
* Order total cannot be less than zero.

Encapsulation protects these rules.

## Getters and Setters

Getters are fine when callers need read access.

Setters should not be automatic. Prefer meaningful methods.

```java
order.cancel();
```

is better than:

```java
order.setStatus("CANCELLED");
```

because the method can enforce business rules.

---

# 3. Internal Working

Encapsulation is enforced by access modifiers:

| Modifier | Access |
| -------- | ------ |
| `private` | Same class |
| default | Same package |
| `protected` | Same package and subclasses |
| `public` | Everywhere |

The compiler prevents invalid access.

---

# 4. Common Mistakes

* Making fields public.
* Creating setters for every field without validation.
* Returning mutable internal collections directly.
* Putting validation outside the domain object everywhere.
* Allowing invalid object state through empty constructors.

---

# 5. Best Practices

* Keep fields private.
* Validate state inside constructors and methods.
* Prefer behavior methods over blind setters.
* Return immutable views or copies for collections.
* Make invalid states impossible where practical.
* Keep business rules close to the data they protect.

---

# 6. Code Example

```java
class Order {
    private String status = "CREATED";
    private double totalAmount;

    Order(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.totalAmount = totalAmount;
    }

    void pay() {
        if (!"CREATED".equals(status)) {
            throw new IllegalStateException("Only created order can be paid");
        }
        status = "PAID";
    }

    String getStatus() {
        return status;
    }
}
```

---

# 7. Real-world Scenarios

* Bank account balance updates.
* Order state transitions.
* User email validation.
* Inventory quantity changes.
* API DTO validation before service logic.

---

# Revision Notes

* Encapsulation protects object state.
* Fields should usually be private.
* Methods should control mutation.
* Constructors should create valid objects.
* Avoid blind setters.
* Protect mutable collections.
* Encapsulation helps enforce business invariants.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Hide state | `private` fields |
| Controlled update | Business method |
| Validate creation | Constructor |
| Read state | Getter |
| Protect list | Return copy/unmodifiable view |

---

# Interview Questions with Answers

### 1. What is encapsulation?

Encapsulation is hiding internal state and exposing controlled behavior.

### 2. Why avoid public fields?

They allow uncontrolled changes and make business rules easy to bypass.

### 3. Are getters and setters always good?

No. Setters can weaken encapsulation if they allow invalid state.

---

# Hands-on Exercises

### Exercise 1

Create a `Wallet` that prevents negative balance.

**Answer**

```java
class Wallet {
    private double balance;

    void addMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
    }

    double getBalance() {
        return balance;
    }
}
```

