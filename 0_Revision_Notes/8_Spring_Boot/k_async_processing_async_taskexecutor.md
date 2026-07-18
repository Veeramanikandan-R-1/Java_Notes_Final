# Revision Notes

* `@Async` runs method asynchronously.
* Enable with `@EnableAsync`.
* Use `TaskExecutor` to control thread pool.
* Async method should be called through Spring proxy.
* Return `CompletableFuture` when result is needed.
* Handle exceptions carefully.
* Avoid using default executor blindly in production.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Enable async | `@EnableAsync` |
| Async method | `@Async` |
| Executor | `TaskExecutor` |
| Async result | `CompletableFuture` |
| Pool config | `ThreadPoolTaskExecutor` |

---

# Interview Questions with Answers

### 1. What does @Async do?

It runs a Spring bean method in another thread using configured executor.

### 2. Why custom TaskExecutor?

To control pool size, queue, thread names, and rejection behavior.

### 3. Why self-invocation issue?

Calling async method from same class bypasses Spring proxy.

