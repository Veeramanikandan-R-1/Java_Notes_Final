# Revision Notes

* Transaction groups database operations into one unit.
* Commit saves changes; rollback cancels them.
* Place `@Transactional` on service use cases.
* Spring transactions are proxy-based.
* Self-invocation can bypass transactional behavior.
* Runtime exceptions roll back by default.
* Managed entities are dirty checked inside transaction.
* Keep transactions short.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Write use case | `@Transactional` |
| Read use case | `@Transactional(readOnly = true)` |
| Default propagation | `REQUIRED` |
| Default rollback | Runtime exception |
| Boundary | Service layer |
| Avoid | Long network calls in transaction |

---

# Interview Questions with Answers

### 1. Why service-level transactions?

Business use cases often involve multiple database operations that must succeed or fail together.

### 2. What is self-invocation?

A method in the same class calls another transactional method directly, bypassing the Spring proxy.

### 3. What does `readOnly = true` do?

It communicates read intent and can let the provider/database optimize behavior.

---

# Hands-on Exercises

### Exercise 1

Update an entity using dirty checking.

**Answer**

```java
@Transactional
public void rename(Long id, String name) {
    Product product = repository.findById(id).orElseThrow();
    product.rename(name);
}
```

