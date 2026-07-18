# Revision Notes

* Executor Framework manages task execution.
* Thread pool reuses worker threads.
* `ExecutorService` supports task submission and shutdown.
* `execute()` submits task without result.
* `submit()` returns `Future`.
* `Future.get()` blocks for result.
* Always shut down executors.
* Use bounded queues for production safety.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Fixed pool | `Executors.newFixedThreadPool(n)` |
| Submit task | `submit()` |
| Fire task | `execute()` |
| Result | `Future` |
| Wait result | `future.get()` |
| Stop pool | `shutdown()` |
| Scheduled tasks | `ScheduledExecutorService` |

---

# Interview Questions with Answers

### 1. Why use thread pool?

To reuse threads, reduce creation overhead, and control concurrency.

### 2. execute() vs submit()?

`execute()` returns nothing. `submit()` returns `Future`.

### 3. Why avoid unbounded queues?

They can grow until memory is exhausted under load.

---

# Hands-on Exercises

### Exercise 1

Create fixed thread pool.

**Answer**

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
try {
    executor.submit(() -> System.out.println("task"));
} finally {
    executor.shutdown();
}
```

