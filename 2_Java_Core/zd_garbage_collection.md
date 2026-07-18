# Garbage Collection in Java

### Subtopic: Heap and Stack

---

# 1. Fundamentals

Garbage Collection automatically reclaims memory used by objects that are no longer reachable.

```java
User user = new User();
user = null;
```

If no other reference points to the object, it becomes eligible for garbage collection.

---

# 2. Core Concepts

## Heap

Objects are allocated on heap.

```java
Order order = new Order();
```

The `Order` object lives on heap.

## Stack

Method calls and local variables are stored in stack frames.

When a method ends, its stack frame is removed.

## Reachability

An object is alive if it can be reached from GC roots.

GC roots include:

* Active thread stack references
* Static fields
* JNI references
* Some JVM internal references

## Eligible for GC

Object is eligible when no live path can reach it.

```java
List<String> list = new ArrayList<>();
list = null;
```

## System.gc()

```java
System.gc();
```

This is only a request. JVM may ignore it.

Do not depend on it in production logic.

---

# 3. Internal Working

GC usually works in phases:

1. Mark reachable objects.
2. Remove unreachable objects.
3. Compact memory if needed.

Modern collectors are optimized to reduce pause time and support large heaps.

Common collectors include G1, ZGC, and Shenandoah depending on JDK and workload.

---

# 4. Common Mistakes

* Assuming null assignment immediately frees memory.
* Keeping growing static collections.
* Forgetting to close resources.
* Holding references in caches forever.
* Creating too many short-lived objects unnecessarily.
* Thinking GC handles non-memory resources like files and sockets reliably.

Memory leak in Java means unwanted references keep objects reachable.

---

# 5. Best Practices

* Avoid unbounded caches.
* Close files, sockets, and DB connections.
* Use try-with-resources.
* Monitor heap usage and GC pauses.
* Use profilers for memory leaks.
* Prefer bounded queues and caches.
* Do not call `System.gc()` in application code.

---

# 6. Code Example

```java
class UserCache {
    private final Map<Long, User> cache = new LinkedHashMap<>();

    void put(Long id, User user) {
        if (cache.size() > 1000) {
            cache.clear();
        }
        cache.put(id, user);
    }
}
```

Real production code should use a proper bounded cache library.

---

# 7. Real-world Scenarios

* Diagnosing `OutOfMemoryError`.
* Reducing GC pauses in services.
* Fixing static map memory leaks.
* Tuning heap for containers.
* Monitoring memory during batch jobs.

---

# Revision Notes

* GC cleans unreachable heap objects.
* Objects live on heap.
* Method frames live on stack.
* Object must be unreachable to be GC eligible.
* GC roots keep objects alive.
* `System.gc()` is only a request.
* Java memory leaks happen through unwanted references.
* Close external resources manually or with try-with-resources.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| Heap | Object memory |
| Stack | Method frames |
| GC root | Starting point for reachability |
| Eligible object | Unreachable object |
| Memory leak | Unwanted reachable object |
| `System.gc()` | GC request only |

---

# Interview Questions with Answers

### 1. What is garbage collection?

Automatic cleanup of unreachable heap objects.

### 2. Can Java have memory leaks?

Yes. Objects can remain reachable accidentally through static fields, caches, or listeners.

### 3. Does GC close files?

No reliable cleanup should depend on GC. Use try-with-resources.

---

# Hands-on Exercises

### Exercise 1

Identify leak risk.

```java
static List<byte[]> data = new ArrayList<>();
```

**Answer**

If data keeps growing and is never cleared, objects remain reachable through static reference and cannot be collected.

