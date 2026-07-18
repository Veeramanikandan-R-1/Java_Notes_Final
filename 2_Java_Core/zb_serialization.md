# Serialization in Java

### Subtopic: Serializable, transient, serialVersionUID

---

# 1. Fundamentals

Serialization converts an object into a byte stream.

Deserialization converts byte stream back into an object.

```java
class User implements Serializable {
    private String name;
}
```

It is used when object state must be stored or transferred.

---

# 2. Core Concepts

## Serializable

`Serializable` is a marker interface.

```java
class SessionData implements Serializable {
    private String username;
}
```

It has no methods. It tells Java that the object can be serialized.

## ObjectOutputStream

```java
try (ObjectOutputStream out =
             new ObjectOutputStream(new FileOutputStream("user.ser"))) {
    out.writeObject(user);
}
```

## ObjectInputStream

```java
try (ObjectInputStream in =
             new ObjectInputStream(new FileInputStream("user.ser"))) {
    User user = (User) in.readObject();
}
```

## transient

`transient` fields are skipped during serialization.

```java
class User implements Serializable {
    private String username;
    private transient String password;
}
```

Use it for secrets, derived values, or non-serializable fields.

## serialVersionUID

`serialVersionUID` identifies class version during deserialization.

```java
private static final long serialVersionUID = 1L;
```

If class structure changes incompatibly, deserialization can fail with `InvalidClassException`.

---

# 3. Internal Working

Serialization writes object state, including serializable nested objects.

Static fields are not serialized because they belong to class, not object.

Constructors are not called for the serializable class during deserialization.

---

# 4. Common Mistakes

* Serializing sensitive fields without `transient`.
* Not defining `serialVersionUID`.
* Assuming static fields are serialized.
* Serializing objects with non-serializable nested fields.
* Using native Java serialization for untrusted data.

Java deserialization of untrusted data can be dangerous. Prefer JSON or safer formats for APIs.

---

# 5. Best Practices

* Define `serialVersionUID`.
* Mark sensitive fields `transient`.
* Avoid Java native serialization for external APIs.
* Prefer JSON, Avro, or protobuf for service communication.
* Keep serialized classes stable.
* Validate data after deserialization where needed.

---

# 6. Code Example

```java
import java.io.Serializable;

class LoginSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private transient String accessToken;

    LoginSession(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }
}
```

---

# 7. Real-world Scenarios

* HTTP session replication.
* Caching object state.
* Legacy RMI systems.
* Local object persistence.
* Messaging in older systems.

---

# Revision Notes

* Serialization converts object to byte stream.
* Deserialization reconstructs object.
* `Serializable` is marker interface.
* `transient` skips field serialization.
* `serialVersionUID` controls class version compatibility.
* Static fields are not serialized.
* Avoid native Java serialization for untrusted data.

---

# Cheat Sheet

| Need | Code |
| ---- | ---- |
| Make serializable | `implements Serializable` |
| Version | `serialVersionUID` |
| Skip field | `transient` |
| Write object | `ObjectOutputStream` |
| Read object | `ObjectInputStream` |

---

# Interview Questions with Answers

### 1. What is Serializable?

A marker interface indicating that object state can be serialized.

### 2. What is transient?

A keyword that prevents a field from being serialized.

### 3. Why define serialVersionUID?

To control version compatibility during deserialization.

---

# Hands-on Exercises

### Exercise 1

Create serializable `UserSession` and skip token.

**Answer**

```java
class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private transient String token;
}
```

