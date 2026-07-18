# Revision Notes

* Recursion means a method calls itself.
* Base case stops recursion.
* Recursive case reduces the problem.
* Each recursive call uses stack memory.
* Deep recursion can cause `StackOverflowError`.
* Memoization avoids repeated subproblem computation.
* Recursion is natural for trees, DFS, backtracking, and divide-and-conquer.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Base case | Stop condition |
| Recursive case | Self-call |
| Call stack | Active method frames |
| Stack overflow | Too many calls |
| Memoization | Cache results |
| Backtracking | Try, recurse, undo |

---

# Interview Questions with Answers

### 1. What are the two required parts of recursion?

Base case and recursive case.

### 2. Why can recursion be risky?

Too many recursive calls can overflow the stack.

### 3. When is recursion useful?

When the problem naturally breaks into smaller similar subproblems, such as trees and backtracking.

---

# Hands-on Exercises

### Exercise 1

Calculate factorial.

**Answer**

```java
public static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```

---

### Exercise 2

Print numbers from 1 to n.

**Answer**

```java
public static void printOneToN(int n) {
    if (n == 0) return;
    printOneToN(n - 1);
    System.out.println(n);
}
```

