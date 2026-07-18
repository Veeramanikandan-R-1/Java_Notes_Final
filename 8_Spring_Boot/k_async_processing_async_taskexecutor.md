# Spring Boot

### Subtopic: Async Processing - @Async, TaskExecutor

---

# 1. Fundamentals

Async processing runs work in a different thread so the caller does not wait for the full operation.

Enable async:

```java
@EnableAsync
@SpringBootApplication
class DemoApplication {
}
```

Use `@Async`:

```java
@Async
void sendEmail() {
}
```

---

# 2. Core Concepts

## Return Types

Async methods can return:

| Type | Use |
| ---- | --- |
| `void` | Fire-and-forget |
| `CompletableFuture<T>` | Async result |

---

## TaskExecutor

Configure thread pool:

```java
@Bean
TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(20);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("app-async-");
    executor.initialize();
    return executor;
}
```

---

# 3. Internal Working

Spring applies async behavior using proxies.

When another bean calls an `@Async` method through the proxy, Spring submits work to a task executor.

Self-invocation can bypass async behavior because the call does not pass through the proxy.

---

# 4. Common Mistakes

* Forgetting `@EnableAsync`.
* Calling `@Async` method from same class.
* Using async for database writes without transaction awareness.
* Leaving executor unbounded.
* Ignoring failures in `void` async methods.
* Passing request-scoped objects to async code without care.

---

# 5. Best Practices

* Configure a bounded `TaskExecutor`.
* Use `CompletableFuture` when caller needs result.
* Log async failures.
* Keep async jobs idempotent.
* Use separate executors for different workloads when needed.

---

# 6. Code Example

```java
@Service
class EmailService {
    @Async
    CompletableFuture<Boolean> sendWelcomeEmail(String email) {
        System.out.println("Sending email to " + email);
        return CompletableFuture.completedFuture(true);
    }
}
```

---

# 7. Real-world Scenarios

* Sending emails after registration.
* Generating reports.
* Calling slow third-party APIs.
* Parallel enrichment of response data.
* Non-critical notification processing.

---

# Revision Notes

* Use `@EnableAsync` to enable async execution.
* Use `@Async` on methods called through Spring proxy.
* Configure `TaskExecutor` for production.
* Self-invocation can bypass async.
* Use `CompletableFuture` for async results.
* Handle async exceptions.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable async | `@EnableAsync` |
| Async method | `@Async` |
| Executor | `TaskExecutor` |
| Async result | `CompletableFuture<T>` |
| Thread pool impl | `ThreadPoolTaskExecutor` |

---

# Interview Questions with Answers

### 1. How does `@Async` work?

Spring proxies the bean and submits method execution to a task executor.

### 2. Why can self-invocation fail?

The internal method call does not go through the Spring proxy.

### 3. Why configure a custom executor?

To control thread count, queue size, naming, and resource usage.

---

# Hands-on Exercises

### Exercise 1

Create an async email method.

**Answer**

```java
@Async
CompletableFuture<Void> send(String email) {
    return CompletableFuture.completedFuture(null);
}
```

