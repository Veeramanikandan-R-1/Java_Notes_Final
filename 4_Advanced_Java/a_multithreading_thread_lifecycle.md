# Multithreading in Java

### Subtopic: Thread Lifecycle

---

# 1. Fundamentals

Multithreading allows multiple tasks to run concurrently within the same application.

```java
Thread thread = new Thread(() -> System.out.println("Running"));
thread.start();
```

Use `start()`, not `run()`, to create a new thread of execution.

---

# 2. Core Concepts

## Thread Lifecycle States

| State | Meaning |
| ----- | ------- |
| NEW | Thread object created but not started |
| RUNNABLE | Ready or running |
| BLOCKED | Waiting to acquire monitor lock |
| WAITING | Waiting indefinitely |
| TIMED_WAITING | Waiting for a specific time |
| TERMINATED | Finished execution |

---

## Creating Threads

Using `Thread`:

```java
Thread thread = new Thread(() -> process());
thread.start();
```

Using `Runnable`:

```java
Runnable task = () -> System.out.println("Task");
new Thread(task).start();
```

In production, prefer executor services over manual thread creation.

---

## start() vs run()

```java
thread.start(); // new thread
thread.run();   // normal method call
```

`start()` asks JVM to create a new call stack for the thread.

---

## sleep()

```java
Thread.sleep(1000);
```

Moves thread to `TIMED_WAITING`.

It does not release locks.

---

## join()

```java
thread.join();
```

Current thread waits until another thread finishes.

---

# 3. Internal Working

Each thread has its own stack.

Objects are shared through heap memory.

This is why shared mutable state needs careful synchronization.

Thread scheduling is handled by JVM and operating system. You cannot assume exact execution order.

---

# 4. Common Mistakes

* Calling `run()` instead of `start()`.
* Assuming thread execution order.
* Sharing mutable state without synchronization.
* Creating too many raw threads.
* Ignoring `InterruptedException`.
* Using `sleep()` for coordination.

---

# 5. Best Practices

* Prefer `ExecutorService` for task execution.
* Keep shared mutable state minimal.
* Handle interruption properly.
* Use thread-safe collections where needed.
* Give threads meaningful names in production.
* Avoid manually managing many threads.

---

# 6. Code Example

```java
class WorkerTask implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " processing");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new WorkerTask(), "order-worker");
        thread.start();
    }
}
```

---

# 7. Real-world Scenarios

* Background processing.
* Parallel API calls.
* Async report generation.
* Batch jobs.
* Server request handling.

---

# Revision Notes

* Thread enables concurrent execution.
* `start()` creates a new thread.
* `run()` is a normal method call.
* Threads have separate stacks.
* Heap objects can be shared.
* Thread lifecycle includes NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.
* Prefer executors over raw threads.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Create task | `Runnable` |
| Start thread | `thread.start()` |
| Sleep | `Thread.sleep(ms)` |
| Wait for finish | `thread.join()` |
| Current thread | `Thread.currentThread()` |
| Preferred production API | `ExecutorService` |

---

# Interview Questions with Answers

### 1. start() vs run()?

`start()` starts a new thread. `run()` executes like a normal method in the current thread.

### 2. What is BLOCKED state?

A thread is blocked when waiting to acquire a monitor lock.

### 3. Why is shared mutable state risky?

Multiple threads can read/write the same data unpredictably without synchronization.

---

# Hands-on Exercises

### Exercise 1

Create and start a thread that prints current thread name.

**Answer**

```java
Thread thread = new Thread(() ->
        System.out.println(Thread.currentThread().getName()));
thread.start();
```

