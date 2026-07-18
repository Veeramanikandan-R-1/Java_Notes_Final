# Revision - OpenFeign - Service Communication

## Core Recall

- OpenFeign creates HTTP clients from Java interfaces.
- Use it for synchronous service calls.
- It integrates with service discovery by service name.
- Network calls need timeout, failure handling, and observability.

## Production Checklist

- Explicit request and response DTOs.
- Connect and read timeouts.
- Error decoder for meaningful exceptions.
- Resilience4j circuit breaker.
- Retry only idempotent operations.

## Mistakes

- Long chains of Feign calls.
- Retrying payments blindly.
- No timeout.
- Treating remote call like local method call.

## Cheat Sheet

| Annotation | Use |
|---|---|
| `@FeignClient` | Declares remote client |
| `@GetMapping` | Maps GET endpoint |
| `@PostMapping` | Maps POST endpoint |
| `@RequestBody` | Sends payload |
| `@PathVariable` | Path parameter |

## Interview Answers

**Why use Feign?**  
It makes remote HTTP contracts explicit and reduces boilerplate.

**Main risk?**  
It hides latency and failure behind normal Java method calls.

## Exercise

Create a `PaymentClient`, configure timeouts, and add fallback for timeout.
