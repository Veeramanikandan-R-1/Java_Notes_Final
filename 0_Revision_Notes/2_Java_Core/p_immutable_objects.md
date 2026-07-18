# Revision Notes

* Immutable objects cannot change after creation.
* Mutable objects can change after creation.
* `String`, wrapper classes, and Java time classes are immutable.
* `ArrayList`, arrays, and `StringBuilder` are mutable.
* `final` prevents reassignment, not object mutation.
* Use `private final` fields and no setters.
* Make defensive copies of mutable constructor arguments.
* Do not expose mutable internal fields through getters.
* Immutable objects are safe for sharing across threads.
* Immutable objects are safer as `HashMap` keys.

---

# Cheat Sheet

| Requirement | Code/Approach |
| ----------- | ------------- |
| Final field | `private final String name;` |
| No mutation | No setters |
| Copy list input | `List.copyOf(items)` |
| Simple immutable DTO | `record UserDto(...)` |
| Mutable internal builder | Return final `String` |
| Safe key | Immutable key class |

---

# Interview Questions with Answers

### 1. What is immutability?

Immutability means object state cannot be changed after construction.

---

### 2. Difference between mutable and immutable objects?

Mutable objects can change state. Immutable objects create new objects for changed values.

---

### 3. Is a final list immutable?

No. The reference is final, but list contents can still change.

---

### 4. Why use defensive copies?

To prevent external code from modifying internal state through shared references.

---

### 5. Are Java records fully immutable?

Records are shallowly immutable. Mutable fields inside records still need defensive copying.

---

# Hands-on Exercises

### Exercise 1

Create immutable `Money`.

**Answer**

```java
public final class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String currency() {
        return currency;
    }
}
```

---

### Exercise 2

Make list field safe.

**Answer**

```java
public final class OrderView {
    private final List<String> items;

    public OrderView(List<String> items) {
        this.items = List.copyOf(items);
    }

    public List<String> items() {
        return items;
    }
}
```

