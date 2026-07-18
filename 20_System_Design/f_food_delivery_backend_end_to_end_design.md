# Food Delivery Backend

### Subtopic: End-to-End Design

---

# 1. Fundamentals

A food delivery backend connects customers, restaurants, delivery partners, payments, and notifications.

Core flows:

* Browse restaurants.
* Place order.
* Accept/reject order.
* Assign delivery partner.
* Track delivery.
* Process payment.

---

# 2. Main Services

```text
User Service
Restaurant Service
Menu Service
Order Service
Payment Service
Delivery Service
Notification Service
Tracking Service
```

Start as a modular monolith for learning, then split into services when scale requires.

---

# 3. Order Flow

```text
Customer places order
Order service creates order
Payment service authorizes payment
Restaurant accepts order
Delivery partner assigned
Customer receives status updates
```

Use events for status changes:

```text
OrderPlaced
PaymentCompleted
RestaurantAccepted
DeliveryAssigned
OrderDelivered
```

---

# 4. Data Model

Important tables:

* users
* restaurants
* menu_items
* orders
* order_items
* payments
* deliveries
* delivery_location_events

---

# 5. Common Mistakes

* Making order state transitions uncontrolled.
* Coupling payment and order too tightly.
* No idempotency for payment callbacks.
* No strategy for live tracking scale.
* No handling for restaurant rejection.
* No compensation flow for failures.

---

# 6. Best Practices

* Model order state machine clearly.
* Use idempotency for order/payment APIs.
* Use async events for notifications.
* Keep payment callbacks secure.
* Cache restaurant/menu reads.
* Track delivery updates separately from order core data.
* Use transactions for local consistency.

---

# Revision Notes

* Food delivery has user, restaurant, order, payment, delivery, notification domains.
* Order state machine is central.
* Payment callbacks must be idempotent.
* Restaurant/menu data is read-heavy.
* Delivery tracking can be high-write.
* Events decouple status updates.
* Failure handling needs compensation.

---

# Cheat Sheet

| Area | Design |
| ---- | ------ |
| Order consistency | State machine |
| Payment safety | Idempotency |
| Notifications | Async events |
| Menu reads | Cache |
| Tracking | Separate event stream |
| Failures | Compensation |

---

# Interview Questions with Answers

### 1. How design order flow?

Use clear states like CREATED, PAID, ACCEPTED, PICKED_UP, DELIVERED, CANCELLED with controlled transitions.

### 2. How handle payment callback duplicate?

Use payment transaction ID/idempotency key and ignore already processed callbacks.

### 3. Why separate tracking data?

Location updates are frequent and should not overload core order storage.

