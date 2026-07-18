# Revision Notes

* Thread states are important.
* `start()` creates a new thread.
* `run()` is normal method call.
* ExecutorService manages thread pools.
* Race condition comes from unsafe shared mutation.
* `volatile` gives visibility, not atomicity.
* `synchronized` provides locking.
* BlockingQueue helps producer-consumer.

---

# Cheat Sheet

| Area | Focus |
| ---- | ----- |
| Thread | lifecycle |
| Executor | pools |
| volatile | visibility |
| synchronized | lock |
| Atomic | counters |
| BlockingQueue | coordination |

---

# Interview Questions with Answers

### 1. volatile vs synchronized?

`volatile` gives visibility. `synchronized` gives visibility and mutual exclusion.

### 2. What is deadlock?

Threads wait forever for locks held by each other.

