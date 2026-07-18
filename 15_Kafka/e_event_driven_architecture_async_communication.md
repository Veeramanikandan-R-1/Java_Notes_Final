# Event Driven Architecture - Async Communication

Act as a Principal Java Backend Engineer.

Topic To Teach: Event Driven Architecture  
Subtopic: Async Communication

## 1. Fundamentals

Event driven architecture uses events to communicate that something happened. Producers publish events, and consumers react asynchronously.

Instead of asking another service to do work immediately, a service records its own state change and emits an event.

## 2. Core Concepts

### Event

An immutable fact, such as `OrderPlaced`, `PaymentAuthorized`, or `ShipmentDispatched`.

### Command vs Event

A command asks for action: `AuthorizePayment`.  
An event states a fact: `PaymentAuthorized`.

### Choreography

Services react to each other's events without a central orchestrator.

### Orchestration

A coordinator service controls the workflow and sends commands to participants.

### Eventual Consistency

Services become consistent over time. Immediate consistency across services is avoided when possible.

## 3. Example Flow

1. `Order Service` saves order as `PENDING_PAYMENT`.
2. It publishes `OrderPlaced`.
3. `Payment Service` consumes and authorizes payment.
4. It publishes `PaymentAuthorized`.
5. `Order Service` consumes and marks order as `CONFIRMED`.
6. `Notification Service` sends confirmation.

## 4. Code Example

```java
public record PaymentAuthorizedEvent(
        String eventId,
        String orderId,
        String paymentId,
        Instant occurredAt
) {}
```

```java
@KafkaListener(topics = "payment-authorized", groupId = "order-service")
public void onPaymentAuthorized(PaymentAuthorizedEvent event) {
    orderService.confirmOrder(event.orderId(), event.paymentId());
}
```

## 5. Internal Working

Kafka decouples producers from consumers. Producers do not know how many consumers exist. Each consumer group keeps its own offset and processes events independently.

This improves scalability and resilience, but it introduces eventual consistency, duplicate handling, schema evolution, and replay concerns.

## 6. Common Mistakes

- Publishing vague events such as `OrderUpdated`.
- Treating events as mutable database rows.
- No event versioning.
- Consumers not idempotent.
- No clear owner for event contracts.
- Using async events when the user needs immediate confirmation.
- Ignoring observability across asynchronous flows.

## 7. Best Practices

- Name events as past-tense facts.
- Include event ID, aggregate ID, occurred time, and version.
- Keep payload useful but not bloated.
- Maintain backward-compatible schemas.
- Use outbox pattern for reliable publishing.
- Make consumers idempotent.
- Add tracing, correlation ID, and business audit.
- Decide when to use choreography vs orchestration.

## 8. Real-World Scenario

In checkout, the user needs fast order acceptance, but invoice, notification, analytics, and loyalty points can happen asynchronously. Kafka events allow those services to evolve independently without slowing checkout.

## Revision Notes

- Events are facts, not commands.
- Async communication reduces direct coupling.
- Kafka enables independent consumer groups.
- Eventual consistency is expected.
- Idempotency, schema evolution, and observability are mandatory.

## Cheat Sheet

| Choice | Use When |
|---|---|
| Synchronous REST | Immediate answer required |
| Kafka event | Something happened and others may react |
| Choreography | Simple decentralized flow |
| Orchestration | Complex workflow with central visibility |
| Outbox | DB change and event must be reliable |

## Interview Q&A

**Q: What is event driven architecture?**  
A: A style where services publish facts as events and other services react asynchronously.

**Q: What is eventual consistency?**  
A: Different services may temporarily see different states but converge through events.

**Q: Why are event IDs important?**  
A: They help consumers detect duplicates and support tracing or replay.

## Hands-on Exercises

1. Model checkout using events.
2. Define `OrderPlaced`, `PaymentAuthorized`, and `OrderConfirmed` payloads.
3. Add idempotency handling in `order-service`.
