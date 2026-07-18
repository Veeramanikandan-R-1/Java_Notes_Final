# Revision Notes

* Class is a blueprint.
* Object is a runtime instance of a class.
* Fields store object state.
* Methods define behavior.
* Constructor initializes an object.
* `this` refers to the current object.
* Objects are created with `new`.
* References point to objects stored on heap.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| `class User {}` | Class declaration |
| `new User()` | Object creation |
| Field | State |
| Method | Behavior |
| Constructor | Initialization |
| `this.name` | Current object's field |

---

# Interview Questions with Answers

### 1. Difference between class and object?

Class is the blueprint. Object is the actual runtime instance.

### 2. Why should fields usually be private?

To protect state and force controlled access through methods.

### 3. What happens when `new` is used?

Memory is allocated, fields get default values, constructor runs, and a reference is returned.

---

# Hands-on Exercises

### Exercise 1

Create `Customer` with id and email.

**Answer**

```java
class Customer {
    private final long id;
    private final String email;

    Customer(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
```

