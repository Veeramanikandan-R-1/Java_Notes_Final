# Spring Boot

### Subtopic: Logging - SLF4J, Logback

---

# 1. Fundamentals

Logging records application behavior for debugging, monitoring, and incident analysis.

Spring Boot uses SLF4J as the logging API and Logback as the default implementation.

```java
private static final Logger log = LoggerFactory.getLogger(OrderService.class);
```

---

# 2. Core Concepts

## SLF4J

SLF4J is a facade. Application code logs through SLF4J without depending directly on a logging implementation.

```java
log.info("orderId={} status={}", orderId, status);
```

Use placeholders instead of string concatenation.

---

## Log Levels

| Level | Use |
| ----- | --- |
| `TRACE` | Very detailed flow |
| `DEBUG` | Debugging details |
| `INFO` | Important business/system events |
| `WARN` | Recoverable problem |
| `ERROR` | Failure needing attention |

---

## Logback

Logback controls appenders, patterns, files, rotation, and formatting.

Boot can configure levels through properties:

```properties
logging.level.com.example.order=DEBUG
```

---

# 3. Internal Working

Spring Boot initializes logging early during startup.

Logs are routed through SLF4J to Logback appenders such as console or file appenders.

Production systems often collect logs using agents and send them to centralized platforms.

---

# 4. Common Mistakes

* Logging passwords, tokens, OTPs, or card data.
* Using `System.out.println` in backend services.
* Logging huge request/response bodies.
* Logging expected business validation as errors.
* Building log messages with string concatenation.

---

# 5. Best Practices

* Use SLF4J placeholders.
* Include identifiers like order ID and correlation ID.
* Use appropriate log levels.
* Keep sensitive data out of logs.
* Prefer structured logging for distributed systems.

---

# 6. Code Example

```java
@Service
class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    void cancel(Long orderId) {
        log.info("Cancelling order orderId={}", orderId);
    }
}
```

---

# 7. Real-world Scenarios

* Debugging failed payment flows.
* Tracking request correlation IDs.
* Measuring batch job progress.
* Investigating production incidents.
* Auditing important state transitions.

---

# Revision Notes

* SLF4J is the logging facade.
* Logback is Spring Boot's default logging implementation.
* Use placeholders instead of concatenation.
* Choose correct log levels.
* Do not log secrets or OTP values.
* Use correlation IDs for request tracing.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Logger | `LoggerFactory.getLogger()` |
| Info log | `log.info()` |
| Warning | `log.warn()` |
| Error | `log.error()` |
| Package level | `logging.level.package=DEBUG` |

---

# Interview Questions with Answers

### 1. What is SLF4J?

A logging facade used by application code to avoid direct dependency on a logging implementation.

### 2. Why use placeholders?

They avoid unnecessary string creation and keep logs structured.

### 3. What should not be logged?

Secrets, passwords, tokens, OTPs, and sensitive personal data.

---

# Hands-on Exercises

### Exercise 1

Add a logger to a service and log an order ID.

**Answer**

```java
private static final Logger log = LoggerFactory.getLogger(OrderService.class);

log.info("Created order orderId={}", orderId);
```

