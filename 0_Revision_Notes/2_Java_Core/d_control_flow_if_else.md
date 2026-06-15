# Revision Notes

* `if` executes a block when condition is true.
* `if-else` selects between two paths.
* `if-else-if` handles multiple conditions.
* Nested ifs increase complexity.
* Guard clauses reduce nesting.
* Ternary operator is concise but should remain simple.
* `switch` is ideal for fixed known values.
* Modern switch (`->`) avoids fall-through.
* Switch expressions return values.
* Enums + switch are common in enterprise systems.
* Large switch statements often indicate Strategy Pattern opportunities.
* Authorization, validation, retries, feature flags, circuit breakers all depend on control flow.

---

# Cheat Sheet

```java
// if
if(condition) { }

// if else
if(condition) { }
else { }

// else if
if(a) { }
else if(b) { }
else { }

// ternary
String x = cond ? "A" : "B";

// switch expression
String result =
    switch(status) {
        case ACTIVE -> "Allowed";
        case BLOCKED -> "Denied";
        default -> "Unknown";
    };

// enum switch
switch(orderStatus) {
    case CREATED -> create();
    case PAID -> pay();
}
```

---

# Interview Flashcards

### Q1: When should switch be preferred over if-else?

**Answer:** When checking one variable against multiple fixed values, especially enums or constants.

---

### Q2: What is fall-through?

**Answer:** Execution continues into the next case when `break` is missing in traditional switch.

---

### Q3: What improvement did Java 14 bring to switch?

**Answer:** Switch expressions and arrow syntax (`->`) eliminating accidental fall-through.

---

### Q4: Why are guard clauses preferred?

**Answer:** They reduce nesting and improve readability.

---

### Q5: What JVM instructions power switch?

**Answer:** `tableswitch` and `lookupswitch`.

---

### Q6: Why are enums preferred in switch?

**Answer:** Type safety, compile-time checking, and better maintainability.

---

### Q7: What design pattern commonly replaces giant switch statements?

**Answer:** Strategy Pattern.

---

# Active Recall Questions

### Question

What is the biggest maintainability problem with nested if statements?

### Answer

Deep nesting increases complexity and makes logic difficult to understand and modify.

---

### Question

Why is modern switch safer?

### Answer

No accidental fall-through because `->` executes exactly one branch.

---

### Question

What is a guard clause?

### Answer

An early return that exits invalid flows immediately.

---

### Question

Why should authorization checks happen first?

### Answer

To prevent unauthorized users from reaching business logic.

---

### Question

What does a large switch often indicate?

### Answer

A missing abstraction such as Strategy Pattern or Command Pattern.

---

# Hands-On Exercises

## Exercise 1

Determine customer tier.

```java
score >= 1000 -> GOLD
score >= 500 -> SILVER
otherwise -> BRONZE
```

### Solution

```java
if(score >= 1000)
    tier = "GOLD";
else if(score >= 500)
    tier = "SILVER";
else
    tier = "BRONZE";
```

---

## Exercise 2

Convert payment status using switch expression.

Input:

```java
PENDING
SUCCESS
FAILED
```

Output:

```java
Waiting
Completed
Rejected
```

### Solution

```java
String message =
    switch(status) {

        case PENDING -> "Waiting";
        case SUCCESS -> "Completed";
        case FAILED -> "Rejected";
    };
```

---

## Exercise 3

Refactor Nested Logic

Before:

```java
if(user != null) {
    if(user.isActive()) {
        if(user.hasPermission()) {
            process();
        }
    }
}
```

### Solution

```java
if(user == null)
    return;

if(!user.isActive())
    return;

if(!user.hasPermission())
    return;

process();
```

---

## Exercise 4 (Senior Level)

Implement payment processor selection.

```java
enum Provider {
    STRIPE,
    PAYPAL,
    RAZORPAY
}
```

Use switch expression to return processor name.

### Solution

```java
String processor =
    switch(provider) {

        case STRIPE -> "StripeProcessor";
        case PAYPAL -> "PaypalProcessor";
        case RAZORPAY -> "RazorpayProcessor";
    };
```

---

## Exercise 5 (Architect Level)

Replace:

```java
switch(paymentProvider) {
   ...
   50 cases
}
```

with:

```java
Map<Provider, PaymentProcessor>
```

and implement Strategy Pattern-based dispatching. This is how large-scale Spring Boot payment systems are typically designed.