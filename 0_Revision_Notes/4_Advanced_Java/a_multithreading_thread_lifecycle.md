# Revision Notes

* Thread enables concurrent execution.
* `start()` creates a new thread.
* `run()` is only a normal method call.
* Each thread has its own stack.
* Heap objects can be shared between threads.
* Thread states: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.
* Prefer executors over raw threads in production.
* Do not use `sleep()` for reliable coordination.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Create task | `Runnable` |
| Start thread | `start()` |
| Pause | `Thread.sleep()` |
| Wait for finish | `join()` |
| Current thread | `Thread.currentThread()` |
| Thread pool | `ExecutorService` |

---

# Interview Questions with Answers

### 1. start() vs run()?

`start()` creates a new thread. `run()` executes in the current thread.

### 2. What is BLOCKED state?

Thread is waiting to acquire a monitor lock.

### 3. Why is multithreading hard?

Because shared mutable state can cause race conditions and visibility issues.

---

# Hands-on Exercises

### Exercise 1

Print current thread name.

**Answer**

```java
Thread thread = new Thread(() ->
        System.out.println(Thread.currentThread().getName()));
thread.start();
```

