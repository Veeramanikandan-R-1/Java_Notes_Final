# Revision Notes

* Garbage Collection reclaims unreachable heap objects.
* Heap stores objects and arrays.
* Stack stores method frames and local variables.
* GC roots are starting points for reachability.
* Reachable objects are not collected.
* `System.gc()` is not guaranteed.
* Java memory leak means unwanted references keep objects alive.
* GC does not replace closing files, sockets, or DB connections.

---

# Cheat Sheet

| Term | Meaning |
| ---- | ------- |
| Heap | Object storage |
| Stack | Method call storage |
| Reachable | Can be accessed from GC roots |
| Eligible | Unreachable |
| Leak | Accidentally reachable |
| GC pause | Time spent collecting |

---

# Interview Questions with Answers

### 1. When is object eligible for GC?

When it is no longer reachable from any GC root.

### 2. What are common Java memory leaks?

Unbounded caches, static collections, listeners not removed, and resources not closed.

### 3. What does System.gc() do?

It requests garbage collection, but JVM is not required to run it immediately.

---

# Hands-on Exercises

### Exercise 1

How to safely close file resource?

**Answer**

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    return reader.readLine();
}
```

