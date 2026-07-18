# Kafka Consumers - Consumers

Act as a Principal Java Backend Engineer.

Topic To Teach: Kafka Consumers  
Subtopic: Consumers

## 1. Fundamentals

A Kafka consumer reads records from topic partitions. Consumers process records and commit offsets to remember progress.

Consuming is not just reading. A production consumer must handle failures, duplicates, ordering, retries, shutdown, and observability.

## 2. Core Concepts

### Offset

An offset is the record position inside a partition. Kafka does not mark records as deleted after reading.

### Commit

Committing an offset tells Kafka that the consumer group processed records up to that point.

### Deserialization

Consumers convert bytes back into Java objects. Bad payloads can break processing if not handled carefully.

### Poll Loop

Kafka consumers poll records from brokers. In Spring Kafka, `@KafkaListener` manages this loop.

## 3. Spring Boot Consumer Example

```java
@Component
public class OrderCreatedConsumer {
    private final InvoiceService invoiceService;

    public OrderCreatedConsumer(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @KafkaListener(topics = "order-created", groupId = "invoice-service")
    public void consume(OrderCreatedEvent event) {
        invoiceService.createInvoice(event.orderId(), event.customerId(), event.amount());
    }
}
```

Config:

```yaml
spring:
  kafka:
    consumer:
      group-id: invoice-service
      auto-offset-reset: earliest
      enable-auto-commit: false
```

## 4. Internal Working

The consumer fetches records in batches, processes them, and commits offsets. If processing fails before commit, the record may be read again. Therefore, consumers should be idempotent.

At-least-once processing is common: records are not lost, but duplicates can happen.

## 5. Common Mistakes

- Assuming consumers process exactly once by default.
- Auto-committing before business processing succeeds.
- Consumer logic is not idempotent.
- Poison messages block the partition forever.
- No monitoring for consumer lag.
- Slow processing causes poll interval problems.

## 6. Best Practices

- Disable auto-commit for important processing.
- Make handlers idempotent using unique event IDs or business keys.
- Use dead letter topics for poison records.
- Track consumer lag.
- Keep processing time below `max.poll.interval.ms`.
- Use batch listeners only when batch semantics are understood.
- Validate event schema and handle deserialization errors.

## 7. Real-World Scenario

`invoice-service` consumes `order-created`. If it crashes after creating invoice but before committing offset, Kafka redelivers the event. Without idempotency, duplicate invoices are created. Use `orderId` as a unique key in invoice storage.

## Revision Notes

- Consumer reads records from partitions.
- Offset tracks position.
- Commit records successful progress.
- At-least-once means duplicates are possible.
- Consumer handlers must be idempotent.

## Cheat Sheet

| Concern | Practice |
|---|---|
| Duplicate events | Idempotency |
| Poison records | Dead letter topic |
| Progress | Offset commit |
| Delay | Consumer lag metric |
| Bad payload | Error handling deserializer |

## Interview Q&A

**Q: What is an offset?**  
A: The record position in a topic partition.

**Q: Why disable auto commit?**  
A: To commit only after business processing succeeds.

**Q: Why must consumers be idempotent?**  
A: Kafka may redeliver records after failures.

## Hands-on Exercises

1. Consume `order-created` events with `@KafkaListener`.
2. Store processed `orderId` to avoid duplicates.
3. Add consumer lag monitoring.
