# Revision Notes

* IoC means Spring controls object creation and wiring.
* Dependency Injection provides required dependencies from outside the class.
* Spring container manages beans.
* DI reduces tight coupling and improves testability.
* Constructor injection is preferred for required dependencies.
* Avoid creating dependencies manually with `new` inside services.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| IoC | Container controls object creation |
| DI | Dependencies are provided externally |
| Bean | Object managed by Spring |
| Container | Spring ApplicationContext |
| Constructor injection | Preferred required dependency style |

---

# Interview Questions with Answers

### 1. What is IoC?

Inversion of Control means the framework manages object creation and dependency wiring.

### 2. Why DI?

It makes code loosely coupled, easier to test, and easier to change.

### 3. Why constructor injection?

It makes dependencies mandatory and supports immutable service design.

