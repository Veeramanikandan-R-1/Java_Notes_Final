# Map in Java

### Subtopic: HashMap, TreeMap

---

# 1. Fundamentals

A `Map` stores key-value pairs.

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Java", 90);
```

Keys are unique. Values can be duplicated.

---

# 2. Core Concepts

## HashMap

`HashMap` provides fast key-based lookup.

```java
Map<Long, String> users = new HashMap<>();
users.put(1L, "Ravi");
users.get(1L);
```

Average time for `put()` and `get()` is O(1).

It does not guarantee ordering.

## TreeMap

`TreeMap` stores keys in sorted order.

```java
Map<String, Integer> sorted = new TreeMap<>();
sorted.put("b", 2);
sorted.put("a", 1);
```

Keys are sorted naturally or by comparator.

Operations are O(log n).

## Common APIs

```java
map.put(key, value);
map.get(key);
map.getOrDefault(key, defaultValue);
map.containsKey(key);
map.remove(key);
map.keySet();
map.values();
map.entrySet();
```

## computeIfAbsent()

Useful for grouping.

```java
map.computeIfAbsent(role, key -> new ArrayList<>()).add(user);
```

---

# 3. Internal Working

`HashMap` uses `hashCode()` to find bucket and `equals()` to compare keys.

That is why map keys must have stable and correct equality.

`TreeMap` uses ordering through `Comparable` or `Comparator`.

---

# 4. Common Mistakes

* Using mutable objects as `HashMap` keys.
* Overriding `equals()` without `hashCode()`.
* Assuming `HashMap` order.
* Calling `get()` and not handling null.
* Using `containsValue()` for frequent lookup.
* Using `TreeMap` when ordering is not needed.

---

# 5. Best Practices

* Use `HashMap` for fast lookup.
* Use `LinkedHashMap` when insertion order matters.
* Use `TreeMap` when sorted keys are required.
* Use immutable keys.
* Use `getOrDefault()` for counters.
* Use `computeIfAbsent()` for grouping.
* Iterate with `entrySet()` when both key and value are needed.

---

# 6. Code Example

```java
public static Map<String, Long> countByStatus(List<String> statuses) {
    Map<String, Long> count = new HashMap<>();

    for (String status : statuses) {
        count.put(status, count.getOrDefault(status, 0L) + 1);
    }

    return count;
}
```

---

# 7. Real-world Scenarios

* Cache lookup by ID.
* Count by status.
* Group users by role.
* Sorted reports by date.
* Request header key-value data.

---

# Revision Notes

* `Map` stores key-value pairs.
* Keys are unique.
* `HashMap` is fast but unordered.
* `TreeMap` keeps keys sorted.
* `HashMap` uses `hashCode()` and `equals()`.
* `TreeMap` uses comparison.
* Use immutable map keys.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Add/update | `put(k, v)` |
| Read | `get(k)` |
| Default | `getOrDefault(k, d)` |
| Check key | `containsKey(k)` |
| Remove | `remove(k)` |
| Iterate | `entrySet()` |
| Group | `computeIfAbsent()` |
| Sorted map | `TreeMap` |

---

# Interview Questions with Answers

### 1. How does HashMap find a value?

It uses key hash code to find bucket and equals to match the key.

### 2. HashMap vs TreeMap?

HashMap is faster average lookup and unordered. TreeMap is sorted and O(log n).

### 3. Why should map keys be immutable?

Changing key state can change hash code and break lookup.

---

# Hands-on Exercises

### Exercise 1

Count word frequency.

**Answer**

```java
public static Map<String, Integer> frequency(List<String> words) {
    Map<String, Integer> map = new HashMap<>();
    for (String word : words) {
        map.put(word, map.getOrDefault(word, 0) + 1);
    }
    return map;
}
```

