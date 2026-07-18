# Concurrent Collections in Java

### Subtopic: Blocking Queue

---

# 1. Fundamentals

Concurrent collections are designed for safe access by multiple threads.

`BlockingQueue` is a thread-safe queue that can block producers or consumers.

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
```

It is commonly used in producer-consumer systems.

---

# 2. Core Concepts

## BlockingQueue

Producer adds data.

Consumer removes data.

If queue is full, producer may wait.

If queue is empty, consumer may wait.

---

## put() and take()

```java
queue.put("job");   // waits if full
String job = queue.take(); // waits if empty
```

---

## offer() and poll()

```java
boolean added = queue.offer("job");
String job = queue.poll();
```

These do not wait indefinitely.

Timed versions:

```java
queue.offer("job", 1, TimeUnit.SECONDS);
queue.poll(1, TimeUnit.SECONDS);
```

---

## Common Implementations

| Implementation | Use |
| -------------- | --- |
| `ArrayBlockingQueue` | Bounded queue backed by array |
| `LinkedBlockingQueue` | Optionally bounded linked queue |
| `PriorityBlockingQueue` | Priority-based processing |
| `DelayQueue` | Delayed task processing |

---

# 3. Internal Working

Blocking queues coordinate threads internally using locks/conditions or concurrent algorithms.

They remove the need for manual `wait()` and `notify()` in most producer-consumer code.

---

# 4. Common Mistakes

* Using unbounded queue in high-load system.
* Ignoring interruption.
* Using `add()` and getting exception when full.
* Blocking forever with `take()` during shutdown.
* Mixing external locks with concurrent collections unnecessarily.
* Not defining backpressure strategy.

---

# 5. Best Practices

* Prefer bounded queues for production.
* Handle `InterruptedException`.
* Use timed `offer()`/`poll()` when shutdown matters.
* Use poison pill or shutdown signal for consumers.
* Monitor queue size.
* Match queue type to workload.

---

# 6. Code Example

```java
class Worker implements Runnable {
    private final BlockingQueue<String> queue;

    Worker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String job = queue.take();
                System.out.println("Processing " + job);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
```

---

# 7. Real-world Scenarios

* Producer-consumer workflows.
* Background job queues.
* Log buffering.
* Rate-limited processing.
* Thread pool task queues.

---

# Revision Notes

* Concurrent collections are thread-safe.
* `BlockingQueue` supports producer-consumer coordination.
* `put()` waits if full.
* `take()` waits if empty.
* `offer()` and `poll()` can avoid indefinite blocking.
* Prefer bounded queues for backpressure.
* Handle interruption correctly.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Add and wait | `put()` |
| Remove and wait | `take()` |
| Add no wait | `offer()` |
| Remove no wait | `poll()` |
| Bounded array queue | `ArrayBlockingQueue` |
| Linked queue | `LinkedBlockingQueue` |

---

# Interview Questions with Answers

### 1. What is BlockingQueue?

A thread-safe queue that can block producers when full and consumers when empty.

### 2. put() vs offer()?

`put()` waits if queue is full. `offer()` returns false or waits only for specified timeout.

### 3. Why use bounded queue?

To apply backpressure and avoid unbounded memory growth.

---

# Hands-on Exercises

### Exercise 1

Create bounded queue and add item safely.

**Answer**

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
boolean added = queue.offer("job", 1, TimeUnit.SECONDS);
```

