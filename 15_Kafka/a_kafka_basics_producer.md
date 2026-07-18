# Kafka Basics - Producer

Act as a Principal Java Backend Engineer.

Topic To Teach: Kafka Basics  
Subtopic: Producer

Important Instructions:
DO NOT overshare more content; be precise. Build knowledge progressively from fundamentals to advanced concepts. Connect every section logically to the previous section. Explain what, why, how, and when. Focus on production-grade Java backend engineering.

## 1. Fundamentals

Apache Kafka is a distributed event streaming platform. A producer publishes records to a topic. A topic is split into partitions, and each record is appended to one partition.

A Kafka record usually has:

- Key
- Value
- Headers
- Timestamp
- Topic
- Partition
- Offset after write

## 2. Core Concepts

### Topic

A named stream of records, such as `order-created`.

### Partition

An ordered append-only log. Kafka ordering is guaranteed only within a partition.

### Key

The key decides partitioning. Records with the same key go to the same partition by default, preserving order for that key.

### Acknowledgment

Producer `acks` controls durability:

- `acks=0`: no broker acknowledgment.
- `acks=1`: leader acknowledges.
- `acks=all`: leader and in-sync replicas acknowledge.

### Serialization

Producers convert Java objects into bytes. Common formats are JSON, Avro, Protobuf, and String.

## 3. Spring Boot Producer Example

```java
public record OrderCreatedEvent(
        String orderId,
        String customerId,
        BigDecimal amount
) {}
```

```java
@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, OrderCreatedEvent>> publish(OrderCreatedEvent event) {
        return kafkaTemplate.send("order-created", event.orderId(), event);
    }
}
```

Config:

```yaml
spring:
  kafka:
    producer:
      acks: all
      retries: 3
      properties:
        enable.idempotence: true
        delivery.timeout.ms: 120000
        linger.ms: 5
```

## 4. Internal Working

The producer batches records, chooses partitions, serializes data, sends requests to broker leaders, and waits for acknowledgments. Kafka writes the record to the partition log and returns metadata.

Idempotent producers avoid duplicates caused by retry after uncertain send failure.

## 5. Common Mistakes

- Publishing without a meaningful key.
- Assuming ordering across all partitions.
- Using `acks=1` for critical business events.
- No schema strategy.
- Ignoring async send failures.
- Creating Kafka topics manually in production without ownership and retention rules.

## 6. Best Practices

- Use business keys such as `orderId` or `customerId`.
- Use `acks=all` and idempotence for important events.
- Handle send callback failures.
- Define topic naming, retention, partitions, and ownership.
- Use schema registry for strongly governed contracts.
- Keep events immutable and backward compatible.
- Use the outbox pattern when publishing must be consistent with database commits.

## 7. Real-World Scenario

When `order-service` places an order, it saves the order and publishes `OrderCreatedEvent`. If database save succeeds but Kafka publish fails, downstream services miss the event. In production, use transactional outbox to avoid this gap.

## Revision Notes

- Producer writes records to topics.
- Topics are split into partitions.
- Key controls partitioning and per-key ordering.
- `acks=all` improves durability.
- Idempotence reduces duplicate sends during retry.

## Cheat Sheet

| Setting | Production Direction |
|---|---|
| `acks` | `all` for critical events |
| `enable.idempotence` | `true` |
| Key | Business identifier |
| Format | JSON for simple, Avro/Protobuf for governed schemas |
| Consistency | Outbox pattern |

## Interview Q&A

**Q: Why use a Kafka message key?**  
A: It controls partitioning and preserves ordering for records with the same key.

**Q: Does Kafka guarantee global ordering?**  
A: No. Ordering is guaranteed only within a partition.

**Q: What is the outbox pattern?**  
A: Persist business data and an event record in the same database transaction, then publish the event asynchronously.

## Hands-on Exercises

1. Produce `OrderCreatedEvent` using `KafkaTemplate`.
2. Use `orderId` as the key.
3. Add async failure logging for failed sends.
