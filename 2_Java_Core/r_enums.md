# Enums in Java

### Subtopic: Basics, Constructors, Methods

---

# 1. Fundamentals

An enum represents a fixed set of constants.

```java
enum OrderStatus {
    CREATED,
    PAID,
    CANCELLED
}
```

Use enums when a value must be one of a known set.

---

# 2. Core Concepts

## Basic Usage

```java
OrderStatus status = OrderStatus.CREATED;
```

Comparison is safe with `==`.

```java
if (status == OrderStatus.PAID) {
    System.out.println("Order paid");
}
```

## Enum in switch

```java
switch (status) {
    case CREATED -> System.out.println("Created");
    case PAID -> System.out.println("Paid");
    case CANCELLED -> System.out.println("Cancelled");
}
```

## Enum Constructor

Enums can have fields and constructors.

```java
enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    int level() {
        return level;
    }
}
```

Enum constructors are private by default.

## Enum Methods

```java
enum PaymentStatus {
    SUCCESS,
    FAILED;

    boolean isFinal() {
        return this == SUCCESS || this == FAILED;
    }
}
```

## values() and valueOf()

```java
OrderStatus[] statuses = OrderStatus.values();
OrderStatus status = OrderStatus.valueOf("PAID");
```

`valueOf()` throws `IllegalArgumentException` for invalid names.

---

# 3. Internal Working

Each enum constant is a singleton object.

```java
OrderStatus.CREATED == OrderStatus.CREATED // true
```

Enums are type-safe. A method accepting `OrderStatus` cannot accidentally receive `"PAID "` or `"paid"`.

---

# 4. Common Mistakes

* Using strings for fixed business states.
* Calling `valueOf()` on untrusted input without handling exceptions.
* Persisting enum ordinal in database.
* Putting too much business logic inside enum.
* Forgetting enum names are case-sensitive.

Avoid ordinal persistence because reordering enum constants changes meanings.

---

# 5. Best Practices

* Use enums for fixed sets of values.
* Compare enums with `==`.
* Persist stable codes, not ordinal.
* Add fields when each constant needs metadata.
* Use static lookup methods for external values.

---

# 6. Code Example

```java
import java.util.Arrays;

enum PaymentMode {
    UPI("upi"),
    CARD("card"),
    NET_BANKING("net_banking");

    private final String code;

    PaymentMode(String code) {
        this.code = code;
    }

    static PaymentMode fromCode(String code) {
        return Arrays.stream(values())
                .filter(mode -> mode.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment mode"));
    }
}
```

---

# 7. Real-world Scenarios

* Order status.
* Payment mode.
* User role.
* Priority levels.
* Error categories.
* Workflow states.

---

# Revision Notes

* Enum defines fixed constants.
* Enum constants are singleton objects.
* Compare enums with `==`.
* Enums can have fields, constructors, and methods.
* `values()` returns all constants.
* `valueOf()` converts exact name to enum.
* Do not persist ordinal.

---

# Cheat Sheet

| Need | Code |
| ---- | ---- |
| Define enum | `enum Status { ACTIVE }` |
| Compare | `status == Status.ACTIVE` |
| All values | `Status.values()` |
| From name | `Status.valueOf("ACTIVE")` |
| Field | `private final String code;` |
| Constructor | `Status(String code)` |

---

# Interview Questions with Answers

### 1. What is enum?

A type-safe way to represent a fixed set of constants.

### 2. Can enum have constructor?

Yes. Enum constructors are private by default.

### 3. Why avoid enum ordinal in database?

Reordering constants changes ordinal values and corrupts meaning.

---

# Hands-on Exercises

### Exercise 1

Create enum `UserRole` with code.

**Answer**

```java
enum UserRole {
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    String code() {
        return code;
    }
}
```

