# Revision Notes

* Message queue decouples producers and consumers.
* Kafka is a distributed event streaming platform.
* Topic stores event stream.
* Partition enables scalability.
* Producer sends events.
* Consumer reads events.
* Consumer group shares partition processing.
* Offset tracks consumer progress.
* Consumers should be idempotent.

---

# Cheat Sheet

| Need | Kafka Concept |
| ---- | ------------- |
| Event stream | Topic |
| Scale stream | Partition |
| Publish | Producer |
| Consume | Consumer |
| Scale consumers | Consumer group |
| Failure handling | DLQ |

---

# Interview Questions with Answers

### 1. Why use Kafka?

To decouple services and process events asynchronously at scale.

### 2. What is consumer lag?

How far a consumer is behind the latest available message.

### 3. Why idempotency?

Retries can process the same event more than once.

