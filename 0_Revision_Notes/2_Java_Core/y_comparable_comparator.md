# Revision Notes

* `Comparable` gives natural order.
* `Comparator` gives custom external order.
* `compareTo()` is used by Comparable.
* `compare()` is used by Comparator.
* Return negative, zero, or positive.
* Use `Integer.compare()` instead of subtraction.
* Use `thenComparing()` for multi-field sort.
* Use null helpers for nullable values.

---

# Cheat Sheet

| Requirement | Code |
| ----------- | ---- |
| Natural sort | `implements Comparable<T>` |
| Custom sort | `Comparator.comparing(...)` |
| Descending | `.reversed()` |
| Multiple fields | `.thenComparing(...)` |
| Null safe | `Comparator.nullsLast(...)` |

---

# Interview Questions with Answers

### 1. When use Comparable?

When the class has one clear natural ordering.

### 2. When use Comparator?

When sorting logic is external or multiple orderings are needed.

### 3. How does TreeSet use comparison?

It uses comparison to order elements and detect duplicates.

---

# Hands-on Exercises

### Exercise 1

Sort strings by length.

**Answer**

```java
names.sort(Comparator.comparingInt(String::length));
```

