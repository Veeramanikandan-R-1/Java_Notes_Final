# Caching

### Subtopic: Distributed Cache

---

# 1. Fundamentals

Caching stores frequently accessed data in a faster layer to reduce latency and backend load.

```text
Client -> App -> Cache -> Database
```

Distributed cache is shared by multiple application instances.

Common tool:

```text
Redis
```

---

# 2. Core Concepts

## Cache Aside Pattern

Application manages cache.

```text
Read cache
If miss -> read database
Store result in cache
Return result
```

## TTL

TTL means time to live.

```text
user:101 expires in 10 minutes
```

TTL prevents stale data from living forever.

## Cache Invalidation

Hardest part of caching.

Options:

* Delete cache on update.
* Update cache on update.
* Use short TTL.
* Use event-based invalidation.

## Local vs Distributed Cache

| Type | Scope | Example |
| ---- | ----- | ------- |
| Local | Single app instance | Caffeine |
| Distributed | Shared by instances | Redis |

Distributed cache is better when many app instances need same cached data.

---

# 3. Internal Working

Cache improves read performance by avoiding repeated database calls.

But cache introduces consistency tradeoffs. Cached data may be stale for some time.

For high-traffic systems, cache also protects database from read spikes.

---

# 4. Common Mistakes

* No TTL.
* Caching highly volatile data.
* Caching nulls without strategy.
* Cache stampede during mass expiry.
* Not invalidating cache after update.
* Using cache as source of truth.

---

# 5. Best Practices

* Cache read-heavy data.
* Use TTL.
* Invalidate on writes.
* Protect against cache stampede.
* Monitor hit ratio.
* Keep cache values compact.
* Treat database as source of truth.

---

# 6. Real-world Scenario

Product catalog is read frequently and updated less often. Cache product details in Redis with TTL and invalidate when product changes.

---

# Revision Notes

* Cache reduces latency and database load.
* Distributed cache is shared across instances.
* Redis is common distributed cache.
* Cache-aside is common pattern.
* TTL prevents permanent stale data.
* Invalidation is critical.
* Database remains source of truth.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Shared cache | Redis |
| Single instance cache | Caffeine |
| Expiry | TTL |
| On update | Invalidate cache |
| Read-heavy data | Good cache candidate |

---

# Interview Questions with Answers

### 1. What is cache-aside?

Application checks cache first, loads from database on miss, then stores result in cache.

### 2. What is cache stampede?

Many requests hit database at once after popular cached data expires.

### 3. Why use TTL?

To limit stale data and prevent cache from growing forever.

