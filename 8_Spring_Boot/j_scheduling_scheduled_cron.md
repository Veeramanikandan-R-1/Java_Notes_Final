# Spring Boot

### Subtopic: Scheduling - @Scheduled, Cron

---

# 1. Fundamentals

Scheduling runs methods automatically at fixed times or intervals.

Enable scheduling:

```java
@EnableScheduling
@SpringBootApplication
class DemoApplication {
}
```

Create a scheduled method:

```java
@Scheduled(fixedRate = 60000)
void runEveryMinute() {
}
```

---

# 2. Core Concepts

## Fixed Rate

Runs based on start time interval.

```java
@Scheduled(fixedRate = 5000)
```

## Fixed Delay

Runs after previous execution completes plus delay.

```java
@Scheduled(fixedDelay = 5000)
```

## Cron

Runs based on cron expression.

```java
@Scheduled(cron = "0 0 2 * * *")
```

This runs daily at 2 AM.

---

# 3. Internal Working

Spring detects `@Scheduled` methods and registers them with a task scheduler.

By default, scheduled tasks may use a small scheduler setup. For production workloads, configure a scheduler thread pool when multiple jobs can run.

---

# 4. Common Mistakes

* Forgetting `@EnableScheduling`.
* Running long jobs on a single scheduler thread.
* Not handling exceptions inside scheduled jobs.
* Running the same job on every instance in a multi-node deployment.
* Hardcoding cron expressions.

---

# 5. Best Practices

* Externalize cron expressions.
* Log job start, success, failure, and duration.
* Make jobs idempotent.
* Use distributed locks for clustered deployments.
* Keep scheduled methods thin and delegate to services.

---

# 6. Code Example

```java
@Component
class InvoiceScheduler {
    private final InvoiceService invoiceService;

    InvoiceScheduler(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Scheduled(cron = "${jobs.invoice.cron}")
    void generateInvoices() {
        invoiceService.generatePendingInvoices();
    }
}
```

---

# 7. Real-world Scenarios

* Daily invoice generation.
* Cache cleanup.
* Sending reminder emails.
* Polling external systems.
* Retrying failed background work.

---

# Revision Notes

* Use `@EnableScheduling` to enable scheduling.
* Use `@Scheduled` on methods.
* `fixedRate` measures from task start time.
* `fixedDelay` waits after task completion.
* Cron supports calendar-based scheduling.
* Use distributed locks in multi-node deployments.

---

# Cheat Sheet

| Need | Use |
| ---- | --- |
| Enable | `@EnableScheduling` |
| Interval from start | `fixedRate` |
| Delay after finish | `fixedDelay` |
| Calendar schedule | `cron` |
| External cron | `${jobs.name.cron}` |

---

# Interview Questions with Answers

### 1. Difference between fixedRate and fixedDelay?

`fixedRate` schedules based on start times. `fixedDelay` waits after previous execution finishes.

### 2. What risk exists in multi-instance scheduling?

The same job can run simultaneously on every instance unless coordinated.

### 3. Why externalize cron?

So timing can change per environment without code changes.

---

# Hands-on Exercises

### Exercise 1

Create a job that runs every night at 1 AM.

**Answer**

```java
@Scheduled(cron = "0 0 1 * * *")
void nightlyJob() {
}
```

