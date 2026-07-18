# Revision Notes

* Main domains: user, restaurant, menu, order, payment, delivery, notification.
* Order state machine is central.
* Payment callbacks must be idempotent.
* Restaurant/menu reads benefit from caching.
* Delivery location updates are high-write.
* Events decouple status changes.
* Failure flows need compensation.

---

# Cheat Sheet

| Area | Design |
| ---- | ------ |
| Order flow | State machine |
| Payment | Idempotency |
| Notifications | Events |
| Menu | Cache |
| Tracking | Separate event stream |
| Failure | Compensation |

---

# Interview Questions with Answers

### 1. How model order state?

Use controlled transitions like CREATED, PAID, ACCEPTED, PICKED_UP, DELIVERED, CANCELLED.

### 2. Why idempotent payment callback?

Payment providers may retry callbacks.

### 3. Why separate tracking?

Location updates are frequent and should not overload order storage.

