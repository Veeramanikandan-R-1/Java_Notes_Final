# Revision Notes

* Cache reduces latency and database load.
* Distributed cache is shared by multiple app instances.
* Redis is common for distributed caching.
* Cache-aside means app checks cache, then DB on miss.
* TTL controls expiry.
* Cache invalidation is hard.
* Database should remain source of truth.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Shared cache | Redis |
| Local cache | Caffeine |
| Expiry | TTL |
| On update | Invalidate |
| Hot read data | Cache candidate |
| Stampede control | Lock/jitter/preload |

---

# Interview Questions with Answers

### 1. What is cache-aside?

Application reads cache first, loads DB on miss, then writes result to cache.

### 2. What is cache stampede?

Many requests hit database together after popular cache key expires.

### 3. Why TTL?

To reduce stale data and prevent unlimited cache retention.

