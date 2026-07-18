# Transactions

### Subtopic: ACID

---

# 1. Fundamentals

A transaction is a group of database operations treated as one unit of work.

Either all operations succeed, or none should be permanently applied.

Example:

```sql
BEGIN;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

COMMIT;
```

If something fails:

```sql
ROLLBACK;
```

---

# 2. Core Concepts

## ACID

| Property | Meaning |
| -------- | ------- |
| Atomicity | All operations commit or all rollback |
| Consistency | Data moves from one valid state to another |
| Isolation | Concurrent transactions do not corrupt each other |
| Durability | Committed data survives failures |

## Commit

`COMMIT` makes changes permanent.

## Rollback

`ROLLBACK` cancels changes made in the transaction.

## Autocommit

Many clients run each SQL statement as its own transaction by default.

For multi-step business operations, disable autocommit or use framework transaction management.

## Isolation Levels

| Level | Prevents |
| ----- | -------- |
| Read Uncommitted | Very weak, dirty reads may happen in some DBs |
| Read Committed | Dirty reads |
| Repeatable Read | Dirty reads, many non-repeatable reads |
| Serializable | Strongest, behaves like serial order |

PostgreSQL treats `READ UNCOMMITTED` as `READ COMMITTED`.

---

# 3. Internal Working

Databases use transaction logs and concurrency control.

PostgreSQL uses MVCC, Multi-Version Concurrency Control.

With MVCC:

* Readers do not usually block writers.
* Writers create new row versions.
* Each transaction sees a consistent snapshot.
* Old row versions are later cleaned by vacuum.

Locks still matter for updates, deletes, schema changes, and conflicting writes.

---

# 4. Common Mistakes

* Updating multiple tables without a transaction.
* Keeping transactions open while calling external APIs.
* Doing slow user interaction inside a transaction.
* Assuming isolation prevents every race condition.
* Forgetting to handle deadlocks and serialization failures.
* Using transaction boundaries that are too large.
* Swallowing exceptions and accidentally committing bad state.

Bad:

```java
transferMoney();
callPaymentGateway();
updateAuditLog();
```

If this all runs inside one long transaction, locks may be held too long.

---

# 5. Best Practices

* Keep transactions short.
* Put all related database writes in the same transaction.
* Do external network calls outside the critical transaction when possible.
* Use proper isolation for money, inventory, and booking workflows.
* Add constraints to support consistency.
* Retry deadlocks and serialization failures where safe.
* Design idempotent operations for distributed systems.

---

# 6. Code Example

JDBC transaction:

```java
try {
    connection.setAutoCommit(false);

    debitAccount(connection, fromAccountId, amount);
    creditAccount(connection, toAccountId, amount);

    connection.commit();
} catch (SQLException ex) {
    connection.rollback();
    throw ex;
} finally {
    connection.setAutoCommit(true);
}
```

Spring style:

```java
@Transactional
public void transfer(long fromId, long toId, BigDecimal amount) {
    accountRepository.debit(fromId, amount);
    accountRepository.credit(toId, amount);
}
```

---

# 7. Real-world Scenarios

* Bank transfer debits one account and credits another.
* Order placement creates order, order items, and inventory reservation.
* User signup creates profile and audit event.
* Payment processing records status changes consistently.
* Job scheduler claims tasks without double processing.

---

# Revision Notes

* Transaction is one unit of work.
* ACID means Atomicity, Consistency, Isolation, Durability.
* `COMMIT` saves changes.
* `ROLLBACK` cancels changes.
* Keep transactions short.
* Use retries for deadlocks and serialization conflicts where safe.
* PostgreSQL uses MVCC.

---

# Cheat Sheet

| Need | SQL/Concept |
| ---- | ----------- |
| Start | `BEGIN` |
| Save | `COMMIT` |
| Cancel | `ROLLBACK` |
| Java manual | `setAutoCommit(false)` |
| Spring | `@Transactional` |
| Strong isolation | `SERIALIZABLE` |

---

# Interview Questions with Answers

### 1. What is a transaction?

A transaction is a set of operations executed as one unit, where all changes commit together or roll back together.

### 2. What is isolation?

Isolation controls how concurrent transactions see and affect each other's changes.

### 3. Why should transactions be short?

Long transactions hold locks and row versions longer, increasing contention, deadlocks, and cleanup pressure.

---

# Hands-on Exercises

### Exercise 1

Write SQL for a transfer transaction.

**Answer**

```sql
BEGIN;
UPDATE accounts SET balance = balance - 500 WHERE id = 1;
UPDATE accounts SET balance = balance + 500 WHERE id = 2;
COMMIT;
```

### Exercise 2

What should happen if the second update fails?

**Answer**

Run `ROLLBACK` so the first update is not permanently applied.

