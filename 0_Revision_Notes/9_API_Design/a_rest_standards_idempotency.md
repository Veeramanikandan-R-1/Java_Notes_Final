# Revision Notes

* Idempotency means repeating the same request produces the same final server state.
* `GET`, `PUT`, and `DELETE` are idempotent by design.
* `POST` is usually not idempotent because it often creates a new resource or action.
* Critical `POST` operations can be protected with `Idempotency-Key`.
* Store idempotency key, request hash, status code, and response atomically.
* Same key with different payload should be rejected.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Read safely | `GET` |
| Replace resource | `PUT` |
| Delete resource | `DELETE` |
| Retry-safe create | `POST` + idempotency key |
| Detect misuse | Request hash |
| Prevent duplicate key | Unique constraint |

---

# Interview Questions with Answers

### 1. What is idempotency?

Repeating the same request does not keep changing server state.

### 2. Is same response required?

No. Final state matters more than identical response.

### 3. Why use idempotency keys?

They prevent duplicate side effects during retries, especially for payments and order creation.

---

# Hands-on Exercises

### Exercise 1

Make payment creation retry-safe.

**Answer**

```http
POST /payments
Idempotency-Key: payment-101
```

