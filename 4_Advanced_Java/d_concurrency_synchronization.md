# Concurrency in Java

### Subtopic: Synchronization

---

# 1. Fundamentals

Concurrency problems happen when multiple threads access shared mutable state.

Synchronization controls access so only one thread enters a critical section at a time.

```java
public synchronized void increment() {
    count++;
}
```

---

# 2. Core Concepts

## Race Condition

Occurs when result depends on thread timing.

```java
count++;
```

This is not atomic. It involves read, modify, write.

## synchronized Method

```java
class Counter {
    private int count;

    public synchronized void increment() {
        count++;
    }
}
```

The lock is on the current object.

## synchronized Block

```java
synchronized (lock) {
    count++;
}
```

Use block to reduce lock scope.

## volatile

`volatile` guarantees visibility, not atomicity.

```java
private volatile boolean running = true;
```

Good for flags, not for compound operations like increment.

## Atomic Classes

```java
AtomicInteger count = new AtomicInteger();
count.incrementAndGet();
```

Useful for lock-free counters.

---

# 3. Internal Working

Every Java object can act as a monitor lock.

When a thread enters synchronized code, it acquires the monitor.

Other threads trying to enter synchronized code guarded by the same monitor become blocked.

Synchronization also creates memory visibility guarantees.

---

# 4. Common Mistakes

* Assuming `count++` is atomic.
* Using `volatile` for compound operations.
* Locking on publicly accessible objects.
* Holding locks while doing slow IO.
* Creating deadlocks by inconsistent lock order.
* Synchronizing too much and hurting throughput.

---

# 5. Best Practices

* Minimize shared mutable state.
* Keep critical sections small.
* Use atomic classes for simple counters.
* Use concurrent collections.
* Avoid nested locks where possible.
* Use consistent lock ordering.
* Prefer higher-level concurrency utilities.

---

# 6. Code Example

```java
class SafeCounter {
    private int count;
    private final Object lock = new Object();

    void increment() {
        synchronized (lock) {
            count++;
        }
    }

    int value() {
        synchronized (lock) {
            return count;
        }
    }
}
```

---

# 7. Real-world Scenarios

* Shared counters.
* In-memory state updates.
* Cache refresh coordination.
* Singleton initialization.
* Rate limiter state.

---

# Revision Notes

* Race condition happens due to unsafe shared mutation.
* `synchronized` protects critical section.
* Object monitor is used for locking.
* `volatile` gives visibility, not atomicity.
* Atomic classes help with lock-free simple operations.
* Keep lock scope small.
* Avoid deadlocks with consistent lock ordering.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Mutual exclusion | `synchronized` |
| Visibility flag | `volatile` |
| Atomic counter | `AtomicInteger` |
| Explicit lock | `ReentrantLock` |
| Thread-safe map | `ConcurrentHashMap` |
| Producer-consumer | `BlockingQueue` |

---

# Interview Questions with Answers

### 1. What is race condition?

A bug where output depends on unpredictable thread execution timing.

### 2. volatile vs synchronized?

`volatile` ensures visibility. `synchronized` ensures mutual exclusion and visibility.

### 3. Why is count++ not thread-safe?

It is a read-modify-write sequence, not a single atomic operation.

---

# Hands-on Exercises

### Exercise 1

Create atomic counter.

**Answer**

```java
class Counter {
    private final AtomicInteger count = new AtomicInteger();

    int increment() {
        return count.incrementAndGet();
    }
}
```

