# Normalization

### Subtopic: 1NF, 2NF, 3NF

---

# 1. Fundamentals

Normalization is the process of designing relational tables to reduce duplication and protect data integrity.

It helps backend systems avoid update anomalies, inconsistent records, and difficult maintenance.

The common forms for application design are:

* First Normal Form, 1NF.
* Second Normal Form, 2NF.
* Third Normal Form, 3NF.

---

# 2. Core Concepts

## 1NF

First Normal Form means:

* Each column contains atomic values.
* There are no repeating groups.
* Each row can be uniquely identified.

Bad:

```text
orders(id, customer_name, items)
1, "Asha", "book,pen,bag"
```

Good:

```text
orders(id, customer_id)
order_items(id, order_id, product_id, quantity)
```

## 2NF

Second Normal Form means:

* Table is already in 1NF.
* Non-key columns depend on the whole primary key.

This matters mostly when a table has a composite primary key.

Bad:

```text
order_items(order_id, product_id, product_name, quantity)
```

If key is `(order_id, product_id)`, `product_name` depends only on `product_id`, not the whole key.

Good:

```text
products(id, name)
order_items(order_id, product_id, quantity)
```

## 3NF

Third Normal Form means:

* Table is already in 2NF.
* Non-key columns do not depend on other non-key columns.

Bad:

```text
employees(id, name, department_id, department_name)
```

`department_name` depends on `department_id`, not directly on employee.

Good:

```text
employees(id, name, department_id)
departments(id, name)
```

---

# 3. Internal Working

Normalization works by identifying dependencies.

Types:

| Dependency | Meaning |
| ---------- | ------- |
| Functional dependency | One value determines another |
| Partial dependency | Column depends on part of composite key |
| Transitive dependency | Column depends on another non-key column |

Databases enforce normalized design through:

* Primary keys.
* Foreign keys.
* Unique constraints.
* Not null constraints.
* Check constraints.

---

# 4. Common Mistakes

* Storing comma-separated lists in one column.
* Duplicating master data in transaction tables.
* Over-normalizing tiny lookup data without benefit.
* Under-normalizing critical business data and causing inconsistency.
* Ignoring foreign keys in production schemas.
* Treating normalization as opposite of performance.
* Denormalizing before measuring actual bottlenecks.

---

# 5. Best Practices

* Normalize core transactional data first.
* Use foreign keys for important relationships.
* Keep reference data in separate tables.
* Denormalize intentionally for reporting, caching, or read-heavy paths.
* Document denormalized columns and keep update rules clear.
* Use migrations to evolve schema safely.
* Model business identity separately from technical primary keys when needed.

---

# 6. Code Example

Normalized order schema:

```sql
CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(80) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customers(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    order_id BIGINT NOT NULL REFERENCES orders(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (order_id, product_id)
);
```

---

# 7. Real-world Scenarios

* E-commerce separates customers, orders, products, and order items.
* Banking separates accounts, transactions, and account owners.
* HR system separates employees and departments.
* SaaS app separates organizations, users, memberships, and roles.
* Reporting tables may denormalize data for faster dashboards.

---

# Revision Notes

* Normalization reduces duplication and inconsistency.
* 1NF requires atomic values and no repeating groups.
* 2NF removes partial dependency on composite keys.
* 3NF removes transitive dependency between non-key columns.
* Foreign keys preserve relationships.
* Denormalization is acceptable when intentional and measured.

---

# Cheat Sheet

| Form | Rule |
| ---- | ---- |
| 1NF | Atomic columns, no repeating groups |
| 2NF | No partial dependency on composite key |
| 3NF | No transitive dependency |
| PK | Uniquely identifies row |
| FK | References another table |

---

# Interview Questions with Answers

### 1. What is normalization?

Normalization is organizing tables to reduce duplication and maintain consistent data.

### 2. What violates 1NF?

A column containing multiple values, such as comma-separated product ids.

### 3. When is denormalization acceptable?

When a measured read-performance or reporting need justifies duplicated data and the update rules are controlled.

---

# Hands-on Exercises

### Exercise 1

Split `students(id, name, course_names)` into normalized tables.

**Answer**

```text
students(id, name)
courses(id, name)
student_courses(student_id, course_id)
```

### Exercise 2

Identify the issue: `orders(id, customer_id, customer_email)`.

**Answer**

`customer_email` belongs to `customers`; storing it in `orders` duplicates customer data unless intentionally denormalized.

