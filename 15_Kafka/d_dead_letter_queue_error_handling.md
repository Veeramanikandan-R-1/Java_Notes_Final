# Dead Letter Queue - Error Handling

Act as a Principal Java Backend Engineer.

Topic To Teach: Dead Letter Queue  
Subtopic: Error Handling

## 1. Fundamentals

A dead letter queue, commonly implemented as a dead letter topic in Kafka, stores records that cannot be processed after configured attempts.

It prevents one bad message from blocking an entire partition forever.

## 2. Core Concepts

### Poison Message

A record that repeatedly fails processing due to bad data, incompatible schema, missing dependency behavior, or unexpected business state.

### Retry

Retry is useful for transient failures such as temporary database or network issues.

### Dead Letter Topic

After retries are exhausted, the record is published to a separate topic such as `order-created.DLT`.

### Recovery

Teams inspect, fix, replay, or discard DLT records based on business rules.

## 3. Spring Kafka Example

```java
@Bean
DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
    DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);
    FixedBackOff backOff = new FixedBackOff(1000L, 3L);
    return new DefaultErrorHandler(recoverer, backOff);
}
```

Consumer:

```java
@KafkaListener(topics = "order-created", groupId = "invoice-service")
public void consume(OrderCreatedEvent event) {
    invoiceService.createInvoice(event);
}
```

After repeated failure, the record can be published to a DLT.

## 4. Internal Working

When listener processing throws an exception, the error handler controls retry behavior. If retries are exhausted, the recoverer publishes the failed record with error metadata to a dead letter topic.

Offsets must be handled carefully so the consumer can continue after recovery.

## 5. Common Mistakes

- Infinite retry on poison messages.
- Sending every failure directly to DLT without retrying transient errors.
- No alerting on DLT growth.
- No replay process.
- Losing original headers and error details.
- Using DLT as a hidden trash bin.

## 6. Best Practices

- Separate retryable and non-retryable exceptions.
- Use bounded retries with backoff.
- Preserve original topic, partition, offset, key, and error cause.
- Alert on DLT count and age.
- Build a controlled replay process.
- Make consumers idempotent before replaying.
- Document ownership for each DLT.

## 7. Real-World Scenario

`invoice-service` fails because one event has invalid tax data. Without DLT, that partition stops. With DLT, the bad event moves to `order-created.DLT`, an alert fires, and the consumer continues processing later valid orders.

## Revision Notes

- DLT stores failed records after retries.
- Prevents poison message blockage.
- Retry transient failures; DLT permanent or repeated failures.
- DLT needs alerting and replay process.
- Preserve error metadata.

## Cheat Sheet

| Failure | Handling |
|---|---|
| Temporary DB timeout | Retry with backoff |
| Invalid schema | DLT |
| Missing required field | DLT |
| Downstream outage | Retry, then DLT or pause |
| Duplicate event | Idempotent ignore |

## Interview Q&A

**Q: Why use a DLT?**  
A: To isolate unprocessable records and keep partitions moving.

**Q: Should all errors go immediately to DLT?**  
A: No. Retry transient errors first.

**Q: What must a DLT process include?**  
A: Alerting, inspection, correction, replay, and audit.

## Hands-on Exercises

1. Configure `DefaultErrorHandler` with three retries.
2. Publish failed records to `.DLT`.
3. Write a replay consumer for corrected DLT records.
