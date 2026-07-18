# Revision Notes

* Notification service sends email, SMS, push, etc.
* It should usually be asynchronous.
* Templates separate message text from code.
* Channel strategy handles multiple delivery types.
* Provider adapters isolate vendor APIs.
* Retries handle transient failures.
* DLQ stores repeatedly failed messages.
* Idempotency prevents duplicate sends.

---

# Cheat Sheet

| Need | Design |
| ---- | ------ |
| Async | Queue/Kafka |
| Multi-channel | Strategy |
| Vendor isolation | Adapter |
| Duplicate prevention | Idempotency key |
| Failures | Retry + DLQ |
| Message content | Template |

---

# Interview Questions with Answers

### 1. Why async notification?

To avoid slowing business transactions and improve reliability.

### 2. How avoid duplicate sends?

Store and check idempotency key.

### 3. How handle vendor failure?

Timeout, retry, failover, and DLQ.

