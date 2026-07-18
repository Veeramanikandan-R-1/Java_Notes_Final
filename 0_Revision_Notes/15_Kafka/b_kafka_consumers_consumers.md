# Revision - Kafka Consumers - Consumers

## Core Recall

- Consumer reads records from partitions.
- Offset identifies position.
- Commit marks processed progress.
- At-least-once delivery can create duplicates.
- Idempotency is required for reliable consumers.

## Production Checklist

- Disable auto commit for critical processing.
- Commit after success.
- Handle deserialization errors.
- Monitor lag.
- Use DLT for poison messages.

## Mistakes

- Auto-commit before work succeeds.
- Non-idempotent database writes.
- No lag alerts.
- Poison message blocks partition.

## Cheat Sheet

| Concern | Solution |
|---|---|
| Duplicate | Idempotency |
| Bad payload | Error handling deserializer |
| Poison message | DLT |
| Slow consumer | Lag monitoring |
| Progress | Offset commit |

## Interview Answers

**Why can duplicates happen?**  
If processing succeeds but commit fails, Kafka may redeliver.

**What is consumer lag?**  
Difference between latest topic offset and committed group offset.

## Exercise

Create an idempotent invoice consumer using `orderId` uniqueness.
