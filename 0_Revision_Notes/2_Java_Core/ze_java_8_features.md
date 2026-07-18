# Revision Notes

* Java 8 introduced lambdas, streams, Optional, default methods, and new Date/Time API.
* Lambda implements a functional interface.
* Functional interface has one abstract method.
* Use `@FunctionalInterface` for compiler safety.
* `Predicate` checks condition.
* `Function` transforms value.
* `Consumer` accepts value and returns nothing.
* `Supplier` supplies value.
* Captured local variables must be effectively final.

---

# Cheat Sheet

| Interface | Method | Purpose |
| --------- | ------ | ------- |
| `Predicate<T>` | `test` | condition |
| `Function<T,R>` | `apply` | transform |
| `Consumer<T>` | `accept` | consume |
| `Supplier<T>` | `get` | supply |
| `Runnable` | `run` | task |

---

# Interview Questions with Answers

### 1. Why lambdas were added?

To pass behavior concisely and support functional-style APIs like streams.

### 2. Lambda vs anonymous class?

Lambda is concise and works only with functional interfaces. Anonymous class can implement more complex class bodies.

### 3. What is method reference?

Short syntax for lambda that calls an existing method.

---

# Hands-on Exercises

### Exercise 1

Use Function to convert string to length.

**Answer**

```java
Function<String, Integer> length = String::length;
```

