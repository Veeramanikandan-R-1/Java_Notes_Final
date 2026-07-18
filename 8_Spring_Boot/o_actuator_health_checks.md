# Spring Boot

### Subtopic: Actuator - Health Checks

---

# 1. Fundamentals

Spring Boot Actuator adds production-ready endpoints for monitoring and managing applications.

Common dependency:

```text
spring-boot-starter-actuator
```

Health endpoint:

```text
/actuator/health
```

---

# 2. Core Concepts

## Common Endpoints

| Endpoint | Purpose |
| -------- | ------- |
| `/actuator/health` | Application health |
| `/actuator/info` | Application info |
| `/actuator/metrics` | Metrics |
| `/actuator/env` | Environment details |
| `/actuator/loggers` | Logger levels |

Expose only what is needed.

---

## Health Checks

Health checks report whether the service is ready or alive.

Boot can include health indicators for database, disk space, mail, cache, and custom systems.

---

## Custom HealthIndicator

```java
@Component
class PaymentHealthIndicator implements HealthIndicator {
    public Health health() {
        boolean available = true;
        return available ? Health.up().build() : Health.down().build();
    }
}
```

---

# 3. Internal Working

Actuator registers management endpoints.

Health endpoint aggregates health indicators and returns status such as `UP`, `DOWN`, or `OUT_OF_SERVICE`.

In container platforms, liveness and readiness probes can call actuator endpoints.

---

# 4. Common Mistakes

* Exposing sensitive actuator endpoints publicly.
* Making health checks too slow.
* Calling unreliable external systems in liveness checks.
* Ignoring readiness during deployment.
* Not separating liveness from readiness.

---

# 5. Best Practices

* Expose minimal actuator endpoints.
* Secure management endpoints.
* Keep liveness simple.
* Use readiness for dependency checks.
* Add custom health indicators for critical dependencies.
* Monitor metrics and logs together.

---

# 6. Code Example

```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=when_authorized
management.health.livenessstate.enabled=true
management.health.readinessstate.enabled=true
```

---

# 7. Real-world Scenarios

* Kubernetes readiness probes.
* Load balancer health checks.
* Monitoring database connectivity.
* Detecting disk space issues.
* Exposing build info for deployed versions.

---

# Revision Notes

* Actuator provides production monitoring endpoints.
* `/actuator/health` reports application health.
* Health indicators contribute to health status.
* Expose only required endpoints.
* Secure sensitive actuator data.
* Use readiness and liveness correctly.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Dependency | `spring-boot-starter-actuator` |
| Health | `/actuator/health` |
| Metrics | `/actuator/metrics` |
| Custom health | `HealthIndicator` |
| Expose endpoints | `management.endpoints.web.exposure.include` |

---

# Interview Questions with Answers

### 1. What is Actuator?

Spring Boot feature that exposes operational endpoints for monitoring and management.

### 2. What is health endpoint used for?

To report whether the application and dependencies are healthy.

### 3. Why secure actuator endpoints?

Some endpoints can expose environment, metrics, or operational details.

---

# Hands-on Exercises

### Exercise 1

Expose only health and info endpoints.

**Answer**

```properties
management.endpoints.web.exposure.include=health,info
```

