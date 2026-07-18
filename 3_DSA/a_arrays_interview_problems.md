# Arrays Interview Problems

### Subtopic: Interview Problems

---

# 1. Fundamentals

An array stores fixed-size elements in indexed order.

Interview problems on arrays usually test:

* Index handling
* Edge cases
* Time and space complexity
* Pattern recognition
* Clean implementation

Common patterns:

* Two pointers
* Sliding window
* Prefix sum
* Binary search
* Hashing
* Sorting

---

# 2. Core Concepts

## Two Pointers

Use when working from both ends or after sorting.

```java
public static boolean hasPairSum(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
        int sum = arr[left] + arr[right];
        if (sum == target) return true;
        if (sum < target) left++;
        else right--;
    }

    return false;
}
```

Works only when the array is sorted.

---

## Sliding Window

Use for contiguous subarray problems.

```java
public static int maxSumOfSizeK(int[] arr, int k) {
    if (arr == null || k <= 0 || arr.length < k) {
        throw new IllegalArgumentException("Invalid input");
    }

    int window = 0;
    for (int i = 0; i < k; i++) {
        window += arr[i];
    }

    int max = window;
    for (int i = k; i < arr.length; i++) {
        window += arr[i] - arr[i - k];
        max = Math.max(max, window);
    }

    return max;
}
```

Time: O(n)

---

## Prefix Sum

Use when repeated range sum queries are needed.

```java
public static int[] prefixSum(int[] arr) {
    int[] prefix = new int[arr.length];
    prefix[0] = arr[0];

    for (int i = 1; i < arr.length; i++) {
        prefix[i] = prefix[i - 1] + arr[i];
    }

    return prefix;
}
```

Range sum:

```java
public static int rangeSum(int[] prefix, int left, int right) {
    if (left == 0) return prefix[right];
    return prefix[right] - prefix[left - 1];
}
```

---

## Kadane's Algorithm

Find maximum subarray sum.

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

This works even with negative numbers.

---

## Binary Search

Use only when data is sorted.

```java
public static int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }

    return -1;
}
```

---

# 3. Internal Working

Arrays provide O(1) indexed access because memory location can be calculated using base address and index.

Most optimizations come from avoiding repeated scans.

Example:

* Brute force range sum: O(n) per query
* Prefix sum: O(1) per query after O(n) preprocessing

---

# 4. Common Mistakes

* Off-by-one loop errors.
* Not checking empty arrays.
* Using binary search on unsorted data.
* Forgetting duplicate values.
* Losing original indexes after sorting.
* Integer overflow in sum problems.
* Wrong window shrink/expand logic.

---

# 5. Best Practices

* Clarify constraints before coding.
* State brute force first.
* Choose pattern based on problem shape.
* Use `long` for large sums.
* Use `left + (right - left) / 2` for binary search.
* Dry run with edge cases.
* Explain complexity clearly.

---

# 6. Code Examples

## Two Sum

```java
public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> indexByValue = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int needed = target - nums[i];

        if (indexByValue.containsKey(needed)) {
            return new int[] {indexByValue.get(needed), i};
        }

        indexByValue.put(nums[i], i);
    }

    return new int[] {-1, -1};
}
```

---

## Move Zeroes

```java
public static void moveZeroes(int[] nums) {
    int insert = 0;

    for (int num : nums) {
        if (num != 0) {
            nums[insert++] = num;
        }
    }

    while (insert < nums.length) {
        nums[insert++] = 0;
    }
}
```

---

# 7. Real-world Scenarios

* Time-series metrics.
* Log processing windows.
* Ranking and sorting.
* Batch validation.
* Search over sorted IDs.
* Analytics range queries.

---

# Revision Notes

* Two pointers need sorted data or opposite-end scanning.
* Sliding window works on contiguous subarrays.
* Prefix sum optimizes range sum queries.
* Binary search requires sorted array.
* Kadane finds maximum subarray sum.
* Hashing optimizes lookup problems.
* Always handle empty, single-element, duplicate, and negative cases.

---

# Cheat Sheet

| Problem Type | Pattern |
| ------------ | ------- |
| Pair sum sorted | Two pointers |
| Pair sum unsorted | HashMap |
| Max fixed range | Sliding window |
| Repeated range sum | Prefix sum |
| Max subarray | Kadane |
| Sorted search | Binary search |

---

# Interview Questions with Answers

### 1. How do you approach array problems?

Start with brute force, identify repeated work, choose a pattern, then optimize.

### 2. Why use prefix sum?

To answer range sum queries in O(1) after O(n) preprocessing.

### 3. Why is binary search O(log n)?

Each step eliminates half of the search space.

---

# Hands-on Exercises

### Exercise 1

Find second largest distinct element.

**Answer**

```java
public static int secondLargest(int[] arr) {
    int largest = Integer.MIN_VALUE;
    int second = Integer.MIN_VALUE;

    for (int num : arr) {
        if (num > largest) {
            second = largest;
            largest = num;
        } else if (num > second && num != largest) {
            second = num;
        }
    }

    return second;
}
```

