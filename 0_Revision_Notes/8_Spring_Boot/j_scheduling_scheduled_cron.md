# Revision Notes

* Scheduling runs tasks at fixed time or interval.
* Enable with `@EnableScheduling`.
* Use `@Scheduled`.
* Cron expressions define calendar-based schedule.
* Scheduled jobs should be idempotent.
* In multi-instance deployments, avoid duplicate execution.
* Use distributed lock for singleton job behavior.

---

# Cheat Sheet

| Need | Annotation |
| ---- | ---------- |
| Enable | `@EnableScheduling` |
| Fixed rate | `@Scheduled(fixedRate = ...)` |
| Fixed delay | `@Scheduled(fixedDelay = ...)` |
| Cron | `@Scheduled(cron = "...")` |

---

# Interview Questions with Answers

### 1. fixedRate vs fixedDelay?

FixedRate counts from task start time. FixedDelay waits after task completion.

### 2. What issue in multiple instances?

Each instance may run the scheduled job unless coordination is added.

### 3. Why idempotent jobs?

Retries or duplicate execution should not corrupt data.

