# Consumer Groups - Scaling

Act as a Principal Java Backend Engineer.

Topic To Teach: Consumer Groups  
Subtopic: Scaling

## 1. Fundamentals

A consumer group is a set of consumers that share work for a topic. Kafka assigns each partition to only one consumer within the same group at a time.

Consumer groups provide horizontal scaling and fault tolerance for consumers.

## 2. Core Concepts

### Partition Assignment

If a topic has six partitions and a group has three consumers, each consumer may process two partitions.

### Scaling Limit

Maximum parallelism for one topic within one group is limited by partition count. If there are three partitions and six consumers, three consumers will be idle.

### Rebalancing

When consumers join or leave, Kafka redistributes partitions. During rebalancing, processing may pause briefly.

### Multiple Groups

Different services use different group IDs to receive their own copy of events. `invoice-service` and `email-service` should not share the same group ID if both need every order event.

## 3. Example

```java
@KafkaListener(
        topics = "order-created",
        groupId = "invoice-service",
        concurrency = "3"
)
public void consume(OrderCreatedEvent event) {
    invoiceService.createInvoice(event);
}
```

If `order-created` has three partitions, concurrency `3` can process partitions in parallel.

## 4. Internal Working

Kafka group coordinator manages membership. Consumers send heartbeats. If a consumer stops heartbeating, its partitions are reassigned.

Offsets are stored per consumer group, topic, and partition. This is why multiple groups can read the same topic independently.

## 5. Common Mistakes

- More consumers than partitions and expecting more throughput.
- Same group ID used by unrelated services.
- Frequent rebalances due to unstable consumers.
- Long processing without adjusting poll settings.
- Changing partition count without understanding ordering impact.
- Not planning partition count for future throughput.

## 6. Best Practices

- Choose group ID per consuming application.
- Size partitions based on throughput and ordering needs.
- Use stable consumer instances.
- Keep processing fast or offload long work.
- Monitor lag by group.
- Use cooperative rebalancing when appropriate.
- Keep message keys aligned with ordering requirements.

## 7. Real-World Scenario

`order-created` has six partitions. `invoice-service` runs three instances and processes in parallel. Later traffic doubles. You can scale to six instances before needing more partitions. If ordering by `orderId` matters, keep all events for one order using the same key.

## Revision Notes

- Consumer group shares partition work.
- One partition goes to one consumer in a group.
- Parallelism is limited by partition count.
- Different services should use different group IDs.
- Rebalancing redistributes partitions.

## Cheat Sheet

| Situation | Result |
|---|---|
| 3 partitions, 3 consumers | All active |
| 3 partitions, 6 consumers | 3 idle |
| 2 services, same group | They split events |
| 2 services, different groups | Both receive events |

## Interview Q&A

**Q: How does Kafka scale consumers?**  
A: By assigning partitions across consumers in the same group.

**Q: Can two consumers in the same group read the same partition?**  
A: Not at the same time under normal assignment.

**Q: What limits consumer parallelism?**  
A: The number of partitions.

## Hands-on Exercises

1. Create a topic with three partitions.
2. Run one, then three, then five consumers in the same group.
3. Observe partition assignment and idle consumers.
