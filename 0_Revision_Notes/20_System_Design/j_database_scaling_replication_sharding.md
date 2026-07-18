# Revision Notes

* Replication copies data to replicas.
* Read replicas scale reads.
* Primary usually handles writes.
* Replication lag can cause stale reads.
* Sharding splits data across databases.
* Shard key decides placement.
* Sharding scales data/write load but adds complexity.
* Avoid sharding too early.

---

# Cheat Sheet

| Need | Technique |
| ---- | --------- |
| Scale reads | Read replicas |
| Scale writes/data | Sharding |
| Avoid stale read | Read primary |
| Faster query | Index |
| Reduce read load | Cache |
| Route shard | Shard key |

---

# Interview Questions with Answers

### 1. Replication vs sharding?

Replication copies same data. Sharding splits different data.

### 2. What is replication lag?

Delay for changes to reach replicas.

### 3. Good shard key?

Stable, evenly distributed, and aligned with common queries.

