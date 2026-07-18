# Revision Notes

* Actuator provides production-ready operational endpoints.
* Health endpoint reports application status.
* Metrics endpoint exposes app metrics.
* Expose only required endpoints.
* Secure sensitive actuator endpoints.
* Health checks are used by load balancers and orchestrators.
* Custom health indicators can check dependencies.

---

# Cheat Sheet

| Need | Endpoint/Concept |
| ---- | ---------------- |
| Health | `/actuator/health` |
| Metrics | `/actuator/metrics` |
| Info | `/actuator/info` |
| Config | `management.endpoints.web.exposure.include` |
| Custom health | `HealthIndicator` |

---

# Interview Questions with Answers

### 1. What is Actuator?

Spring Boot module that exposes operational endpoints for monitoring and management.

### 2. Why health check?

Load balancers and platforms use it to know if app instance can receive traffic.

### 3. Should all endpoints be public?

No. Expose and secure only what is needed.

