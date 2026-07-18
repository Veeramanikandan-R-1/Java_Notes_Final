# Streams API in Java

### Subtopic: Map, Filter, Reduce

---

# 1. Fundamentals

Streams process collections in a declarative style.

```java
List<String> activeUsers = users.stream()
        .filter(User::isActive)
        .map(User::getName)
        .toList();
```

A stream does not store data. It processes data from a source.

---

# 2. Core Concepts

## Stream Pipeline

A stream pipeline has:

1. Source
2. Intermediate operations
3. Terminal operation

```java
users.stream()              // source
        .filter(User::isActive) // intermediate
        .toList();              // terminal
```

Intermediate operations are lazy. They run only when terminal operation is called.

## filter()

Keeps elements matching condition.

```java
List<Integer> even = numbers.stream()
        .filter(n -> n % 2 == 0)
        .toList();
```

## map()

Transforms each element.

```java
List<String> names = users.stream()
        .map(User::getName)
        .toList();
```

## reduce()

Combines elements into one result.

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

## collect()

Collect into a collection or grouped result.

```java
Map<String, List<User>> byRole = users.stream()
        .collect(Collectors.groupingBy(User::getRole));
```

## sorted()

```java
List<User> sorted = users.stream()
        .sorted(Comparator.comparing(User::getName))
        .toList();
```

---

# 3. Internal Working

Streams are lazy.

```java
Stream<String> stream = names.stream()
        .filter(name -> name.startsWith("A"));
```

Nothing runs yet.

Execution starts when terminal operation runs:

```java
stream.toList();
```

A stream can be consumed only once.

---

# 4. Common Mistakes

* Reusing a consumed stream.
* Using streams for logic that becomes unreadable.
* Performing side effects inside `map()`.
* Forgetting null handling.
* Using parallel streams without measuring.
* Calling `get()` on empty Optional returned by stream operations.

---

# 5. Best Practices

* Use streams for transformation, filtering, grouping, and aggregation.
* Keep lambdas short.
* Prefer method references when readable.
* Avoid side effects in stream operations.
* Use loops when logic is complex or stateful.
* Use parallel streams only after profiling.

---

# 6. Code Example

```java
class Order {
    private final String status;
    private final double amount;

    String getStatus() {
        return status;
    }

    double getAmount() {
        return amount;
    }
}

double paidTotal = orders.stream()
        .filter(order -> "PAID".equals(order.getStatus()))
        .mapToDouble(Order::getAmount)
        .sum();
```

---

# 7. Real-world Scenarios

* Convert entities to DTOs.
* Filter active users.
* Group orders by status.
* Sum invoice amounts.
* Find first matching record.
* Build response lists.

---

# Revision Notes

* Stream processes data from a source.
* Intermediate operations are lazy.
* Terminal operation triggers execution.
* `filter()` keeps matching elements.
* `map()` transforms elements.
* `reduce()` combines elements.
* Streams can be consumed once.
* Avoid side effects inside streams.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Filter | `filter(predicate)` |
| Transform | `map(function)` |
| Sum numbers | `mapToInt(...).sum()` |
| Combine | `reduce(identity, op)` |
| Sort | `sorted(comparator)` |
| Group | `Collectors.groupingBy()` |
| List result | `toList()` |
| First | `findFirst()` |

---

# Interview Questions with Answers

### 1. What is stream?

A pipeline for processing data from a source.

### 2. Difference between map and filter?

`map()` transforms elements. `filter()` keeps or removes elements.

### 3. What is lazy evaluation?

Intermediate operations execute only when a terminal operation is called.

---

# Hands-on Exercises

### Exercise 1

Get names of active users.

**Answer**

```java
List<String> names = users.stream()
        .filter(User::isActive)
        .map(User::getName)
        .toList();
```

