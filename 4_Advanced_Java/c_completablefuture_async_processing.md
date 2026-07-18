# CompletableFuture in Java

### Subtopic: Async Processing

---

# 1. Fundamentals

`CompletableFuture` represents an asynchronous computation that can be completed later.

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "result");
```

It improves over `Future` by supporting chaining, combining, and exception handling.

---

# 2. Core Concepts

## supplyAsync()

Use when task returns a value.

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> loadUser());
```

## runAsync()

Use when task does not return value.

```java
CompletableFuture<Void> future =
        CompletableFuture.runAsync(() -> sendEmail());
```

## thenApply()

Transforms result.

```java
future.thenApply(String::toUpperCase);
```

## thenAccept()

Consumes result.

```java
future.thenAccept(System.out::println);
```

## thenCompose()

Chains dependent async calls.

```java
getUser(id).thenCompose(user -> getOrders(user.id()));
```

## thenCombine()

Combines independent async calls.

```java
profileFuture.thenCombine(orderFuture, UserSummary::new);
```

## Exception Handling

```java
future.exceptionally(ex -> "fallback");
```

or:

```java
future.handle((result, ex) -> ex == null ? result : "fallback");
```

---

# 3. Internal Working

By default, async methods use the common ForkJoinPool.

For backend services, prefer passing a custom executor for control.

```java
CompletableFuture.supplyAsync(() -> callApi(), executor);
```

This prevents heavy async workloads from accidentally blocking shared common pool threads.

---

# 4. Common Mistakes

* Blocking immediately with `join()` or `get()`.
* Using common pool for blocking IO.
* Ignoring exceptions.
* Creating async chains that are harder to read than normal code.
* Forgetting timeouts.
* Mixing sync and async logic carelessly.

---

# 5. Best Practices

* Use custom executor for production async tasks.
* Use `thenCompose()` for dependent async calls.
* Use `thenCombine()` for independent calls.
* Handle exceptions in the chain.
* Add timeouts for external calls.
* Keep async chains readable.

---

# 6. Code Example

```java
CompletableFuture<User> userFuture =
        CompletableFuture.supplyAsync(() -> userClient.getUser(id), executor);

CompletableFuture<List<Order>> ordersFuture =
        CompletableFuture.supplyAsync(() -> orderClient.getOrders(id), executor);

CompletableFuture<UserDashboard> dashboard =
        userFuture.thenCombine(ordersFuture, UserDashboard::new)
                .exceptionally(ex -> UserDashboard.empty());
```

---

# 7. Real-world Scenarios

* Parallel downstream API calls.
* Async email/notification.
* Dashboard aggregation.
* Background enrichment.
* Non-blocking orchestration.

---

# Revision Notes

* `CompletableFuture` supports async computation.
* `supplyAsync()` returns value.
* `runAsync()` returns no value.
* `thenApply()` transforms.
* `thenCompose()` chains dependent async tasks.
* `thenCombine()` combines independent tasks.
* Use custom executor for production.
* Handle exceptions and timeouts.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Async with value | `supplyAsync()` |
| Async no value | `runAsync()` |
| Transform | `thenApply()` |
| Consume | `thenAccept()` |
| Chain future | `thenCompose()` |
| Combine futures | `thenCombine()` |
| Error fallback | `exceptionally()` |

---

# Interview Questions with Answers

### 1. Future vs CompletableFuture?

`Future` mainly blocks for result. `CompletableFuture` supports chaining, combining, and error handling.

### 2. thenApply vs thenCompose?

`thenApply()` transforms a value. `thenCompose()` flattens and chains another `CompletableFuture`.

### 3. Why use custom executor?

To control thread pool size and avoid blocking the common pool.

---

# Hands-on Exercises

### Exercise 1

Run two independent async calls and combine results.

**Answer**

```java
CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> "A");
CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "B");

String result = a.thenCombine(b, (x, y) -> x + y).join();
```

