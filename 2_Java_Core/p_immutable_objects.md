# Immutable Objects in Java

### Subtopic: Mutable vs Immutable

---

# 1. Fundamentals

An immutable object is an object whose state cannot change after creation.

```java
String name = "Java";
```

`String` is immutable. Any modification creates a new object.

```java
String updated = name.concat(" Backend");
```

The original `name` remains unchanged.

---

## Mutable vs Immutable

| Type | Meaning | Example |
| ---- | ------- | ------- |
| Mutable | State can change after creation | `ArrayList`, `StringBuilder` |
| Immutable | State cannot change after creation | `String`, `Integer`, `LocalDate` |

Mutable object:

```java
List<String> names = new ArrayList<>();
names.add("Ravi");
```

Immutable object:

```java
LocalDate date = LocalDate.of(2026, 7, 18);
LocalDate next = date.plusDays(1);
```

`date` is not changed. `next` is a new object.

---

# 2. Core Concepts

## 2.1 Why Immutability Matters

Immutable objects are easier to reason about because their state is stable.

They are useful for:

* Value objects
* DTOs
* Cache keys
* Configuration
* Money and date values
* Multi-threaded code

---

## 2.2 How to Create an Immutable Class

Rules:

* Make class `final` or prevent unsafe subclassing.
* Make fields `private final`.
* Do not provide setters.
* Initialize all fields through constructor.
* Make defensive copies of mutable inputs.
* Return defensive copies of mutable fields.

Example:

```java
import java.util.ArrayList;
import java.util.List;

public final class Order {
    private final long id;
    private final List<String> items;

    public Order(long id, List<String> items) {
        this.id = id;
        this.items = new ArrayList<>(items);
    }

    public long getId() {
        return id;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
```

---

## 2.3 final Reference vs Immutable Object

`final` prevents reassignment of the reference. It does not make the object immutable.

```java
final List<String> names = new ArrayList<>();
names.add("Java"); // allowed
```

The reference cannot point to another list, but the list content can still change.

---

## 2.4 Defensive Copy

Defensive copy protects internal state from external mutation.

Bad:

```java
this.items = items;
```

External code can still modify the same list.

Good:

```java
this.items = new ArrayList<>(items);
```

Also protect getters:

```java
public List<String> getItems() {
    return List.copyOf(items);
}
```

---

## 2.5 Records and Immutability

Java records are good for immutable data carriers.

```java
public record UserResponse(long id, String email) {
}
```

Records make fields final and provide constructor, getters, `equals()`, `hashCode()`, and `toString()`.

But records are only shallowly immutable. If a record contains a mutable list, protect it.

```java
public record OrderResponse(long id, List<String> items) {
    public OrderResponse {
        items = List.copyOf(items);
    }
}
```

---

# 3. Internal Working

## Safe Sharing

Immutable objects can be safely shared because no thread can change their state after construction.

```java
private static final List<String> ALLOWED_ROLES = List.of("ADMIN", "USER");
```

No synchronization is needed for reading immutable state.

---

## HashMap Key Safety

If an object used as a key changes fields involved in `hashCode()`, map lookup may fail.

Immutable keys avoid this problem.

```java
Map<UserKey, User> cache = new HashMap<>();
```

`UserKey` should be immutable.

---

# 4. Common Mistakes

## 1. Returning Mutable Internal Fields

```java
public List<String> getItems() {
    return items; // risky
}
```

External code can mutate object state.

---

## 2. Believing final Means Immutable

```java
private final List<String> roles;
```

The list can still be mutated unless copied or wrapped safely.

---

## 3. Using Mutable Objects as HashMap Keys

If key state changes after insertion, retrieval can fail.

---

## 4. Overusing Immutability

For large objects that change frequently, creating many copies can increase memory pressure.

---

# 5. Best Practices

* Prefer immutable objects for value types.
* Use `private final` fields.
* Do not expose setters.
* Use defensive copies for mutable fields.
* Use `List.copyOf()`, `Set.copyOf()`, and `Map.copyOf()` for read-only copies.
* Keep mutable builders internal and return immutable results.
* Use records for simple immutable DTOs.

---

# 6. Code Examples

## Immutable Value Object

```java
import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = Objects.requireNonNull(amount);
        this.currency = Objects.requireNonNull(currency);
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

## Immutable Class with List

```java
import java.util.List;

public final class Invoice {
    private final String invoiceNumber;
    private final List<String> lineItems;

    public Invoice(String invoiceNumber, List<String> lineItems) {
        this.invoiceNumber = invoiceNumber;
        this.lineItems = List.copyOf(lineItems);
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public List<String> getLineItems() {
        return lineItems;
    }
}
```

`List.copyOf()` creates an unmodifiable copy.

---

# 7. Real-world Scenarios

## 1. API Response DTOs

Immutable DTOs prevent accidental changes after mapping.

## 2. Cache Keys

Immutable keys keep cache lookup stable.

## 3. Configuration Objects

Application config should not change unexpectedly at runtime.

## 4. Date and Time

Java time classes like `LocalDate` are immutable, making date calculations safer.

---

# Revision Notes

* Immutable objects cannot change after creation.
* Mutable objects can change after creation.
* `final` reference does not make the object immutable.
* Use private final fields and no setters.
* Use defensive copies for mutable fields.
* Immutable objects are thread-safe for sharing.
* Immutable keys are safer for `HashMap`.
* Records are shallowly immutable.

---

# Cheat Sheet

| Need | Approach |
| ---- | -------- |
| Immutable fields | `private final` |
| No mutation | No setters |
| Protect input list | `List.copyOf(input)` |
| Protect output list | Return unmodifiable copy |
| Simple DTO | `record` |
| Cache key | Immutable class |
| Frequent mutation | Use mutable object internally |

---

# Interview Questions with Answers

### 1. What is an immutable object?

An object whose state cannot change after it is created.

---

### 2. Does final make an object immutable?

No. `final` prevents reference reassignment, but the referenced object may still be mutable.

---

### 3. Why are immutable objects thread-safe?

Because no thread can modify their state after construction.

---

### 4. Why are immutable objects good as map keys?

Their hash-relevant state does not change, so lookup remains stable.

---

# Hands-on Exercises

### Exercise 1

Create an immutable `Address` class.

**Answer**

```java
public final class Address {
    private final String city;
    private final String pinCode;

    public Address(String city, String pinCode) {
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }
}
```

---

### Exercise 2

Fix this class:

```java
class Team {
    private final List<String> members;

    Team(List<String> members) {
        this.members = members;
    }
}
```

**Answer**

```java
class Team {
    private final List<String> members;

    Team(List<String> members) {
        this.members = List.copyOf(members);
    }

    List<String> getMembers() {
        return members;
    }
}
```

