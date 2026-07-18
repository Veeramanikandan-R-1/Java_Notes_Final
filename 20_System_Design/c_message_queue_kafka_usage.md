# Message Queue

### Subtopic: Kafka Usage

---

# 1. Fundamentals

A message queue decouples producers and consumers.

```text
Producer -> Queue/Topic -> Consumer
```

Kafka is commonly used for high-throughput event streaming.

---

# 2. Core Concepts

## Producer

Sends messages/events.

```text
Order Service -> order-created topic
```

## Consumer

Reads messages/events.

```text
Notification Service reads order-created
```

## Topic

Named stream of records.

## Partition

Topic is split into partitions for scaling.

## Consumer Group

Consumers in same group share partition processing.

---

# 3. Internal Working

Kafka stores records in partitions. Each record has an offset.

Consumers track offsets to know what they have processed.

Kafka enables asynchronous processing and replay when offsets are managed correctly.

---

# 4. Common Mistakes

* Treating Kafka like a simple request-response system.
* Not designing idempotent consumers.
* Ignoring message ordering boundaries.
* Poor partition key choice.
* No dead letter queue for failures.
* Not monitoring consumer lag.

---

# 5. Best Practices

* Use events for async workflows.
* Make consumers idempotent.
* Choose partition key carefully.
* Monitor lag.
* Use DLQ for poison messages.
* Keep event schema stable and versioned.
* Avoid sending huge payloads.

---

# 6. Real-world Scenario

Order service publishes `OrderCreated`. Inventory service reserves stock, notification service sends email, and analytics service records metrics independently.

---

# Revision Notes

* Message queue decouples producer and consumer.
* Kafka uses topics and partitions.
* Producers publish events.
* Consumers read events.
* Consumer groups scale consumption.
* Offsets track progress.
* Idempotent consumers are important.

---

# Cheat Sheet

| Need | Kafka Concept |
| ---- | ------------- |
| Event stream | Topic |
| Scale topic | Partition |
| Send event | Producer |
| Read event | Consumer |
| Scale consumers | Consumer group |
| Track progress | Offset |

---

# Interview Questions with Answers

### 1. Why use Kafka?

For scalable, durable, asynchronous event communication.

### 2. What is consumer lag?

Difference between latest topic offset and consumer processed offset.

### 3. Why idempotent consumer?

Messages may be retried, so processing same event twice should not corrupt data.

