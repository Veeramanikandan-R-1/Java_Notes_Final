# API Gateway - Spring Cloud Gateway

Act as a Principal Java Backend Engineer.

Topic To Teach: API Gateway  
Subtopic: Spring Cloud Gateway

## 1. Fundamentals

An API Gateway is the entry point for external clients. It routes requests to internal services and handles cross-cutting concerns such as authentication, rate limiting, logging, CORS, and request transformation.

Spring Cloud Gateway is a reactive gateway built on Spring WebFlux.

## 2. Core Concepts

### Route

A route maps a request predicate to a target service.

### Predicate

A predicate decides whether a route matches, such as path, method, header, or host.

### Filter

A filter modifies request or response behavior. Filters can add headers, validate tokens, log requests, or enforce rate limits.

### Gateway vs Service Logic

The gateway should handle edge concerns. Business rules belong inside services.

## 3. Configuration Example

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=1
```

`lb://order-service` uses service discovery and load balancing.

## 4. Internal Working

Spring Cloud Gateway receives the request on a reactive Netty server. It evaluates route predicates, applies pre-filters, forwards the request, then applies post-filters before returning the response.

Because it is reactive, blocking code inside gateway filters can harm throughput.

## 5. Common Mistakes

- Putting business logic in the gateway.
- Blocking calls inside gateway filters.
- Exposing internal service paths directly.
- Missing rate limits for public APIs.
- Not propagating correlation IDs.
- Treating the gateway as the only security layer.

## 6. Best Practices

- Keep gateway routes simple and explicit.
- Authenticate at the gateway, authorize in services when business rules matter.
- Use `lb://` with discovery for internal services.
- Add request ID, correlation ID, and trace headers.
- Enforce CORS, rate limits, and payload size limits.
- Version external APIs clearly, such as `/api/v1/orders`.
- Keep internal service APIs hidden from public networks.

## 7. Code Example

Custom filter:

```java
@Component
public class CorrelationIdFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = Optional.ofNullable(
                exchange.getRequest().getHeaders().getFirst("X-Correlation-Id")
        ).orElse(UUID.randomUUID().toString());

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("X-Correlation-Id", correlationId)
                .build();

        return chain.filter(exchange.mutate().request(request).build());
    }
}
```

## 8. Real-World Scenario

A mobile app calls `/api/orders`. The gateway validates JWT, applies rate limiting, attaches correlation ID, and routes the request to `order-service`. The order service still checks whether the user can access the order.

## Revision Notes

- Gateway is the external entry point.
- Routes use predicates and filters.
- Use it for edge concerns, not business rules.
- Avoid blocking code in gateway filters.
- Protect internal services from direct public access.

## Cheat Sheet

| Gateway Task | Where |
|---|---|
| Routing | Route predicate |
| Auth token validation | Gateway filter |
| Business authorization | Service |
| Rate limiting | Gateway |
| Audit of business action | Service |

## Interview Q&A

**Q: Why use an API Gateway?**  
A: To centralize external routing and edge concerns while keeping internal services protected.

**Q: Should authorization live in the gateway?**  
A: Coarse authorization can happen at the gateway, but business-specific authorization should be enforced inside the service.

**Q: Why avoid blocking in Spring Cloud Gateway?**  
A: It uses a reactive event loop; blocking code can reduce throughput for many requests.

## Hands-on Exercises

1. Route `/api/orders/**` to `order-service`.
2. Add a global filter that injects `X-Correlation-Id`.
3. Add a route-level rate limit for public APIs.
