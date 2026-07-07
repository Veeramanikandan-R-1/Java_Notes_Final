# Java Loops

Before learning Collections, Streams, Multithreading, and Spring Boot data processing, you must understand loops well.

A loop is one of the fundamental control-flow mechanisms in programming.

---

# 1. Fundamentals

## What is a Loop?

A loop repeatedly executes a block of code until a condition becomes false.

Without loops:

```java
System.out.println("Hello");
System.out.println("Hello");
System.out.println("Hello");
System.out.println("Hello");
System.out.println("Hello");
```

With a loop:

```java
for(int i = 0; i < 5; i++) {
    System.out.println("Hello");
}
```

---

## Why Do Loops Exist?

Real applications process multiple items:

* Users
* Orders
* Products
* Files
* Database records
* API responses

Example:

```java
List<User> users = getUsers();
```

You need a mechanism to process each user.

Loops solve this problem.

---

## Mental Model

Every loop has 3 components:

```text
Start
  ↓
Condition Check
  ↓
Execute Code
  ↓
Update State
  ↓
Condition Check Again
```

Example:

```java
for(int i = 1; i <= 3; i++) {
    System.out.println(i);
}
```

Execution:

```text
i=1 → print 1
i=2 → print 2
i=3 → print 3
i=4 → stop
```

---

# 2. Types of Loops

Java provides:

1. for loop
2. while loop
3. do-while loop
4. enhanced for loop (for-each)

---

# 3. For Loop

Most commonly used loop.

## Syntax

```java
for(initialization; condition; update) {
    // code
}
```

Example:

