# Revision Notes

* Enum represents fixed constants.
* Enum constants are singleton objects.
* Use `==` for enum comparison.
* Enums can contain fields, constructors, and methods.
* `values()` returns all enum constants.
* `valueOf()` requires exact enum name.
* Avoid persisting ordinal.
* Prefer stable codes for database or API values.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Define | `enum Status { ACTIVE, INACTIVE }` |
| Compare | `status == Status.ACTIVE` |
| Iterate | `Status.values()` |
| Parse name | `Status.valueOf("ACTIVE")` |
| Add field | `private final String code;` |
| Constructor | `Status(String code)` |

---

# Interview Questions with Answers

### 1. Why use enum instead of String?

Enums are type-safe and prevent invalid values.

### 2. Can enum implement interface?

Yes.

### 3. What happens if `valueOf()` gets invalid value?

It throws `IllegalArgumentException`.

---

# Hands-on Exercises

### Exercise 1

Create `OrderStatus` with `isFinal()`.

**Answer**

```java
enum OrderStatus {
    CREATED,
    PAID,
    CANCELLED;

    boolean isFinal() {
        return this == PAID || this == CANCELLED;
    }
}
```

