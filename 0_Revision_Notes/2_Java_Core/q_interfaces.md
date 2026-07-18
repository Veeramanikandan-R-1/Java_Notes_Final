# Revision Notes

* Interface defines a contract.
* Use `implements` to implement an interface.
* Java supports multiple interface implementation.
* Default methods provide implementation inside interfaces.
* Static interface methods are called with interface name.
* Functional interface has one abstract method.
* Lambdas work with functional interfaces.
* Keep interfaces small and behavior-focused.

---

# Cheat Sheet

| Concept | Code |
| ------- | ---- |
| Interface | `interface Sender {}` |
| Implement | `class EmailSender implements Sender` |
| Default method | `default void log() {}` |
| Static method | `static boolean valid()` |
| Functional | `@FunctionalInterface` |
| Lambda | `value -> value != null` |

---

# Interview Questions with Answers

### 1. What is the purpose of interface?

To define a contract and allow code to depend on behavior instead of concrete classes.

### 2. Difference between default and static interface methods?

Default methods are inherited by implementations. Static methods belong to the interface itself.

### 3. Can an interface extend another interface?

Yes.

### 4. Why use `@FunctionalInterface`?

It lets the compiler verify that the interface has only one abstract method.

---

# Hands-on Exercises

### Exercise 1

Create `NotificationSender` interface and one implementation.

**Answer**

```java
interface NotificationSender {
    void send(String message);
}

class EmailSender implements NotificationSender {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}
```