```java
for(int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Output:

```text
1
2
3
4
5
```

---

## Internal Working

```java
for(int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Equivalent to:

```java
int i = 1;

while(i <= 5) {
    System.out.println(i);
    i++;
}
```

Execution Flow:

```text
Initialize i
    ↓
Check condition
    ↓
Execute body
    ↓
Update i
    ↓
Check condition again
```

---

## When to Use

Use when number of iterations is known.

Examples:

```java
for(int i = 0; i < 10; i++)
```

```java
for(int i = 0; i < array.length; i++)
```

---

## Production Example

Processing paginated records:

```java
for(int page = 0; page < totalPages; page++) {
    fetchPage(page);
}
```

---

# 4. While Loop

Used when iteration count is unknown.

## Syntax

```java
while(condition) {
    // code
}
```

Example:

```java
int count = 1;

while(count <= 5) {
    System.out.println(count);
    count++;
}
```

---

## Internal Working

```text
Condition
   ↓
True?
   ↓
Execute
   ↓
Back to Condition
```

---

## When to Use

Use when stopping condition is dynamic.

Examples:

### Reading File

```java
while(scanner.hasNextLine()) {
    System.out.println(scanner.nextLine());
}
```

### Polling

```java
while(!jobCompleted()) {
    Thread.sleep(1000);
}
```

---

## Production Example

Kafka Consumer:

```java
while(true) {
    consumer.poll();
}
```

Runs continuously until service shuts down.

---

# 5. Do-While Loop

Rare in backend applications.

## Syntax

```java
do {
    // code
} while(condition);
```

Example:

```java
int count = 1;

do {
    System.out.println(count);
    count++;
} while(count <= 5);
```

---

## Difference from While

While:

```java
while(condition)
```

Condition checked first.

Do-While:

```java
do
```

Code executes first.

---

### Example

```java
int x = 10;

while(x < 5) {
    System.out.println("Hello");
}
```

Output:

```text
Nothing
```

Do-while:

```java
int x = 10;

do {
    System.out.println("Hello");
} while(x < 5);
```

Output:

```text
Hello
```

---

## When to Use

Only when code must execute at least once.

Rare in enterprise Java.

---

# 6. Enhanced For Loop (For-Each)

Introduced to simplify collection traversal.

## Syntax

```java
for(Type variable : collection)
```

Example:

```java
String[] names = {"John", "Mike", "Sam"};

for(String name : names) {
    System.out.println(name);
}
```

---

## Collection Example

```java
List<String> users = List.of(
        "Alice",
        "Bob",
        "Charlie"
);

for(String user : users) {
    System.out.println(user);
}
```

---

## Internal Working

Behind the scenes:

```java
for(String user : users)
```

Uses Iterator.

Conceptually:

```java
Iterator<String> iterator = users.iterator();

while(iterator.hasNext()) {
    String user = iterator.next();
}
```

You'll learn Iterators in Collections.

---

## When to Use

Read-only traversal.

Preferred for:

```java
List
Set
Array
```

---

## When NOT to Use

When index is needed.

Bad:

```java
for(String name : names)
```

Need position?

Use:

```java
for(int i = 0; i < names.size(); i++)
```

---

# 7. Loop Control Statements

---

# break

Terminates loop immediately.

Example:

```java
for(int i = 1; i <= 10; i++) {

    if(i == 5) {
        break;
    }

    System.out.println(i);
}
```

Output:

```text
1
2
3
4
```

---

## Production Example

Find first matching user.

```java
for(User user : users) {

    if(user.getId().equals(id)) {
        found = user;
        break;
    }
}
```

---

# continue

Skips current iteration.

Example:

```java
for(int i = 1; i <= 5; i++) {

    if(i == 3) {
        continue;
    }

    System.out.println(i);
}
```

Output:

```text
1
2
4
5
```

---

## Production Example

Skip inactive users.

```java
for(User user : users) {

    if(!user.isActive()) {
        continue;
    }

    process(user);
}
```

---

# Nested Loops

Loop inside another loop.

Example:

```java
for(int i = 1; i <= 3; i++) {

    for(int j = 1; j <= 3; j++) {
        System.out.println(i + "," + j);
    }

}
```

Output:

```text
1,1
1,2
1,3
2,1
...
```

---

## Complexity Impact

Single loop:

```java
O(n)
```

Nested loop:

```java
O(n²)
```

Very important in interviews.

---

# Real-World Backend Scenarios

## Processing Database Records

```java
for(User user : users) {
    sendEmail(user);
}
```

---

## Batch Processing

```java
for(Order order : orders) {
    process(order);
}
```

---

## Validation

```java
for(Product product : products) {

    if(product.getPrice() < 0) {
        throw new IllegalArgumentException();
    }

}
```

---

## Building Responses

```java
List<UserDto> result = new ArrayList<>();

for(User user : users) {
    result.add(convert(user));
}
```

Very common in Spring Boot.

---

# Common Mistakes

## 1. Infinite Loop

Bad:

```java
while(true) {
}
```

No exit condition.

CPU consumption becomes 100%.

---

## 2. Wrong Boundary Condition

Bad:

```java
for(int i = 0; i <= arr.length; i++)
```

Produces:

```text
ArrayIndexOutOfBoundsException
```

Correct:

```java
for(int i = 0; i < arr.length; i++)
```

---

## 3. Modifying Collection During Iteration

Bad:

```java
for(String name : names) {
    names.remove(name);
}
```

Produces:

```text
ConcurrentModificationException
```

---

## 4. Unnecessary Nested Loops

Bad:

```java
for(...)
    for(...)
```

Without considering complexity.

Can destroy performance.

---

## 5. Using While Instead of For

Bad:

```java
int i = 0;

while(i < 10) {
    i++;
}
```

Use for-loop when count is known.

---

# Best Practices

## Use Enhanced For Loop for Reading

Preferred:

```java
for(User user : users)
```

Readable and safe.

---

## Keep Loop Body Small

Bad:

```java
for(User user : users) {
   // 100 lines
}
```

Good:

```java
for(User user : users) {
    processUser(user);
}
```

---

## Avoid Deep Nesting

Bad:

```java
for(...)
    if(...)
        while(...)
            if(...)
```

Hard to maintain.

---

## Prefer Streams for Complex Transformations

Instead of:

```java
for(...)
```

Modern Java often uses:

```java
users.stream()
```

But learn loops first.

Streams are built on the same iteration concepts.

---

## Consider Time Complexity

Always ask:

```text
How many times will this execute?
```

Senior engineers think about performance.

---

# Interview Questions & Answers

## What is the difference between while and do-while?

**Answer**

while checks condition before execution.

do-while executes at least once.

---

## When should you use a for loop?

When number of iterations is known.

---

## When should you use a while loop?

When termination condition is dynamic or unknown.

---

## Difference between for loop and enhanced for loop?

| For Loop             | Enhanced For |
| -------------------- | ------------ |
| Has index            | No index     |
| More control         | Simpler      |
| Can modify via index | Read-focused |

---

## What is an infinite loop?

A loop whose condition never becomes false.

Example:

```java
while(true) {
}
```

---

## What is the time complexity of nested loops?

Typically:

```java
O(n²)
```

---

## How does enhanced for loop work internally?

Uses Iterator internally for collections.

---

# Revision Notes

```text
for       -> Known iterations

while     -> Unknown iterations

do-while  -> Execute at least once

for-each  -> Traverse collections

break     -> Exit loop

continue  -> Skip iteration

Nested loops increase complexity

for-each internally uses Iterator

Always check loop boundaries

Avoid infinite loops

Think about Big-O complexity
```

---

# Cheat Sheet

```java
// For Loop
for(int i = 0; i < n; i++) {
}

// While Loop
while(condition) {
}

// Do While
do {
} while(condition);

// For Each
for(String s : list) {
}

// Break
break;

// Continue
continue;
```

---

# Hands-On Exercises

## 1. Print Numbers 1 to 100

**Solution**

```java
for(int i = 1; i <= 100; i++) {
    System.out.println(i);
}
```

---

## 2. Sum Numbers 1 to N

Input:

```java
N = 5
```

Output:

```text
15
```

**Solution**

```java
int sum = 0;

for(int i = 1; i <= 5; i++) {
    sum += i;
}

System.out.println(sum);
```

---

## 3. Count Even Numbers

**Solution**

```java
for(int i = 1; i <= 100; i++) {

    if(i % 2 == 0) {
        System.out.println(i);
    }

}
```

---

## 4. Find Maximum Element

**Solution**

```java
int[] arr = {4, 8, 2, 10, 3};

int max = arr[0];

for(int num : arr) {

    if(num > max) {
        max = num;
    }

}

System.out.println(max);
```

---

## 5. Count Occurrences

Input:

```java
{1,2,3,2,2,4}
```

Count occurrences of 2.

**Solution**

```java
int count = 0;

for(int num : arr) {

    if(num == 2) {
        count++;
    }

}
```

---

## 6. Remove Invalid Users

Given:

```java
List<User>
```

Create another list containing only active users.

**Solution**

```java
List<User> activeUsers = new ArrayList<>();

for(User user : users) {

    if(user.isActive()) {
        activeUsers.add(user);
    }

}
```

This exercise resembles actual Spring Boot service-layer code.
