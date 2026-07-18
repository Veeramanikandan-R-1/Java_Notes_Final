# Revision Notes

* Normalization organizes tables to reduce duplication and inconsistency.
* 1NF means atomic values and no repeating groups.
* 2NF means every non-key column depends on the whole key.
* 3NF means non-key columns do not depend on other non-key columns.
* Primary keys identify rows.
* Foreign keys model relationships.
* Denormalization should be intentional, documented, and measured.

---

# Cheat Sheet

| Form | Meaning |
| ---- | ------- |
| 1NF | Atomic values |
| 2NF | No partial dependency |
| 3NF | No transitive dependency |
| PK | Unique row identity |
| FK | Reference to another table |
| Join table | Models many-to-many |

---

# Interview Questions with Answers

### 1. What is normalization?

Normalization is designing relational tables to reduce duplicate data and preserve consistency.

### 2. What violates 1NF?

Repeating groups or multiple values in one column, such as comma-separated product ids.

### 3. What is transitive dependency?

A non-key column depends on another non-key column instead of depending directly on the key.

---

# Hands-on Exercises

### Exercise 1

Normalize `orders(id, product_ids)`.

**Answer**

```text
orders(id)
order_items(order_id, product_id)
products(id)
```

