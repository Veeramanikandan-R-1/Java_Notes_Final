# Revision Notes

* Rate limiting controls request volume.
* It protects against abuse and overload.
* Fixed window is simple but bursty.
* Sliding window is more accurate.
* Token bucket allows controlled bursts.
* Leaky bucket smooths traffic.
* Distributed rate limiting commonly uses Redis.
* Return HTTP 429 when limit exceeded.

---

# Cheat Sheet

| Need | Algorithm |
| ---- | --------- |
| Simple | Fixed window |
| Accurate rolling | Sliding window |
| Bursts | Token bucket |
| Smooth output | Leaky bucket |
| Distributed | Redis |
| Rejection | HTTP 429 |

---

# Interview Questions with Answers

### 1. Token bucket vs leaky bucket?

Token bucket allows bursts. Leaky bucket processes at steady rate.

### 2. Why Redis?

To share limit counters across app instances.

### 3. What identity can be limited?

User ID, IP, API key, tenant ID, or endpoint key.

