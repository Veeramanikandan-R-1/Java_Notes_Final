# Revision - Kafka Basics - Producer

## Core Recall

- Producer publishes records to topics.
- Topic is split into partitions.
- Record key controls partitioning.
- Ordering is guaranteed only within one partition.
- `acks=all` plus idempotence improves durability.

## Production Checklist

- Use meaningful business key.
- Handle async send failures.
- Define schema and versioning.
- Use outbox for DB plus event consistency.
- Configure retention, partitions, and ownership.

## Mistakes

- No key.
- Assuming global ordering.
- Ignoring failed futures.
- No schema strategy.

## Cheat Sheet

| Concept | Meaning |
|---|---|
| Topic | Named event stream |
| Partition | Ordered log |
| Offset | Position in partition |
| Key | Partition decision |
| `acks` | Durability acknowledgment |

## Interview Answers

**Why key events?**  
To route related records to the same partition and preserve per-key order.

**What is outbox?**  
Store business change and event in one DB transaction, then publish reliably.

## Exercise

Publish `OrderCreatedEvent` with `orderId` as key and log failed sends.
