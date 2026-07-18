# Executor Framework in Java

### Subtopic: Thread Pools

---

# 1. Fundamentals

Executor Framework separates task submission from thread management.

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> process());
```

Instead of creating threads manually, tasks are submitted to a pool.

---

# 2. Core Concepts

## Executor

Basic interface for executing tasks.

```java
Executor executor = command -> new Thread(command).start();
```

## ExecutorService

Adds lifecycle and result handling.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
```

## submit() vs execute()

```java
executor.execute(task); // no result
Future<?> future = executor.submit(task); // result or exception tracking
```

## Future

Represents result available later.

```java
Future<Integer> future = executor.submit(() -> 10);
Integer value = future.get();
```

`get()` blocks until result is available.

## Thread Pool Types

| Pool | Use |
| ---- | --- |
| Fixed thread pool | Stable number of workers |
| Cached thread pool | Many short-lived async tasks |
| Single thread executor | Sequential background work |
| Scheduled executor | Delayed or periodic tasks |

Prefer `ThreadPoolExecutor` for production control.

---

# 3. Internal Working

A thread pool usually has:

* Worker threads
* Task queue
* Rejection policy
* Pool size configuration

Tasks are placed into queue and workers pick them up.

If queue and pool are full, rejection policy applies.

---

# 4. Common Mistakes

* Not shutting down executor.
* Using unbounded queues blindly.
* Using cached pool for heavy tasks.
* Blocking inside too many pool threads.
* Calling `Future.get()` immediately and losing async benefit.
* Not sizing pool based on workload.

---

# 5. Best Practices

* Always shut down executor.
* Use bounded queues for production workloads.
* Separate CPU-bound and IO-bound pools.
* Name threads using custom `ThreadFactory`.
* Monitor queue size and rejection count.
* Use `CompletableFuture` for async composition.

---

# 6. Code Example

```java
ExecutorService executor = Executors.newFixedThreadPool(4);

try {
    Future<String> future = executor.submit(() -> "done");
    System.out.println(future.get());
} finally {
    executor.shutdown();
}
```

---

# 7. Real-world Scenarios

* Background email sending.
* Report generation.
* Parallel file processing.
* Async external API calls.
* Scheduled cleanup jobs.

---

# Revision Notes

* Executor Framework manages thread execution.
* `ExecutorService` supports submit and shutdown.
* `Future` represents async result.
* Thread pool reuses worker threads.
* Always shut down executors.
* Use bounded queues for production safety.
* Size pools based on CPU-bound or IO-bound workload.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Fixed pool | `Executors.newFixedThreadPool(n)` |
| Submit result | `submit()` |
| Fire task | `execute()` |
| Result | `Future.get()` |
| Shutdown | `shutdown()` |
| Scheduled work | `ScheduledExecutorService` |

---

# Interview Questions with Answers

### 1. Why use thread pool?

To reuse threads and control concurrency instead of creating unlimited raw threads.

### 2. execute() vs submit()?

`execute()` does not return result. `submit()` returns `Future`.

### 3. Why should executor be shut down?

Worker threads may keep JVM alive and waste resources.

---

# Hands-on Exercises

### Exercise 1

Run three tasks using fixed thread pool.

**Answer**

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
try {
    for (int i = 0; i < 3; i++) {
        executor.submit(() -> System.out.println(Thread.currentThread().getName()));
    }
} finally {
    executor.shutdown();
}
```

