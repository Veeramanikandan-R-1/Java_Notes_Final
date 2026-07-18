# OpenFeign - Service Communication

Act as a Principal Java Backend Engineer.

Topic To Teach: OpenFeign  
Subtopic: Service Communication

## 1. Fundamentals

OpenFeign is a declarative HTTP client. Instead of manually building URLs and parsing responses, you define a Java interface and let Feign generate the implementation.

Feign is useful when one service must call another service synchronously.

## 2. Core Concepts

### Declarative Client

You describe the remote API as an interface:

```java
@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/payments/authorize")
    PaymentResponse authorize(@RequestBody PaymentRequest request);
}
```

### Service Name Resolution

When used with discovery, `name = "payment-service"` resolves through Eureka or Spring Cloud LoadBalancer.

### Synchronous Communication

Feign calls block the current request thread. This is simple but increases coupling and latency risk.

### Error Handling

Remote services fail. Feign clients need timeouts, error decoders, retries only for safe operations, and circuit breakers.

## 3. Internal Working

At startup, Spring creates a proxy for the Feign interface. When a method is called, the proxy builds an HTTP request from annotations, sends it through a configured HTTP client, decodes the response, and returns a Java object.

## 4. Common Mistakes

- Calling many services inside one request path.
- No timeout configuration.
- Retrying non-idempotent operations like payment capture.
- Exposing internal DTOs as public contracts.
- Catching all exceptions and returning fake success.
- Ignoring versioning and backward compatibility.

## 5. Best Practices

- Use Feign for business-critical synchronous calls only when a response is required immediately.
- Keep request and response DTOs explicit.
- Configure connect and read timeouts.
- Use Resilience4j for circuit breaker, retry, bulkhead, and fallback.
- Use correlation IDs and structured logs.
- Prefer async events when immediate response is not required.

## 6. Code Example

Client:

```java
@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryClient {
    @GetMapping("/items/{sku}/availability")
    InventoryAvailability getAvailability(@PathVariable String sku);
}
```

Usage:

```java
@Service
public class OrderApplicationService {
    private final InventoryClient inventoryClient;

    public OrderApplicationService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(String sku) {
        InventoryAvailability availability = inventoryClient.getAvailability(sku);
        if (!availability.available()) {
            throw new IllegalStateException("Item is not available");
        }
    }
}
```

Timeout config:

```yaml
spring:
  cloud:
    openfeign:
      client:
        config:
          inventory-service:
            connectTimeout: 1000
            readTimeout: 2000
```

## 7. Real-World Scenario

`order-service` calls `inventory-service` before accepting an order. If inventory is required immediately, Feign is acceptable. If inventory can be reserved asynchronously, Kafka events may reduce coupling.

## Revision Notes

- Feign turns interfaces into HTTP clients.
- Best for synchronous service-to-service calls.
- Needs timeouts and resilience.
- Avoid deep call chains.
- Use explicit DTO contracts.

## Cheat Sheet

| Need | Feign Feature |
|---|---|
| Call remote service | `@FeignClient` |
| Map endpoint | Spring MVC annotations |
| Resolve service | Service discovery name |
| Handle failure | ErrorDecoder, Resilience4j |
| Control latency | Connect/read timeouts |

## Interview Q&A

**Q: Why use Feign instead of RestTemplate or RestClient?**  
A: Feign reduces boilerplate and makes remote API contracts explicit through interfaces.

**Q: What is the risk of Feign?**  
A: It can hide network calls behind simple method calls, making latency and failure easy to underestimate.

**Q: Should Feign calls be retried?**  
A: Only when the operation is safe or idempotent. Retrying payment capture can create duplicate charges.

## Hands-on Exercises

1. Create a Feign client for `payment-service`.
2. Configure 1 second connect timeout and 2 second read timeout.
3. Add a fallback strategy for temporary payment service failure.
