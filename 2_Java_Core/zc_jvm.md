# JVM in Java

### Subtopic: Class Loader, Memory

---

# 1. Fundamentals

JVM stands for Java Virtual Machine.

It runs Java bytecode and provides platform independence.

Flow:

```text
Java source -> javac -> bytecode -> JVM -> machine execution
```

---

# 2. Core Concepts

## Class Loader

Class loader loads `.class` files into JVM memory.

Main steps:

1. Loading
2. Linking
3. Initialization

Class loaders:

* Bootstrap ClassLoader
* Platform ClassLoader
* Application ClassLoader

## JVM Memory Areas

### Heap

Stores objects and arrays.

```java
User user = new User();
```

The `User` object is on heap.

### Stack

Stores method call frames, local variables, and references.

Each thread has its own stack.

### Method Area / Metaspace

Stores class metadata.

Examples:

* Class names
* Method metadata
* Runtime constant pool

### Program Counter

Stores current execution instruction for a thread.

### Native Method Stack

Used for native method execution.

---

# 3. Internal Working

When a Java program runs:

1. Class loader loads required classes.
2. Bytecode verifier checks safety.
3. JVM interprets or JIT-compiles bytecode.
4. Objects are allocated on heap.
5. Garbage collector cleans unused objects.

JIT compiler converts hot bytecode paths into optimized machine code.

---

# 4. Common Mistakes

* Thinking Java directly runs source code.
* Confusing stack object references with heap objects.
* Assuming local variables are shared between threads.
* Ignoring memory leaks caused by unwanted references.
* Tuning JVM flags without measurement.

---

# 5. Best Practices

* Understand heap vs stack clearly.
* Monitor memory before tuning.
* Use profiling tools for leaks.
* Avoid static collections holding growing data.
* Size heap based on workload and container limits.
* Keep classpath clean to avoid class loading conflicts.

---

# 6. Code Example

```java
class MemoryExample {
    public static void main(String[] args) {
        int count = 10;              // stack local variable
        User user = new User();      // reference on stack, object on heap
    }
}
```

---

# 7. Real-world Scenarios

* Debugging `OutOfMemoryError`.
* Understanding thread stack traces.
* Tuning service heap size.
* Diagnosing class loading issues.
* Reading production JVM metrics.

---

# Revision Notes

* JVM runs bytecode.
* ClassLoader loads classes.
* Heap stores objects.
* Stack stores method frames and local variables.
* Each thread has its own stack.
* Metaspace stores class metadata.
* JIT optimizes hot code.
* GC cleans unused heap objects.

---

# Cheat Sheet

| Area | Stores |
| ---- | ------ |
| Heap | Objects and arrays |
| Stack | Method frames and local variables |
| Metaspace | Class metadata |
| PC Register | Current instruction |
| Native Stack | Native method data |

---

# Interview Questions with Answers

### 1. What is JVM?

The runtime engine that executes Java bytecode.

### 2. Heap vs Stack?

Heap stores objects. Stack stores method frames, local variables, and references.

### 3. What is class loading?

The process of loading class bytecode into JVM memory.

---

# Hands-on Exercises

### Exercise 1

Explain where `new User()` object is stored.

**Answer**

The object is stored on heap. The local reference variable may be stored in the current thread stack frame.

