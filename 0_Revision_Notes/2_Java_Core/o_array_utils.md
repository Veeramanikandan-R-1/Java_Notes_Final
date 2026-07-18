# Arrays Utility in Java (`java.util.Arrays`)

### Subtopics: `sort()`, `binarySearch()`, `fill()`, `copyOf()`, `equals()`

# Revision Notes

* `Arrays` is a utility class with static methods.
* `sort()` sorts arrays (primitive: Dual-Pivot Quicksort, objects: TimSort).
* `binarySearch()` requires a sorted array.
* `fill()` initializes or resets array values.
* `copyOf()` creates a new array without modifying the original.
* `equals()` compares contents, while `==` compares references.
* Prefer JDK utility methods over manual implementations.

---

# Cheat Sheet

| Method                  | Purpose             | Time Complexity |
| ----------------------- | ------------------- | --------------- |
| `Arrays.sort()`         | Sort array          | O(n log n)      |
| `Arrays.binarySearch()` | Search sorted array | O(log n)        |
| `Arrays.fill()`         | Fill values         | O(n)            |
| `Arrays.copyOf()`       | Copy array          | O(n)            |
| `Arrays.equals()`       | Compare contents    | O(n)            |

Examples

```java
Arrays.sort(arr);

Arrays.binarySearch(arr,10);

Arrays.fill(arr,5);

Arrays.copyOf(arr,arr.length);

Arrays.equals(arr1,arr2);
```

---

# Interview Questions with Answers

### 1. Why should `binarySearch()` only be used on sorted arrays?

**Answer:** Binary Search assumes the array is sorted so it can eliminate half of the search space each iteration. On an unsorted array, the result is undefined.

---

### 2. What is the difference between `Arrays.equals()` and `==`?

**Answer:**

* `==` compares object references.
* `Arrays.equals()` compares element values.

---

### 3. Does `Arrays.copyOf()` create a deep copy?

**Answer:** No. For primitive arrays it copies values. For object arrays it copies references (shallow copy).

---

### 4. Which sorting algorithm does `Arrays.sort()` use?

**Answer:**

* Primitive arrays: Dual-Pivot Quicksort.
* Object arrays: TimSort.

---

### 5. What does a negative result from `binarySearch()` mean?

**Answer:** The element was not found. The negative value also encodes the insertion point where the element would be inserted to maintain sorted order.

---

### 6. Why use `copyOf()` instead of assigning one array to another?

**Answer:** Assignment copies only the reference. `copyOf()` creates a separate array, preventing accidental modification of the original.

---

# Hands-on Exercises

## Exercise 1

Sort the following array.

```java
int[] arr = {9,4,7,2,1};
```

### Answer

```java
Arrays.sort(arr);

System.out.println(Arrays.toString(arr));
```

---

## Exercise 2

Search for `20`.

```java
int[] arr = {10,20,30,40,50};
```

### Answer

```java
int index = Arrays.binarySearch(arr,20);

System.out.println(index);
```

---

## Exercise 3

Fill an array of size 6 with `-1`.

### Answer

```java
int[] arr = new int[6];

Arrays.fill(arr,-1);
```

---

## Exercise 4

Create a copy of an array with two extra elements.

### Answer

```java
int[] arr = {1,2,3};

int[] copy = Arrays.copyOf(arr,5);

System.out.println(Arrays.toString(copy));
```

Output

```text
[1,2,3,0,0]
```

---

## Exercise 5

Compare two arrays.

```java
int[] a = {1,2,3};

int[] b = {1,2,3};
```

### Answer

```java
System.out.println(Arrays.equals(a,b));
```

---

## Exercise 6 (Production-style)

Implement a method that returns `true` if a sorted array contains a given employee ID.

### Answer

```java
import java.util.Arrays;

public class EmployeeSearch {

    public static boolean containsEmployeeId(int[] employeeIds, int id) {
        return Arrays.binarySearch(employeeIds, id) >= 0;
    }

    public static void main(String[] args) {
        int[] employeeIds = {101, 104, 108, 115, 120};

        System.out.println(containsEmployeeId(employeeIds, 108)); // true
        System.out.println(containsEmployeeId(employeeIds, 109)); // false
    }
}
```

This pattern is common in backend services when working with pre-sorted in-memory datasets for fast lookups.
