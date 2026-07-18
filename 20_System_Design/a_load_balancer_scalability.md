# Load Balancer

### Subtopic: Scalability

---

# 1. Fundamentals

A load balancer distributes incoming traffic across multiple backend instances.

Without load balancer:

```text
Client -> Single Server
```

With load balancer:

```text
Client -> Load Balancer -> Server 1 / Server 2 / Server 3
```

It improves availability, scalability, and fault tolerance.

---

# 2. Core Concepts

## Why Load Balancer is Needed

* Prevents one server from becoming overloaded.
* Allows horizontal scaling.
* Routes around unhealthy instances.
* Provides stable entry point for clients.

## Layer 4 vs Layer 7

| Type | Works At | Example Routing |
| ---- | -------- | --------------- |
| L4 | TCP/UDP | IP + port |
| L7 | HTTP | Path, host, headers |

L7 is common for REST APIs because it understands HTTP.

## Algorithms

| Algorithm | Meaning |
| --------- | ------- |
| Round robin | Send requests in order |
| Least connections | Pick server with fewer active connections |
| Weighted routing | Send more traffic to stronger servers |
| IP hash | Same client IP maps to same server |

## Health Checks

Load balancer periodically checks server health.

```text
GET /actuator/health
```

Unhealthy instances are removed from rotation.

---

# 3. Internal Working

The load balancer receives request, chooses backend using routing rule, forwards request, and returns response to client.

For stateless services, any instance can handle any request. This makes scaling easier.

Stateful session data should be externalized to Redis, database, or token-based auth.

---

# 4. Common Mistakes

* Keeping session state inside one server.
* No health checks.
* Wrong timeout configuration.
* Not handling backend slow responses.
* Assuming load balancer fixes bad application performance.
* No autoscaling strategy.

---

# 5. Best Practices

* Make backend services stateless.
* Add health check endpoints.
* Use proper timeouts.
* Use horizontal scaling for traffic growth.
* Monitor latency, error rate, and unhealthy hosts.
* Use sticky sessions only when truly needed.

---

# 6. Real-world Scenario

An ecommerce app runs 5 order-service instances. A load balancer routes incoming order requests across all instances. If one instance fails, health checks remove it automatically.

---

# Revision Notes

* Load balancer distributes traffic across instances.
* Enables horizontal scaling.
* L4 works at transport level.
* L7 understands HTTP.
* Health checks remove bad instances.
* Stateless services scale better.
* Sticky sessions should be avoided unless needed.

---

# Cheat Sheet

| Need | Concept |
| ---- | ------- |
| Scale app | Horizontal scaling |
| Route HTTP path | L7 load balancer |
| Detect failures | Health checks |
| Stable entry | Single LB endpoint |
| Avoid session issue | Stateless services |

---

# Interview Questions with Answers

### 1. Why use load balancer?

To distribute traffic, improve availability, and scale services horizontally.

### 2. L4 vs L7 load balancer?

L4 routes by network information like IP and port. L7 routes using HTTP data like path and headers.

### 3. Why should services be stateless behind load balancer?

Any instance can handle any request, making failover and scaling easier.

