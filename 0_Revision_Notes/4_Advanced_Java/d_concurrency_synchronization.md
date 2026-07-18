# Revision Notes

* Race condition happens when result depends on thread timing.
* `synchronized` provides mutual exclusion and visibility.
* Every object can act as a monitor lock.
* `volatile` gives visibility but not atomicity.
* `count++` is not atomic.
* Atomic classes are useful for simple counters.
* Keep critical sections small.
* Avoid deadlocks by consistent lock ordering.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Lock section | `synchronized` |
| Visibility flag | `volatile` |
| Atomic count | `AtomicInteger` |
| Explicit lock | `ReentrantLock` |
| Thread-safe map | `ConcurrentHashMap` |

---

# Interview Questions with Answers

### 1. What is race condition?

A concurrency bug where output depends on unpredictable thread scheduling.

### 2. volatile vs synchronized?

`volatile` ensures visibility. `synchronized` ensures mutual exclusion and visibility.

### 3. Why is count++ unsafe?

It is read-modify-write, not atomic.

---

# Hands-on Exercises

### Exercise 1

Atomic increment.

**Answer**

```java
AtomicInteger count = new AtomicInteger();
int value = count.incrementAndGet();
```

