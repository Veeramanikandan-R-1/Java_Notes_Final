# Revision Notes

* Template Method defines algorithm skeleton.
* Base class controls workflow order.
* Subclasses customize specific steps.
* Template method is often final.
* Use when workflow is stable.
* Prefer Strategy when behavior needs runtime switching.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Template method | Fixed workflow |
| Abstract step | Subclass behavior |
| Concrete step | Shared behavior |
| Subclass | Implements variable steps |

---

# Interview Questions with Answers

### 1. What does Template Method solve?

It avoids duplicate workflow logic while allowing customization of specific steps.

### 2. Template Method vs Strategy?

Template Method uses inheritance. Strategy uses composition.

### 3. Why make template method final?

To protect the required algorithm order.

