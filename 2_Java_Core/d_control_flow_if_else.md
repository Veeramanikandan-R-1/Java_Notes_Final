# Control Flow in Java: `if`, `else`, `switch`

## Senior Java Backend Engineer Deep Dive

Control flow is one of the most fundamental concepts in software engineering. Every backend system—from a simple REST API to a globally distributed payment platform—relies on control flow to make decisions.

When an HTTP request reaches a Spring Boot application, the application continuously asks questions:

* Is the user authenticated?
* Does the account exist?
* Is the account active?
* Is inventory available?
* Which payment provider should be selected?
* Should we retry this operation?
* Is the request rate-limited?

Every one of these decisions is implemented using control flow.

---

# 1. Fundamentals

Control flow determines **which block of code executes based on conditions.**

Without control flow, programs execute sequentially:

```java
System.out.println("Step 1");
System.out.println("Step 2");
System.out.println("Step 3");
```

Real systems require decision-making:

```java
if (paymentSuccessful) {
    shipOrder();
}
```

The JVM evaluates a condition and chooses a path.

Think of control flow as the routing engine of your application.

---

# Why Control Flow Exists

A backend service constantly reacts to changing states:

| Condition            | Action          |
| -------------------- | --------------- |
| User authenticated   | Allow access    |
| User blocked         | Reject request  |
| Product out of stock | Return error    |
| Order paid           | Create shipment |
| Payment failed       | Retry           |

Without control flow, all paths would execute.

---

# 2. if Statement

The simplest decision mechanism.

```java
if (condition) {
    // execute
}
```

Example:

```java
if (user.isActive()) {
    sendNotification();
}
```

---

## Internal Working

Compiler generates bytecode.

Conceptually:

```java
if(balance > 0)
```

becomes:

```text
LOAD balance
COMPARE 0
IF_FALSE jump
EXECUTE BLOCK
```

The JVM evaluates a boolean expression.

If true:

```java
execute block
```

Else:

```java
skip block
```

---

# Production Example

Authentication service:

```java
if (!jwtTokenValid) {
    throw new UnauthorizedException();
}
```

Every secured Spring Boot application performs thousands of such checks.

---

# 3. if-else

Two execution paths.

```java
if (condition) {
    doA();
} else {
    doB();
}
```

Example:

```java
if (accountBalance >= amount) {
    debit();
} else {
    rejectTransaction();
}
```

---

## Real Banking Scenario

```java
if (account.isFrozen()) {
    throw new AccountFrozenException();
} else {
    processTransfer();
}
```

Business rule selection is fundamentally if-else logic.

---

# 4. if-else-if Ladder

Multiple conditions.

```java
if(condition1) {

}
else if(condition2) {

}
else if(condition3) {

}
else {

}
```

Example:

```java
if(score >= 90) {
    grade = "A";
}
else if(score >= 80) {
    grade = "B";
}
else if(score >= 70) {
    grade = "C";
}
else {
    grade = "F";
}
```

---

# Internal Working

Evaluation occurs sequentially.

```text
Check condition1
↓
Check condition2
↓
Check condition3
↓
Else
```

First matching condition wins.

This means order matters.

---

# Common Production Mistake

Bad:

```java
if(score >= 60)
```

before

```java
if(score >= 90)
```

The second condition never executes.

---

# Nested if Statements

```java
if(userExists) {

    if(userActive) {

        if(userVerified) {

        }

    }

}
```

Example:

```java
if(user != null) {

    if(user.isActive()) {

        if(user.hasPermission()) {
            accessGranted();
        }

    }

}
```

---

# Why Nested if is Dangerous

Deep nesting increases:

* Complexity
* Cognitive load
* Bug probability

Bad:

```java
if(a) {
    if(b) {
        if(c) {
            if(d) {
            }
        }
    }
}
```

---

# Best Practice

Use Guard Clauses.

Instead of:

