# Revision - API Gateway - Spring Cloud Gateway

## Core Recall

- API Gateway is the external entry point.
- Spring Cloud Gateway uses routes, predicates, and filters.
- Gateway handles edge concerns.
- Business rules belong in services.
- Avoid blocking code in gateway filters.

## Production Checklist

- Route external paths to internal services.
- Validate token at the edge.
- Enforce CORS and rate limits.
- Propagate correlation ID.
- Keep internal services private.

## Mistakes

- Business logic in gateway.
- Blocking database calls in filters.
- Exposing internal paths.
- Relying only on gateway for all authorization.

## Cheat Sheet

| Concern | Location |
|---|---|
| Routing | Gateway |
| JWT validation | Gateway |
| Business authorization | Service |
| Rate limiting | Gateway |
| Domain audit | Service |

## Interview Answers

**Why gateway?**  
It centralizes external routing and edge controls.

**Why not put business logic there?**  
It makes the gateway a bottleneck and weakens service ownership.

## Exercise

Route `/api/orders/**`, strip prefix, and inject `X-Correlation-Id`.
