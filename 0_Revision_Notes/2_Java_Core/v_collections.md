# Revision Notes

* Collections store groups of objects.
* `List` is ordered and allows duplicates.
* `Set` stores unique values.
* `Queue` is used for ordered processing.
* `ArrayList` is the common default list.
* `HashSet` is fast but unordered.
* `LinkedHashSet` preserves insertion order.
* `TreeSet` stores sorted unique values.
* `ArrayDeque` is preferred for queue/stack operations.

---

# Cheat Sheet

| Requirement | Use |
| ----------- | --- |
| Ordered data | `ArrayList` |
| Unique data | `HashSet` |
| Unique ordered | `LinkedHashSet` |
| Sorted unique | `TreeSet` |
| Queue | `ArrayDeque` |
| Immutable | `List.of`, `Set.of` |

---

# Interview Questions with Answers

### 1. List vs Set?

List allows duplicates. Set does not.

### 2. ArrayList vs LinkedList?

ArrayList uses array and is better for random access. LinkedList uses nodes and is rarely better for normal list use.

### 3. Why does HashSet need equals and hashCode?

It uses them to detect duplicate logical objects.

---

# Hands-on Exercises

### Exercise 1

Find unique roles from a list.

**Answer**

```java
Set<String> roles = new HashSet<>(List.of("ADMIN", "USER", "ADMIN"));
```

