# Entity Lifecycle

### Subtopic: Persistence Context, States

---

# 1. Fundamentals

The persistence context is a first-level cache and identity map managed by JPA.

It tracks entity instances and their state during a unit of work.

---

# 2. Core Concepts

## Entity States

| State | Meaning |
| ----- | ------- |
| Transient | New object, not managed, not in database |
| Managed | Attached to persistence context |
| Detached | Was managed, persistence context closed or entity detached |
| Removed | Scheduled for deletion |

## Managed Entity

```java
User user = entityManager.find(User.class, 1L);
user.changeEmail("new@example.com");
```

No explicit update call is required. Hibernate detects the change.

## Persist

```java
entityManager.persist(user);
```

Moves transient entity to managed state.

## Merge

```java
User managedCopy = entityManager.merge(detachedUser);
```

`merge` copies detached state into a managed instance. The returned object is managed, not necessarily the original argument.

## Remove

```java
entityManager.remove(user);
```

Marks managed entity for deletion.

---

# 3. Internal Working

The persistence context keeps one managed instance per entity identity. If the same row is loaded twice inside one context, Hibernate returns the same Java object reference.

Hibernate stores snapshots of managed entities. During flush, it compares current values with snapshots and generates SQL for changes.

---

# 4. Common Mistakes

* Expecting changes to detached entities to be saved automatically.
* Ignoring the object returned by `merge`.
* Calling `save` repeatedly when dirty checking is enough.
* Accessing lazy fields after entity becomes detached.
* Keeping persistence context open too long.
* Mixing entity objects across transactions without understanding state.

---

# 5. Best Practices

* Modify managed entities inside transactions.
* Use DTOs between layers instead of detached entities for updates.
* Keep transaction scope clear and short.
* Understand that `findById` inside transaction returns managed entity.
* Use `merge` carefully.
* Flush intentionally only when needed.

---

# 6. Code Example

```java
@Transactional
public void changeEmail(Long userId, String email) {
    User user = userRepository.findById(userId).orElseThrow();
    user.changeEmail(email);
}
```

Hibernate updates the row during flush because `user` is managed.

```java
@Transactional
public User updateDetached(User detached) {
    User managed = entityManager.merge(detached);
    return managed;
}
```

---

# 7. Real-world Scenarios

* Updating user profile inside service method.
* Loading aggregate root and changing child collection.
* Handling detached data from web requests.
* Deleting an entity with business validation.
* Avoiding duplicate object instances for same row in one transaction.

---

# Revision Notes

* Persistence context is first-level cache and identity map.
* Entity states: transient, managed, detached, removed.
* Managed entities are dirty checked.
* `persist` makes new entity managed.
* `merge` copies detached state and returns managed instance.
* Detached changes are not saved automatically.

---

# Cheat Sheet

| State/Operation | Meaning |
| --------------- | ------- |
| Transient | New, not managed |
| Managed | Tracked by persistence context |
| Detached | No longer tracked |
| Removed | Scheduled for delete |
| `persist` | New to managed |
| `merge` | Detached state to managed copy |
| Flush | Sync changes to DB |

---

# Interview Questions with Answers

### 1. What is persistence context?

JPA's first-level cache that manages entity identity and change tracking.

### 2. What is detached entity?

An entity object that is no longer associated with an active persistence context.

### 3. Why no update call in dirty checking?

Hibernate tracks managed entity snapshots and writes changed fields during flush.

---

# Hands-on Exercises

### Exercise 1

Update a managed product price.

**Answer**

```java
@Transactional
public void changePrice(Long id, BigDecimal price) {
    Product product = productRepository.findById(id).orElseThrow();
    product.changePrice(price);
}
```

