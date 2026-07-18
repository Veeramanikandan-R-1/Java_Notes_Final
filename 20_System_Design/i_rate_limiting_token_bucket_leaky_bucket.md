# Rate Limiting

### Subtopic: Token Bucket, Leaky Bucket

---

# 1. Fundamentals

Rate limiting controls how many requests a client can make in a time window.

It protects systems from abuse, overload, and accidental traffic spikes.

---

# 2. Core Concepts

## Fixed Window

Allow N requests per fixed interval.

Simple but can allow bursts at window boundary.

## Sliding Window

Tracks requests over rolling time window.

More accurate but more storage/compute.

## Token Bucket

Bucket refills tokens at fixed rate.

Each request consumes one token.

Allows controlled bursts.

## Leaky Bucket

Requests are processed at fixed rate.

Smooths traffic but may queue or drop bursts.

---

# 3. Internal Working

Distributed rate limiting usually uses Redis.

Key example:

```text
rate:user:101
```

TTL controls time window.

Atomic Redis operations prevent race conditions between app instances.

---

# 4. Common Mistakes

* Per-instance rate limit in multi-instance app.
* No clear client identity.
* Not returning rate limit headers.
* Same limit for all endpoints.
* No bypass strategy for internal systems.
* Not considering burst behavior.

---

# 5. Best Practices

* Use Redis for distributed limits.
* Choose key carefully: user, IP, API key.
* Return HTTP `429 Too Many Requests`.
* Include retry headers.
* Use stricter limits for expensive APIs.
* Monitor rejected requests.

---

# Revision Notes

* Rate limiting protects systems.
* Fixed window is simple but bursty.
* Sliding window is more accurate.
* Token bucket allows controlled bursts.
* Leaky bucket smooths request rate.
* Distributed limit commonly uses Redis.
* Return HTTP 429 when limit exceeded.

---

# Cheat Sheet

| Need | Algorithm |
| ---- | --------- |
| Simple limit | Fixed window |
| Accurate rolling limit | Sliding window |
| Allow bursts | Token bucket |
| Smooth traffic | Leaky bucket |
| Distributed limit | Redis |
| Reject response | 429 |

---

# Interview Questions with Answers

### 1. Token bucket vs leaky bucket?

Token bucket allows bursts up to bucket capacity. Leaky bucket processes at steady rate.

### 2. Why use Redis?

To share rate limit state across multiple app instances.

### 3. What status code for rate limit?

HTTP 429 Too Many Requests.

