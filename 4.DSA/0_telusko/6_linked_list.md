# Linked List in DSA (Java — Own Implementation)

## 1. What is a Linked List?

A Linked List is a linear data structure where elements are stored in separate objects called nodes. Each node contains:

- `data` — the value stored in the node.
- `next` — a reference to the next node.

Unlike an array, linked list nodes do not need to be stored in contiguous memory locations.

Example:

```
head
 |
 v
[10 | next] -> [20 | next] -> [30 | null]
                                  ^
                               Last node
```

Here, `head` points to the first node, and the last node points to `null`.

## 2. Why Do We Need a Linked List?

| Feature             | Array                              | Linked List                           |
| ------------------- | ---------------------------------- | ------------------------------------- |
| Memory layout       | Contiguous elements                | Nodes can be scattered in memory      |
| Access by index     | O(1)                               | O(n)                                  |
| Insert at beginning | O(n) for shifting                  | O(1)                                  |
| Append at end       | O(1) amortized for a dynamic array | O(n) without a tail; O(1) with a tail |
| Extra memory        | Usually less                       | Extra reference per node              |

Important: Linked lists are useful when frequent insertions and deletions are needed, especially when the node position is already known. They are not always faster than arrays.

## 3. Implement a Singly Linked List from Scratch

We will implement our own linked list without using Java's built-in `LinkedList` class.

The implementation supports:

- Insert at the beginning
- Insert at the end
- Delete by value
- Search for a value
- Display all elements

### Complete working Java code

```
public class Main {

    // Represents one node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        private Node head;

        // Insert at the beginning: O(1)
        public void insertAtBeginning(int data) {
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }

        // Insert at the end: O(n)
        public void insertAtEnd(int data) {
            Node newNode = new Node(data);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        // Search for a value: O(n)
        public boolean search(int value) {
            Node current = head;

            while (current != null) {
                if (current.data == value) {
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        // Delete the first occurrence of a value: O(n)
        public void delete(int value) {

            if (head == null) {
                return;
            }

            // If the head node contains the value
            if (head.data == value) {
                head = head.next;
                return;
            }

            Node current = head;

            while (current.next != null
                    && current.next.data != value) {
                current = current.next;
            }

            // Value found
            if (current.next != null) {
                current.next = current.next.next;
            }
        }

        // Display the list: O(n)
        public void display() {
            Node current = head;

            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }

            System.out.println("null");
        }
    }

    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);

        list.display();

        list.insertAtBeginning(5);
        list.display();

        System.out.println("Search 20: " + list.search(20));
        System.out.println("Search 99: " + list.search(99));

        list.delete(20);
        list.display();

        list.delete(5);
        list.display();
    }
}
```

Output:

```
10 -> 20 -> 30 -> null
5 -> 10 -> 20 -> 30 -> null
Search 20: true
Search 99: false
5 -> 10 -> 30 -> null
10 -> 30 -> null
```

## 4. Understand the Important Operations

### A. Insertion at the beginning

```
Node newNode = new Node(data);
newNode.next = head;
head = newNode;
```

Suppose the list is:

```
head -> 10 -> 20 -> null
```

Insert `5`:

1. Create a new node containing `5`.
2. Make its `next` point to the current head (`10`).
3. Update `head` to point to `5`.

Result:

```
head -> 5 -> 10 -> 20 -> null
```

Time complexity: O(1).

### B. Insertion at the end

```
Node current = head;

while (current.next != null) {
    current = current.next;
}

current.next = newNode;
```

Traverse until the last node, then connect it to the new node.

Time complexity: O(n) because we traverse the list. A maintained `tail` reference would make appending O(1).

### C. Deletion

```
current.next = current.next.next;
```

Suppose the list is:

```
10 -> 20 -> 30 -> null
```

To delete `20`, update the `next` reference of node `10` to point to node `30`.

Result:

```
10 -> 30 -> null
```

The node containing `20` is no longer reachable from the head.

Important: Deleting the head requires a separate case because there is no previous node.

### D. Traversal

```
Node current = head;

while (current != null) {
    System.out.println(current.data);
    current = current.next;
}
```

Start at `head` and follow `next` references until reaching `null`.

Time complexity: O(n).

## 5. Time Complexity Summary

| Operation                    | Complexity |
| ---------------------------- | ---------- |
| Insert at beginning          | O(1)       |
| Insert at end (without tail) | O(n)       |
| Search                       | O(n)       |
| Access by index              | O(n)       |
| Delete by value              | O(n)       |
| Display / traverse           | O(n)       |

## 6. Types of Linked Lists

- Singly Linked List: Each node points to the next node.
- Doubly Linked List: Each node points to both the previous and next nodes.
- Circular Linked List: The last node points back to the first node instead of `null`.

## 7. Important Interview Points

1. `head` references the first node.
2. A node contains data and a reference to another node.
3. Linked lists do not support O(1) random access by index.
4. Inserting at the beginning takes O(1).
5. In a singly linked list, deleting a node efficiently requires access to its previous node, except when deleting the head.
6. Java automatically reclaims unreachable nodes when they are no longer referenced and are eligible for garbage collection.

Practice next: Try implementing `insertAtPosition(int position, int data)`, `getSize()`, and `reverse()` yourself. Reversing a singly linked list using three references — `prev`, `current`, and `next` — is especially important for DSA interviews.