# Revision Notes

* Fetch strategy decides when relationships are loaded.
* Lazy loads on access.
* Eager loads immediately.
* `@ManyToOne` and `@OneToOne` are eager by default.
* `@OneToMany` and `@ManyToMany` are lazy by default.
* Prefer lazy relationships and fetch per use case.
* Lazy access outside persistence context can throw `LazyInitializationException`.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Delay load | `FetchType.LAZY` |
| Immediate load | `FetchType.EAGER` |
| Query-specific load | `join fetch` |
| Read model | DTO projection |
| Declarative fetch | `@EntityGraph` |

---

# Interview Questions with Answers

### 1. What is lazy loading?

Related data is loaded only when accessed.

### 2. Why avoid eager by default?

It can fetch unnecessary data and create heavy joins.

### 3. How avoid lazy initialization errors?

Fetch needed data inside a transaction and map to DTOs before returning.

---

# Hands-on Exercises

### Exercise 1

Make a `ManyToOne` lazy.

**Answer**

```java
@ManyToOne(fetch = FetchType.LAZY)
private Customer customer;
```

