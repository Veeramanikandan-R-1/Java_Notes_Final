# Revision Notes

* URL shortener maps short code to long URL.
* Redirect path is read-heavy.
* Use Redis for hot mapping cache.
* Use database for durable mapping.
* Code generation must avoid collisions.
* Use unique index on short code.
* Process analytics asynchronously.

---

# Cheat Sheet

| Area | Choice |
| ---- | ------ |
| Code | Base62 / random |
| Cache | Redis |
| Storage | DB table |
| Redirect | 301/302 |
| Uniqueness | Unique index |
| Analytics | Async event |

---

# Interview Questions with Answers

### 1. How generate code?

Base62 encode unique ID or generate random code with collision check.

### 2. Why cache mappings?

Redirects need low latency and are read-heavy.

### 3. How track clicks?

Emit async event so redirect remains fast.

