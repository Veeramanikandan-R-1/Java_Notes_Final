# Revision Notes

* Observer models one-to-many notification.
* Subject publishes event.
* Observers react to event.
* It decouples event producer from consumers.
* Watch for slow observers.
* Handle observer failures independently.
* Spring application events follow similar idea.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Subject | Maintains observers |
| Observer | Handles notification |
| Event | Data about change |
| Notify | Trigger listeners |

---

# Interview Questions with Answers

### 1. What does Observer solve?

It lets multiple components react to an event without tight coupling.

### 2. Observer vs Strategy?

Observer notifies many listeners. Strategy chooses one behavior.

### 3. Risk of Observer?

Hidden side effects and slow/failing observers.

