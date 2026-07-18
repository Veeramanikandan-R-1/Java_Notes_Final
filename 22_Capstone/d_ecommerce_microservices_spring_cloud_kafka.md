# Capstone: Ecommerce Microservices

### Subtopic: Spring Cloud + Kafka

---

# 1. Goal

Build an ecommerce backend using microservices, Spring Cloud, PostgreSQL, Redis, and Kafka.

This project validates:

* Service boundaries
* API Gateway
* Service discovery
* Inter-service communication
* Event-driven architecture
* Distributed failure handling

---

# 2. Services

```text
User Service
Product Service
Order Service
Payment Service
Inventory Service
Notification Service
API Gateway
Config Server
```

Each service owns its database.

---

# 3. Core Flow

```text
Customer places order
Order service creates pending order
Inventory service reserves stock
Payment service processes payment
Order service confirms order
Notification service sends confirmation
```

Use Kafka events:

```text
OrderCreated
InventoryReserved
PaymentCompleted
OrderConfirmed
```

---

# 4. Key Design Decisions

* API Gateway routes external requests.
* Config Server centralizes configuration.
* Service discovery helps locate services.
* Kafka decouples async workflows.
* Redis caches product/catalog reads.
* Resilience4j handles failures.

---

# 5. Common Challenges

* Distributed transactions.
* Eventual consistency.
* Duplicate events.
* Service failure.
* Schema changes.
* Observability across services.

---

# 6. Best Practices

* Keep service boundaries business-oriented.
* Use idempotent consumers.
* Use correlation IDs.
* Add retries and DLQ.
* Avoid shared databases.
* Use centralized logs and tracing.
* Start simple and evolve.

---

# Revision Notes

* Microservices split business capabilities.
* Each service owns its data.
* Kafka enables async workflows.
* API Gateway is external entry point.
* Config Server centralizes config.
* Resilience4j protects calls.
* Eventual consistency is expected.
* Observability is mandatory.

---

# Interview Questions with Answers

### 1. Why database per service?

To keep services independently deployable and avoid tight coupling.

### 2. How handle distributed transaction?

Use saga/event-driven flow with compensation instead of one global transaction.

### 3. Why correlation ID?

To trace one request across multiple services.

