# Spring Core

### Subtopic: Events - Application Events

---

# 1. Fundamentals

Spring application events allow one part of the application to publish something that happened, while other parts react to it.

This reduces direct coupling between components.

Example:

```text
Order placed -> publish event -> email listener sends confirmation
```

The order service does not need to know about email implementation.

---

# 2. Core Concepts

## Event

An event is usually a simple object.

```java
public record OrderPlacedEvent(Long orderId, String email) {
}
```

---

## Publisher

```java
@Service
class OrderService {
    private final ApplicationEventPublisher publisher;

    OrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    void placeOrder(Long orderId, String email) {
        publisher.publishEvent(new OrderPlacedEvent(orderId, email));
    }
}
```

---

## Listener

```java
@Component
class OrderEmailListener {
    @EventListener
    void onOrderPlaced(OrderPlacedEvent event) {
        System.out.println("Send email to " + event.email());
    }
}
```

---

# 3. Internal Working

Spring uses an application event multicaster.

By default, event listeners usually run synchronously in the same thread as the publisher.

If you enable async execution and annotate listeners with `@Async`, listeners can run on a separate executor.

Transaction-aware event handling can be done with `@TransactionalEventListener`.

---

# 4. Common Mistakes

* Assuming events are asynchronous by default.
* Putting critical transactional writes in normal listeners without understanding transaction boundaries.
* Publishing events before the main operation succeeds.
* Hiding business flow behind too many events.
* Ignoring listener failure behavior.

---

# 5. Best Practices

* Use events for side effects and decoupled reactions.
* Keep event payloads small and meaningful.
* Use `@TransactionalEventListener` when reaction should happen after commit.
* Use async listeners for slow non-critical work.
* Log listener failures clearly.

---

# 6. Code Example

```java
public record UserRegisteredEvent(Long userId, String email) {
}

@Component
class WelcomeEmailListener {
    @EventListener
    void handle(UserRegisteredEvent event) {
        System.out.println("Send welcome email to " + event.email());
    }
}
```

---

# 7. Real-world Scenarios

* Send email after user registration.
* Update search index after product update.
* Clear cache after configuration change.
* Create audit trail after sensitive operation.
* Trigger notification after payment success.

---

# Revision Notes

* Spring events decouple publishers and listeners.
* Event payload can be a plain Java object or record.
* Publish using `ApplicationEventPublisher`.
* Listen using `@EventListener`.
* Events are usually synchronous by default.
* Use `@TransactionalEventListener` for transaction-bound reactions.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Publish event | `ApplicationEventPublisher` |
| Listen event | `@EventListener` |
| After transaction commit | `@TransactionalEventListener` |
| Async listener | `@Async` |

---

# Interview Questions with Answers

### 1. Why use application events?

To decouple the component that detects something from the components that react to it.

### 2. Are Spring events asynchronous by default?

No. They are usually synchronous unless async execution is configured.

### 3. When use `@TransactionalEventListener`?

When the listener should run based on transaction phase, commonly after commit.

---

# Hands-on Exercises

### Exercise 1

Publish an event after user registration.

**Answer**

```java
publisher.publishEvent(new UserRegisteredEvent(userId, email));
```

