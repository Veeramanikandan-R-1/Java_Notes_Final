# Revision Notes

* Abstraction hides internal implementation.
* Interface defines a contract.
* Abstract class can provide shared state and partial behavior.
* Service code should depend on abstractions when implementations vary.
* Interfaces support flexible testing and dependency injection.
* Keep abstractions focused.
* Avoid creating abstractions before there is a real need.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Interface | Contract |
| Abstract class | Shared base behavior |
| Abstract method | Method without body |
| Implementation | Concrete class |
| Dependency inversion | Depend on abstraction |

---

# Interview Questions with Answers

### 1. What is abstraction?

Hiding implementation details and exposing only required behavior.

### 2. Can abstract class have constructor?

Yes. It can initialize state used by subclasses.

### 3. Can a class implement multiple interfaces?

Yes.

---

# Hands-on Exercises

### Exercise 1

Create `MessageSender` abstraction.

**Answer**

```java
interface MessageSender {
    void send(String message);
}

class ConsoleMessageSender implements MessageSender {
    public void send(String message) {
        System.out.println(message);
    }
}
```

