# SQL Interview

### Subtopic: Joins, Indexes, Transactions

---

# 1. Fundamentals

SQL interviews test whether you can query data correctly and understand performance and consistency.

Core areas:

* CRUD
* Joins
* Grouping
* Indexes
* Transactions
* Normalization

---

# 2. Must-Know Topics

## Joins

Inner join returns matching rows.

Left join returns all left rows plus matching right rows.

Right join returns all right rows plus matching left rows.

## Indexes

Indexes speed reads but add write overhead.

Best for columns used in:

* WHERE
* JOIN
* ORDER BY
* GROUP BY

## Transactions

ACID:

* Atomicity
* Consistency
* Isolation
* Durability

---

# 3. Common Interview Mistakes

* Confusing WHERE and HAVING.
* Not knowing left join null behavior.
* Saying indexes always improve performance.
* Ignoring transaction isolation.
* Not reading execution plans.
* Using SELECT * everywhere.

---

# 4. Best Practices

* Select only required columns.
* Index high-value query columns.
* Use transactions for multi-step consistency.
* Read execution plans.
* Avoid unnecessary joins.
* Understand isolation level tradeoffs.

---

# 5. Interview Questions with Answers

### 1. Inner join vs left join?

Inner join returns only matching rows. Left join returns all rows from left table even when right table has no match.

### 2. What is index?

A data structure that speeds lookup but adds storage and write overhead.

### 3. What is transaction?

A group of operations executed as one logical unit.

### 4. What is ACID?

Properties that ensure reliable transaction processing.

---

# Revision Notes

* Joins combine tables.
* Indexes improve read performance.
* Indexes slow writes.
* Transactions provide consistency.
* ACID is critical for backend systems.
* Execution plans show query strategy.

---

# Cheat Sheet

| Topic | Must Know |
| ----- | --------- |
| Inner join | Matching rows |
| Left join | All left rows |
| Index | Faster lookup |
| Transaction | Atomic unit |
| ACID | Reliability |
| Execution plan | Query optimization |

