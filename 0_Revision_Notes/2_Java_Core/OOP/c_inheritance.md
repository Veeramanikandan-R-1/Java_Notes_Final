# Revision Notes

* Inheritance lets a class acquire parent fields and methods.
* Use `extends` for class inheritance.
* Java supports single class inheritance.
* Parent constructor executes before child constructor.
* Use `super` to call parent constructor or methods.
* Method overriding enables specialized child behavior.
* Use inheritance only for true "is-a" relationships.
* Prefer composition for "has-a" relationships.

---

# Cheat Sheet

| Need | Code |
| ---- | ---- |
| Inherit | `class Dog extends Animal` |
| Override | `@Override` |
| Parent constructor | `super(args)` |
| Parent method | `super.method()` |
| Prevent inheritance | `final class` |

---

# Interview Questions with Answers

### 1. What is inheritance?

It allows a subclass to reuse and specialize superclass behavior.

### 2. Why does Java avoid multiple class inheritance?

To avoid ambiguity problems like conflicting inherited implementations.

### 3. What is constructor chaining?

The process where superclass constructors run before subclass constructors.

---

# Hands-on Exercises

### Exercise 1

Create `BaseNotification` and `EmailNotification`.

**Answer**

```java
class BaseNotification {
    void send() {
        System.out.println("Sending notification");
    }
}

class EmailNotification extends BaseNotification {
    @Override
    void send() {
        System.out.println("Sending email");
    }
}
```

