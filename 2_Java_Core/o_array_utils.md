# Arrays Utility in Java (`java.util.Arrays`)

### Subtopics: `sort()`, `binarySearch()`, `fill()`, `copyOf()`, `equals()`

---

# 1. Fundamentals

## What is `java.util.Arrays`?

`Arrays` is a **utility class** in the `java.util` package that provides static methods to perform common operations on arrays.

Without `Arrays`, developers would have to manually implement operations such as:

* Sorting
* Searching
* Copying
* Comparing
* Filling

Example:

```java
import java.util.Arrays;

int[] numbers = {5, 2, 8, 1};

Arrays.sort(numbers);
```

---

## Why is it needed?

Imagine sorting an array manually.

```java
// Bubble Sort (simplified)
for (int i = 0; i < arr.length - 1; i++) {
    ...
}
```

Instead:

```java
Arrays.sort(arr);
```

Benefits:

* Optimized implementation
* Less code
* Better readability
* Well-tested JDK implementation

---

## Key Methods

| Method           | Purpose                              |
| ---------------- | ------------------------------------ |
| `sort()`         | Sort elements                        |
| `binarySearch()` | Search efficiently in a sorted array |
| `fill()`         | Fill array with same value           |
| `copyOf()`       | Create resized copy                  |
| `equals()`       | Compare array contents               |

---

# 2. Core Concepts

---

# `Arrays.sort()`

## What?

Sorts an array in ascending order.

```java
int[] arr = {4, 1, 8, 2};

Arrays.sort(arr);

System.out.println(Arrays.toString(arr));
```

Output

```text
[1, 2, 4, 8]
```

---

## Why?

Sorting enables:

* Fast searching
* Ordered processing
* Ranking
* Report generation

---

## Object Arrays

```java
String[] names = {"John", "Adam", "Chris"};

Arrays.sort(names);
```

Output

```text
[Adam, Chris, John]
```

Objects are sorted using their **natural ordering** (`Comparable`).

---

## Custom Sorting

```java
Integer[] numbers = {5, 2, 8};

Arrays.sort(numbers, Collections.reverseOrder());
```

Output

```text
[8, 5, 2]
```

Works only with object arrays (`Integer[]`), not primitive arrays (`int[]`).

---

# `Arrays.binarySearch()`

## What?

Searches an element using **Binary Search**.

```java
int[] arr = {1,2,3,4,5};

int index = Arrays.binarySearch(arr, 4);

System.out.println(index);
```

Output

```text
3
```

---

## Why?

Binary Search is much faster than linear search.

| Search | Time Complexity |
| ------ | --------------- |
| Linear | O(n)            |
| Binary | O(log n)        |

---

## Important Rule

The array **must already be sorted**.

Correct

```java
Arrays.sort(arr);

Arrays.binarySearch(arr, 10);
```

Wrong

```java
int[] arr = {5,3,8};

Arrays.binarySearch(arr,3);
```

Result is **undefined**.

---

## Element Not Found

```java
int index = Arrays.binarySearch(arr, 100);
```

Output

```text
-6
```

Negative value means "not found".

---

# `Arrays.fill()`

## What?

Fills every element with the same value.

```java
int[] arr = new int[5];

Arrays.fill(arr, 10);
```

Output

```text
[10,10,10,10,10]
```

---

## Fill Partial Array

```java
Arrays.fill(arr,1,4,99);
```

Output

```text
[10,99,99,99,10]
```

Index 4 is excluded.

---

## Why?

Useful for:

* Initialization
* Resetting buffers
* Default values

---

# `Arrays.copyOf()`

## What?

Creates a new array.

```java
int[] arr = {1,2,3};

int[] copy = Arrays.copyOf(arr,3);
```

---

## Increase Size

```java
int[] bigger = Arrays.copyOf(arr,5);
```

Output

```text
[1,2,3,0,0]
```

Extra elements receive default values.

---

