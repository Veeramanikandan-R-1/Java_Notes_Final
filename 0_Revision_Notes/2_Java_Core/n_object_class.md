# Revision Notes

* `Object` is the root class of all Java classes.
* Every class inherits methods like `equals()`, `hashCode()`, `toString()`, and `getClass()`.
* Default `equals()` behaves like reference comparison.
* Override `equals()` for logical equality.
* Override `hashCode()` whenever `equals()` is overridden.
* Equal objects must return the same hash code.
* `toString()` should be useful for debugging but must not expose secrets.
* `clone()` creates a shallow copy by default.
* Prefer copy constructors or factory methods over `clone()` for complex objects.

---

# Cheat Sheet

| Method | Purpose |
| ------ | ------- |
| `equals(Object o)` | Logical equality |
| `hashCode()` | Hash-based collection support |
| `toString()` | Debug/log representation |
| `clone()` | Object copy |
| `getClass()` | Runtime class information |

---

# Interview Questions with Answers

### 1. What is the Object class?

It is the root class of Java. Every class directly or indirectly extends it.

---

### 2. Difference between `==` and `equals()`?

`==` compares references. `equals()` compares logical equality when properly overridden.

---

### 3. Why must equals() and hashCode() be overridden together?

Hash collections use `hashCode()` to find a bucket and `equals()` to compare keys. If the contract is broken, lookups and deduplication can fail.

---

### 4. What is the hashCode contract?

If two objects are equal according to `equals()`, they must have the same hash code.

---

### 5. Is clone() deep copy or shallow copy?

`Object.clone()` performs a shallow copy.

---

# Hands-on Exercises

### Exercise 1

Create `Employee` equality based on employee id.

**Answer**

```java
class Employee {
    private final long id;

    Employee(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
```

---

### Exercise 2

Why should passwords not be included in `toString()`?

**Answer**

Because `toString()` often appears in logs and debugging output, which can expose sensitive data.

