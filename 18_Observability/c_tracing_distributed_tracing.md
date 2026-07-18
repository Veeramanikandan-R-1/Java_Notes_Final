# Tracing

### Subtopic: Distributed Tracing

---

# 1. Fundamentals

Distributed tracing follows one request across multiple services.

In microservice or cloud systems, one user action may call an API gateway, order service, payment service, inventory service, database, and external provider. Tracing shows that journey.

Tracing helps answer:

* Where did latency come from?
* Which service failed?
* Which downstream call caused the error?
* How are services connected?

---

# 2. Core Concepts

## Trace and Span

| Term | Meaning |
| ---- | ------- |
| Trace | Complete journey of a request |
| Span | One operation inside a trace |
| Trace ID | Identifier shared by all spans in a trace |
| Span ID | Identifier for one span |
| Parent span | The operation that called another operation |

Example:

```text
trace: checkout request
  span: API POST /checkout
    span: call inventory-service
    span: call payment-service
    span: save order
```

---

## Context Propagation

Trace context must move across service boundaries using headers.

Common header standard:

```text
traceparent
```

If propagation breaks, traces become fragmented and debugging becomes harder.

---

## Sampling

Sampling controls how many traces are collected.

Collecting every trace can be expensive in high-traffic systems. Production systems often sample normal traffic but keep more error or slow traces.

---

# 3. Internal Working

Java services usually instrument frameworks through OpenTelemetry, Micrometer Tracing, or vendor agents.

Instrumentation creates spans around inbound HTTP requests, outbound HTTP calls, database queries, messaging operations, and custom business operations.

The application exports spans to a collector, which sends them to a tracing backend such as Jaeger, Tempo, Zipkin, Datadog, or cloud-native tooling.

---

# 4. Common Mistakes

* Not propagating trace headers across HTTP or messaging.
* Creating too many custom spans.
* Adding sensitive data to span attributes.
* Sampling so aggressively that incidents have no useful traces.
* Forgetting async and message queue boundaries.
* Treating tracing as a replacement for logs and metrics.
* Not correlating logs with trace IDs.

---

# 5. Best Practices

* Use OpenTelemetry-compatible instrumentation.
* Propagate trace context across all service boundaries.
* Include trace IDs in logs.
* Add custom spans only around important business or dependency operations.
* Keep span names stable and low-cardinality.
* Redact sensitive attributes.
* Use traces with metrics and logs, not instead of them.
* Tune sampling based on traffic, cost, and incident needs.

---

# 6. Code Example

```java
@Service
public class CheckoutService {
    private final Tracer tracer;

    public CheckoutService(Tracer tracer) {
        this.tracer = tracer;
    }

    public CheckoutResult checkout(CheckoutCommand command) {
        Span span = tracer.spanBuilder("checkout.validate-and-place-order").startSpan();
        try (Scope scope = span.makeCurrent()) {
            span.setAttribute("checkout.channel", command.channel());
            return placeOrder(command);
        } catch (RuntimeException ex) {
            span.recordException(ex);
            span.setStatus(StatusCode.ERROR);
            throw ex;
        } finally {
            span.end();
        }
    }
}
```

Use manual spans sparingly. Framework instrumentation should cover common HTTP and database boundaries.

---

# 7. Real-world Scenarios

* Finding a slow downstream payment provider call.
* Debugging request failures across API gateway and services.
* Correlating logs by trace ID during an incident.
* Understanding latency after adding a new dependency.
* Tracing asynchronous processing through message queues.

---

# Revision Notes

* A trace is the full request journey.
* A span is one operation in that journey.
* Trace context must propagate across services.
* Sampling controls cost and volume.
* Trace IDs should appear in logs.
* Tracing complements logs and metrics.

---

# Cheat Sheet

| Need | Concept |
| ---- | ------- |
| Full request | Trace |
| Single operation | Span |
| Correlation | Trace ID |
| Propagation header | `traceparent` |
| Standard | OpenTelemetry |
| Backend examples | Jaeger, Tempo, Zipkin |

---

# Interview Questions with Answers

### 1. Why use distributed tracing?

To see how a request moves across services and identify latency or failure points.

### 2. What happens if trace propagation breaks?

The request appears as separate traces, making correlation across services difficult.

### 3. Why sample traces?

To control storage, processing cost, and overhead in high-traffic systems.

---

# Hands-on Exercises

### Exercise 1

Name three places automatic instrumentation usually creates spans.

**Answer**

```text
Inbound HTTP request
Outbound HTTP client call
Database query
```

