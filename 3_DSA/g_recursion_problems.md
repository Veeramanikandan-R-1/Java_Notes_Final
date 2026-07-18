# Recursion

### Subtopic: Recursion Problems

---

# 1. Fundamentals

Recursion is a technique where a method calls itself to solve a smaller version of the same problem.

Every recursive solution needs:

* Base case
* Recursive case
* Progress toward base case

```java
public static int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```

---

# 2. Core Concepts

## Base Case

Stops recursion.

```java
if (n == 0) return 1;
```

Without base case, recursion becomes infinite.

## Recursive Case

Calls the same method with smaller input.

```java
return n * factorial(n - 1);
```

## Call Stack

Each recursive call creates a new stack frame.

Too many calls can cause `StackOverflowError`.

---

# 3. Important Recursion Problems

## Factorial

```java
public static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```

## Fibonacci

```java
public static int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

This simple version is inefficient because it recomputes subproblems.

## Sum of Array

```java
public static int sum(int[] arr, int index) {
    if (index == arr.length) return 0;
    return arr[index] + sum(arr, index + 1);
}
```

## Reverse String

```java
public static String reverse(String s) {
    if (s == null || s.length() <= 1) return s;
    return reverse(s.substring(1)) + s.charAt(0);
}
```

For production, prefer iterative/StringBuilder for large strings.

## Binary Tree Depth

```java
public static int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

---

# 4. Internal Working

Recursion uses stack frames.

For `factorial(3)`:

```text
factorial(3)
factorial(2)
factorial(1)
```

Then returns unwind:

```text
1 -> 2 -> 6
```

The cost depends on number of calls and work per call.

---

# 5. Common Mistakes

* Missing base case.
* Base case never reached.
* Too many duplicate calls.
* Stack overflow for deep recursion.
* Not reducing input correctly.
* Using substring repeatedly and creating many strings.

---

# 6. Best Practices

* Define base case first.
* Ensure input moves toward base case.
* Draw recursion tree for complex problems.
* Use memoization for repeated subproblems.
* Prefer iteration for very deep recursion.
* Track time and space complexity.

---

# 7. Real-world Scenarios

* Tree traversal.
* Graph DFS.
* Backtracking.
* Parsing nested structures.
* Divide-and-conquer algorithms.
* Dynamic programming with memoization.

---

# Revision Notes

* Recursion means method calls itself.
* Base case stops recursion.
* Recursive case reduces problem.
* Each call uses stack memory.
* Deep recursion can cause `StackOverflowError`.
* Memoization avoids repeated work.
* Recursion is natural for trees and backtracking.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Base case | Stop condition |
| Recursive case | Self call |
| Call stack | Stores active calls |
| Stack overflow | Too many calls |
| Memoization | Cache repeated results |
| Backtracking | Try, recurse, undo |

---

# Interview Questions with Answers

### 1. What is recursion?

A method solving a problem by calling itself on smaller input.

### 2. Why base case is important?

It stops infinite recursion.

### 3. Recursion vs iteration?

Recursion is cleaner for hierarchical problems. Iteration is often safer for very deep loops.

---

# Hands-on Exercises

### Exercise 1

Print numbers from 1 to n.

**Answer**

```java
public static void printOneToN(int n) {
    if (n == 0) return;
    printOneToN(n - 1);
    System.out.println(n);
}
```

