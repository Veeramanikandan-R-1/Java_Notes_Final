# Observer Design Pattern

### Subtopic: Observer

---

# 1. Fundamentals

Observer pattern allows objects to be notified when another object changes.

It models one-to-many communication.

---

# 2. Core Concepts

## Observer

```java
interface OrderObserver {
    void onOrderPlaced(Order order);
}
```

## Concrete Observer

```java
class EmailNotifier implements OrderObserver {
    public void onOrderPlaced(Order order) {
        System.out.println("Sending email");
    }
}
```

## Subject

```java
class OrderService {
    private final List<OrderObserver> observers = new ArrayList<>();

    void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    void placeOrder(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(order);
        }
    }
}
```

---

# 3. Internal Working

Subject keeps a list of observers and notifies them when event happens.

This decouples event producer from event consumers.

---

# 4. Common Mistakes

* Observer list grows and observers are never removed.
* One observer failure stops all others.
* Synchronous observers make main flow slow.
* Hidden side effects make debugging hard.
* Event payload contains too much mutable state.

---

# 5. Best Practices

* Keep event payload small and immutable.
* Handle observer failures independently.
* Consider async event processing for slow work.
* Remove observers when no longer needed.
* In Spring, use application events for this style.

---

# 6. Real-world Scenarios

* Domain events.
* Application events in Spring.
* UI event listeners.
* Cache invalidation.
* Notification triggers.

---

# Revision Notes

* Observer models one-to-many notification.
* Subject publishes events.
* Observers react to events.
* It decouples producer and consumers.
* Watch for slow or failing observers.
* Spring events use similar idea.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Subject | Maintains observers |
| Observer | Reacts to event |
| Event | Carries change data |
| Notify | Calls observers |

---

# Interview Questions with Answers

### 1. What problem does Observer solve?

It lets multiple components react to an event without tightly coupling them to the producer.

### 2. Observer vs Strategy?

Strategy chooses one behavior. Observer notifies many listeners.

### 3. Risk of Observer?

Hidden side effects and slow/failing observers.

