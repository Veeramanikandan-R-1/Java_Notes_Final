# Scalability

### Subtopic: Horizontal, Vertical Scaling

---

# 1. Fundamentals

Scalability means system can handle increased load.

Two main approaches:

* Vertical scaling
* Horizontal scaling

---

# 2. Core Concepts

## Vertical Scaling

Increase power of existing server.

```text
More CPU, RAM, disk
```

Pros:

* Simple.
* No distributed complexity.

Cons:

* Hardware limit.
* Single point of failure.
* Can become expensive.

## Horizontal Scaling

Add more server instances.

```text
Server 1 + Server 2 + Server 3
```

Pros:

* Better availability.
* Easier to scale gradually.
* Works well with stateless services.

Cons:

* Needs load balancing.
* More distributed complexity.
* Shared state must be externalized.

---

# 3. Internal Working

Horizontal scaling works best when services are stateless.

State should be stored in:

* Database
* Redis
* Object storage
* Message queue

Then any instance can serve any request.

---

# 4. Common Mistakes

* Scaling app but not database.
* Keeping session state in memory.
* Ignoring bottlenecks.
* No load testing.
* Assuming more instances always solve latency.
* No autoscaling metrics.

---

# 5. Best Practices

* Make services stateless.
* Use load balancer.
* Cache read-heavy data.
* Scale bottleneck first.
* Use metrics before scaling.
* Load test critical flows.
* Plan database scaling separately.

---

# Revision Notes

* Vertical scaling adds power to one machine.
* Horizontal scaling adds more machines.
* Horizontal scaling improves availability.
* Stateless services scale better.
* Load balancer distributes traffic.
* Database can become bottleneck.
* Measure before scaling.

---

# Cheat Sheet

| Need | Scaling |
| ---- | ------- |
| Simple quick increase | Vertical |
| High availability | Horizontal |
| Many app instances | Load balancer |
| Shared session | Redis/token |
| Read pressure | Cache/read replicas |

---

# Interview Questions with Answers

### 1. Horizontal vs vertical scaling?

Vertical scaling increases one machine capacity. Horizontal scaling adds more machines.

### 2. Why stateless services?

They allow any instance to handle any request.

### 3. What should you scale first?

The measured bottleneck, not a guessed component.

