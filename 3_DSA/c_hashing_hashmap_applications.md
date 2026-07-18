# Hashing in DSA

### Subtopic: HashMap Applications

---

# 1. Fundamentals

Hashing maps data to a hash code so lookup can be fast.

In Java, common hash-based structures are:

* `HashMap`
* `HashSet`
* `LinkedHashMap`
* `LinkedHashSet`

For interviews, hashing is mostly used to avoid nested loops.

---

# 2. Core Concepts

## HashMap for Lookup

```java
Map<Integer, Integer> indexByValue = new HashMap<>();
```

Use when you need to quickly answer:

* Have I seen this before?
* What is the count?
* What is the index?
* Which group does this belong to?

---

## Frequency Map

```java
Map<String, Integer> freq = new HashMap<>();

for (String word : words) {
    freq.put(word, freq.getOrDefault(word, 0) + 1);
}
```

---

## Grouping

```java
Map<String, List<String>> byFirstLetter = new HashMap<>();

for (String name : names) {
    String key = name.substring(0, 1);
    byFirstLetter.computeIfAbsent(key, k -> new ArrayList<>()).add(name);
}
```

---

## Deduplication

```java
Set<Integer> seen = new HashSet<>();

for (int num : nums) {
    if (!seen.add(num)) {
        System.out.println("Duplicate: " + num);
    }
}
```

---

# 3. Internal Working

`HashMap` uses:

1. `hashCode()` to find bucket.
2. `equals()` to compare keys in bucket.

Average lookup is O(1), but poor hashing or many collisions can degrade performance.

For custom objects as keys, correct `equals()` and `hashCode()` are mandatory.

---

# 4. Common Mistakes

* Using mutable keys.
* Forgetting `equals()` and `hashCode()`.
* Assuming `HashMap` keeps order.
* Using `containsValue()` frequently.
* Not handling missing keys.
* Overusing maps when simple array frequency is enough.

---

# 5. Best Practices

* Use `HashMap` for fast lookup.
* Use `LinkedHashMap` when insertion order matters.
* Use `TreeMap` when sorted keys are required.
* Use arrays for small fixed character ranges.
* Use immutable keys.
* Use `getOrDefault()` for counters.
* Use `computeIfAbsent()` for grouping.

---

# 6. Code Examples

## Two Sum

```java
public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int need = target - nums[i];

        if (map.containsKey(need)) {
            return new int[] {map.get(need), i};
        }

        map.put(nums[i], i);
    }

    return new int[] {-1, -1};
}
```

---

## First Duplicate

```java
public static int firstDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
        if (!seen.add(num)) {
            return num;
        }
    }

    return -1;
}
```

---

# 7. Real-world Scenarios

* Caches.
* Deduplication.
* Counting events.
* Grouping records.
* Fast ID lookup.
* Detecting repeated requests.

---

# Revision Notes

* Hashing gives fast lookup.
* `HashMap` stores key-value pairs.
* `HashSet` stores unique values.
* `HashMap` uses `hashCode()` and `equals()`.
* Use `getOrDefault()` for frequency.
* Use `computeIfAbsent()` for grouping.
* Use immutable keys.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Frequency count | `HashMap<T, Integer>` |
| Deduplication | `HashSet<T>` |
| Preserve order | `LinkedHashMap` |
| Sorted keys | `TreeMap` |
| Grouping | `computeIfAbsent()` |
| Fast lookup | `containsKey()` |

---

# Interview Questions with Answers

### 1. Why is HashMap average O(1)?

It uses hash code to directly locate the bucket.

### 2. Why are mutable keys dangerous?

If key hash changes after insertion, lookup may fail.

### 3. When use HashSet?

When you only need uniqueness or membership checking.

---

# Hands-on Exercises

### Exercise 1

Count frequency of integers.

**Answer**

```java
public static Map<Integer, Integer> frequency(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    return map;
}
```

