# Java Basics — Variables & Data Types

## Senior Java Backend Engineer Perspective

Most beginners think variables and data types are the simplest topic in Java.

In reality, many production incidents, performance bottlenecks, memory leaks, database bugs, serialization failures, and distributed-system inconsistencies originate from poor choices of variables and data types.

As a Senior Java Engineer, you should not think:

> "A variable stores a value."

You should think:

> "A variable represents a contract between JVM memory, CPU execution, database storage, network serialization, API communication, and business logic."

This mindset is what separates a junior developer from a senior backend engineer.

---

# 1. Why Variables Exist

Let's start from the machine level.

A CPU processes data.

Consider:

```java
100 + 200
```

The CPU can execute this directly.

But real applications process:

* User IDs
* Account balances
* Order amounts
* Product quantities
* JWT tokens
* API payloads

These values change during execution.

We need names to reference them.

That's what variables provide.

```java
int quantity = 100;
```

Instead of:

```java
100
```

we now have:

```java
quantity
```

which is:

* readable
* maintainable
* reusable

---

# 2. What Exactly Is a Variable?

A variable is:

> A named storage location associated with a specific data type.

Example:

```java
int quantity = 10;
```

Contains:

### Variable Name

```java
quantity
```

### Data Type

```java
int
```

### Value

```java
10
```

---

# Internal JVM View

Java source:

```java
int quantity = 10;
```

At runtime:

```text
Stack Frame
----------------
quantity -> 10
----------------
```

The variable is stored in the current method's stack frame.

This distinction becomes critical when discussing memory.

---

# 3. Variables and Memory

Understanding memory is impossible without understanding variables.

JVM memory consists primarily of:

```text
Heap
Stack
Metaspace
Native Memory
```

For variables, Heap and Stack matter most.

---

# Example

```java
public void process() {
    int age = 25;

    User user = new User();
}
```

Memory:

```text
STACK
----------------
age = 25
user = reference
----------------

HEAP
----------------
User Object
----------------
```

Notice:

```java
int age
```

stores actual value.

But:

```java
User user
```

stores reference.

This difference drives almost everything in Java memory behavior.

---

# 4. Java Data Types Classification

Java divides data types into two categories.

```text
Data Types
   |
   +------------------+
   |                  |
Primitive        Reference
```

---

# Primitive Data Types

Java has exactly 8 primitive types.

```java
byte
short
int
long

float
double

char

boolean
```

These are not objects.

They directly store values.

---

# Reference Data Types

Everything else.

Examples:

```java
String
User
List
Map
Set
Integer
Long
BigDecimal
```

These store references to objects.

---

# Why This Separation Exists

Performance.

Primitive:

```java
int count = 100;
```

Memory:

```text
Stack
-----
100
-----
```

Direct access.

Fast.

Reference:

```java
Integer count = 100;
```

Memory:

```text
Stack
-------
Reference
-------

Heap
-------
Integer Object
-------
```

Extra indirection.

More memory.

More GC pressure.

---

# 5. Primitive Data Types Deep Dive

---

## byte

Size:

```text
8 bits
1 byte
```

Range:

```text
-128 to 127
```

Example:

```java
byte statusCode = 1;
```

Useful when memory matters.

Rare in business applications.

Common in:

* file processing
* network protocols
* image processing

---

## short

```text
16 bits
```

Range:

```text
-32,768 to 32,767
```

Rarely used.

Most applications jump directly to int.

---

## int

Most common numeric type.

```java
int userCount = 1000;
```

Size:

```text
32 bits
```

Range:

```text
-2.1 billion
to
+2.1 billion
```

Used everywhere:

* IDs
* counts
* pagination
* retries

---

## long

```java
long userId = 99999999999L;
```

Size:

```text
64 bits
```

Critical in production systems.

Most database IDs use:

```java
Long
BIGINT
```

Example:

```java
Long orderId;
```

Spring Boot + JPA:

```java
@Id
private Long id;
```

---

## float

