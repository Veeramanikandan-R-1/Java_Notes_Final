# Decorator Design Pattern

### Subtopic: Decorator

---

# 1. Fundamentals

Decorator adds behavior to an object dynamically without changing its class.

It wraps an object with another object implementing the same interface.

---

# 2. Core Concepts

## Component

```java
interface Notifier {
    void send(String message);
}
```

## Concrete Component

```java
class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}
```

## Decorator

```java
class LoggingNotifier implements Notifier {
    private final Notifier delegate;

    LoggingNotifier(Notifier delegate) {
        this.delegate = delegate;
    }

    public void send(String message) {
        System.out.println("Sending notification");
        delegate.send(message);
    }
}
```

Usage:

```java
Notifier notifier = new LoggingNotifier(new EmailNotifier());
notifier.send("Welcome");
```

---

# 3. Internal Working

Decorator implements same interface and delegates to wrapped object.

Multiple decorators can be chained.

---

# 4. Common Mistakes

* Changing interface in decorator.
* Too many nested decorators.
* Decorator doing unrelated business logic.
* Forgetting to delegate.
* Making behavior order unclear.

---

# 5. Best Practices

* Keep decorator focused.
* Preserve original interface.
* Use for cross-cutting behavior.
* Keep wrapping order clear.
* Prefer AOP for common Spring cross-cutting concerns.

---

# 6. Real-world Scenarios

* Java IO streams.
* Logging wrapper.
* Caching wrapper.
* Retry wrapper.
* Metrics collection.

---

# Revision Notes

* Decorator adds behavior without changing class.
* It implements same interface.
* It wraps and delegates to another object.
* Multiple decorators can be chained.
* Java IO uses decorator heavily.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Component | Interface |
| Concrete component | Base behavior |
| Decorator | Wrapper |
| Delegate | Wrapped object |

---

# Interview Questions with Answers

### 1. What problem does Decorator solve?

It adds behavior dynamically without modifying the original class.

### 2. Decorator vs Inheritance?

Decorator composes behavior at runtime. Inheritance fixes behavior at class level.

### 3. Example in Java?

`BufferedInputStream` decorating `InputStream`.

