# URL Shortener

### Subtopic: End-to-End Design

---

# 1. Fundamentals

A URL shortener converts long URLs into short codes.

```text
https://very-long-url.com/page -> https://sho.rt/abc123
```

Core operations:

* Create short URL.
* Redirect short URL to original.
* Track analytics.

---

# 2. Requirements

Functional:

* Generate short code.
* Store long URL mapping.
* Redirect by code.
* Optional custom alias.
* Optional expiry.

Non-functional:

* Low redirect latency.
* High availability.
* Unique short codes.
* Read-heavy scaling.

---

# 3. High-Level Design

```text
Client -> API Gateway -> URL Service -> DB
                              |
                            Redis
```

Read path:

```text
GET /abc123 -> cache lookup -> DB fallback -> redirect
```

Write path:

```text
POST long URL -> generate code -> store mapping -> return short URL
```

---

# 4. Short Code Generation

Options:

* Base62 encoded numeric ID.
* Random code with collision check.
* Distributed ID generator.

Base62 characters:

```text
a-z A-Z 0-9
```

---

# 5. Data Model

```text
short_code
long_url
created_at
expires_at
user_id
```

Index:

```text
short_code unique index
```

---

# 6. Common Mistakes

* No unique constraint on short code.
* No cache for hot redirects.
* Not validating URLs.
* Not handling expired links.
* Not planning for analytics write volume.
* Generating predictable codes when privacy matters.

---

# 7. Best Practices

* Cache hot mappings.
* Use 301/302 intentionally.
* Validate and normalize long URLs.
* Use unique index on code.
* Keep redirect path very fast.
* Process analytics asynchronously.

---

# Revision Notes

* URL shortener is read-heavy.
* Short code maps to long URL.
* Redis improves redirect latency.
* DB stores durable mapping.
* Unique code generation is critical.
* Analytics should be async.
* Expiry and custom aliases are optional features.

---

# Cheat Sheet

| Area | Design |
| ---- | ------ |
| Short code | Base62 / random |
| Fast redirect | Redis cache |
| Durability | Database |
| Uniqueness | Unique index |
| Analytics | Async events |
| Expiry | `expires_at` |

---

# Interview Questions with Answers

### 1. How generate short code?

Use Base62 encoding of unique ID or random code with collision check.

### 2. Why cache URL mappings?

Redirects are read-heavy and need low latency.

### 3. How handle analytics?

Publish click events asynchronously to avoid slowing redirect.

