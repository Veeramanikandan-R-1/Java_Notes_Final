# Revision Notes

* Linked list stores elements as nodes.
* Each node has data and next reference.
* Head points to first node.
* Insert at head is O(1).
* Search is O(n).
* Random access is O(n).
* Pointer handling is the main difficulty.
* Handle empty list and head deletion carefully.

---

# Cheat Sheet

| Operation | Complexity |
| --------- | ---------- |
| Add first | O(1) |
| Add last without tail | O(n) |
| Search | O(n) |
| Delete head | O(1) |
| Delete by value | O(n) |
| Reverse | O(n) |

---

# Interview Questions with Answers

### 1. Array vs linked list?

Array has fast random access. Linked list has pointer-based insertion but slow random access.

### 2. How do you detect a cycle?

Use slow and fast pointers. If they meet, there is a cycle.

### 3. Why use dummy node?

It simplifies edge cases, especially deletion at the head.

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

---

### Exercise 2

Detect cycle.

**Answer**

```java
public boolean hasCycle(Node head) {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }

    return false;
}
```

