# Capstone: Order Management

### Subtopic: Spring Boot + PostgreSQL

---

# 1. Goal

Build an order management backend with Spring Boot and PostgreSQL.

This project validates:

* Relational modeling
* Transactions
* JPA relationships
* Order state transitions
* REST API design
* Query optimization

---

# 2. Core Features

* Create order.
* Add order items.
* Confirm order.
* Cancel order.
* Get order by ID.
* List orders by customer/status.
* Calculate order total.

---

# 3. Data Model

Tables:

* customers
* products
* orders
* order_items

Order fields:

```text
id, customer_id, status, total_amount, created_at, updated_at
```

Order item fields:

```text
id, order_id, product_id, quantity, price
```

---

# 4. Business Rules

* Order starts as CREATED.
* Only CREATED order can be confirmed.
* Confirmed order cannot be edited casually.
* Cancel rules depend on status.
* Total is calculated from order items.

---

# 5. API Design

```text
POST /api/orders
GET /api/orders/{id}
GET /api/orders?customerId=1&status=CREATED
POST /api/orders/{id}/confirm
POST /api/orders/{id}/cancel
```

---

# 6. Best Practices

* Use transaction for order creation.
* Model status as enum.
* Use optimistic locking if concurrent updates matter.
* Avoid exposing entities.
* Add indexes on customer/status.
* Handle invalid state with custom exception.
* Write integration tests for repository queries.

---

# Revision Notes

* Order management tests real backend modeling.
* Order and order items need transaction consistency.
* Status enum controls lifecycle.
* Service layer owns business rules.
* PostgreSQL stores relational data.
* Index query columns.
* Use DTOs for API.

---

# Interview Questions with Answers

### 1. Why transaction for order creation?

Order and items must be saved consistently as one unit.

### 2. Why enum for status?

It prevents invalid string states and makes transitions clearer.

### 3. How avoid invalid status transition?

Validate current status in service before changing state.

