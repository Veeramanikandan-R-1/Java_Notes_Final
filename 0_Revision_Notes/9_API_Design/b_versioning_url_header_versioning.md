# Revision Notes

* Version APIs only for backward-incompatible contract changes.
* URL versioning uses paths like `/api/v1/orders`.
* Header versioning uses headers like `X-API-Version: 2`.
* Adding optional fields usually does not require a new version.
* Removing, renaming, or changing field types usually requires a new version.
* Keep DTOs separate for different API versions.
* Use deprecation and sunset dates for old versions.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Simple versioning | `/api/v1/...` |
| Header version | `X-API-Version` |
| Media type version | `Accept` header |
| Breaking change | New version |
| Add optional field | Same version |
| Stability | Contract tests |

---

# Interview Questions with Answers

### 1. Why version APIs?

To evolve the API without breaking existing clients.

### 2. Which is easier: URL or header versioning?

URL versioning is usually easier to see, debug, cache, and route.

### 3. Why separate DTOs by version?

Because each version is a client contract and should not change accidentally.

---

# Hands-on Exercises

### Exercise 1

Show URL versioning for orders.

**Answer**

```http
GET /api/v1/orders/10
GET /api/v2/orders/10
```

