# Revision - Microservice Principles - Service Boundaries

## Core Recall

- A microservice owns one business capability.
- Boundaries should follow domain behavior, not database tables.
- Each service owns its data and exposes contracts.
- Independent deployment is the strongest boundary test.
- Wrong boundaries create a distributed monolith.

## Production Checklist

- No shared database writes.
- Stable API or event contracts.
- Backward-compatible changes.
- Correlation IDs across service calls.
- Local transaction inside service; saga/events across services.

## Mistakes

- One service per entity.
- Shared common business library.
- Synchronous call chain for every workflow.
- Splitting before domain understanding.

## Cheat Sheet

| Question | Good Signal |
|---|---|
| Who owns the data? | Exactly one service |
| Can it deploy alone? | Yes |
| Is API business-focused? | Yes |
| Are failures handled? | Timeout, retry, idempotency |

## Interview Answers

**How do you choose boundaries?**  
Use business capabilities and bounded contexts, then verify data ownership and independent deployability.

**Why avoid shared DB?**  
It couples schema, releases, and business rules across services.

## Exercise

Design boundaries for bookstore: catalog, cart, order, payment, shipment, invoice.
