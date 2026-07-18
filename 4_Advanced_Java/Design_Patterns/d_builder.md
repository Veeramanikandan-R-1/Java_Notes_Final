# Builder Design Pattern

### Subtopic: Builder

---

# 1. Fundamentals

Builder pattern creates complex objects step by step.

Use it when constructors have many optional fields.

---

# 2. Core Concepts

## Problem

```java
User user = new User("Ravi", "r@test.com", 30, "ADMIN", true);
```

Long constructors are hard to read and easy to misuse.

## Builder Solution

```java
class User {
    private final String name;
    private final String email;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    static class Builder {
        private String name;
        private String email;
        private int age;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder email(String email) {
            this.email = email;
            return this;
        }

        Builder age(int age) {
            this.age = age;
            return this;
        }

        User build() {
            return new User(this);
        }
    }
}
```

Usage:

```java
User user = new User.Builder()
        .name("Ravi")
        .email("r@test.com")
        .age(30)
        .build();
```

---

# 3. Internal Working

Builder stores construction values temporarily and creates final object in `build()`.

It helps keep the final object immutable.

---

# 4. Common Mistakes

* Not validating required fields in `build()`.
* Making final object mutable unnecessarily.
* Using builder for very simple objects.
* Forgetting defensive copies for collections.
* Reusing builder accidentally across objects.

---

# 5. Best Practices

* Use builder for complex object creation.
* Validate in `build()`.
* Make target object immutable where possible.
* Keep required fields clear.
* Use Lombok `@Builder` carefully if project allows it.

---

# 6. Real-world Scenarios

* Request DTOs.
* Configuration objects.
* Test data creation.
* Complex domain objects.
* API response construction.

---

# Revision Notes

* Builder creates complex objects step by step.
* Useful for many optional fields.
* Improves readability.
* Supports immutable objects.
* Validate required fields in `build()`.
* Avoid for simple objects.

---

# Cheat Sheet

| Need | Builder Benefit |
| ---- | --------------- |
| Many optional fields | Readable construction |
| Immutable object | Set once in constructor |
| Validation | `build()` method |
| Test data | Fluent setup |

---

# Interview Questions with Answers

### 1. What problem does Builder solve?

It avoids long confusing constructors for complex objects.

### 2. Builder vs Factory?

Builder assembles complex object step by step. Factory chooses which object to create.

### 3. Why builder works well with immutability?

All fields can be collected first and assigned once to final fields.

