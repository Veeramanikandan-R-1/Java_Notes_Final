# Revision Notes

* Distributed tracing follows requests across services.
* Trace means full request journey.
* Span means one operation in that journey.
* Trace ID links all spans in a trace.
* Context propagation carries trace data through headers.
* Sampling limits volume and cost.
* OpenTelemetry is the common standard.
* Include trace IDs in logs for correlation.

---

# Cheat Sheet

| Need | Concept |
| ---- | ------- |
| Complete request | Trace |
| One operation | Span |
| Shared ID | Trace ID |
| Propagation header | `traceparent` |
| Standard | OpenTelemetry |
| Collector target | Jaeger, Tempo, Zipkin |

---

# Interview Questions with Answers

### 1. Why does tracing matter in microservices?

It shows where a request went and which service or dependency caused latency or failure.

### 2. What is context propagation?

Passing trace identifiers across service boundaries so spans connect into one trace.

### 3. Is tracing enough by itself?

No. Use it with metrics for trends and logs for detailed event context.

---

# Hands-on Exercises

### Exercise 1

List the three observability pillars.

**Answer**

```text
Logs
Metrics
Traces
```

