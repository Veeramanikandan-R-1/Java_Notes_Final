# Multithreading Interview

### Subtopic: Threads, Executors, Concurrency

---

# 1. Fundamentals

Multithreading interviews test thread lifecycle, synchronization, executor framework, and concurrency safety.

---

# 2. Must-Know Topics

## Thread Lifecycle

States:

* NEW
* RUNNABLE
* BLOCKED
* WAITING
* TIMED_WAITING
* TERMINATED

## Executor Framework

Use thread pools instead of manually creating many threads.

## Synchronization

Protects shared mutable state.

## volatile

Gives visibility, not atomicity.

## Concurrent Collections

Use tools like:

* `ConcurrentHashMap`
* `BlockingQueue`
* `CopyOnWriteArrayList`

---

# 3. Common Interview Mistakes

* Saying `volatile` makes increment atomic.
* Calling `run()` instead of `start()`.
* Not knowing executor shutdown.
* Ignoring deadlock.
* Not explaining race condition with example.
* Using `sleep()` for coordination.

---

# 4. Best Practices

* Minimize shared mutable state.
* Prefer executors.
* Use concurrent collections.
* Handle interruption.
* Keep locks small.
* Avoid nested locks.
* Monitor thread pools.

---

# 5. Interview Questions with Answers

### 1. start() vs run()?

`start()` creates a new thread. `run()` is normal method execution.

### 2. volatile vs synchronized?

`volatile` ensures visibility. `synchronized` gives visibility and mutual exclusion.

### 3. What is deadlock?

Two or more threads wait forever for locks held by each other.

### 4. Why use BlockingQueue?

It simplifies producer-consumer coordination.

---

# Revision Notes

* Threads have lifecycle states.
* Shared mutable state causes race conditions.
* ExecutorService manages thread pools.
* `volatile` is visibility only.
* `synchronized` protects critical sections.
* BlockingQueue helps producer-consumer.
* Always shut down executors.

---

# Cheat Sheet

| Topic | Must Know |
| ----- | --------- |
| Thread | start vs run |
| Executor | thread pool |
| Race condition | unsafe shared state |
| volatile | visibility |
| synchronized | locking |
| BlockingQueue | producer-consumer |

