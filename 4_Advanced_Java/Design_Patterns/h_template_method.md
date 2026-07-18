# Template Method Design Pattern

### Subtopic: Template Method

---

# 1. Fundamentals

Template Method defines the skeleton of an algorithm in a base class and lets subclasses customize steps.

Use it when workflow is fixed but some steps vary.

---

# 2. Core Concepts

## Abstract Base Class

```java
abstract class ReportGenerator {
    public final void generate() {
        fetchData();
        format();
        export();
    }

    protected abstract void fetchData();
    protected abstract void format();

    private void export() {
        System.out.println("Exporting report");
    }
}
```

## Concrete Class

```java
class SalesReportGenerator extends ReportGenerator {
    protected void fetchData() {
        System.out.println("Fetching sales data");
    }

    protected void format() {
        System.out.println("Formatting sales report");
    }
}
```

---

# 3. Internal Working

Parent class controls algorithm order.

Subclasses fill specific steps.

The template method is often `final` to prevent subclasses from changing workflow order.

---

# 4. Common Mistakes

* Making inheritance hierarchy too rigid.
* Letting subclasses change core workflow.
* Putting too many abstract steps.
* Using template method when strategy would be more flexible.
* Base class becoming too large.

---

# 5. Best Practices

* Use when workflow order is stable.
* Keep template method final.
* Keep abstract steps focused.
* Prefer composition if runtime behavior switching is needed.
* Avoid deep inheritance.

---

# 6. Real-world Scenarios

* Report generation.
* Data import pipeline.
* Payment processing workflow.
* File parsing workflow.
* Test setup frameworks.

---

# Revision Notes

* Template Method defines algorithm skeleton.
* Base class controls workflow.
* Subclasses customize steps.
* Template method is often final.
* Use when workflow is fixed.
* Prefer Strategy when behavior must vary dynamically.

---

# Cheat Sheet

| Part | Role |
| ---- | ---- |
| Template method | Fixed workflow |
| Abstract step | Subclass-specific behavior |
| Concrete step | Shared behavior |
| Subclass | Provides custom steps |

---

# Interview Questions with Answers

### 1. What problem does Template Method solve?

It avoids duplicate workflow code while allowing subclasses to customize specific steps.

### 2. Template Method vs Strategy?

Template Method uses inheritance and fixed workflow. Strategy uses composition and interchangeable behavior.

### 3. Why make template method final?

To prevent subclasses from changing the required algorithm order.

