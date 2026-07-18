# N Plus One Problem

### Subtopic: Optimization

---

# 1. Fundamentals

N plus one happens when one query loads parent records, then one additional query is executed for each parent to load related data.

```text
1 query for orders
N queries for each order.customer
```

This is one of the most common JPA performance problems.

---

# 2. Core Concepts

## Example

```java
List<Order> orders = orderRepository.findAll();
for (Order order : orders) {
    System.out.println(order.getCustomer().getName());
}
```

If `customer` is lazy, each access can trigger another query.

## Common Fixes

| Technique | Use Case |
| --------- | -------- |
| Fetch join | Load required association in same query |
| Entity graph | Declarative fetch plan |
| DTO projection | Read only required fields |
| Batch fetching | Reduce many queries into fewer IN queries |

---

# 3. Internal Working

Hibernate lazy associations are loaded on access. When iterating over many parent entities, each uninitialized association can cause its own select.

Batch fetching changes this pattern by loading multiple lazy associations at once using an `IN` clause.

---

# 4. Common Mistakes

* Solving all N plus one issues with eager loading.
* Not checking SQL logs during development.
* Using fetch join on multiple collections and creating row explosion.
* Paginating collection fetch joins carelessly.
* Returning entities from APIs and triggering lazy loads in serializers.
* Ignoring production data volume.

---

# 5. Best Practices

* Enable SQL logging or use datasource proxy in development.
* Design repository methods around screen/query needs.
* Use fetch join for single-valued associations.
* Use DTO projections for list pages.
* Use batch size for repeated lazy loads.
* Test query counts for important endpoints.

---

# 6. Code Example

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
           select o
           from Order o
           join fetch o.customer
           where o.status = :status
           """)
    List<Order> findByStatusWithCustomer(OrderStatus status);
}
```

DTO projection:

```java
@Query("""
       select new com.example.OrderRow(o.id, c.name, o.totalAmount)
       from Order o
       join o.customer c
       where o.status = :status
       """)
List<OrderRow> findRows(OrderStatus status);
```

Batch fetching:

```java
@BatchSize(size = 50)
@ManyToOne(fetch = FetchType.LAZY)
private Customer customer;
```

---

# 7. Real-world Scenarios

* Order list showing customer name.
* Blog posts showing author names.
* Products showing category names.
* Transaction list showing account holder.
* Admin exports touching related entities.

---

# Revision Notes

* N plus one means 1 parent query plus N child queries.
* Lazy access inside loops often causes it.
* Eager loading is not a universal fix.
* Use fetch join, entity graph, DTO projection, or batch fetching.
* Verify by inspecting SQL and query counts.

---

# Cheat Sheet

| Problem | Fix |
| ------- | --- |
| Need relation now | `join fetch` |
| Read-only list | DTO projection |
| Declarative fetch | `@EntityGraph` |
| Many lazy refs | `@BatchSize` |
| Detect issue | SQL logs/query count |
| Avoid | Blind eager loading |

---

# Interview Questions with Answers

### 1. What is N plus one?

One query loads parent rows, then N more queries load related data for each parent.

### 2. How do you fix it?

Fetch only what the use case needs using fetch join, entity graph, DTO projection, or batch fetching.

### 3. Why is eager loading not always the answer?

It can load unnecessary data and create heavy joins in unrelated use cases.

---

# Hands-on Exercises

### Exercise 1

Fix order list N plus one for customer names.

**Answer**

```java
@Query("select o from Order o join fetch o.customer")
List<Order> findAllWithCustomer();
```

