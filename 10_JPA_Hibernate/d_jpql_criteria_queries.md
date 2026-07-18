# JPQL and Criteria

### Subtopic: Queries

---

# 1. Fundamentals

JPQL is JPA query language based on entity names and fields, not table and column names.

```java
@Query("select o from Order o where o.status = :status")
List<Order> findByStatus(OrderStatus status);
```

Criteria API builds queries programmatically and is useful for dynamic filters.

---

# 2. Core Concepts

## JPQL

JPQL works with entities.

```text
Order entity field: status
Database column: order_status
JPQL uses: o.status
```

## Parameter Binding

Always bind parameters.

```java
where o.customer.id = :customerId
```

This avoids injection and improves readability.

## Fetch Join

```java
select o from Order o join fetch o.customer
```

Fetch join loads association in the same query.

## DTO Projection

```java
select new com.example.OrderSummary(o.id, o.status, o.total)
from Order o
```

Use DTO projection for read screens that do not need managed entities.

## Criteria API

Criteria is type-safe at runtime structure level and useful when filters are optional.

---

# 3. Internal Working

Hibernate parses JPQL into SQL based on entity mapping metadata. JPQL is portable across databases, while generated SQL depends on dialect and mappings.

Criteria API creates the same logical query model programmatically.

---

# 4. Common Mistakes

* Writing table names in JPQL.
* Concatenating user input into queries.
* Using fetch join with pagination on collection relationships without understanding result expansion.
* Selecting entities for read-only list screens when DTOs are enough.
* Forgetting indexes for queried columns.
* Creating unreadable Criteria code for simple fixed queries.

---

# 5. Best Practices

* Use derived queries for simple repository methods.
* Use JPQL for readable fixed business queries.
* Use Criteria or Specifications for dynamic filters.
* Use DTO projections for read-heavy endpoints.
* Bind parameters.
* Inspect generated SQL for important queries.

---

# 6. Code Example

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
           select new com.example.api.OrderSummary(o.id, o.status, o.totalAmount)
           from Order o
           where (:status is null or o.status = :status)
           order by o.createdAt desc
           """)
    List<OrderSummary> findSummaries(OrderStatus status);
}
```

```java
public List<Order> search(OrderStatus status, Long customerId) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> order = cq.from(Order.class);

    List<Predicate> predicates = new ArrayList<>();
    if (status != null) {
        predicates.add(cb.equal(order.get("status"), status));
    }
    if (customerId != null) {
        predicates.add(cb.equal(order.get("customer").get("id"), customerId));
    }

    cq.where(predicates.toArray(Predicate[]::new));
    return entityManager.createQuery(cq).getResultList();
}
```

---

# 7. Real-world Scenarios

* Search orders by optional filters.
* Load details with required associations.
* Build dashboard DTOs.
* Admin screens with dynamic filters.
* Reporting queries with aggregation.

---

# Revision Notes

* JPQL queries entities and fields.
* SQL queries tables and columns.
* Criteria builds queries programmatically.
* Use parameter binding.
* Fetch join loads associations.
* DTO projections are good for read-only screens.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Simple query | Derived repository method |
| Fixed custom query | JPQL |
| Dynamic filters | Criteria or Specification |
| Load association | `join fetch` |
| Read-only response | DTO projection |
| Safety | Parameter binding |

---

# Interview Questions with Answers

### 1. JPQL vs SQL?

JPQL uses entity names and fields. SQL uses database tables and columns.

### 2. When use Criteria API?

When queries are dynamic and depend on optional filters or composed predicates.

### 3. What is fetch join?

A JPQL join that also initializes the joined association.

---

# Hands-on Exercises

### Exercise 1

Write JPQL to fetch paid orders.

**Answer**

```java
@Query("select o from Order o where o.status = 'PAID'")
List<Order> findPaidOrders();
```

