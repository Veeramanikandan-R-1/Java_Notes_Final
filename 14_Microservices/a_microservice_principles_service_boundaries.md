# Microservice Principles - Service Boundaries

Act as a Principal Java Backend Engineer.

Topic To Teach: Microservice Principles  
Subtopic: Service Boundaries

Important Instructions:
DO NOT overshare more content; be precise. Build knowledge progressively from fundamentals to advanced concepts. Connect every section logically to the previous section. Explain what, why, how, and when. Focus on production-grade Java backend engineering.

## 1. Fundamentals

A microservice is an independently deployable service that owns a focused business capability. The most important design decision is not the framework; it is the service boundary.

A service boundary defines:

- What business capability the service owns.
- Which data it owns.
- Which APIs or events it exposes.
- Which changes can be deployed independently.

Good boundaries reduce coordination. Bad boundaries create distributed monoliths where every feature needs changes in many services.

## 2. Core Concepts

### Business Capability

Start with the domain, not tables. In an e-commerce system, `Order Service`, `Payment Service`, and `Inventory Service` are business capabilities. `UserTableService` or `CommonDbService` are usually poor boundaries.

### Data Ownership

Each service should own its data model. Other services should not directly read or write its database. They communicate through APIs, events, or controlled read models.

### Loose Coupling

Loose coupling means one service can change internally without forcing other services to change. Use stable contracts, versioning, and asynchronous events where useful.

### High Cohesion

High cohesion means related business rules live together. If order validation rules are split across five services, the boundary is probably wrong.

### Independent Deployability

A service should be deployable without deploying its consumers. This requires backward-compatible APIs, database migration discipline, and contract testing.

## 3. Internal Working

In production, service boundaries show up in:

- Code ownership: separate repositories or clear module boundaries.
- Runtime ownership: each service has its own process, scaling, health checks, logs, metrics, and deployment pipeline.
- Data ownership: schema changes are local to one service.
- Contract ownership: APIs and events become the integration surface.

If two services share the same database tables, they are coupled at the deepest level. If two teams must always release together, the boundary is weak.

## 4. Common Mistakes

- Creating one service per entity instead of per business capability.
- Sharing database tables across services.
- Building a `common` library that contains business logic used everywhere.
- Making every interaction synchronous REST.
- Splitting too early before the domain is understood.
- Ignoring failure handling, retries, idempotency, and observability.
- Treating microservices as a performance solution instead of an organizational and scalability pattern.

## 5. Best Practices

- Begin with a modular monolith if the domain is unclear.
- Use domain-driven design terms: bounded context, aggregate, domain event.
- Keep service APIs business-focused, not database-focused.
- Prefer local transactions inside one service; use saga or events across services.
- Design every operation with timeout, retry policy, and idempotency.
- Maintain API contracts using OpenAPI, Pact, or Spring Cloud Contract.
- Add correlation IDs to trace a business request across services.

## 6. Code Example

Poor boundary:

```java
@RestController
class OrderDataController {
    @GetMapping("/orders/{id}/payment-status")
    String getPaymentStatusFromOrderTable(@PathVariable Long id) {
        return orderRepository.findById(id).get().getPaymentStatus();
    }
}
```

Better boundary:

```java
@RestController
class OrderController {
    private final OrderApplicationService orderService;

    @PostMapping("/orders")
    ResponseEntity<OrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        OrderResponse response = orderService.placeOrder(request);
        return ResponseEntity.accepted().body(response);
    }
}
```

The controller exposes a business action. Payment can be handled by publishing an event:

```java
public record OrderPlacedEvent(
        String orderId,
        String customerId,
        BigDecimal amount
) {}
```

## 7. Real-World Scenario

In a food delivery system:

- `Restaurant Service` owns menu availability.
- `Order Service` owns order lifecycle.
- `Payment Service` owns payment authorization and refunds.
- `Delivery Service` owns rider assignment.

The order service should not update payment tables. It requests payment through an API or event and reacts to `PaymentAuthorized` or `PaymentFailed`.

## Revision Notes

- Service boundaries are business boundaries.
- Each service owns its data.
- Avoid shared databases.
- Independent deployability is the real test.
- Use events and contracts to reduce coupling.

## Cheat Sheet

| Need | Practice |
|---|---|
| Clear ownership | One service owns one capability |
| Data safety | Database per service |
| Reliability | Timeouts, retries, idempotency |
| Change safety | Backward-compatible contracts |
| Debugging | Logs, metrics, traces, correlation IDs |

## Interview Q&A

**Q: How do you identify service boundaries?**  
A: Start from business capabilities and bounded contexts. Validate that each service can own its data and deploy independently.

**Q: Why not share one database across microservices?**  
A: It creates tight coupling, hidden dependencies, unsafe schema changes, and makes independent deployment difficult.

**Q: What is a distributed monolith?**  
A: A system with multiple services that still require coordinated releases and tightly coupled data or APIs.

## Hands-on Exercises

1. Design service boundaries for an online bookstore.
2. Identify which service owns `Cart`, `Order`, `Payment`, `Shipment`, and `Invoice`.
3. Write one REST command and one domain event for `Order Service`.
