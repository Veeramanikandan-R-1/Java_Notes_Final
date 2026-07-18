# Revision Notes

* CAP applies during network partitions.
* Consistency means latest data for reads.
* Availability means every request gets response.
* Partition tolerance means system survives network split.
* Distributed systems must consider partitions.
* During partition, choose CP or AP.
* Business requirements decide tradeoff.

---

# Cheat Sheet

| Term | Meaning |
| ---- | ------- |
| C | Consistency |
| A | Availability |
| P | Partition tolerance |
| CP | Consistency over availability |
| AP | Availability over consistency |

---

# Interview Questions with Answers

### 1. What is CAP?

During partition, a distributed system must trade off consistency and availability.

### 2. Is CA realistic?

Not during network partitions in distributed systems.

### 3. Where use eventual consistency?

Feeds, analytics, notifications, and non-critical read models.