```java
float temperature = 36.5f;
```

32-bit floating point.

Rarely used in backend systems.

Precision issues.

---

## double

```java
double price = 12.99;
```

64-bit floating point.

Default decimal type.

Better precision than float.

But still dangerous for money.

---

# Why Double Is Dangerous

Example:

```java
System.out.println(0.1 + 0.2);
```

Output:

```text
0.30000000000000004
```

Reason:

Binary floating-point representation.

---

# Production Rule

Never use:

```java
double
float
```

for money.

Use:

```java
BigDecimal
```

Example:

```java
BigDecimal amount =
    new BigDecimal("10.50");
```

This is one of the most frequently asked senior interview questions.

---

## char

Stores Unicode character.

```java
char grade = 'A';
```

Size:

```text
16 bits
```

Internally:

```java
char c = 65;
```

Produces:

```text
A
```

Because chars store Unicode values.

---

## boolean

```java
boolean active = true;
```

Represents:

```java
true
false
```

Used heavily for:

* feature flags
* status fields
* authorization checks

---

# 6. Reference Types Deep Dive

Everything that is not primitive is an object.

Example:

```java
String name = "John";
```

Memory:

```text
STACK
-------
reference
-------

HEAP
-------
String Object
-------
```

---

# Why Objects Exist

Objects provide:

* behavior
* encapsulation
* inheritance
* polymorphism

Impossible with primitives.

---

# Example

```java
User user =
    new User(1L, "John");
```

Contains:

```java
user.getName();
user.getId();
```

Methods require objects.

---

# 7. Wrapper Classes

Each primitive has an object equivalent.

| Primitive | Wrapper   |
| --------- | --------- |
| byte      | Byte      |
| short     | Short     |
| int       | Integer   |
| long      | Long      |
| float     | Float     |
| double    | Double    |
| char      | Character |
| boolean   | Boolean   |

---

# Why Wrapper Classes Exist

Collections require objects.

Invalid:

```java
List<int> numbers;
```

Valid:

```java
List<Integer> numbers;
```

Because generics work with objects.

---

# Autoboxing

Java automatically converts.

```java
Integer count = 10;
```

Actually:

```java
Integer count =
    Integer.valueOf(10);
```

---

# Unboxing

```java
Integer count = 10;

int x = count;
```

Actually:

```java
count.intValue();
```

---

# Hidden Production Problem

```java
Integer count = null;

int x = count;
```

Throws:

```java
NullPointerException
```

Common microservice production bug.

---

# 8. Variable Scope

Critical for thread safety.

---

## Local Variables

```java
public void process() {
    int count = 0;
}
```

Lives inside method.

Thread-safe.

Stored in stack.

---

## Instance Variables

```java
class UserService {

    private int count;
}
```

Stored in heap.

Shared across threads.

Potential concurrency issues.

---

## Static Variables

```java
private static int counter;
```

Single copy per class.

Application-wide state.

Dangerous in distributed systems.

---

# Production Example

Bad:

```java
@Service
public class CounterService {

    private int counter = 0;
}
```

Spring Beans:

```text
Singleton by default
```

Multiple requests:

```text
Race Condition
```

Possible.

---

# 9. Type Conversion

---

## Widening

Safe conversion.

```java
int x = 100;

long y = x;
```

Automatic.

---

## Narrowing

Unsafe.

```java
long x = 1000;

int y = (int)x;
```

Explicit cast required.

---

# Production Incident Example

```java
long userId = 5000000000L;

int id = (int)userId;
```

Overflow.

Data corruption.

Database inconsistencies.

---

# 10. Type Promotion

Java automatically promotes types.

Example:

```java
byte a = 10;
byte b = 20;

int c = a + b;
```

Result:

```java
int
```

not byte.

Interview favorite.

---

# 11. var Keyword

Java 10 introduced:

```java
var name = "John";
```

Compiler infers:

```java
String name = "John";
```

---

# When to Use

Good:

```java
var user =
    repository.findById(id);
```