```java
if(user != null) {
    if(user.isActive()) {
        process();
    }
}
```

Use:

```java
if(user == null) {
    return;
}

if(!user.isActive()) {
    return;
}

process();
```

Cleaner and easier to maintain.

---

# Ternary Operator

Short form of if-else.

```java
condition ? value1 : value2
```

Example:

```java
String status =
        active ? "ACTIVE" : "INACTIVE";
```

Equivalent:

```java
String status;

if(active) {
    status = "ACTIVE";
}
else {
    status = "INACTIVE";
}
```

---

# Production Usage

DTO mapping:

```java
String label =
    order.isPaid()
        ? "PAID"
        : "PENDING";
```

---

# Avoid Complex Ternary

Bad:

```java
String result =
    a ? b ? "X" : "Y"
      : c ? "A" : "B";
```

Unreadable.

---

# 5. switch Statement

Used when selecting one path among multiple known values.

---

## Traditional Switch

```java
switch(day) {

    case MONDAY:
        work();
        break;

    case SATURDAY:
        relax();
        break;

    default:
        sleep();
}
```

---

# Internal Working

For integer-like values, JVM often generates:

### tableswitch

```text
1 -> jump A
2 -> jump B
3 -> jump C
```

Very fast O(1) dispatch.

Or

### lookupswitch

For sparse values.

```text
100 -> jump A
500 -> jump B
900 -> jump C
```

Uses lookup table.

---

# Why Switch Can Be Faster

Long chain:

```java
if(a == 1)
else if(a == 2)
else if(a == 3)
```

requires sequential checks.

Switch may use direct jump tables.

---

# Fall Through

Classic switch problem.

```java
switch(day) {

    case MONDAY:
        System.out.println("Work");

    case TUESDAY:
        System.out.println("More work");
}
```

Output:

```text
Work
More work
```

Because no break.

---

# Senior Engineer Rule

Never rely on accidental fall-through.

Always use:

```java
break;
```

or modern switch expressions.

---

# Modern Switch (Java 14+)

Huge improvement.

```java
switch(status) {

    case ACTIVE -> process();

    case BLOCKED -> reject();

    case SUSPENDED -> audit();
}
```

No break needed.

---

# Switch Expression

Returns values.

```java
String result =
        switch(status) {

            case ACTIVE -> "Allowed";

            case BLOCKED -> "Denied";

            default -> "Unknown";
        };
```

Very common in modern Java.

---

# Multiple Labels

```java
switch(day) {

    case SATURDAY, SUNDAY ->
            relax();

    default ->
            work();
}
```

Cleaner than many if statements.

---

# Yield Keyword

Complex logic inside switch.

```java
String result =
    switch(type) {

        case PREMIUM -> {

            audit();

            yield "Fast Delivery";
        }

        default -> "Normal";
    };
```

---

# Enum + Switch

Most common enterprise usage.

```java
public enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    CANCELLED
}
```

```java
switch(status) {

    case CREATED -> createOrder();

    case PAID -> processPayment();

    case SHIPPED -> ship();

    case CANCELLED -> cancel();
}
```

---

# 6. Production Usage

## Spring Boot Request Processing

```java
if (!userAuthenticated) {
    throw new UnauthorizedException();
}
```

---

## Payment Routing

```java
switch(provider) {

    case STRIPE ->
            stripeService.process();

    case PAYPAL ->
            paypalService.process();

    case RAZORPAY ->
            razorpayService.process();
}
```

---

## Retry Logic

```java
if(retryCount < MAX_RETRY) {
    retry();
}
```

---

## Feature Flags

```java
if(featureFlagEnabled) {
    useNewFlow();
}
else {
    useOldFlow();
}
```

Common in microservices deployment.

---

# 7. Performance Considerations

## Branch Prediction

Modern CPUs predict branches.

Good:

```java
if(userExists)
```

where 99% requests satisfy condition.

CPU predicts correctly.

---

Bad:

Random conditions.

