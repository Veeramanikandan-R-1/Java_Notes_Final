# Stack in DSA — Java (Own Implementation)

## 1. What is a Stack?

A Stack is a linear data structure that follows LIFO (Last In, First Out).

The last element inserted is the first element removed.

Real-world example: A stack of plates. You place a plate on top and remove the top plate first.

Main operations:

- `push()` — Add an element to the top.
- `pop()` — Remove and return the top element.
- `peek()` — Return the top element without removing it.
- `isEmpty()` — Check whether the stack is empty.
- `size()` — Return the number of elements.

## 2. How a Stack Works

Consider these operations:

```
push(10)
push(20)
push(30)
```

Stack after insertion:

```
   TOP
  +----+
  | 30 |
  +----+
  | 20 |
  +----+
  | 10 |
  +----+
```

Now perform `pop()`.

```
   TOP
  +----+
  | 20 |
  +----+
  | 10 |
  +----+
```

The value `30` is removed because it was inserted last.

## 3. Implement a Stack from Scratch Using an Array

We will implement our own stack without using Java's built-in `Stack` or `Deque`.

```
import java.util.Arrays;

public class Main {

    static class Stack {
        private int[] arr;
        private int top;

        Stack(int capacity) {
            arr = new int[capacity];
            top = -1;
        }

        // Add an element: O(1)
        public void push(int value) {
            if (top == arr.length - 1) {
                throw new IllegalStateException("Stack overflow");
            }

            arr[++top] = value;
        }

        // Remove and return the top element: O(1)
        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }

            return arr[top--];
        }

        // Return the top element without removing it: O(1)
        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }

            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }

        public void display() {
            System.out.println(
                Arrays.toString(Arrays.copyOfRange(arr, 0, top + 1))
            );
        }
    }

    public static void main(String[] args) {

        Stack stack = new Stack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.display();

        System.out.println("Top: " + stack.peek());
        System.out.println("Removed: " + stack.pop());
        System.out.println("Top after pop: " + stack.peek());
        System.out.println("Size: " + stack.size());
        System.out.println("Empty: " + stack.isEmpty());

        stack.display();
    }
}
```

Output:

```
[10, 20, 30]
Top: 30
Removed: 30
Top after pop: 20
Size: 2
Empty: false
[10, 20]
```

## 4. Understand the Important Logic

### A. Why initialize `top = -1`?

```
int top = -1;
```

`top` stores the index of the top element.

- `top = -1` → Stack is empty.
- `top = 0` → One element exists.
- `top = 2` → Three elements exist.

### B. How does `push()` work?

```
arr[++top] = value;
```

`++top` increments `top` first, then inserts the value.

For example, if `top = -1`, pushing `10` makes `top = 0` and stores `10` at `arr[0]`.

### C. How does `pop()` work?

```
return arr[top--];
```

Returns the current top element, then decrements `top`.

If `top = 2`, it returns `arr[2]` and changes `top` to `1`.

The old value remains physically in the array, but it is no longer part of the logical stack.

### D. Stack Overflow vs Stack Underflow

- Stack overflow: Trying to push an element when the fixed-capacity stack is full.
- Stack underflow: Trying to pop an element when the stack is empty.

Our implementation checks for both conditions.

## 5. Time Complexity

| Operation   | Time Complexity |
| ----------- | --------------- |
| `push()`    | O(1)            |
| `pop()`     | O(1)            |
| `peek()`    | O(1)            |
| `isEmpty()` | O(1)            |
| `size()`    | O(1)            |
| `display()` | O(n)            |

The array implementation has O(n) space complexity, where `n` is the stack's fixed capacity.

## 6. Where Is a Stack Used in DSA?

1. Function calls and recursion — Java uses a call stack to manage method calls.
2. Undo/Redo — Editors track recent actions.
3. Balanced parentheses — Check expressions such as `{[()]}`.
4. String reversal — The last character pushed is the first character popped.
5. Depth-First Search (DFS) — Used to explore graphs and trees.
6. Monotonic stack problems — Used for next greater element and similar array problems.

## 7. Stack vs Linked List

| Feature                  | Array-based Stack                     | Linked List-based Stack                               |
| ------------------------ | ------------------------------------- | ----------------------------------------------------- |
| Storage                  | Fixed capacity in this implementation | Grows as nodes are added, subject to available memory |
| Push at top              | O(1)                                  | O(1)                                                  |
| Pop from top             | O(1)                                  | O(1)                                                  |
| Extra memory per element | No node reference                     | Node reference(s)                                     |
| Overflow                 | When array is full                    | When memory allocation fails                          |

Interview tip: Understand why `top` starts at `-1`, why `push()` uses `++top`, and why `pop()` uses `top--`. These three details form the core of an array-based stack implementation.