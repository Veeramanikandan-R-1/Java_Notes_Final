# Transactions

### Subtopic: Transactional Services

---

# 1. Fundamentals

A transaction groups database operations into one unit of work.

If all operations succeed, the transaction commits. If something fails, it rolls back.

In Spring, transactions are usually placed on service methods.

```java
@Transactional
public void placeOrder(CreateOrderRequest request) {
}
```

---

# 2. Core Concepts

## `@Transactional`

`@Transactional` opens a transaction around a method through Spring AOP.

```java
@Transactional(readOnly = true)
public OrderDetails getOrder(Long id) {
}
```

## Rollback Rules

By default, Spring rolls back on unchecked exceptions.

```java
throw new IllegalStateException("Payment failed");
```

Checked exceptions need explicit rollback configuration if required.

## Persistence Context Scope

Inside a transaction, loaded entities are managed. Hibernate dirty checking synchronizes changes at flush or commit.

## Propagation

Propagation defines how a method participates in existing transactions.

Common default:

```java
Propagation.REQUIRED
```

It joins an existing transaction or creates a new one.

---

# 3. Internal Working

Spring creates a proxy around the bean. When another bean calls a transactional method, the proxy starts transaction logic before the method and commits or rolls back after it.

Self-invocation does not pass through the proxy, so `this.someTransactionalMethod()` may not apply transaction behavior.

---

# 4. Common Mistakes

* Putting transactions only on repository methods.
* Calling transactional methods from the same class and expecting proxy behavior.
* Doing remote API calls inside long database transactions.
* Accessing lazy entities after transaction ends.
* Forgetting `readOnly = true` for read queries.
* Catching exceptions and hiding rollback conditions.

---

# 5. Best Practices

* Put transaction boundaries at service layer.
* Keep transactions short.
* Use `readOnly = true` for query methods.
* Avoid network calls inside transactions when possible.
* Let exceptions propagate when rollback is needed.
* Design service methods around business use cases.

---

# 6. Code Example

```java
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public Long placeOrder(CreateOrderRequest request) {
        Order order = new Order(request.customerId());
        request.items().forEach(item -> order.addItem(new OrderItem(item.productId(), item.quantity())));

        orderRepository.save(order);
        paymentRepository.save(new Payment(order, request.amount()));

        return order.getId();
    }

    @Transactional(readOnly = true)
    public OrderDetails getOrder(Long id) {
        return orderRepository.findDetails(id).orElseThrow();
    }
}
```

---

# 7. Real-world Scenarios

* Creating order and payment record together.
* Updating account balance and transaction log.
* Marking invoice paid and recording audit event.
* Bulk status update.
* Read-only reporting query.

---

# Revision Notes

* Transaction groups operations into one unit.
* Commit applies changes; rollback cancels them.
* Put `@Transactional` on service use cases.
* Dirty checking works for managed entities inside transaction.
* Spring transactions use proxies.
* Self-invocation can bypass transactional behavior.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Write transaction | `@Transactional` |
| Read transaction | `@Transactional(readOnly = true)` |
| Default propagation | `REQUIRED` |
| Rollback default | Runtime exceptions |
| Boundary layer | Service |
| Avoid | Long remote calls inside transaction |

---

# Interview Questions with Answers

### 1. Why put transactions on service methods?

Because services represent business use cases that often need multiple repository operations to succeed or fail together.

### 2. What is self-invocation issue?

A method calling another method on the same object bypasses the Spring proxy, so transactional advice may not run.

### 3. When does dirty checking flush updates?

Usually before query execution that needs synchronization and at transaction commit.

---

# Hands-on Exercises

### Exercise 1

Make a user update method transactional.

**Answer**

```java
@Transactional
public void changeEmail(Long userId, String email) {
    User user = userRepository.findById(userId).orElseThrow();
    user.changeEmail(email);
}
```

