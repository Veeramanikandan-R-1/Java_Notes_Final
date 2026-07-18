# Revision Notes

* Inner classes are declared inside classes or blocks.
* Non-static inner class has hidden outer object reference.
* Static nested class does not require outer object.
* Local class is declared inside a method.
* Anonymous class is unnamed one-off implementation.
* Lambda is cleaner for functional interfaces.
* Prefer static nested class when outer instance is not needed.

---

# Cheat Sheet

| Concept | Syntax |
| ------- | ------ |
| Inner | `class Outer { class Inner {} }` |
| Static nested | `static class Nested {}` |
| Local | Class inside method |
| Anonymous | `new Runnable() { ... }` |
| Lambda | `() -> run()` |

---

# Interview Questions with Answers

### 1. What is a static nested class?

A nested class that does not depend on an instance of the outer class.

### 2. What is a local class?

A class declared inside a method or block.

### 3. Can anonymous class have constructor?

No named constructor, because it has no class name.

---

# Hands-on Exercises

### Exercise 1

Create anonymous `Runnable`.

**Answer**

```java
Runnable task = new Runnable() {
    public void run() {
        System.out.println("Task running");
    }
};
```

