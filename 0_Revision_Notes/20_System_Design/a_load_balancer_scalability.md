# Revision Notes

* Load balancer distributes traffic across backend instances.
* It enables horizontal scaling.
* L4 routes using IP/port.
* L7 routes using HTTP path, host, and headers.
* Health checks remove unhealthy instances.
* Stateless services work best behind load balancers.
* Sticky sessions should be avoided unless necessary.

---

# Cheat Sheet

| Need | Concept |
| ---- | ------- |
| Scale app | Horizontal scaling |
| HTTP routing | L7 load balancer |
| Failure detection | Health checks |
| Session externalization | Redis/JWT |
| Distribution | Round robin / least connections |

---

# Interview Questions with Answers

### 1. Why use load balancer?

To distribute load, improve availability, and route around unhealthy instances.

### 2. L4 vs L7?

L4 works at transport layer. L7 understands HTTP.

### 3. Why stateless services?

Any instance can handle any request, making scaling and failover simpler.

