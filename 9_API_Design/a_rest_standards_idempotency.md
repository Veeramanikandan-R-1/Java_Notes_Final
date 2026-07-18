# REST Standards

### Subtopic: Idempotency

---

# 1. Fundamentals

Idempotency means making the same request once or many times produces the same final server state.

In API design, idempotency protects clients from retries, network timeouts, duplicate clicks, and message redelivery.

```text
PUT /users/10/status
{ "status": "ACTIVE" }
```

Calling this once or five times should leave user `10` with status `ACTIVE`.

---

# 2. Core Concepts

## Idempotent HTTP Methods

| Method | Idempotent | Reason |
| ------ | ---------- | ------ |
| `GET` | Yes | Reads resource without changing state |
| `PUT` | Yes | Replaces resource with requested state |
| `DELETE` | Yes | Resource remains deleted after first success |
| `PATCH` | Depends | Safe only when patch operation is designed idempotently |
| `POST` | Usually no | Commonly creates a new resource/action |

Idempotent does not mean "same response every time". It means same final state.

## Safe vs Idempotent

Safe methods do not change server state. `GET` is safe and idempotent.

Idempotent methods may change state, but repeated calls do not keep changing it. `PUT` and `DELETE` are idempotent but not safe.

## Idempotency Keys

For non-idempotent operations like payments or order creation, clients can send a unique key.

```http
POST /payments
Idempotency-Key: pay_2026_001
```

Server stores the key, request fingerprint, and result. If the same key arrives again, server returns the original result instead of creating another payment.

---

# 3. Internal Working

Typical server flow:

1. Receive request with idempotency key.
2. Check whether key exists.
3. If yes, return stored result.
4. If no, process operation inside a transaction.
5. Store key and response atomically with business change.

The important part is atomicity. If payment is created but the key is not stored, a retry can duplicate the operation.

---

# 4. Common Mistakes

* Treating `POST` as automatically safe to retry.
* Making `DELETE` return `500` when resource is already deleted.
* Using idempotency key without request validation.
* Not expiring old idempotency keys.
* Storing key after the business operation outside the same transaction.
* Thinking idempotency means identical response status every time.

---

# 5. Best Practices

* Use `PUT` when client knows the resource identifier and sends desired final state.
* Use `POST` for server-generated resources and protect critical operations with idempotency keys.
* Store idempotency records with a unique constraint.
* Compare request hash for repeated keys to prevent key reuse with different payloads.
* Make retryable endpoints clear in API docs.
* Return useful conflict responses for duplicate keys with different payloads.

---

# 6. Code Example

```java
@PostMapping("/payments")
public ResponseEntity<PaymentResponse> createPayment(
        @RequestHeader("Idempotency-Key") String key,
        @RequestBody PaymentRequest request) {

    return idempotencyService.execute(key, request, () ->
            paymentService.createPayment(request)
    );
}
```

```java
@Transactional
public ResponseEntity<PaymentResponse> execute(
        String key,
        PaymentRequest request,
        Supplier<PaymentResponse> operation) {

    String requestHash = hash(request);

    return repository.findByKey(key)
            .map(record -> {
                if (!record.requestHash().equals(requestHash)) {
                    throw new IllegalArgumentException("Idempotency key reused with different request");
                }
                return ResponseEntity.status(record.statusCode()).body(record.response());
            })
            .orElseGet(() -> {
                PaymentResponse response = operation.get();
                repository.save(new IdempotencyRecord(key, requestHash, 201, response));
                return ResponseEntity.status(201).body(response);
            });
}
```

---

# 7. Real-world Scenarios

* Payment creation where duplicate charges are unacceptable.
* Order submission after mobile network retry.
* Webhook consumers receiving the same event more than once.
* Background job commands retried by a queue.
* Account status update using `PUT`.

---

# Revision Notes

* Idempotency means repeated identical requests produce the same final state.
* `GET`, `PUT`, and `DELETE` are idempotent by HTTP semantics.
* `POST` is usually not idempotent unless protected with an idempotency key.
* Idempotency key storage must be atomic with the business operation.
* Reusing the same key with different payload should be rejected.

---

# Cheat Sheet

| Need | Design |
| ---- | ------ |
| Read | `GET` |
| Replace known resource | `PUT` |
| Delete resource | `DELETE` |
| Create with retry safety | `POST` + `Idempotency-Key` |
| Prevent key reuse | Store request hash |
| Prevent duplicates | Unique key constraint |

---

# Interview Questions with Answers

### 1. What is idempotency?

It is the property where repeating the same request does not change the final server state after the first successful execution.

### 2. Is `POST` idempotent?

Not by default. It can be made idempotent using an idempotency key and server-side result storage.

### 3. Can an idempotent request return different status codes?

Yes. A first `DELETE` may return `204`, and a repeated `DELETE` may return `404` or `204`, but the final state remains deleted.

---

# Hands-on Exercises

### Exercise 1

Design an idempotent endpoint to update order status.

**Answer**

```http
PUT /orders/101/status
Content-Type: application/json

{ "status": "CANCELLED" }
```

The repeated request keeps order `101` in `CANCELLED` state.

