# Revision Notes

* Reflection inspects runtime class metadata.
* `Class` represents loaded class information.
* `Field` represents fields.
* `Method` represents methods.
* `Constructor` creates objects reflectively.
* Reflection can read annotations.
* `setAccessible(true)` can bypass access checks.
* Reflection is powerful but slower and more fragile than normal code.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Class | `User.class` |
| Runtime class | `user.getClass()` |
| Load by name | `Class.forName(name)` |
| Fields | `getDeclaredFields()` |
| Method | `getDeclaredMethod()` |
| Invoke | `method.invoke(obj)` |
| Private access | `setAccessible(true)` |

---

# Interview Questions with Answers

### 1. What is Class object?

Runtime metadata representation of a loaded Java class.

### 2. Can reflection access private fields?

Yes, with `setAccessible(true)`, subject to access rules and module restrictions.

### 3. Why avoid reflection in business code?

It is harder to read, slower, fragile, and can break encapsulation.

---

# Hands-on Exercises

### Exercise 1

Get runtime class name.

**Answer**

```java
String className = object.getClass().getName();
```