Bad:

```java
var x = calculate();
```

Type unclear.

Senior engineers optimize readability.

---

# 12. Internal Working

Consider:

```java
String name = "John";
```

Execution:

### Step 1

String object created.

### Step 2

Object allocated in heap.

### Step 3

Reference stored in stack.

### Step 4

GC tracks object lifecycle.

---

# For Primitive

```java
int age = 30;
```

Direct stack storage.

No heap object.

No GC involvement.

---

# 13. Production Usage

---

## Spring Boot DTOs

```java
public class UserRequest {

    private Long id;

    private String name;

    private Boolean active;
}
```

Choice of types matters.

---

### Why Long?

Database:

```sql
BIGINT
```

Mapping:

```java
Long
```

---

### Why Boolean?

Can represent:

```java
true
false
null
```

Useful in PATCH APIs.

---

# Microservices Serialization

JSON:

```json
{
  "id": 100,
  "active": true
}
```

Mapped to:

```java
Long id;
Boolean active;
```

Wrong data type choices can break APIs.

---

# Database Impact

Example:

```sql
salary DECIMAL(19,2)
```

Java:

```java
BigDecimal
```

Correct.

Not:

```java
double
```

Otherwise:

```text
Precision Loss
```

Financial bugs.

---

# Performance Considerations

---

## Primitive Faster Than Wrapper

```java
int x;
```

vs

```java
Integer x;
```

Wrapper involves:

* object creation
* heap allocation
* GC

---

## Collections

```java
List<Integer>
```

creates many objects.

Large-scale systems may use:

```java
Trove
FastUtil
Agrona
```

primitive collections.

---

# Memory Considerations

Approximate sizes:

| Type    | Size          |
| ------- | ------------- |
| byte    | 1             |
| short   | 2             |
| int     | 4             |
| long    | 8             |
| float   | 4             |
| double  | 8             |
| char    | 2             |
| boolean | JVM Dependent |

Wrapper objects consume significantly more memory.

Example:

```java
Integer
```

may consume:

```text
16+ bytes
```

for a 4-byte value.

---

# Security Considerations

---

## Numeric Overflow

Example:

```java
int balance = Integer.MAX_VALUE;

balance++;
```

Overflow.

Unexpected behavior.

---

## Deserialization Issues

Wrong data types in APIs:

```java
Long id
```

receives:

```json
"id":"abc"
```

Validation failure.

---

## Monetary Calculations

Never:

```java
double balance
```

Always:

```java
BigDecimal balance
```

---

# Common Mistakes

### Using Double for Money

Most common production bug.

---

### Using Integer Instead of int

Unnecessary object creation.

---

### Ignoring Nullability

```java
Integer count = null;
```

Potential NPE.

---

### Wrong Type for Database Mapping

```java
double
```

mapped to:

```sql
DECIMAL
```

Bad design.

---

### Using Static Mutable Variables

Breaks scalability.

---

# Best Practices

### Prefer Primitive Types

When null is not required.

```java
int count;
```

---

### Use Wrapper Types in DTOs

```java
Integer age;
```

when null is meaningful.

---

### Use BigDecimal for Money

```java
BigDecimal amount;
```

Always.

---

### Use Long for IDs

```java
Long userId;
```

Industry standard.

---

### Minimize Autoboxing

Avoid:

```java
Integer total = 0;
```

inside tight loops.

---

# Real-World Scenario

## Payment Service

Wrong Design:

```java
double balance;
```

After millions of transactions:

```text
0.01 cent discrepancies
```

Accumulated financial loss.

Correct Design:

```java
BigDecimal balance;
```

Used by:

* Banks
* Payment gateways
* Trading systems

because precision is mandatory.

---

# Revision Notes

* Variables are named storage locations.
* Primitives store values directly.
* References store object addresses.
* Stack stores local variables.
* Heap stores objects.
* Wrapper classes enable object behavior.
* Autoboxing/unboxing can introduce performance and NPE issues.
* Use Long for IDs.
* Use BigDecimal for money.
* Prefer primitives when null is unnecessary.
* Understand memory implications of every type choice.

