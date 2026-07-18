# Collections in Java

### Subtopic: List, Set, Queue

---

# 1. Fundamentals

Java Collections Framework provides reusable data structures for storing and processing groups of objects.

Main interfaces:

* `List`
* `Set`
* `Queue`
* `Map`

This note focuses on `List`, `Set`, and `Queue`.

---

# 2. Core Concepts

## List

`List` stores ordered elements and allows duplicates.

```java
List<String> names = new ArrayList<>();
names.add("Java");
names.add("Java");
```

Common implementations:

| Implementation | Best For |
| -------------- | -------- |
| `ArrayList` | Fast random access |
| `LinkedList` | Queue-like operations, rarely preferred as list |

`ArrayList` is the default choice for most list use cases.

## Set

`Set` stores unique elements.

```java
Set<String> roles = new HashSet<>();
roles.add("ADMIN");
roles.add("ADMIN");
```

Only one value is stored.

Common implementations:

| Implementation | Behavior |
| -------------- | -------- |
| `HashSet` | Fast, no order guarantee |
| `LinkedHashSet` | Maintains insertion order |
| `TreeSet` | Sorted order |

## Queue

`Queue` is used for processing elements in order.

```java
Queue<String> queue = new ArrayDeque<>();
queue.offer("job1");
String job = queue.poll();
```

Prefer `ArrayDeque` for normal queue/stack behavior.

## Common APIs

```java
collection.add(value);
collection.remove(value);
collection.contains(value);
collection.size();
collection.isEmpty();
```

---

# 3. Internal Working

`ArrayList` uses a resizable array internally.

`HashSet` internally uses hashing, backed by a hash table.

`TreeSet` uses a sorted tree structure.

`ArrayDeque` uses a resizable circular array.

Performance depends on the chosen implementation.

---

# 4. Common Mistakes

* Using `List` when uniqueness is required.
* Using `LinkedList` by default.
* Modifying collection while iterating incorrectly.
* Forgetting `equals()` and `hashCode()` for objects in `HashSet`.
* Assuming `HashSet` preserves order.
* Returning mutable internal collections from APIs.

---

# 5. Best Practices

* Use interface type for variables: `List`, `Set`, `Queue`.
* Use `ArrayList` for most list needs.
* Use `HashSet` for uniqueness.
* Use `LinkedHashSet` when uniqueness and insertion order both matter.
* Use `ArrayDeque` for queues and stacks.
* Return immutable copies from public APIs when needed.
* Choose collection based on access pattern.

---

# 6. Code Example

```java
class RoleService {
    Set<String> normalizeRoles(List<String> input) {
        Set<String> roles = new LinkedHashSet<>();

        for (String role : input) {
            if (role != null && !role.isBlank()) {
                roles.add(role.strip().toUpperCase(Locale.ROOT));
            }
        }

        return roles;
    }
}
```

---

# 7. Real-world Scenarios

* `List` for API result order.
* `Set` for permissions and deduplication.
* `Queue` for background jobs.
* `TreeSet` for sorted unique values.
* `ArrayDeque` for BFS and task processing.

---

# Revision Notes

* `List` is ordered and allows duplicates.
* `Set` stores unique elements.
* `Queue` processes elements in order.
* `ArrayList` is default list choice.
* `HashSet` does not guarantee order.
* `LinkedHashSet` preserves insertion order.
* `TreeSet` keeps sorted order.
* `ArrayDeque` is preferred for queue/stack operations.

---

# Cheat Sheet

| Need | Collection |
| ---- | ---------- |
| Ordered duplicates | `ArrayList` |
| Unique values | `HashSet` |
| Unique + insertion order | `LinkedHashSet` |
| Sorted unique | `TreeSet` |
| Queue | `ArrayDeque` |
| Immutable list | `List.of()` |

---

# Interview Questions with Answers

### 1. Difference between List and Set?

`List` allows duplicates and preserves order. `Set` stores unique elements.

### 2. Why is ArrayList commonly preferred?

It provides fast random access and good performance for most read-heavy list use cases.

### 3. Does HashSet maintain insertion order?

No. Use `LinkedHashSet` if insertion order matters.

---

# Hands-on Exercises

### Exercise 1

Remove duplicates while preserving insertion order.

**Answer**

```java
public static List<String> unique(List<String> input) {
    return new ArrayList<>(new LinkedHashSet<>(input));
}
```

