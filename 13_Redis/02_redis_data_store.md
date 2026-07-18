# Redis

### Subtopic: Data Store

---

# 1. Fundamentals

Redis is an in-memory data store commonly used as a cache, distributed lock helper, rate limiter, queue-like structure, session store, and fast key-value database.

Redis is fast because it keeps data primarily in memory and uses efficient data structures.

Common backend use cases:

* Caching
* Rate limiting
* Distributed locks
* Session storage
* Leaderboards
* Counters
* Idempotency keys
* Short-lived verification codes
* Pub/Sub events

---

# 2. Core Data Structures

| Structure | Use |
| --------- | --- |
| String | Simple value, JSON blob, counter |
| Hash | Object-like fields |
| List | Queue-like ordered values |
| Set | Unique unordered values |
| Sorted Set | Ranking and leaderboard |
| Stream | Append-only event log |
| Bitmap | Compact boolean flags |
| HyperLogLog | Approximate unique counts |

Choose data structure based on access pattern, not only storage shape.

---

# 3. Basic Commands

```redis
SET user:1:name "Asha"
GET user:1:name

SET otp:login:123456 "u1" EX 300
DEL otp:login:123456

INCR rate:user:u1
EXPIRE rate:user:u1 60
```

TTL is essential for temporary data such as OTPs, sessions, locks, and idempotency keys.

---

# 4. Redis with Spring Data Redis

```java
@Service
class OtpService {

    private final StringRedisTemplate redisTemplate;

    OtpService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    void storeOtp(String phone, String otp) {
        redisTemplate.opsForValue().set(
            "otp:" + phone,
            otp,
            Duration.ofMinutes(5)
        );
    }

    boolean verifyOtp(String phone, String otp) {
        String stored = redisTemplate.opsForValue().get("otp:" + phone);
        return otp.equals(stored);
    }
}
```

Use typed templates and serializers deliberately. Serialization choices affect compatibility and debugging.

---

# 5. Internal Working

Redis uses a single-threaded event loop for command execution in traditional setups.

This design avoids many locking costs and keeps operations fast. Expensive commands can still block other clients.

Important internals:

* Data lives in memory.
* Commands are atomic at single-command level.
* Persistence can be configured using RDB snapshots and AOF.
* Replication can copy data to replicas.
* Cluster mode partitions keys across hash slots.
* Eviction policy controls what happens when memory is full.

Redis is fast, but it is not magic. Poor key design or expensive commands can hurt production systems.

---

# 6. Expiry and Eviction

Expiry is per-key TTL.

Eviction happens when Redis reaches max memory.

Common policies:

| Policy | Meaning |
| ------ | ------- |
| `noeviction` | Return error when memory is full |
| `allkeys-lru` | Evict least recently used keys |
| `volatile-lru` | Evict LRU keys only among keys with TTL |
| `allkeys-lfu` | Evict least frequently used keys |
| `volatile-ttl` | Evict keys with nearest expiry |

Pick policy based on whether Redis is used as cache, store, or mixed workload.

---

# 7. Distributed Locks

Simple lock pattern:

```redis
SET lock:order:o1 request-123 NX PX 10000
```

Meaning:

* `NX`: set only if key does not exist
* `PX 10000`: expire after 10 seconds

Release should verify owner token before deleting.

```lua
if redis.call("GET", KEYS[1]) == ARGV[1] then
    return redis.call("DEL", KEYS[1])
else
    return 0
end
```

For critical locking, use a mature library and understand failure modes.

---

# 8. Rate Limiting

Fixed-window example:

```java
public boolean allow(String userId) {
    String key = "rate:login:" + userId;
    Long count = redisTemplate.opsForValue().increment(key);

    if (count != null && count == 1) {
        redisTemplate.expire(key, Duration.ofMinutes(1));
    }

    return count != null && count <= 5;
}
```

This allows 5 attempts per minute.

For high-risk systems, consider sliding-window or token-bucket approaches.

---

# 9. Common Mistakes

* Using Redis as the source of truth without persistence and backup planning.
* Running dangerous commands like `KEYS *` in production.
* No TTL on temporary keys.
* Large values that cause memory pressure.
* Poor key naming.
* Assuming Redis transactions are the same as relational database transactions.
* Not handling Redis downtime.
* Using blocking commands carelessly.
* Sharing Redis between cache and critical data without isolation.
* Ignoring serialization compatibility.

---

# 10. Best Practices

* Use clear key namespaces such as `user:123:session`.
* Set TTL for temporary data.
* Prefer `SCAN` over `KEYS` for iteration.
* Monitor memory, connected clients, latency, evictions, and hit rate.
* Use separate Redis instances or databases for different criticality levels when needed.
* Configure persistence when Redis stores important data.
* Design fallback behavior for Redis outages.
* Avoid storing very large objects.
* Use pipelining for bulk independent commands.
* Use Lua scripts for small atomic multi-step operations.

---

# 11. Production Backend Scenarios

* Store idempotency keys for payment requests.
* Rate limit login attempts.
* Keep short-lived OTP values.
* Maintain online user presence.
* Store API response cache.
* Build leaderboard with sorted sets.
* Implement distributed lock around scheduled jobs.
* Store Spring Session data for horizontally scaled apps.

---

# Revision Notes

* Redis is an in-memory data store with rich data structures.
* Strings, hashes, sets, sorted sets, lists, and streams cover different access patterns.
* Single Redis commands are atomic.
* TTL prevents temporary data from living forever.
* Eviction policy matters when memory is full.
* Avoid `KEYS *` in production.
* Plan fallback behavior for Redis downtime.

---

# Cheat Sheet

| Need | Redis |
| ---- | ----- |
| Set value | `SET key value` |
| Get value | `GET key` |
| Set with TTL | `SET key value EX seconds` |
| Delete | `DEL key` |
| Counter | `INCR key` |
| Hash field | `HSET key field value` |
| Unique values | `SADD key value` |
| Ranking | `ZADD key score member` |
| Iterate safely | `SCAN` |

---

# Interview Questions with Answers

### 1. Why is Redis fast?

It stores data primarily in memory and uses efficient event-driven command processing.

### 2. Is Redis only a cache?

No. It can also be used for counters, locks, queues, streams, sessions, leaderboards, and short-lived data.

### 3. Why avoid `KEYS *` in production?

It can block Redis while scanning all keys, causing latency spikes.

### 4. What is the purpose of TTL?

TTL automatically removes keys after a defined time, which is critical for temporary data.

---

# Hands-on Exercises

### Exercise 1

Store an OTP in Redis with a five-minute TTL.

### Exercise 2

Implement a Redis-based login rate limiter allowing five attempts per minute.

