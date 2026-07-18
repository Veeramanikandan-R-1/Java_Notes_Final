# Revision Notes

* Binary tree node has at most two children.
* Root is the top node.
* Leaf node has no children.
* Inorder traversal is left-root-right.
* Preorder traversal is root-left-right.
* Postorder traversal is left-right-root.
* Level order traversal uses queue.
* DFS usually uses recursion or stack.
* BFS uses queue.

---

# Cheat Sheet

| Traversal | Order |
| --------- | ----- |
| Inorder | Left, Root, Right |
| Preorder | Root, Left, Right |
| Postorder | Left, Right, Root |
| Level Order | Breadth First |

---

# Interview Questions with Answers

### 1. What is a binary tree?

A tree where each node has at most two children.

### 2. Binary tree vs BST?

Binary tree only limits child count. BST also enforces left smaller and right greater ordering.

### 3. DFS vs BFS?

DFS explores depth first. BFS explores level by level.

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

---

### Exercise 2

Count tree nodes.

**Answer**

```java
public static int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}
```

