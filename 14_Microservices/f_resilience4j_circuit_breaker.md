# Resilience4j - Circuit Breaker

Act as a Principal Java Backend Engineer.

Topic To Teach: Resilience4j  
Subtopic: Circuit Breaker

## 1. Fundamentals

Resilience4j is a fault tolerance library for Java. A circuit breaker prevents repeated calls to an unhealthy dependency, giving it time to recover and protecting the caller from slow failures.

It is essential in microservices because every remote call can fail, timeout, or become slow.

## 2. Core Concepts

### Closed

Normal state. Calls are allowed and failures are measured.

### Open

Failure rate or slow-call rate crossed the threshold. Calls are blocked immediately.

### Half-Open

After a wait duration, a limited number of test calls are allowed. If they succeed, the circuit closes. If they fail, it opens again.

### Fallback

A fallback is an alternate response when the main call fails or circuit is open. It should be honest and safe, not fake success.

## 3. Internal Working

Resilience4j tracks call results in a sliding window. It calculates failure rate and slow-call rate. Once the configured threshold is exceeded and the minimum number of calls is reached, the circuit opens.

## 4. Code Example

```java
@Service
public class PaymentGateway {
    private final PaymentClient paymentClient;

    public PaymentGateway(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public PaymentResponse authorize(PaymentRequest request) {
        return paymentClient.authorize(request);
    }

    PaymentResponse paymentFallback(PaymentRequest request, Throwable ex) {
        return PaymentResponse.pending("Payment authorization is delayed");
    }
}
```

Configuration:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 20
        minimumNumberOfCalls: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
```

## 5. Common Mistakes

- Using fallback to hide data corruption or payment failure.
- No timeout before circuit breaker.
- Retrying aggressively and increasing load on a failing service.
- Same circuit breaker config for every dependency.
- Not monitoring circuit state changes.
- Opening circuit on business validation errors.

## 6. Best Practices

- Configure timeouts first.
- Use circuit breaker around remote calls, not local validation.
- Tune thresholds per dependency.
- Combine with retry only for safe operations.
- Add metrics and alerts for open circuits.
- Return degraded but truthful responses.
- Use idempotency keys for write operations.

## 7. Real-World Scenario

If payment provider latency spikes, `order-service` should not block all request threads. The circuit breaker opens, order placement can return `PAYMENT_PENDING`, and a background process can complete authorization later.

## Revision Notes

- Circuit breaker protects callers from failing dependencies.
- States: closed, open, half-open.
- Use fallback carefully.
- Timeouts and metrics are mandatory.
- Do not retry unsafe writes blindly.

## Cheat Sheet

| Pattern | Purpose |
|---|---|
| Timeout | Stop waiting forever |
| Retry | Retry transient safe failures |
| Circuit breaker | Stop calling failing dependency |
| Bulkhead | Limit concurrent calls |
| Rate limiter | Control request rate |

## Interview Q&A

**Q: What problem does a circuit breaker solve?**  
A: It prevents cascading failures by blocking calls to unhealthy dependencies.

**Q: What is half-open state?**  
A: A recovery test state where limited calls are allowed to check if the dependency is healthy again.

**Q: Can fallback return cached data?**  
A: Yes, if the data is safe for the use case and clearly not misleading.

## Hands-on Exercises

1. Add Resilience4j circuit breaker around a Feign client.
2. Configure a fallback for payment timeout.
3. Expose circuit breaker metrics using Actuator.
