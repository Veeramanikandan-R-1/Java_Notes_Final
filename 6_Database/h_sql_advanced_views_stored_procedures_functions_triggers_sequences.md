# SQL Advanced

### Subtopic: Views, Stored Procedures, Functions, Triggers, Sequences

---

# 1. Fundamentals

Advanced SQL features move some data logic closer to the database.

They are useful for consistency, reporting, auditing, and performance-sensitive workflows, but they must be used carefully in backend systems so business logic does not become hidden and hard to test.

Topics:

* Views.
* Stored procedures.
* Functions.
* Triggers.
* Sequences.

---

# 2. Core Concepts

## Views

A view is a saved query exposed like a table.

```sql
CREATE VIEW active_users AS
SELECT id, email, name
FROM users
WHERE status = 'ACTIVE';
```

Use:

```sql
SELECT * FROM active_users;
```

Views simplify repeated read queries and reporting.

## Functions

A function returns a value or table and can be used inside SQL.

```sql
CREATE FUNCTION full_name(first_name TEXT, last_name TEXT)
RETURNS TEXT
LANGUAGE SQL
AS $$
    SELECT first_name || ' ' || last_name;
$$;
```

Use:

```sql
SELECT full_name('Asha', 'Rao');
```

## Stored Procedures

A procedure performs an action and is called with `CALL`.

```sql
CREATE PROCEDURE mark_user_inactive(user_id BIGINT)
LANGUAGE SQL
AS $$
    UPDATE users
    SET status = 'INACTIVE'
    WHERE id = user_id;
$$;
```

Use:

```sql
CALL mark_user_inactive(10);
```

## Triggers

A trigger automatically runs logic when data changes.

```sql
CREATE FUNCTION set_updated_at()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
```

## Sequences

A sequence generates ordered numeric values.

```sql
CREATE SEQUENCE invoice_number_seq START 1000;

SELECT nextval('invoice_number_seq');
```

PostgreSQL uses sequences behind `SERIAL` and identity columns.

---

# 3. Internal Working

Views are usually expanded into the calling query during planning.

Functions and procedures execute inside the database engine.

Triggers run automatically as part of the triggering statement and transaction.

Sequences are independent objects. In PostgreSQL, sequence values are not rolled back after transaction rollback. This is normal and prevents concurrency bottlenecks.

---

# 4. Common Mistakes

* Hiding complex business logic in triggers without documentation.
* Assuming sequence numbers are gap-free.
* Creating views that are too slow for API use.
* Using functions in filters and blocking index usage.
* Letting stored procedures bypass application validation.
* Forgetting migration and version control for database code.
* Creating trigger recursion accidentally.

---

# 5. Best Practices

* Use views for stable read models and reports.
* Keep triggers small and predictable.
* Use triggers for audit timestamps, audit rows, and invariant enforcement.
* Keep business workflows in application code unless database-side logic is clearly justified.
* Version functions, procedures, views, and triggers through migrations.
* Monitor advanced SQL for performance impact.
* Remember sequence values can have gaps.

---

# 6. Code Example

Audit trigger:

```sql
CREATE TABLE user_audit (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    old_status TEXT,
    new_status TEXT,
    changed_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE FUNCTION audit_user_status()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF OLD.status IS DISTINCT FROM NEW.status THEN
        INSERT INTO user_audit(user_id, old_status, new_status)
        VALUES (OLD.id, OLD.status, NEW.status);
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_audit_user_status
AFTER UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION audit_user_status();
```

Invoice sequence:

```sql
CREATE SEQUENCE invoice_no_seq START 100000;

INSERT INTO invoices(invoice_no, customer_id, total_amount)
VALUES (nextval('invoice_no_seq'), 42, 999.00);
```

---

# 7. Real-world Scenarios

* View exposes active subscriptions for reporting.
* Function calculates tax or normalized search key.
* Procedure archives old records in batches.
* Trigger maintains `updated_at`.
* Trigger writes audit history for regulated data.
* Sequence generates invoice numbers.

---

# Revision Notes

* View is a saved query.
* Function returns a value or table.
* Procedure performs an action and is called with `CALL`.
* Trigger runs automatically on insert, update, or delete.
* Sequence generates numeric values.
* PostgreSQL sequence values can have gaps.
* Version database-side code with migrations.

---

# Cheat Sheet

| Feature | Use |
| ------- | --- |
| View | Reusable read query |
| Function | Return value/table |
| Procedure | Execute action |
| Trigger | Auto-run on data change |
| Sequence | Generate numbers |
| Call procedure | `CALL procedure_name(...)` |
| Next sequence | `nextval('seq_name')` |

---

# Interview Questions with Answers

### 1. What is a view?

A view is a stored SQL query that can be queried like a table.

### 2. Difference between function and procedure?

A function returns a value and can be used in SQL expressions. A procedure is called to perform actions.

### 3. Are sequence numbers gap-free?

No. In PostgreSQL, consumed sequence values are not rolled back, so gaps are expected.

### 4. When should triggers be avoided?

Avoid triggers for large hidden business workflows that are hard to test, debug, and reason about from application code.

---

# Hands-on Exercises

### Exercise 1

Create a view for paid orders.

**Answer**

```sql
CREATE VIEW paid_orders AS
SELECT id, customer_id, total_amount, created_at
FROM orders
WHERE status = 'PAID';
```

### Exercise 2

Create a sequence starting at 5000.

**Answer**

```sql
CREATE SEQUENCE ticket_no_seq START 5000;
```

