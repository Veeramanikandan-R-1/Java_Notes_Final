# Logging

### Subtopic: Structured Logging

---

# 1. Fundamentals

Logging records meaningful events from an application.

Structured logging writes logs as machine-readable key-value data, commonly JSON, instead of free-form text. This makes logs searchable, filterable, and easier to correlate in production.

For Java backends, logs help answer:

* What happened?
* Which request or user was affected?
* Which service produced it?
* Was it expected or an error?
* What context is needed to debug it?

---

# 2. Core Concepts

## Log Levels

| Level | Use |
| ----- | --- |
| `ERROR` | Failure requiring investigation |
| `WARN` | Unexpected but handled condition |
| `INFO` | Important business or lifecycle event |
| `DEBUG` | Diagnostic detail for development |
| `TRACE` | Very detailed flow-level output |

Production defaults are usually `INFO` with targeted `DEBUG` during incidents.

---

## Structured Fields

Useful fields:

```text
timestamp, level, service, environment, traceId, spanId, requestId, userId, orderId, message, exception
```

Avoid logging secrets, passwords, tokens, full payment data, or sensitive personal data.

---

## MDC

Mapped Diagnostic Context stores request-scoped values that logging frameworks add to every log line.

Example fields:

```text
traceId=abc123 requestId=req-42 userId=1001
```

In thread pools and async code, MDC must be propagated carefully because request handling may move across threads.

---

# 3. Internal Working

Most Java applications use SLF4J as a facade with Logback or Log4j2 as the implementation.

The application calls:

```java
log.info("Order created");
```

The backend logger decides formatting, destination, level filtering, and appenders.

In containers, applications should write to stdout/stderr. The platform then ships logs to a collector or log store.

---

# 4. Common Mistakes

* Logging sensitive data.
* Using string concatenation instead of parameterized logs.
* Logging every request body in production.
* Using `ERROR` for expected validation failures.
* Swallowing exceptions without logs.
* Logging the same exception repeatedly at many layers.
* Missing correlation IDs.

---

# 5. Best Practices

* Use parameterized logging.
* Include correlation identifiers.
* Log business milestones at `INFO`.
* Log handled but unusual conditions at `WARN`.
* Log actionable failures at `ERROR`.
* Redact sensitive values.
* Prefer structured JSON logs in production.
* Keep logs useful during incidents, not just during development.

---

# 6. Code Example

```java
private static final Logger log = LoggerFactory.getLogger(OrderService.class);

public OrderResponse createOrder(CreateOrderRequest request) {
    log.info("Creating order for customerId={}", request.customerId());

    try {
        Order order = repository.save(mapper.toEntity(request));
        log.info("Order created orderId={} customerId={}", order.getId(), order.getCustomerId());
        return mapper.toResponse(order);
    } catch (DataAccessException ex) {
        log.error("Order creation failed customerId={}", request.customerId(), ex);
        throw new OrderCreationException("Could not create order", ex);
    }
}
```

---

# 7. Real-world Scenarios

* Finding all errors for one `traceId`.
* Debugging failed payment callbacks without exposing card data.
* Tracking important state transitions such as `ORDER_PLACED`.
* Separating user validation failures from system failures.
* Shipping container logs to ELK, Loki, CloudWatch, or OpenSearch.

---

# Revision Notes

* Structured logs are machine-readable.
* Use levels consistently.
* Add request and trace correlation.
* Never log secrets.
* Log to stdout/stderr in containers.
* MDC helps add request context to logs.

---

# Cheat Sheet

| Need | Practice |
| ---- | -------- |
| Correlation | `traceId`, `requestId` |
| Safe values | IDs, statuses, counts |
| Unsafe values | Passwords, tokens, card data |
| Production format | JSON |
| Java facade | SLF4J |
| Common backend | Logback |

---

# Interview Questions with Answers

### 1. Why structured logging?

It makes logs searchable and queryable by fields such as service, level, trace ID, and business ID.

### 2. What is MDC?

A request-scoped context map that logging frameworks can include in every log event.

### 3. What should not be logged?

Secrets, tokens, passwords, sensitive personal data, and full payment details.

---

# Hands-on Exercises

### Exercise 1

Convert string concatenation to parameterized logging.

**Answer**

```java
log.info("Order created orderId={} customerId={}", orderId, customerId);
```

