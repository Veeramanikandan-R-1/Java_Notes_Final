# Metrics

### Subtopic: Micrometer

---

# 1. Fundamentals

Metrics are numeric measurements collected over time.

Micrometer is the metrics facade commonly used by Spring Boot applications. It lets Java services publish metrics to systems such as Prometheus, Datadog, New Relic, CloudWatch, and others.

Metrics help answer:

* Is the service healthy?
* Is latency increasing?
* Is traffic normal?
* Are errors rising?
* Which dependency is saturated?

---

# 2. Core Concepts

## Metric Types

| Type | Use |
| ---- | --- |
| Counter | Monotonically increasing count |
| Gauge | Current value |
| Timer | Duration and count |
| Distribution summary | Sizes or amounts |

Examples:

```text
http.server.requests
jvm.memory.used
process.cpu.usage
orders.created
payment.failures
```

---

## Tags

Tags add dimensions:

```text
method=GET status=200 uri=/orders/{id}
```

Tags make metrics filterable, but high-cardinality tags can break metrics systems.

Do not tag with unbounded values such as user ID, email, order ID, request ID, or raw URL.

---

## Actuator

Spring Boot Actuator exposes operational endpoints.

Common endpoint:

```text
/actuator/prometheus
```

Prometheus can scrape this endpoint when the Prometheus registry is configured.

---

# 3. Internal Working

Micrometer records measurements in a `MeterRegistry`. The registry exports data in the format expected by the monitoring backend.

Timers usually record count, total time, and histogram or percentile data depending on configuration.

Alerting is usually built from Service Level Indicators:

```text
latency, traffic, errors, saturation
```

---

# 4. Common Mistakes

* Creating high-cardinality tags.
* Alerting on noisy metrics without thresholds or windows.
* Tracking only infrastructure metrics and ignoring business metrics.
* Using averages without percentiles.
* Adding custom metrics with unclear names.
* Not separating success and failure outcomes.
* Treating metrics as logs.

---

# 5. Best Practices

* Use low-cardinality tags.
* Prefer standard Spring Boot HTTP, JVM, and datasource metrics.
* Add business metrics for critical flows.
* Use percentiles or histograms for latency.
* Alert on symptoms users feel.
* Keep metric names stable.
* Document custom metrics.
* Use dashboards for trends and alerts for action.

---

# 6. Code Example

```java
@Service
public class OrderMetrics {
    private final Counter ordersCreated;
    private final Counter orderFailures;

    public OrderMetrics(MeterRegistry registry) {
        this.ordersCreated = Counter.builder("orders.created")
                .description("Number of orders created")
                .register(registry);

        this.orderFailures = Counter.builder("orders.failed")
                .tag("reason", "validation")
                .register(registry);
    }

    public void markCreated() {
        ordersCreated.increment();
    }

    public void markValidationFailure() {
        orderFailures.increment();
    }
}
```

---

# 7. Real-world Scenarios

* Alerting when 5xx rate increases.
* Watching p95 latency after deployment.
* Measuring order creation throughput.
* Tracking database connection pool saturation.
* Building dashboards for API, JVM, and business health.

---

# Revision Notes

* Metrics are numeric time-series data.
* Micrometer is a Java metrics facade.
* Counters increase; gauges show current value; timers measure duration.
* Tags add dimensions but must stay low-cardinality.
* Actuator exposes operational metrics.
* Alert on user-impacting symptoms.

---

# Cheat Sheet

| Need | Micrometer |
| ---- | ---------- |
| Count event | `Counter` |
| Measure duration | `Timer` |
| Current value | `Gauge` |
| Registry | `MeterRegistry` |
| Prometheus endpoint | `/actuator/prometheus` |
| Avoid tag | user ID, request ID |

---

# Interview Questions with Answers

### 1. What is Micrometer?

A metrics facade for Java applications that exports metrics to different monitoring systems.

### 2. What is high cardinality?

Too many unique tag values, such as tagging metrics by user ID or order ID.

### 3. Why are percentiles useful?

They show tail latency, which averages can hide.

---

# Hands-on Exercises

### Exercise 1

Create a counter for failed payments.

**Answer**

```java
Counter paymentFailures = Counter.builder("payments.failed")
        .description("Number of failed payment attempts")
        .register(meterRegistry);
```

