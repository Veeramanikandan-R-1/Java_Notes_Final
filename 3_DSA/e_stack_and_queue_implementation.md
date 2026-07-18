# Stack and Queue

### Subtopic: Implementation

---

# 1. Fundamentals

A stack follows LIFO: Last In, First Out.

A queue follows FIFO: First In, First Out.

```text
Stack: push -> pop from same end
Queue: offer at rear -> poll from front
```

---

# 2. Core Concepts

## Stack APIs

Use `ArrayDeque` instead of legacy `Stack`.

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(10);
stack.push(20);
stack.pop();
stack.peek();
```

## Queue APIs

```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(10);
queue.offer(20);
queue.poll();
queue.peek();
```

## Stack Implementation Using Array

```java
class IntStack {
    private final int[] arr;
    private int top = -1;

    IntStack(int capacity) {
        arr = new int[capacity];
    }

    void push(int value) {
        if (top == arr.length - 1) throw new IllegalStateException("Full");
        arr[++top] = value;
    }

    int pop() {
        if (top == -1) throw new IllegalStateException("Empty");
        return arr[top--];
    }
}
```

## Queue Implementation Using Circular Array

```java
class IntQueue {
    private final int[] arr;
    private int front;
    private int size;

    IntQueue(int capacity) {
        arr = new int[capacity];
    }

    void offer(int value) {
        if (size == arr.length) throw new IllegalStateException("Full");
        int rear = (front + size) % arr.length;
        arr[rear] = value;
        size++;
    }

    int poll() {
        if (size == 0) throw new IllegalStateException("Empty");
        int value = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return value;
    }
}
```

---

# 3. Internal Working

Stack needs one pointer: `top`.

Queue needs front and rear movement. Circular array avoids shifting elements after every poll.

`ArrayDeque` internally uses a resizable circular array and is preferred for most stack/queue needs.

---

# 4. Common Mistakes

* Using old synchronized `Stack` by default.
* Removing queue elements by shifting array.
* Not handling overflow/underflow.
* Confusing `remove()` with `poll()`.
* Confusing `element()` with `peek()`.
* Breaking circular index logic.

---

# 5. Best Practices

* Prefer `ArrayDeque`.
* Use `push/pop/peek` for stack style.
* Use `offer/poll/peek` for queue style.
* Use circular array for manual queue implementation.
* Validate empty/full states.
* Use `BlockingQueue` for producer-consumer concurrency.

---

# 6. Code Examples

## Valid Parentheses

```java
public static boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char ch : s.toCharArray()) {
        if (ch == '(' || ch == '[' || ch == '{') {
            stack.push(ch);
        } else {
            if (stack.isEmpty()) return false;
            char open = stack.pop();
            if (ch == ')' && open != '(') return false;
            if (ch == ']' && open != '[') return false;
            if (ch == '}' && open != '{') return false;
        }
    }

    return stack.isEmpty();
}
```

---

# 7. Real-world Scenarios

* Stack for undo operations.
* Stack for expression parsing.
* Queue for job processing.
* Queue for BFS.
* Blocking queue for producer-consumer systems.

---

# Revision Notes

* Stack is LIFO.
* Queue is FIFO.
* Use `ArrayDeque` for normal stack/queue.
* Stack tracks top.
* Queue tracks front/rear or front/size.
* Circular queue avoids shifting.
* Handle underflow and overflow.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Stack push | `push()` |
| Stack remove | `pop()` |
| Stack read | `peek()` |
| Queue add | `offer()` |
| Queue remove | `poll()` |
| Queue read | `peek()` |
| Preferred class | `ArrayDeque` |

---

# Interview Questions with Answers

### 1. Stack vs Queue?

Stack is Last In First Out. Queue is First In First Out.

### 2. Why use circular queue?

It reuses freed array space without shifting elements.

### 3. Why prefer ArrayDeque over Stack?

`Stack` is legacy and synchronized. `ArrayDeque` is faster for local stack/queue use.

---

# Hands-on Exercises

### Exercise 1

Implement min stack idea.

**Answer**

Maintain two stacks: one for values and one for current minimum values.

