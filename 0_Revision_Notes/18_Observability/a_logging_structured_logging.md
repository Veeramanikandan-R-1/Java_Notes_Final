# Revision Notes

* Structured logging writes machine-readable events.
* Use levels consistently: `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`.
* Include correlation IDs such as `traceId` and `requestId`.
* Use parameterized logging instead of string concatenation.
* Never log secrets, tokens, passwords, or sensitive payment data.
* Containerized apps should log to stdout/stderr.
* MDC adds request context to log lines.

---

# Cheat Sheet

| Need | Practice |
| ---- | -------- |
| Correlate request | `traceId` |
| Business event | `INFO` |
| Recoverable surprise | `WARN` |
| Actionable failure | `ERROR` |
| Production format | JSON |
| Java facade | SLF4J |

---

# Interview Questions with Answers

### 1. Why structured logs?

They can be queried by fields instead of only searched as text.

### 2. What is MDC?

A mapped diagnostic context that attaches request-scoped values to log events.

### 3. Why avoid logging request bodies?

They may contain sensitive data and can create high log volume.

---

# Hands-on Exercises

### Exercise 1

Write a safe order-created log.

**Answer**

```java
log.info("Order created orderId={} customerId={}", orderId, customerId);
```

