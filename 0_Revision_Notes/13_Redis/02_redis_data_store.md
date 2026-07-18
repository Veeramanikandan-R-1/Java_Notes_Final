# Revision Notes

* Redis is an in-memory data store with multiple data structures.
* Common uses include caching, sessions, counters, locks, rate limits, OTPs, and leaderboards.
* Strings, hashes, lists, sets, sorted sets, and streams serve different access patterns.
* Single Redis commands are atomic.
* TTL is essential for temporary keys.
* Eviction policy controls behavior when memory is full.
* Avoid `KEYS *`; prefer `SCAN`.
* Plan for Redis downtime and persistence needs.

---

# Cheat Sheet

| Need | Command |
| ---- | ------- |
| Set | `SET key value` |
| Get | `GET key` |
| Set with TTL | `SET key value EX seconds` |
| Delete | `DEL key` |
| Increment | `INCR key` |
| Hash set | `HSET key field value` |
| Set add | `SADD key value` |
| Sorted set add | `ZADD key score member` |
| Safe iteration | `SCAN` |

---

# Interview Q&A

### 1. Why is Redis fast?

It keeps data in memory and processes commands efficiently.

### 2. What is Redis TTL?

A time limit after which a key expires automatically.

### 3. Why should Redis outages be planned for?

Many apps depend on Redis for performance or coordination, so failure handling prevents cascading outages.

---

# Exercises

### Exercise 1

Store an OTP using `SET otp:<phone> <code> EX 300`.

### Exercise 2

Build a login rate limiter with `INCR` and `EXPIRE`.

