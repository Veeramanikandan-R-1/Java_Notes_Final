# Revision Notes

* JVM executes Java bytecode.
* Java source is compiled into `.class` bytecode.
* ClassLoader loads classes into JVM.
* Heap stores objects and arrays.
* Stack stores method frames and local variables.
* Each thread has its own stack.
* Metaspace stores class metadata.
* JIT compiles hot bytecode into machine code.
* Garbage collector manages heap memory.

---

# Cheat Sheet

| Concept | Meaning |
| ------- | ------- |
| JVM | Executes bytecode |
| ClassLoader | Loads classes |
| Heap | Objects |
| Stack | Method calls |
| Metaspace | Class metadata |
| JIT | Runtime optimization |
| GC | Memory cleanup |

---

# Interview Questions with Answers

### 1. Why is Java platform independent?

Java compiles to bytecode, and JVM implementations run that bytecode on different platforms.

### 2. Does stack store objects?

Objects are stored on heap. Stack stores references and local method data.

### 3. What is JIT?

Just-In-Time compiler optimizes frequently executed bytecode into machine code.

---

# Hands-on Exercises

### Exercise 1

Identify memory area for local primitive variable.

**Answer**

A local primitive variable is stored in the current thread's stack frame.

