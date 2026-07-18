# Trees

### Subtopic: Binary Tree Basics

---

# 1. Fundamentals

A tree is a hierarchical data structure made of nodes.

A binary tree is a tree where each node has at most two children:

* left child
* right child

```java
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
}
```

---

# 2. Core Concepts

## Root

Top node of the tree.

## Leaf

Node with no children.

## Height

Longest path from node to leaf.

## Depth

Distance from root to node.

## Binary Tree Node

```java
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
    }
}
```

---

## Traversals

### Inorder

Left, root, right.

```java
public static void inorder(TreeNode root) {
    if (root == null) return;

    inorder(root.left);
    System.out.println(root.data);
    inorder(root.right);
}
```

### Preorder

Root, left, right.

```java
public static void preorder(TreeNode root) {
    if (root == null) return;

    System.out.println(root.data);
    preorder(root.left);
    preorder(root.right);
}
```

### Postorder

Left, right, root.

```java
public static void postorder(TreeNode root) {
    if (root == null) return;

    postorder(root.left);
    postorder(root.right);
    System.out.println(root.data);
}
```

### Level Order

Breadth-first traversal using queue.

```java
public static void levelOrder(TreeNode root) {
    if (root == null) return;

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.println(node.data);

        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

---

# 3. Internal Working

Recursive traversal uses call stack.

For a balanced tree, recursion depth is O(log n).

For a skewed tree, recursion depth can become O(n), which may risk stack overflow for very deep trees.

Level-order traversal uses explicit queue memory.

---

# 4. Common Mistakes

* Forgetting base condition `root == null`.
* Confusing traversal orders.
* Not handling empty tree.
* Assuming tree is balanced.
* Using recursion blindly for very deep trees.
* Mixing binary tree with binary search tree rules.

---

# 5. Best Practices

* Always handle null root.
* Know traversal order clearly.
* Use queue for BFS.
* Use recursion for clean DFS when depth is reasonable.
* Use iterative traversal for very deep trees.
* Clarify whether tree is normal binary tree or BST.

---

# 6. Code Examples

## Maximum Depth

```java
public static int maxDepth(TreeNode root) {
    if (root == null) return 0;

    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

---

## Count Nodes

```java
public static int countNodes(TreeNode root) {
    if (root == null) return 0;

    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

---

# 7. Real-world Scenarios

* File systems.
* JSON/XML parsing.
* Organization hierarchy.
* Expression trees.
* Database indexes use tree-like structures.

---

# Revision Notes

* Binary tree node has at most two children.
* Root is top node.
* Leaf has no children.
* Inorder is left-root-right.
* Preorder is root-left-right.
* Postorder is left-right-root.
* Level order uses queue.
* DFS recursion uses call stack.

---

# Cheat Sheet

| Traversal | Order |
| --------- | ----- |
| Inorder | Left, Root, Right |
| Preorder | Root, Left, Right |
| Postorder | Left, Right, Root |
| Level order | Breadth first |

---

# Interview Questions with Answers

### 1. What is binary tree?

A tree where each node has at most two children.

### 2. DFS vs BFS?

DFS goes deep first using recursion/stack. BFS processes level by level using queue.

### 3. Binary tree vs BST?

Binary tree only limits child count. BST also enforces ordering rules.

---

# Hands-on Exercises

### Exercise 1

Find maximum depth.

**Answer**

```java
public static int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

