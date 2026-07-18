# Revision Notes

* Persistence context is JPA first-level cache and identity map.
* One entity identity has one managed instance per persistence context.
* States: transient, managed, detached, removed.
* `persist` makes a new entity managed.
* `merge` copies detached state into a managed instance.
* The object returned from `merge` is managed.
* Managed entities are dirty checked.
* Detached changes are not saved automatically.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Transient | New object, not managed |
| Managed | Tracked by persistence context |
| Detached | No longer tracked |
| Removed | Scheduled for delete |
| `persist` | New to managed |
| `merge` | Detached state copied |
| Flush | Sync SQL to database |

---

# Interview Questions with Answers

### 1. What is persistence context?

The JPA-managed first-level cache that tracks entity identity and changes.

### 2. What is detached state?

An entity object exists in memory but is no longer tracked by the persistence context.

### 3. What does `merge` return?

A managed instance containing the copied state.

---

# Hands-on Exercises

### Exercise 1

Persist a new customer.

**Answer**

```java
Customer customer = new Customer("Asha");
entityManager.persist(customer);
```

