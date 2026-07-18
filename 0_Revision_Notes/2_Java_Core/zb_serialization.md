# Revision Notes

* Serialization converts object to byte stream.
* Deserialization converts byte stream back to object.
* `Serializable` is a marker interface.
* `transient` fields are not serialized.
* `serialVersionUID` identifies serialized class version.
* Static fields are not serialized.
* Nested objects must also be serializable.
* Avoid native Java deserialization for untrusted input.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Enable | `implements Serializable` |
| Version | `private static final long serialVersionUID = 1L;` |
| Skip field | `transient` |
| Serialize | `ObjectOutputStream` |
| Deserialize | `ObjectInputStream` |

---

# Interview Questions with Answers

### 1. What is marker interface?

An interface with no methods used to mark a class for special behavior.

### 2. Are constructors called during deserialization?

Not for the serializable class itself.

### 3. Why is deserialization risky?

Untrusted serialized data can trigger security vulnerabilities.

---

# Hands-on Exercises

### Exercise 1

Mark password as non-serialized.

**Answer**

```java
class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private transient String password;
}
```

