# Revision Notes

* Metrics are numeric time-series measurements.
* Micrometer is a metrics facade used by Spring Boot.
* Counter tracks increasing event count.
* Gauge tracks a current value.
* Timer tracks duration and count.
* Tags add dimensions but must be low-cardinality.
* Avoid tags like user ID, order ID, email, or request ID.
* Alerts should reflect user impact.

---

# Cheat Sheet

| Need | Tool |
| ---- | ---- |
| Event count | `Counter` |
| Duration | `Timer` |
| Current value | `Gauge` |
| Registry | `MeterRegistry` |
| Prometheus scrape | `/actuator/prometheus` |
| Key latency view | p95 or p99 |

---

# Interview Questions with Answers

### 1. What is Micrometer?

A Java metrics facade that exports application metrics to monitoring backends.

### 2. Why is high cardinality dangerous?

It creates too many time series, increasing cost and reducing query performance.

### 3. What are the four golden signals?

Latency, traffic, errors, and saturation.

---

# Hands-on Exercises

### Exercise 1

Create a counter for created orders.

**Answer**

```java
Counter.builder("orders.created").register(meterRegistry).increment();
```

