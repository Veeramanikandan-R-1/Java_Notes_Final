# Revision Notes

* Arrays provide O(1) indexed access.
* Most array interview problems are about avoiding repeated scans.
* Two pointers are useful for sorted arrays.
* Sliding window is useful for contiguous subarray problems.
* Prefix sum answers repeated range sum queries quickly.
* Kadane's algorithm finds maximum subarray sum.
* Binary search requires sorted input.
* HashMap is useful for fast lookup problems like Two Sum.

---

# Cheat Sheet

| Problem Type | Pattern |
| ------------ | ------- |
| Pair sum sorted | Two pointers |
| Pair sum unsorted | HashMap |
| Fixed window sum | Sliding window |
| Range sum queries | Prefix sum |
| Maximum subarray | Kadane |
| Search sorted array | Binary search |
| Remove zeroes/duplicates | Two pointers |

---

# Interview Questions with Answers

### 1. How do you optimize Two Sum?

Use `HashMap` to store seen values and indexes, reducing O(n²) to O(n).

### 2. Why use prefix sum?

It converts repeated range sum queries from O(n) each to O(1) each after preprocessing.

### 3. What are common edge cases?

Empty array, one element, duplicates, negative numbers, sorted input, and overflow.

---

# Hands-on Exercises

### Exercise 1

Move zeroes to the end while preserving order.

**Answer**

```java
public static void moveZeroes(int[] nums) {
    int insert = 0;
    for (int num : nums) {
        if (num != 0) nums[insert++] = num;
    }
    while (insert < nums.length) {
        nums[insert++] = 0;
    }
}
```

---

### Exercise 2

Find maximum subarray sum.

**Answer**

```java
public static int maxSubarraySum(int[] arr) {
    int current = arr[0];
    int best = arr[0];

    for (int i = 1; i < arr.length; i++) {
        current = Math.max(arr[i], current + arr[i]);
        best = Math.max(best, current);
    }

    return best;
}
```