```java
if(random.nextBoolean())
```

High misprediction cost.

---

## Long if-else Chains

Bad:

```java
if(a == 1)
else if(a == 2)
...
else if(a == 100)
```

Consider:

```java
switch
```

or

```java
Map<Integer, Handler>
```

---

## Replace Giant Switch

Instead of:

```java
switch(paymentProvider)
```

use Strategy Pattern.

```java
Map<Provider, PaymentProcessor>
```

Production-grade design.

---

# 8. Memory Considerations

Control flow itself consumes almost no memory.

Memory impact comes from:

```java
if(condition) {
    createHugeObject();
}
```

Objects inside branches affect heap usage.

---

# Lazy Allocation

Good:

```java
if(debugEnabled) {
    generateLargeDebugReport();
}
```

Object created only when needed.

---

# 9. Security Considerations

## Authorization Checks

```java
if(!user.hasPermission()) {
    throw new AccessDeniedException();
}
```

Every secure system uses such branches.

---

## Input Validation

```java
if(request.amount < 0) {
    throw new ValidationException();
}
```

---

## Dangerous Logic Ordering

Bad:

```java
if(orderExists) {
    processOrder();
}

if(userAuthorized) {
}
```

Authorization should happen first.

```java
if(!userAuthorized) {
    throw new AccessDeniedException();
}
```

---

# 10. Database Impact

Control flow often determines query execution.

---

## Avoid Unnecessary Queries

Bad:

```java
User user =
    repository.findById(id);

if(id == null)
```

Query already executed.

---

Good:

```java
if(id == null) {
    return;
}

repository.findById(id);
```

---

## Conditional Queries

```java
if(activeOnly) {
    repository.findActiveUsers();
}
else {
    repository.findAllUsers();
}
```

Common in Spring Data JPA.

---

# 11. Microservices Usage

## Circuit Breakers

```java
if(circuitBreakerOpen) {
    return fallback();
}
```

---

## Rate Limiting

```java
if(requestCount > limit) {
    reject();
}
```

---

## Distributed Retry

```java
if(retryCount < maxRetries) {
    retry();
}
```

---

# 12. Common Mistakes

## Using == For Strings

Wrong:

```java
if(status == "ACTIVE")
```

Correct:

```java
if("ACTIVE".equals(status))
```

---

## Missing Break

```java
case A:
case B:
```

Unexpected execution.

---

## Deep Nesting

Bad:

```java
if(a){
 if(b){
  if(c){
   if(d){
```

Use guard clauses.

---

## Null Checks Forgotten

Bad:

```java
if(user.getRole().equals("ADMIN"))
```

Potential NPE.

Better:

```java
if("ADMIN".equals(user.getRole()))
```

---

# 13. Best Practices

### Prefer Guard Clauses

```java
if(user == null) return;
```

---

### Use Switch For Fixed Values

```java
switch(status)
```

instead of 20 if-else statements.

---

### Use Enum Instead of Strings

Bad:

```java
if(status.equals("PAID"))
```

Good:

```java
if(status == OrderStatus.PAID)
```

---

### Replace Huge Switches With Strategy Pattern

```java
Map<Provider, Processor>
```

Scales better.

---

### Keep Conditions Simple

Bad:

```java
if(a && b || c && d || e)
```

Extract methods.

```java
if(isEligibleForDiscount())
```

---

# Real-World Scenario: E-Commerce Order Flow

```java
public void processOrder(Order order) {

    if(order == null) {
        throw new IllegalArgumentException();
    }

    if(order.isCancelled()) {
        return;
    }

    switch(order.getStatus()) {

        case CREATED ->
                reserveInventory(order);

        case PAID ->
                createShipment(order);

        case SHIPPED ->
                notifyCustomer(order);

        case DELIVERED ->
                closeOrder(order);

        default ->
                throw new IllegalStateException();
    }
}
```

This pattern exists in virtually every large backend system.

---

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
