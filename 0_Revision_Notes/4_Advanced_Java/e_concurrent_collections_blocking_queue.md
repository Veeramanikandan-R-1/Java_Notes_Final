# Revision Notes

* Concurrent collections are safe for multi-threaded access.
* `BlockingQueue` supports producer-consumer coordination.
* `put()` waits when queue is full.
* `take()` waits when queue is empty.
* `offer()` and `poll()` can avoid indefinite blocking.
* Prefer bounded queues for backpressure.
* Handle `InterruptedException`.
* Common implementations: `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Add and wait | `put()` |
| Remove and wait | `take()` |
| Add with result | `offer()` |
| Remove with result | `poll()` |
| Bounded queue | `ArrayBlockingQueue` |
| Linked queue | `LinkedBlockingQueue` |

---

# Interview Questions with Answers

### 1. What is BlockingQueue?

A thread-safe queue that can block producers and consumers based on capacity and availability.

### 2. put() vs offer()?

`put()` waits if full. `offer()` can return false or wait for a limited time.

### 3. Why use bounded queue?

To prevent unlimited memory growth and apply backpressure.

---

# Hands-on Exercises

### Exercise 1

Create bounded blocking queue.

**Answer**

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
queue.offer("job");
```

