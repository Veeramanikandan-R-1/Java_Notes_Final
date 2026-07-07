# 7. Revision Notes

* Array = fixed-size contiguous memory
* Access O(1), insertion/deletion O(n)
* Prefix sum & sliding window = optimization patterns
* Binary search requires sorted array
* Use arrays for performance-critical code

---

# 8. Cheat Sheet

```
Access        → O(1)
Insert/Delete → O(n)
Search        → O(n) / O(log n if sorted)

Key Patterns:
- Prefix Sum
- Sliding Window
- Two Pointer
- Kadane

Important APIs:
- Arrays.sort()
- System.arraycopy()
```

---

# 9. Interview Questions (with Answers)

### Q1: Why arrays are faster than ArrayList?

**Answer:**
Arrays avoid resizing, boxing/unboxing, and have contiguous memory → better cache locality.

---

### Q2: When would you NOT use arrays?

**Answer:**
When size is dynamic → use `ArrayList`.

---

### Q3: Difference between Array and ArrayList?

| Feature     | Array             | ArrayList       |
| ----------- | ----------------- | --------------- |
| Size        | Fixed             | Dynamic         |
| Performance | Faster            | Slight overhead |
| Type        | Primitive support | Objects only    |

---

### Q4: How does JVM store arrays?

**Answer:**
Arrays are objects in heap with length metadata + contiguous element storage.

---

### Q5: Optimize range sum queries?

**Answer:**
Use prefix sum array → O(1) queries.

---

# 10. Hands-on Exercises (with Answers)

---

### Problem 1: Reverse an Array

```java
public static void reverse(int[] arr) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
        l++; r--;
    }
}
```

---

### Problem 2: Find Maximum Subarray Sum

Step 1: What is the problem asking?

Given an array of integers, find the contiguous subarray that has the largest sum.

Example
int[] arr = {2, 3, -8, 7, -1, 2, 3};

The question is:

"Among all possible contiguous subarrays, which one has the maximum sum?"

---

### Problem 3: Rotate Array by K

```java
public static void rotate(int[] arr, int k) {
    k = k % arr.length;
    reverse(arr, 0, arr.length - 1);
    reverse(arr, 0, k - 1);
    reverse(arr, k, arr.length - 1);
}
```

---