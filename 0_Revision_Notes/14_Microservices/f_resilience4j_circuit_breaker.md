# Revision - Resilience4j - Circuit Breaker

## Core Recall

- Circuit breaker prevents repeated calls to unhealthy dependencies.
- States: closed, open, half-open.
- It measures failure rate and slow-call rate.
- Fallback must be safe and truthful.
- Combine with timeout and monitoring.

## Production Checklist

- Timeout before circuit breaker.
- Thresholds tuned per dependency.
- Metrics and alerts enabled.
- Retry only safe/idempotent operations.
- Do not count business validation as system failure.

## Mistakes

- Fake success fallback.
- Retrying unsafe writes.
- Same config for every dependency.
- No visibility into open circuits.

## Cheat Sheet

| Pattern | Use |
|---|---|
| Timeout | Bound waiting |
| Retry | Transient safe failures |
| Circuit breaker | Stop failing calls |
| Bulkhead | Limit concurrency |
| Rate limiter | Limit rate |

## Interview Answers

**What problem does it solve?**  
It prevents cascading failure from unhealthy dependencies.

**What is half-open?**  
Limited test calls decide whether the dependency recovered.

## Exercise

Wrap a Feign payment call with Resilience4j and expose metrics.
