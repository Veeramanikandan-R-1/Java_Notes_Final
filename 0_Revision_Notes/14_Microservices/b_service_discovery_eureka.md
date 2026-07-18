# Revision - Service Discovery - Eureka

## Core Recall

- Eureka is a service registry.
- Services register themselves as clients.
- Clients send heartbeats.
- Consumers fetch registry and call by service name.
- Registry is cached and eventually consistent.

## Production Checklist

- Stable `spring.application.name`.
- Eureka not publicly exposed.
- Multiple Eureka servers for HA.
- Timeouts and circuit breakers on consumers.
- Readiness checks before traffic.

## Mistakes

- Hardcoded URLs with discovery.
- Assuming registry means health guarantee.
- Ignoring stale registry entries.
- Using Eureka automatically in Kubernetes without evaluating native discovery.

## Cheat Sheet

| Term | Meaning |
|---|---|
| Register | Service announces itself |
| Heartbeat | Keeps instance alive |
| Fetch registry | Client downloads instances |
| `lb://` | Load-balanced service URI |

## Interview Answers

**Why service discovery?**  
It resolves dynamic service locations without hardcoded host and port values.

**Can Eureka return dead instances?**  
Yes briefly, because discovery is eventually consistent.

## Exercise

Run Eureka on `8761`, register two services, and verify the dashboard.
