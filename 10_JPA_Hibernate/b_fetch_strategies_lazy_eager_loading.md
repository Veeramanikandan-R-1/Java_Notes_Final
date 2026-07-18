# Fetch Strategies

### Subtopic: Lazy and Eager Loading

---

# 1. Fundamentals

Fetch strategy controls when related data is loaded from the database.

```java
@ManyToOne(fetch = FetchType.LAZY)
private Customer customer;
```

Good fetch design prevents unnecessary queries, heavy joins, and lazy loading errors.

---

# 2. Core Concepts

## Lazy Loading

Lazy loading delays fetching related data until it is accessed.

```java
order.getCustomer().getName();
```

This may trigger a query if `customer` was not loaded.

## Eager Loading

Eager loading loads related data immediately with the parent.

```java
@OneToMany(fetch = FetchType.EAGER)
private List<OrderItem> items;
```

This sounds convenient but can create large SQL joins and performance problems.

## Defaults

| Relationship | Default |
| ------------ | ------- |
| `@ManyToOne` | EAGER |
| `@OneToOne` | EAGER |
| `@OneToMany` | LAZY |
| `@ManyToMany` | LAZY |

Many teams explicitly set fetch types to avoid relying on defaults.

---

# 3. Internal Working

Hibernate commonly uses proxies or persistent collections for lazy loading. When code accesses the lazy field inside an open persistence context, Hibernate loads it.

If the session is closed, accessing lazy data can throw `LazyInitializationException`.

---

# 4. Common Mistakes

* Using eager loading to avoid lazy errors.
* Accessing lazy fields after transaction ends.
* Returning entities from controllers and triggering accidental queries.
* Forgetting that `@ManyToOne` is eager by default.
* Fetching large object graphs for simple list pages.
* Using Open Session in View as a hidden query machine.

---

# 5. Best Practices

* Prefer lazy relationships by default.
* Fetch exactly what a use case needs.
* Use fetch joins, entity graphs, or DTO projections for read use cases.
* Keep transactions around service methods.
* Convert entities to DTOs inside the transaction.
* Measure SQL logs for critical endpoints.

---

# 6. Code Example

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
           select o
           from Order o
           join fetch o.customer
           where o.id = :id
           """)
    Optional<Order> findByIdWithCustomer(Long id);
}
```

```java
@Transactional(readOnly = true)
public OrderDetails getOrder(Long id) {
    Order order = repository.findByIdWithCustomer(id)
            .orElseThrow();

    return new OrderDetails(
            order.getId(),
            order.getCustomer().getName()
    );
}
```

---

# 7. Real-world Scenarios

* Order details needs customer and items.
* Order list needs only order summary.
* Admin dashboard needs aggregate counts, not full relationships.
* Export job needs batch fetching to avoid many small queries.

---

# Revision Notes

* Lazy loads relation when accessed.
* Eager loads relation immediately.
* Prefer lazy by default.
* Fetch required data per use case.
* Lazy access outside persistence context can fail.
* Use fetch join, entity graph, or DTO projection intentionally.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Delay loading | `FetchType.LAZY` |
| Load immediately | `FetchType.EAGER` |
| Query-specific relation load | `join fetch` |
| Read shape | DTO projection |
| Service safety | `@Transactional(readOnly = true)` |

---

# Interview Questions with Answers

### 1. What is lazy loading?

Loading related data only when code first accesses it.

### 2. Why avoid eager loading by default?

It can load large object graphs and create expensive SQL.

### 3. What causes `LazyInitializationException`?

Accessing a lazy association after the persistence context is closed.

---

# Hands-on Exercises

### Exercise 1

Fetch order with customer in one query.

**Answer**

```java
@Query("select o from Order o join fetch o.customer where o.id = :id")
Optional<Order> findByIdWithCustomer(Long id);
```

