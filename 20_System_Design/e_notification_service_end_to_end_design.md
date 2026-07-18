# Notification Service

### Subtopic: End-to-End Design

---

# 1. Fundamentals

Notification service sends messages through channels like email, SMS, push, or WhatsApp.

It should be reliable, scalable, and decoupled from business services.

---

# 2. Requirements

Functional:

* Send notification.
* Support multiple channels.
* Template messages.
* Retry failed delivery.
* Track status.

Non-functional:

* High availability.
* Rate limiting.
* Async processing.
* Provider failover.
* Idempotency.

---

# 3. High-Level Design

```text
Order Service -> Kafka -> Notification Service -> Provider
                                |
                              DB
```

Components:

* API/event consumer.
* Template engine.
* Channel strategy.
* Provider adapter.
* Retry scheduler.
* Status store.

---

# 4. Core Concepts

## Template

```text
Hello {name}, your order {orderId} is confirmed.
```

## Channel Strategy

Choose email, SMS, or push implementation using Strategy pattern.

## Retry

Retry transient failures with backoff.

## Idempotency

Use notification request ID to avoid duplicate sends.

---

# 5. Common Mistakes

* Sending notification synchronously inside order transaction.
* No retry strategy.
* No idempotency key.
* No provider timeout.
* Logging OTP/secrets.
* No DLQ for failed messages.

---

# 6. Best Practices

* Use async queue.
* Store delivery status.
* Make send operation idempotent.
* Use templates.
* Add rate limits.
* Use provider adapters.
* Use DLQ for repeated failures.

---

# Revision Notes

* Notification service should be async.
* Supports channels like email, SMS, push.
* Templates separate content from code.
* Retry transient failures.
* Idempotency prevents duplicate sends.
* Provider adapters isolate vendor APIs.
* DLQ handles repeated failures.

---

# Cheat Sheet

| Need | Design |
| ---- | ------ |
| Async delivery | Kafka/queue |
| Multiple channels | Strategy |
| Vendor isolation | Adapter |
| Duplicate prevention | Idempotency key |
| Failures | Retry + DLQ |
| Message text | Templates |

---

# Interview Questions with Answers

### 1. Why async notification?

To avoid slowing business transactions and improve reliability.

### 2. How prevent duplicate notifications?

Use idempotency key and store processed notification IDs.

### 3. How handle provider failures?

Use timeout, retry with backoff, failover, and DLQ.

