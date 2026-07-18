# OOP in Java

### Subtopic: Polymorphism

---

# 1. Fundamentals

Polymorphism means one interface or reference can represent many actual forms.

```java
Payment payment = new CardPayment();
payment.process();
```

The reference type is `Payment`, but the actual object is `CardPayment`.

---

# 2. Core Concepts

## 2.1 Compile-time Polymorphism

Achieved through method overloading.

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

The compiler decides which method to call based on arguments.

---

## 2.2 Runtime Polymorphism

Achieved through method overriding.

```java
interface NotificationSender {
    void send(String message);
}

class EmailSender implements NotificationSender {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class SmsSender implements NotificationSender {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
```

Usage:

```java
NotificationSender sender = new EmailSender();
sender.send("Welcome");
```

Runtime object decides behavior.

---

## 2.3 Upcasting

Child object can be referenced by parent type.

```java
NotificationSender sender = new EmailSender();
```

This enables flexible code.

---

## 2.4 Dynamic Method Dispatch

For overridden methods, JVM selects implementation based on actual object type at runtime.

```java
NotificationSender sender = new SmsSender();
sender.send("OTP");
```

`SmsSender.send()` runs.

---

# 3. Internal Working

Polymorphism separates what code needs from how it is implemented.

Service code depends on abstraction:

```java
class AlertService {
    private final NotificationSender sender;

    AlertService(NotificationSender sender) {
        this.sender = sender;
    }

    void alert(String message) {
        sender.send(message);
    }
}
```

`AlertService` does not care whether notification is sent by email, SMS, or push.

---

# 4. Common Mistakes

* Confusing overloading with overriding.
* Depending on concrete classes instead of interfaces.
* Using `instanceof` everywhere instead of polymorphic methods.
* Breaking parent method contracts in child classes.
* Changing method signature accidentally while trying to override.

Use `@Override` to catch signature mistakes.

---

# 5. Best Practices

* Program to interfaces where behavior varies.
* Use polymorphism to remove long condition chains.
* Keep method contracts clear.
* Use `@Override` for overridden methods.
* Avoid unnecessary downcasting.

---

# 6. Code Example

```java
interface DiscountPolicy {
    double discount(double amount);
}

class RegularDiscount implements DiscountPolicy {
    public double discount(double amount) {
        return amount * 0.05;
    }
}

class PremiumDiscount implements DiscountPolicy {
    public double discount(double amount) {
        return amount * 0.15;
    }
}

class BillingService {
    double finalAmount(double amount, DiscountPolicy policy) {
        return amount - policy.discount(amount);
    }
}
```

---

# 7. Real-world Scenarios

* Payment strategy selection.
* Notification channel handling.
* Discount rules.
* Repository implementations.
* Spring dependency injection with interfaces.

---

# Revision Notes

* Polymorphism means many forms.
* Overloading is compile-time polymorphism.
* Overriding is runtime polymorphism.
* Runtime polymorphism uses dynamic method dispatch.
* Parent references can point to child objects.
* Interfaces make polymorphism cleaner.
* Avoid excessive `instanceof` when polymorphism can solve it.

---

# Cheat Sheet

| Type | Mechanism |
| ---- | --------- |
| Compile-time | Method overloading |
| Runtime | Method overriding |
| Flexible reference | Parent/interface type |
| Runtime selection | Dynamic dispatch |

---

# Interview Questions with Answers

### 1. What is polymorphism?

The ability of one reference type or method name to represent different behaviors.

### 2. Difference between overloading and overriding?

Overloading is same method name with different parameters. Overriding is child class redefining parent behavior.

### 3. Why is polymorphism useful?

It makes code extensible and reduces dependency on concrete implementations.

---

# Hands-on Exercises

### Exercise 1

Create polymorphic payment processors.

**Answer**

```java
interface PaymentProcessor {
    void process(double amount);
}

class UpiProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("UPI: " + amount);
    }
}

class CardProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("Card: " + amount);
    }
}
```

