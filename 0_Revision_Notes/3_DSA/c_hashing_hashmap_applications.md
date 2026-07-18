# Revision Notes

* Hashing is used for fast lookup.
* `HashMap` stores key-value pairs.
* `HashSet` stores unique values.
* `HashMap` uses `hashCode()` and `equals()`.
* Average lookup is O(1).
* Use `getOrDefault()` for frequency counts.
* Use `computeIfAbsent()` for grouping.
* Use immutable keys.
* Do not assume `HashMap` order.

---

# Cheat Sheet

| Need | Structure/API |
| ---- | ------------- |
| Frequency | `HashMap<T, Integer>` |
| Membership | `HashSet<T>` |
| Insertion order | `LinkedHashMap` |
| Sorted keys | `TreeMap` |
| Default count | `getOrDefault()` |
| Grouping | `computeIfAbsent()` |

---

# Interview Questions with Answers

### 1. Why is HashMap fast?

It uses hash code to locate a bucket directly, giving average O(1) access.

### 2. Why are mutable keys risky?

If the key's hash changes after insertion, lookup may fail.

### 3. HashMap vs HashSet?

HashMap stores key-value pairs. HashSet stores only unique values.

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

---

### Exercise 2

Find first duplicate number.

**Answer**

```java
public static int firstDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (!seen.add(num)) return num;
    }
    return -1;
}
```

