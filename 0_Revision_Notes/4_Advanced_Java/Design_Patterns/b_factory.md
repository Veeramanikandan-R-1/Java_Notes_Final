# Revision Notes

* Factory centralizes object creation.
* It hides concrete class construction.
* Factory should return an interface or abstract type.
* Use when creation logic varies.
* Avoid factories for simple constructors.
* Use enum instead of raw strings for type-safe selection.

---

# Cheat Sheet

| Need | Pattern Role |
| ---- | ------------ |
| Hide creation | Factory |
| Multiple implementations | Return abstraction |
| Select by type | Factory method |
| Spring apps | DI often replaces factory |

---

# Interview Questions with Answers

### 1. What does Factory solve?

It reduces coupling by moving object creation logic out of client code.

### 2. Factory vs Strategy?

Factory creates objects. Strategy represents interchangeable behavior.

### 3. When avoid Factory?

When object creation is simple and unlikely to vary.

