# Revision - Dead Letter Queue - Error Handling

## Core Recall

- DLT stores records that fail after retries.
- It prevents poison messages from blocking partitions.
- Retry transient errors; DLT permanent or repeated failures.
- DLT records need metadata, alerting, and replay.

## Production Checklist

- Bounded retry with backoff.
- Non-retryable exception classification.
- Preserve original topic, partition, offset, and key.
- Alert on DLT count and age.
- Controlled replay process.

## Mistakes

- Infinite retry.
- No DLT owner.
- No replay tool.
- Ignoring DLT growth.

## Cheat Sheet

| Failure | Handling |
|---|---|
| DB timeout | Retry |
| Invalid payload | DLT |
| Schema mismatch | DLT |
| Duplicate | Idempotent ignore |

## Interview Answers

**Why use DLT?**  
To isolate unprocessable records and let the consumer continue.

**What happens after DLT?**  
Teams inspect, fix, replay, or discard according to policy.

## Exercise

Configure three retries and publish failures to `order-created.DLT`.