---

# Cheat Sheet

| Category        | Type                                                          |
| --------------- | ------------------------------------------------------------- |
| Integer         | byte, short, int, long                                        |
| Decimal         | float, double                                                 |
| Character       | char                                                          |
| Boolean         | boolean                                                       |
| Object Versions | Byte, Short, Integer, Long, Float, Double, Character, Boolean |

### Production Mapping

| Java       | Database |
| ---------- | -------- |
| Long       | BIGINT   |
| Integer    | INT      |
| String     | VARCHAR  |
| Boolean    | BOOLEAN  |
| BigDecimal | DECIMAL  |

---

# Interview Questions / Flashcards

### Q1: Difference between primitive and reference types?

**Answer:** Primitives store actual values. References store object references pointing to heap memory.

---

### Q2: Why is Integer slower than int?

**Answer:** Integer is an object requiring heap allocation, indirection, and GC tracking.

---

### Q3: Why should money never use double?

**Answer:** Binary floating-point representation introduces precision errors.

---

### Q4: Where are local variables stored?

**Answer:** Stack frame of the executing thread.

---

### Q5: Where are objects stored?

**Answer:** Heap memory.

---

### Q6: What is autoboxing?

**Answer:** Automatic conversion of primitive to wrapper type.

```java
Integer x = 10;
```

becomes:

```java
Integer x = Integer.valueOf(10);
```

---

### Q7: Why are wrapper classes required in collections?

**Answer:** Java generics work only with reference types.

---

### Q8: Why is Long preferred for IDs?

**Answer:** Supports much larger ranges and aligns with BIGINT database columns.

---

# Active Recall Questions (with Answers)

### 1. Why does `Integer count = null; int x = count;` fail?

**Answer:** Unboxing calls `count.intValue()`, causing NPE.

---

### 2. Why are local variables naturally thread-safe?

**Answer:** Each thread has its own stack frame.

---

### 3. What memory area stores instance variables?

**Answer:** Heap.

---

### 4. Why is `BigDecimal` preferred over `double`?

**Answer:** Exact decimal arithmetic without floating-point precision errors.

---

### 5. What happens during autoboxing?

**Answer:** Primitive values are converted into wrapper objects.

---

### 6. Why can static mutable variables be dangerous in microservices?

**Answer:** Shared state causes race conditions and breaks horizontal scalability assumptions.

---

# Hands-On Exercises (with Answers)

---

## Exercise 1: Primitive vs Wrapper

Write a program showing autoboxing and unboxing.

### Solution

```java
public class Main {

    public static void main(String[] args) {

        Integer count = 100; // autoboxing

        int value = count;   // unboxing

        System.out.println(value);
    }
}
```

---

## Exercise 2: Demonstrate Overflow

### Solution

```java
public class Main {

    public static void main(String[] args) {

        int x = Integer.MAX_VALUE;

        x++;

        System.out.println(x);
    }
}
```

Output:

```text
-2147483648
```

---

## Exercise 3: Show Double Precision Issue

### Solution

```java
public class Main {

    public static void main(String[] args) {

        System.out.println(0.1 + 0.2);
    }
}
```

Output:

```text
0.30000000000000004
```

---

## Exercise 4: Fix Monetary Calculation Using BigDecimal

### Solution

```java
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        BigDecimal amount1 =
                new BigDecimal("0.1");

        BigDecimal amount2 =
                new BigDecimal("0.2");

        System.out.println(
                amount1.add(amount2)
        );
    }
}
```

Output:

```text
0.3
```

---

## Exercise 5: Build a Spring Boot DTO

### Solution

```java
public record UserRequest(
        Long id,
        String name,
        Boolean active,
        BigDecimal balance
) {
}
```

This exercise combines:

* Long IDs
* String references
* Boolean wrappers
* BigDecimal monetary values

which reflects real production-grade backend API design.
