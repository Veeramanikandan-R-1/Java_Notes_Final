# Revision Notes

* Singleton ensures one instance.
* Constructor must be private.
* Eager singleton is simple and thread-safe.
* Lazy singleton needs synchronization.
* Enum singleton is robust for plain Java.
* Avoid mutable global state.
* In Spring, singleton bean scope is default.

---

# Cheat Sheet

| Type | Use |
| ---- | --- |
| Eager | Always-needed instance |
| Lazy | Create on demand |
| Enum | Robust singleton |
| Spring bean | Preferred in Spring |

---

# Interview Questions with Answers

### 1. What does Singleton solve?

It ensures a class has only one instance and provides global access.

### 2. Why can Singleton be bad?

It can hide dependencies and create global mutable state.

### 3. Why enum singleton?

It handles serialization and reflection issues better than many manual versions.

