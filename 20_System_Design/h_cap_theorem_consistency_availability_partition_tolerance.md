# CAP Theorem

### Subtopic: Consistency, Availability, Partition Tolerance

---

# 1. Fundamentals

CAP theorem says a distributed system cannot fully guarantee all three during a network partition:

* Consistency
* Availability
* Partition tolerance

During partition, system must choose between consistency and availability.

---

# 2. Core Concepts

## Consistency

Every read sees latest write.

## Availability

Every request receives a non-error response.

## Partition Tolerance

System continues despite network communication failure between nodes.

In real distributed systems, partitions can happen, so partition tolerance is required.

---

# 3. CP vs AP

## CP System

Chooses consistency over availability during partition.

Example:

```text
Reject writes if majority is unavailable.
```

## AP System

Chooses availability over immediate consistency.

Example:

```text
Accept writes and reconcile later.
```

---

# 4. Common Mistakes

* Saying system chooses CA in distributed environment.
* Treating CAP as normal-case performance rule.
* Ignoring business requirements.
* Assuming eventual consistency is always acceptable.
* Using CAP to explain every database behavior.

---

# 5. Best Practices

* Decide consistency by business domain.
* Use strong consistency for money/inventory critical paths.
* Use eventual consistency for feeds, analytics, notifications.
* Communicate stale data possibility clearly.
* Design reconciliation and retry flows.

---

# Revision Notes

* CAP applies during network partition.
* Consistency means latest data.
* Availability means response for every request.
* Partition tolerance means surviving network split.
* Distributed systems must tolerate partitions.
* During partition choose CP or AP.
* Business needs decide tradeoff.

---

# Cheat Sheet

| Term | Meaning |
| ---- | ------- |
| C | Latest consistent data |
| A | Always responds |
| P | Handles network split |
| CP | Consistency over availability |
| AP | Availability over consistency |

---

# Interview Questions with Answers

### 1. What does CAP theorem say?

During network partition, distributed systems must choose between consistency and availability.

### 2. Is CA possible?

Only when partition tolerance is not needed. In real distributed systems, partitions must be considered.

### 3. Where is eventual consistency acceptable?

Notifications, analytics, feeds, and non-critical read models.

