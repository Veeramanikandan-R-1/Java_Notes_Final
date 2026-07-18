# Revision Notes

* `CompletableFuture` represents async computation.
* `supplyAsync()` runs async task with result.
* `runAsync()` runs async task without result.
* `thenApply()` transforms result.
* `thenCompose()` chains dependent futures.
* `thenCombine()` combines independent futures.
* Use custom executor for blocking IO.
* Handle exceptions and timeouts.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Async value | `supplyAsync()` |
| Async no value | `runAsync()` |
| Transform | `thenApply()` |
| Consume | `thenAccept()` |
| Chain | `thenCompose()` |
| Combine | `thenCombine()` |
| Fallback | `exceptionally()` |
| Finish | `join()` |

---

# Interview Questions with Answers

### 1. Future vs CompletableFuture?

`Future` mainly blocks for result. `CompletableFuture` supports composition and callbacks.

### 2. thenApply vs thenCompose?

`thenApply()` maps a value. `thenCompose()` chains another future and flattens it.

### 3. Why use custom executor?

To control threads and avoid blocking the common pool.

---

# Hands-on Exercises

### Exercise 1

Combine two futures.

**Answer**

```java
CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> "A");
CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "B");
String result = a.thenCombine(b, String::concat).join();
```

