# Linked List

### Subtopic: Implementation

---

# 1. Fundamentals

A linked list stores elements as nodes connected by references.

Each node contains:

* Data
* Reference to next node

```java
class Node {
    int data;
    Node next;
}
```

Unlike arrays, linked lists do not need contiguous memory.

---

# 2. Core Concepts

## Node

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

## Head

`head` points to the first node.

```java
private Node head;
```

## Insert at Beginning

```java
public void addFirst(int value) {
    Node node = new Node(value);
    node.next = head;
    head = node;
}
```

## Insert at End

```java
public void addLast(int value) {
    Node node = new Node(value);

    if (head == null) {
        head = node;
        return;
    }

    Node current = head;
    while (current.next != null) {
        current = current.next;
    }

    current.next = node;
}
```

## Delete by Value

```java
public void delete(int value) {
    if (head == null) return;

    if (head.data == value) {
        head = head.next;
        return;
    }

    Node current = head;
    while (current.next != null && current.next.data != value) {
        current = current.next;
    }

    if (current.next != null) {
        current.next = current.next.next;
    }
}
```

---

# 3. Internal Working

Linked list traversal is O(n) because nodes must be followed one by one.

Insertion at head is O(1).

Insertion at tail is O(n) unless a tail reference is maintained.

Random access is O(n), unlike arrays.

---

# 4. Common Mistakes

* Losing reference while deleting.
* Not handling empty list.
* Not handling head deletion.
* Infinite loops due to wrong pointer update.
* Forgetting to move current pointer.
* Not detecting cycles in interview problems.

---

# 5. Best Practices

* Handle empty list first.
* Handle head separately for delete.
* Use dummy node for complex deletion.
* Draw pointer movement before coding.
* Keep node manipulation simple.
* Use slow/fast pointer for cycle problems.

---

# 6. Code Example

```java
class SinglyLinkedList {
    private Node head;

    private static class Node {
        private final int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    void addFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) return true;
            current = current.next;
        }
        return false;
    }
}
```

---

# 7. Real-world Scenarios

* Implementing queues.
* LRU cache internals with doubly linked list.
* Undo/redo chains.
* Memory-efficient insertions.
* Interview pointer problems.

---

# Revision Notes

* Linked list contains nodes.
* Each node stores data and next reference.
* Head points to first node.
* Insert at head is O(1).
* Search is O(n).
* Random access is O(n).
* Pointer handling is the main challenge.

---

# Cheat Sheet

| Operation | Complexity |
| --------- | ---------- |
| Add first | O(1) |
| Add last without tail | O(n) |
| Search | O(n) |
| Delete head | O(1) |
| Delete by value | O(n) |
| Random access | O(n) |

---

# Interview Questions with Answers

### 1. Array vs linked list?

Array has O(1) random access. Linked list has easier pointer-based insertion but O(n) access.

### 2. How to detect cycle?

Use slow and fast pointers. If they meet, cycle exists.

### 3. Why use dummy node?

It simplifies edge cases, especially deletion near head.

---

# Hands-on Exercises

### Exercise 1

Reverse linked list.

**Answer**

```java
public Node reverse(Node head) {
    Node prev = null;
    Node current = head;

    while (current != null) {
        Node next = current.next;
        current.next = prev;
        prev = current;
        current = next;
    }

    return prev;
}
```

