# Revision Notes

* Stream is used for collection processing.
* Stream pipeline has source, intermediate operations, terminal operation.
* Intermediate operations are lazy.
* Terminal operation triggers execution.
* `filter()` selects elements.
* `map()` transforms elements.
* `reduce()` combines values.
* A stream can be consumed only once.
* Avoid side effects in stream operations.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Filter | `.filter(x -> condition)` |
| Map | `.map(x -> value)` |
| Reduce | `.reduce(identity, op)` |
| Collect list | `.toList()` |
| Group | `groupingBy(...)` |
| Sort | `.sorted(...)` |
| Count | `.count()` |
| First | `.findFirst()` |

---

# Interview Questions with Answers

### 1. What is terminal operation?

An operation that triggers stream execution and produces final result or side effect.

### 2. Can a stream be reused?

No. Once consumed, it cannot be reused.

### 3. When should you avoid streams?

When code becomes less readable or requires complex stateful logic.

---

# Hands-on Exercises

### Exercise 1

Find sum of even numbers.

**Answer**

```java
int sum = numbers.stream()
        .filter(n -> n % 2 == 0)
        .mapToInt(Integer::intValue)
        .sum();
```

