# Revision Notes

* Decorator adds behavior dynamically.
* It wraps another object.
* It implements the same interface.
* It delegates to the wrapped object.
* Multiple decorators can be chained.
* Java IO streams use Decorator.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Component | Interface |
| Concrete component | Base object |
| Decorator | Wrapper |
| Delegate | Wrapped object |

---

# Interview Questions with Answers

### 1. What does Decorator solve?

It adds behavior without changing the original class.

### 2. Decorator vs inheritance?

Decorator composes behavior at runtime. Inheritance fixes behavior at class definition.

### 3. Java example?

`BufferedInputStream` wrapping another `InputStream`.

