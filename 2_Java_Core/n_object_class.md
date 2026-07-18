# Object Class in Java

### Subtopic: equals, hashCode, toString, clone

---

# 1. Fundamentals

`Object` is the root class of Java's class hierarchy.

Every class directly or indirectly extends `java.lang.Object`.

```java
class User {
}
```

Compiler understands it like:

```java
class User extends Object {
}
```

This is why every Java object has methods like `equals()`, `hashCode()`, `toString()`, and `getClass()`.

---

# 2. Core Concepts

## 2.1 toString()

`toString()` returns a string representation of an object.

Default output:

```java
User user = new User();
System.out.println(user);
```

Output is usually:

```text
User@1a2b3c
```

That is not useful for debugging. Override it for meaningful output.

```java
class User {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
```

Use `toString()` for debugging and logging, but never expose secrets like passwords or tokens.

---

## 2.2 equals()

`equals()` checks whether two objects are logically equal.

Default implementation behaves like `==`.

```java
User a = new User(1, "Ravi");
User b = new User(1, "Ravi");

System.out.println(a == b);      // false
System.out.println(a.equals(b)); // false unless overridden
```

Override it when object equality should be based on business data.

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return id == user.id;
}
```

For entities, equality is often based on a stable identifier.

---

## 2.3 hashCode()

`hashCode()` returns an integer used by hash-based collections.

Important collections:

* `HashMap`
* `HashSet`
* `LinkedHashMap`
* `ConcurrentHashMap`

Rule:

If two objects are equal according to `equals()`, they must return the same `hashCode()`.

```java
@Override
public int hashCode() {
    return Objects.hash(id);
}
```

If this rule is broken, hash collections behave incorrectly.

---

## 2.4 equals() and hashCode() Contract

The contract:

* Same object must be equal to itself.
* If `a.equals(b)` is true, `b.equals(a)` must be true.
* If `a.equals(b)` and `b.equals(c)`, then `a.equals(c)` must be true.
* Multiple calls should return consistent results if fields do not change.
* Equal objects must have equal hash codes.

Example:

```java
Set<User> users = new HashSet<>();
users.add(new User(1, "Ravi"));
users.add(new User(1, "Ravi"));
```

With correct `equals()` and `hashCode()`, the set stores one logical user.

---

## 2.5 clone()

`clone()` creates a copy of an object.

To use it, a class must implement `Cloneable`.

```java
class User implements Cloneable {
    String name;

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
```

`Object.clone()` performs a shallow copy.

If the object contains mutable nested fields, both objects can share the same nested reference.

```java
class Employee implements Cloneable {
    Address address;
}
```

After shallow clone, both employees may point to the same `Address`.

In production code, copy constructors or factory methods are usually clearer than `clone()`.

---

# 3. Internal Working

## How HashMap Uses hashCode() and equals()

When inserting a key:

```java
map.put(user, value);
```

HashMap:

1. Calls `hashCode()` to find the bucket.
2. Uses `equals()` to compare keys inside that bucket.
3. Updates existing value if equal key exists.

Bad `hashCode()` causes poor distribution. Bad `equals()` causes duplicate or missing keys.

---

## Why Object Methods Matter

These methods affect:

* Collection behavior
* Logging readability
* Debugging
* Entity identity
* Caching keys
* Tests and assertions

For backend systems, incorrect equality can create duplicate users, broken cache lookups, and subtle persistence bugs.

---

# 4. Common Mistakes

## 1. Overriding equals() but not hashCode()

```java
@Override
public boolean equals(Object o) {
    return true;
}
```

This breaks `HashSet` and `HashMap`.

---

## 2. Using Mutable Fields in hashCode()

```java
class User {
    String email; // mutable
}
```

If email changes after insertion into `HashSet`, the object may become unreachable in the set.

---

## 3. Logging Sensitive Fields in toString()

Never include passwords, tokens, secrets, or full card numbers.

---

## 4. Assuming clone() is Deep Copy

`super.clone()` is shallow. Nested mutable objects are shared unless copied manually.

---

# 5. Best Practices

* Always override `equals()` and `hashCode()` together.
* Use stable fields for equality.
* Use `Objects.equals()` for null-safe comparisons.
* Use `Objects.hash()` for simple hash code generation.
* Keep `toString()` useful but safe.
* Prefer copy constructors over `clone()` for complex objects.

---

# 6. Code Example

```java
import java.util.Objects;

class User {
    private final long id;
    private final String email;

    User(long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + "'}";
    }
}
```

---

# 7. Real-world Scenarios

## 1. Cache Keys

Objects used as cache keys must have correct `equals()` and `hashCode()`.

## 2. JPA Entities

Entity equality should be handled carefully, usually around stable identifiers.

## 3. Logging DTOs

`toString()` helps debugging, but it must avoid secrets.

## 4. HashSet Deduplication

`HashSet` depends on equality contracts to remove duplicates.

---

# Revision Notes

* `Object` is the root class of all Java classes.
* `toString()` gives object text representation.
* `equals()` defines logical equality.
* `hashCode()` supports hash-based collections.
* Equal objects must have equal hash codes.
* `clone()` creates a shallow copy by default.
* Override `equals()` and `hashCode()` together.
* Avoid mutable fields in equality and hash code.

---

# Cheat Sheet

| Method | Purpose |
| ------ | ------- |
| `toString()` | Object text representation |
| `equals(Object o)` | Logical equality |
| `hashCode()` | Hash bucket calculation |
| `clone()` | Object copy |
| `getClass()` | Runtime class metadata |

---

# Interview Questions with Answers

### 1. Why is Object class important?

It provides common behavior inherited by all Java objects.

---

### 2. Difference between `==` and `equals()`?

`==` compares references. `equals()` should compare logical object equality when overridden.

---

### 3. Why override hashCode() with equals()?

Hash-based collections first use `hashCode()` and then `equals()`. Equal objects must land in compatible hash buckets.

---

### 4. What is shallow cloning?

The object is copied, but nested object references are shared.

---

# Hands-on Exercises

### Exercise 1

Create a `Product` class where two products are equal if their `id` is equal.

**Answer**

```java
class Product {
    private final long id;

    Product(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
```

---

### Exercise 2

Explain what happens if `equals()` is overridden but `hashCode()` is not.

**Answer**

Objects that are logically equal may go into different hash buckets, causing incorrect behavior in `HashMap` and `HashSet`.

