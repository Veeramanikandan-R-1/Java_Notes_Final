# Database Scaling

### Subtopic: Replication, Sharding

---

# 1. Fundamentals

Database scaling handles increasing data size and query load.

Main techniques:

* Replication
* Sharding
* Indexing
* Caching
* Read/write separation

---

# 2. Core Concepts

## Replication

Copy data from primary database to replicas.

```text
Primary -> Replica 1
        -> Replica 2
```

Use replicas for read scaling and high availability.

## Read Replica

Reads go to replicas. Writes go to primary.

Risk:

```text
Replication lag
```

Recent writes may not appear immediately on replica.

## Sharding

Split data across multiple databases.

Example:

```text
user_id % 4
```

Each shard stores subset of data.

## Partition Key

The field used to decide shard.

Good key has even distribution and supports common queries.

---

# 3. Internal Working

Replication improves read capacity, but write capacity still depends on primary.

Sharding improves data and write scalability, but adds routing, cross-shard query, and migration complexity.

---

# 4. Common Mistakes

* Sharding too early.
* Bad shard key causing hot shard.
* Ignoring replication lag.
* Cross-shard joins.
* No resharding plan.
* Using replicas for read-after-write critical flows.

---

# 5. Best Practices

* Optimize queries before sharding.
* Add indexes and caching first.
* Use read replicas for read-heavy load.
* Choose shard key carefully.
* Avoid cross-shard transactions.
* Monitor lag and hot shards.
* Plan migration strategy.

---

# Revision Notes

* Replication copies data to replicas.
* Read replicas scale reads.
* Primary handles writes.
* Replication lag can cause stale reads.
* Sharding splits data across databases.
* Shard key decides data placement.
* Sharding adds complexity.

---

# Cheat Sheet

| Need | Technique |
| ---- | --------- |
| Scale reads | Replicas |
| Scale writes/data | Sharding |
| Avoid stale read | Read primary |
| Faster query | Index |
| Reduce DB reads | Cache |
| Shard routing | Partition key |

---

# Interview Questions with Answers

### 1. Replication vs sharding?

Replication copies same data to replicas. Sharding splits different data across databases.

### 2. What is replication lag?

Delay between primary write and replica receiving that change.

### 3. What makes good shard key?

Even distribution, stable value, and alignment with common query patterns.

