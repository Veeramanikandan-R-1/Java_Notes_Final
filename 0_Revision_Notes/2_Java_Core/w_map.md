# Revision Notes

* `Map` stores key-value pairs.
* Keys are unique.
* Values can be duplicate.
* `HashMap` is fast and unordered.
* `LinkedHashMap` preserves insertion order.
* `TreeMap` keeps keys sorted.
* `HashMap` depends on `hashCode()` and `equals()`.
* Use immutable keys.
* Use `entrySet()` when key and value are both needed.

---

# Cheat Sheet

| Operation | Code |
| --------- | ---- |
| Put | `map.put(k, v)` |
| Get | `map.get(k)` |
| Default | `map.getOrDefault(k, d)` |
| Check | `map.containsKey(k)` |
| Remove | `map.remove(k)` |
| Keys | `map.keySet()` |
| Values | `map.values()` |
| Entries | `map.entrySet()` |
| Group | `computeIfAbsent()` |

---

# Interview Questions with Answers

### 1. HashMap vs TreeMap?

HashMap is unordered and average O(1). TreeMap is sorted and O(log n).

### 2. Can HashMap have null key?

Yes, one null key is allowed.

### 3. Why are mutable keys dangerous?

If key hash changes after insertion, lookup can fail.

---

# Hands-on Exercises

### Exercise 1

Group names by first character.

**Answer**

```java
Map<Character, List<String>> grouped = new HashMap<>();
for (String name : names) {
    grouped.computeIfAbsent(name.charAt(0), k -> new ArrayList<>()).add(name);
}
```

