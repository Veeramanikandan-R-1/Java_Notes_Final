# Revision Notes

* Stack follows LIFO.
* Queue follows FIFO.
* Use `ArrayDeque` instead of legacy `Stack`.
* Stack operations: `push`, `pop`, `peek`.
* Queue operations: `offer`, `poll`, `peek`.
* Circular queue avoids shifting elements.
* Handle overflow and underflow in manual implementations.
* Use `BlockingQueue` for producer-consumer concurrency.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Stack add | `push()` |
| Stack remove | `pop()` |
| Read top/front | `peek()` |
| Queue add | `offer()` |
| Queue remove | `poll()` |
| Preferred implementation | `ArrayDeque` |

---

# Interview Questions with Answers

### 1. Stack vs Queue?

Stack is Last In First Out. Queue is First In First Out.

### 2. Why use circular queue?

It reuses array space after removals without shifting all elements.

### 3. Why prefer ArrayDeque?

It is faster and cleaner than legacy `Stack` for normal single-threaded stack/queue use.

---

# Hands-on Exercises

### Exercise 1

Validate parentheses.

**Answer**

```java
public static boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char ch : s.toCharArray()) {
        if (ch == '(') {
            stack.push(ch);
        } else if (ch == ')') {
            if (stack.isEmpty()) return false;
            stack.pop();
        }
    }

    return stack.isEmpty();
}
```