## Reduce Size

```java
int[] smaller = Arrays.copyOf(arr,2);
```

Output

```text
[1,2]
```

---

## Why?

Avoids modifying the original array.

---

# `Arrays.equals()`

## What?

Checks whether two arrays contain the same elements in the same order.

```java
int[] a = {1,2,3};

int[] b = {1,2,3};

System.out.println(Arrays.equals(a,b));
```

Output

```text
true
```

---

## Why not `==`?

```java
int[] a = {1,2};

int[] b = {1,2};

System.out.println(a==b);
```

Output

```text
false
```

Because `==` compares references.

---

# 3. Internal Working

## `sort()`

For primitive arrays:

* Uses **Dual-Pivot Quicksort**
* Average Time: **O(n log n)**

For object arrays:

* Uses **TimSort**
* Stable sort
* Optimized for partially sorted data

---

## `binarySearch()`

Algorithm:

```text
Middle

↓

Target smaller?

↓

Search Left

↓

Otherwise

↓

Search Right
```

Each step halves the search space.

Time:

```text
O(log n)
```

---

## `copyOf()`

Internally creates a new array and copies elements (using efficient JVM mechanisms such as `System.arraycopy()`).

Original array remains unchanged.

---

## `equals()`

Internally:

```text
Length equal?

↓

Compare every element

↓

All equal?

↓

true
```

Time Complexity

```text
O(n)
```

---

# 4. Common Mistakes

## Mistake 1

Using `binarySearch()` on an unsorted array.

Wrong

```java
int[] arr={5,2,8};

Arrays.binarySearch(arr,2);
```

Always sort first.

---

## Mistake 2

Using `==`

Wrong

```java
arr1==arr2
```

Correct

```java
Arrays.equals(arr1,arr2)
```

---

## Mistake 3

Assuming `copyOf()` modifies original.

```java
copy[0]=100;
```

Original array remains unchanged.

---

## Mistake 4

Using `reverseOrder()` with primitive arrays.

Wrong

```java
int[]
```

Correct

```java
Integer[]
```

---

## Mistake 5

Ignoring negative result of `binarySearch()`.

Always check:

```java
if(index>=0)
```

---

# 5. Best Practices

✅ Use `Arrays.sort()` instead of writing custom sorting logic.

✅ Always sort before `binarySearch()`.

✅ Use `Arrays.equals()` instead of `==`.

✅ Use `copyOf()` when immutability or defensive copying is required.

✅ Use `fill()` for fast initialization.

---

# 6. Code Example

```java
import java.util.Arrays;

public class ArraysUtilityDemo {

    public static void main(String[] args) {

        int[] arr = {5,2,8,1};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

        int index = Arrays.binarySearch(arr,5);

        System.out.println(index);

        int[] copy = Arrays.copyOf(arr,6);

        System.out.println(Arrays.toString(copy));

        Arrays.fill(copy,4,6,100);

        System.out.println(Arrays.toString(copy));

        System.out.println(Arrays.equals(arr,copy));
    }
}
```

Output

```text
[1, 2, 5, 8]
2
[1, 2, 5, 8, 0, 0]
[1, 2, 5, 8, 100, 100]
false
```

---

# 7. Real-world Scenarios

## Scenario 1: Ranking Scores

```java
Arrays.sort(scores);
```

Used in leaderboards and reports.

---

## Scenario 2: Searching Product IDs

```java
Arrays.binarySearch(ids,targetId);
```

Efficient lookup in sorted datasets.

---

## Scenario 3: Reset Buffer

```java
Arrays.fill(buffer,0);
```

Common in caching and reusable buffers.

---

## Scenario 4: Defensive Copy

```java
this.data = Arrays.copyOf(input,input.length);
```

Prevents external code from modifying internal state.

---

## Scenario 5: Testing

```java
assertTrue(Arrays.equals(expected,actual));
```

Frequently used in JUnit tests.

---

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
