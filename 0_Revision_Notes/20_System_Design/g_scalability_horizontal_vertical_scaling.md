# Revision Notes

* Scalability means handling increased load.
* Vertical scaling adds resources to one machine.
* Horizontal scaling adds more machines.
* Horizontal scaling improves availability.
* Stateless services scale better.
* Load balancer distributes traffic.
* Database can remain bottleneck.
* Measure before scaling.

---

# Cheat Sheet

| Need | Scaling |
| ---- | ------- |
| Quick simple increase | Vertical |
| High availability | Horizontal |
| Many instances | Load balancer |
| Shared state | External store |
| DB read pressure | Cache/replica |

---

# Interview Questions with Answers

### 1. Horizontal vs vertical scaling?

Vertical increases one server. Horizontal adds more servers.

### 2. Why stateless?

Any instance can serve any request.

### 3. What to scale first?

The measured bottleneck.

