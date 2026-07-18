# Revision Notes

* Spring application events decouple publisher and listener.
* Publisher emits event.
* Listener handles event.
* Use events for side effects like notification, audit, cache invalidation.
* `@EventListener` handles events.
* `@TransactionalEventListener` can run after transaction commit.
* Keep event payload focused and immutable.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Publish event | `ApplicationEventPublisher` |
| Listen | `@EventListener` |
| After commit | `@TransactionalEventListener` |
| Async events | `@Async` |

---

# Interview Questions with Answers

### 1. Why use events?

To decouple main business flow from secondary actions.

### 2. @EventListener vs @TransactionalEventListener?

`@EventListener` runs normally. `@TransactionalEventListener` can wait for transaction phase like after commit.

### 3. What is common event use case?

Send notification after order is created.

