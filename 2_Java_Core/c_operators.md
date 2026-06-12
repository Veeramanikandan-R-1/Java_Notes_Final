# Java Operators — Complete Deep Dive for Senior Java Backend Engineers

Operators are among the most frequently used constructs in Java. Every conditional check, every database ID increment, every Spring Security authorization rule, every cache validation, every Kafka offset comparison, every retry mechanism, and every distributed-system decision ultimately depends on operators.

Most developers learn operators as simple symbols (`+`, `-`, `==`, `&&`) and move on.

Senior engineers understand:

* How operators are evaluated internally
* Performance implications
* JVM bytecode generation
* Autoboxing side effects
* Null-safety issues
* Concurrency implications
* Bugs caused by operator precedence
* How operators affect large-scale backend systems

---

# 1. Why Operators Exist

An operator allows Java to perform operations on values and variables.

Without operators:

```java
if(age > 18)
```

would become:

```java
if(compare(age, 18) == GREATER)
```

Operators provide:

* Readability
* Performance
* Concise expressions
* Compiler optimizations

Every programming language uses operators because they map naturally to CPU instructions.

---

# 2. Classification of Java Operators

Java operators are grouped into:

| Category                  | Operators          |
| ------------------------- | ------------------ |
| Arithmetic                | + - * / %          |
| Unary                     | ++ -- + - ! ~      |
| Relational                | == != > < >= <=    |
| Logical                   | && || !            |
| Bitwise                   | & | ^ ~            |
| Shift                     | << >> >>>          |
| Assignment                | = += -= *= /= %=   |
| Conditional/Ternary       | ? :                |
| instanceof                | instanceof         |
| Type Cast                 | (type)             |
| Java 14+ Pattern Matching | instanceof pattern |

Let's go from simple to advanced.

---

# 3. Arithmetic Operators

## Addition (+)

```java
int a = 10;
int b = 20;

int c = a + b;
```

Result:

```java
30
```

---

## Subtraction (-)

```java
int result = 20 - 10;
```

---

## Multiplication (*)

```java
int result = 5 * 4;
```

---

## Division (/)

```java
int result = 10 / 3;
```

Output:

```java
3
```

Integer division truncates decimal values.

---

### Production Bug Example

```java
double avg = total/count;
```

If both are int:

```java
100/3 = 33
```

Then:

```java
33.0
```

Not:

```java
33.333
```

Correct:

```java
double avg = (double) total / count;
```

---

## Modulus (%)

Returns remainder.

```java
10 % 3
```

Output:

```java
1
```

---

### Real Usage

Load balancing:

```java
serverId = userId % totalServers;
```

Kafka partitioning:

```java
partition = hash(key) % partitions;
```

Database sharding:

```java
customerId % 64
```

---

# 4. Unary Operators

Operate on one operand.

---

## Unary Plus

```java
+5
```

Rarely used.

---

## Unary Minus

```java
-5
```

Negates value.

---

## Increment (++)

### Pre Increment

```java
++a
```

Increment first.

Then return.

```java
int a = 5;
int b = ++a;
```

Result:

```java
a = 6
b = 6
```

---

### Post Increment

```java
a++
```

Return first.

Then increment.

```java
int a = 5;
int b = a++;
```

Result:

```java
a = 6
b = 5
```

---

## JVM Bytecode

```java
i++
```

becomes

```java
iinc
```

Special optimized instruction.

Very fast.

---

### Concurrency Danger

Never use:

```java
counter++;
```

for shared state.

Because:

```java
read
increment
write
```

are 3 operations.

Not atomic.

Use:

```java
AtomicInteger.incrementAndGet();
```

---

# 5. Relational Operators

Used for comparison.

---

## Equal (==)

```java
a == b
```

Checks value for primitives.

---

### Primitive Example

```java
int a = 10;
int b = 10;

a == b
```

True.

---

### Object Example

```java
String a = new String("Java");
String b = new String("Java");

a == b
```

False.

Because references differ.

---

### Correct Comparison

```java
a.equals(b)
```

True.

---

### Common Production Bug

```java
if(role == "ADMIN")
```

Wrong.

Use:

```java
if("ADMIN".equals(role))
```

This is a very common Java bug because `==` and `.equals()` do different things for objects like `String`.

### `==` compares object references

```java
String role = new String("ADMIN");

if (role == "ADMIN") {
    System.out.println("Admin");
}
```

`role == "ADMIN"` asks:

> "Do these two variables point to the exact same object in memory?"

Since `new String("ADMIN")` creates a new object, the comparison is usually `false`.

---

### `.equals()` compares contents

```java
if ("ADMIN".equals(role)) {
    System.out.println("Admin");
}
```

This asks:

> "Do these strings contain the same characters?"

Since both strings contain `"ADMIN"`, it returns `true`.

---

### Why use `"ADMIN".equals(role)` instead of `role.equals("ADMIN")`?

Null safety.

#### Risky

```java
String role = null;

if (role.equals("ADMIN")) {   // NullPointerException
}
```

#### Safe

```java
String role = null;

if ("ADMIN".equals(role)) {   // false
}
```

The string literal `"ADMIN"` can never be `null`, so calling `.equals()` on it is safe.

---

### Why does `==` sometimes appear to work?

Java interns string literals:

```java
String a = "ADMIN";
String b = "ADMIN";

System.out.println(a == b);  // true
```

Both variables point to the same interned string object.

But:

```java
String a = "ADMIN";
String b = new String("ADMIN");

System.out.println(a == b);      // false
System.out.println(a.equals(b)); // true
```

This inconsistency is why using `==` for string content comparison is a common production bug.

### Rule of thumb

* Use `==` for primitives (`int`, `long`, `boolean`, etc.).
* Use `.equals()` for comparing string values and most other objects.
* Prefer the null-safe form:

```java
if ("ADMIN".equals(role)) {
    ...
}
```

or, if case should not matter:

```java
if ("ADMIN".equalsIgnoreCase(role)) {
    ...
}
```
---

## Not Equal (!=)

```java
a != b
```

---

## Greater Than

```java
a > b
```

---

## Less Than

```java
a < b
```

---

## Greater Than Equal

```java
a >= b
```

---

## Less Than Equal

```java
a <= b
```

---

# 6. Logical Operators

Used with boolean expressions.

---

## AND (&&)

```java
isActive && hasPermission
```

Both must be true.

---

## OR (||)

```java
isAdmin || isOwner
```

One must be true.

---

## NOT (!)

```java
!isBlocked
```

Negates value.

---

# 7. Short-Circuit Evaluation

Critical interview topic.

---

## AND

```java
if(user != null && user.getId() > 0)
```

Step 1:

```java
user != null
```

If false:

Second condition skipped.

Prevents:

```java
NullPointerException
```

---

## OR

```java
if(cacheHit || databaseCheck())
```

If cacheHit true:

Database call skipped.

Huge performance benefit.

---

### Spring Security Example

```java
if(isAdmin() || hasRole("SUPPORT"))
```

Second method may never execute.

---

# 8. Bitwise Operators

Operate at bit level.

---

## AND (&)

```java
5 & 3
```

Binary:

```text
101
011
---
001
```

Result:

```java
1
```

---

## OR (|)

```java
5 | 3
```

Result:

```java
7
```

---

## XOR (^)

Different bits become 1.

```java
5 ^ 3
```

Result:

```java
6
```

---

## Complement (~)

Flips bits.

```java
~5
```

Advanced low-level operation.

---

### Real Usage

Permissions flags:

```java
READ = 1
WRITE = 2
DELETE = 4
```

Combine:

```java
READ | WRITE
```

---

# 9. Shift Operators

Used for fast binary manipulation.

---

## Left Shift (<<)

```java
5 << 1
```

Equivalent:

```java
5 * 2
```

Result:

```java
10
```

---

## Right Shift (>>)

```java
10 >> 1
```

Equivalent:

```java
10 / 2
```

Result:

```java
5
```

---

## Unsigned Right Shift (>>>)

Used in hashing algorithms.

Important in:

* HashMap
* ConcurrentHashMap
* Kafka
* Netty

---

### HashMap Internal

Hash spreading:

```java
h ^ (h >>> 16)
```

Reduces collisions.

---

# 10. Assignment Operators

---

## Simple Assignment

```java
a = 10;
```

---

## Compound Assignment

```java
a += 5;
```

Equivalent:

```java
a = a + 5;
```

---

### Hidden Casting Behavior

```java
short s = 1;

s += 1;
```

Works.

But:

```java
s = s + 1;
```

Compilation error.

Because:

```java
s + 1
```

becomes int.

---

# 11. Ternary Operator

Compact if-else.

```java
String result =
    age >= 18 ? "Adult" : "Minor";
```

---

### Production Usage

DTO mapping:

```java
String status =
    active ? "ACTIVE" : "INACTIVE";
```

---

### Avoid

```java
a ? b ? c : d : e
```

Unreadable.

---

# 12. instanceof Operator

Checks runtime type.

```java
if(obj instanceof User)
```

---

## Internal Working

Checks object metadata stored in JVM.

Very fast.

---

## Java 16 Pattern Matching

Old:

```java
if(obj instanceof User){
    User user = (User)obj;
}
```

New:

```java
if(obj instanceof User user){
    user.getId();
}
```

Cleaner.

---

# 13. Type Casting Operator

---

## Upcasting

```java
Animal a = new Dog();
```

Implicit.

Safe.

---

## Downcasting

```java
Dog d = (Dog) animal;
```

Explicit.

Potential:

```java
ClassCastException
```

---

### Safe Version

```java
if(animal instanceof Dog d){
    d.bark();
}
```

In Java, **upcasting** and **downcasting** refer to converting references between a superclass and a subclass.

## Upcasting

Upcasting means converting a **subclass object reference** to a **superclass reference**.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}

Dog dog = new Dog();
Animal animal = dog;   // Upcasting
```

### Characteristics

* Happens **automatically** (implicit).
* Safe.
* You can access only members defined in the superclass reference type.

```java
animal.eat();   // OK
// animal.bark(); // Compile-time error
```

Even though the actual object is a `Dog`, the reference type is `Animal`.

---

## Downcasting

Downcasting means converting a **superclass reference** back to a **subclass reference**.

```java
Animal animal = new Dog();

Dog dog = (Dog) animal;   // Downcasting
dog.bark();
```

### Characteristics

* Requires an **explicit cast**.
* Can be unsafe if the object is not actually of that subclass.

---

## Safe Downcasting

Use `instanceof` before downcasting.

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.bark();
}
```

Java 16+ pattern matching:

```java
if (animal instanceof Dog dog) {
    dog.bark();
}
```

---

## Runtime Error Example

```java
Animal animal = new Animal();

Dog dog = (Dog) animal;   // Runtime error
```

Result:

```text
ClassCastException
```

Because the object is actually an `Animal`, not a `Dog`.

---

## Real-world Example

```java
List<String> list = new ArrayList<>();

Object obj = list;              // Upcasting
List<String> list2 = (List<String>) obj; // Downcasting
```

`ArrayList` → `List` → `Object` is common upcasting used throughout Java collections and frameworks.

---

### Summary

| Operation           | Example                       | Cast Required | Safe?                            |
| ------------------- | ----------------------------- | ------------- | -------------------------------- |
| Upcasting           | `Animal a = new Dog();`       | No            | Yes                              |
| Downcasting         | `Dog d = (Dog) a;`            | Yes           | Only if object is actually `Dog` |
| Invalid Downcasting | `Dog d = (Dog) new Animal();` | Yes           | Throws `ClassCastException`      |

A simple way to remember:

* **Upcasting** = move **up** the inheritance hierarchy (`Dog → Animal`).
* **Downcasting** = move **down** the inheritance hierarchy (`Animal → Dog`).

We need **upcasting** and **downcasting** because they enable **polymorphism**, which is one of the core principles of object-oriented programming.

## Why Upcasting?

Suppose you have multiple types of animals:

```java id="w15uw6"
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    void makeSound() {
        System.out.println("Meow");
    }
}
```

Without upcasting, you'd need separate code for each type:

```java id="0ep1uv"
Dog dog = new Dog();
Cat cat = new Cat();
```

With upcasting:

```java id="4gkk0r"
Animal a1 = new Dog();
Animal a2 = new Cat();
```

Now you can write generic code:

```java id="ul2dqa"
List<Animal> animals = List.of(
    new Dog(),
    new Cat()
);

for (Animal animal : animals) {
    animal.makeSound();
}
```

Output:

```text id="ymkz66"
Bark
Meow
```

The JVM calls the correct overridden method based on the actual object type. This is **runtime polymorphism**.

### Real-world examples

Java frameworks use upcasting everywhere:

```java id="0mnbtm"
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
```

You program against the interface (`List`, `Map`) instead of the implementation (`ArrayList`, `HashMap`).

---

## Why Downcasting?

Sometimes you know the actual object is a subclass and need subclass-specific functionality.

```java id="qq1z6s"
Animal animal = new Dog(); // Upcast

animal.makeSound(); // OK
```

But:

```java id="3xix7c"
animal.bark(); // Compile error
```

The compiler only knows `animal` as an `Animal`.

If you need `Dog`-specific methods:

```java id="yj3fyz"
Dog dog = (Dog) animal; // Downcast
dog.bark();
```

---

## Common Framework Example

```java id="1ng78h"
Object obj = "Hello";
```

The variable is typed as `Object`, but later:

```java id="bxx4rt"
String str = (String) obj;
System.out.println(str.length());
```

Here downcasting lets you recover the specific type and use its methods.

---

## In Practice

### Upcasting

Used **all the time**:

* Interfaces (`List`, `Map`, `Set`)
* Spring beans
* Dependency Injection
* Polymorphism
* Design patterns

### Downcasting

Used **less frequently**:

* When APIs return a generic type (`Object`)
* Legacy code
* Framework internals
* When subclass-specific behavior is needed

If you find yourself doing lots of downcasting, it's often a sign that the design can be improved by adding methods to the parent type or using interfaces.

### Short answer

* **Upcasting** lets different subclasses be treated uniformly through a common parent type.
* **Downcasting** lets you regain access to subclass-specific behavior when you know the actual object type.

That's a good observation. In well-designed object-oriented code, you often **don't need downcasting at all**.

For example:

```java
abstract class Animal {
    abstract void makeSound();
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow");
    }
}
```

Then:

```java
Animal animal = new Dog();
animal.makeSound();
```

No downcasting needed. The design relies on polymorphism.

---

### So why does downcasting exist?

Because sometimes the parent type intentionally does **not** expose everything the child can do.

Example:

```java
interface Shape {
    double area();
}

class Circle implements Shape {
    double radius() { return r; }
}
```

A method may accept any shape:

```java
void printArea(Shape shape) {
    System.out.println(shape.area());
}
```

Most of the time that's all you need.

But occasionally you might receive a `Shape` and discover that if it's a `Circle`, you want some circle-specific information:

```java
if (shape instanceof Circle circle) {
    System.out.println(circle.radius());
}
```

That's a legitimate use of downcasting.

---

### Why not just put every method in the parent?

Imagine:

```java
class Animal {
    void bark() {}
    void meow() {}
    void fly() {}
}
```

Now every animal has methods that don't make sense.

```java
Cat cat = new Cat();
cat.bark(); // nonsense
```

The parent should only contain behavior common to all children.

That's why child-specific methods exist, and sometimes accessing them requires downcasting.

---

### In modern Java, frequent downcasting is usually a design smell

When you see code like:

```java
if (animal instanceof Dog) {
    ...
} else if (animal instanceof Cat) {
    ...
} else if (animal instanceof Bird) {
    ...
}
```

it often means polymorphism wasn't used effectively.

A better design is usually:

```java
animal.performAction();
```

and let each subclass implement its own behavior.

---

### Then why is upcasting still important?

Even if you design classes perfectly, upcasting is fundamental.

```java
List<String> list = new ArrayList<>();
```

This is upcasting:

```java
ArrayList<String> -> List<String>
```

It allows code to depend on abstractions rather than implementations.

If Java didn't support upcasting, you'd write:

```java
ArrayList<String> list = new ArrayList<>();
```

everywhere, making it harder to switch implementations later.

---

### Practical takeaway

* **Upcasting is essential** and used constantly because it enables abstraction and polymorphism.
* **Downcasting is sometimes necessary**, but good designs minimize it.
* If you're frequently downcasting, ask whether the behavior belongs in the parent interface/class instead.
* Many senior Java developers view "needing lots of downcasts" as a signal to revisit the design.

---

# 14. Operator Precedence

Major interview topic.

---

Example:

```java
int result = 10 + 20 * 3;
```

Result:

```java
70
```

Not:

```java
90
```

Because:

```java
*
```

executes first.

---

### Common Production Bug

```java
if(a || b && c)
```

Means:

```java
a || (b && c)
```

Not:

```java
(a || b) && c
```

Always use parentheses.

---

# 15. Internal JVM Working

Java source:

```java
int c = a + b;
```

Compiler:

```java
bytecode:
iload_1
iload_2
iadd
```

JIT may optimize further.

CPU executes machine instructions.

Operator execution is extremely fast because JVM maps many operators directly to CPU instructions.

---

# 16. Spring Boot Usage

Operators appear everywhere.

---

## Validation

```java
if(age >= 18)
```

---

## Security

```java
if(isAdmin || isOwner)
```

---

## Feature Flags

```java
if(flagEnabled && userEligible)
```

---

## DTO Mapping

```java
status = active ? "ACTIVE" : "INACTIVE";
```

---

## Rate Limiting

```java
requests % bucketSize
```

---

# 17. Database Impact

Operators frequently drive SQL generation.

JPA:

```java
findBySalaryGreaterThan()
```

Generates:

```sql
salary >
```

---

Range query:

```java
salary >= ?
```

---

Pagination:

```java
offset = page * size;
```

Arithmetic operators.

---

Sharding:

```java
userId % shards
```

Critical.

---

# 18. Performance Considerations

## Short-Circuit Saves CPU

Good:

```java
user != null &&
user.isActive()
```

Avoids unnecessary call.

---

## Bit Shift Faster?

Historically:

```java
x << 1
```

vs

```java
x * 2
```

Modern JVM optimizes both.

No need for micro-optimization.

---

## Avoid Excessive Boxing

Bad:

```java
Integer a = 100;
Integer b = 100;

a == b
```

May give surprising results.

Use:

```java
a.equals(b)
```

---

# 19. Memory Considerations

Operators on primitives:

```java
int
long
double
```

operate directly.

Minimal memory overhead.

---

Boxed types:

```java
Integer
Long
Double
```

create objects.

More memory.

More GC pressure.

---

# 20. Security Considerations

---

## Null Checks

Safe:

```java
if(user != null && user.isActive())
```

---

## String Comparison

Safe:

```java
"ADMIN".equals(role)
```

---

## Authorization

```java
isAdmin || isOwner
```

Must be carefully reviewed.

Incorrect operator choice can create privilege escalation bugs.

---

# Common Mistakes

### Mistake 1

```java
String a = new String("x");
String b = new String("x");

a == b
```

Use:

```java
a.equals(b)
```

---

### Mistake 2

```java
counter++;
```

Shared variable.

Not thread-safe.

---

### Mistake 3

```java
if(a || b && c)
```

Missing parentheses.

---

### Mistake 4

```java
10/3
```

Expecting decimal.

---

### Mistake 5

```java
Integer a = 128;
Integer b = 128;

a == b
```

False.

Use equals.

---

# Best Practices

1. Use `.equals()` for object comparison.
2. Prefer short-circuit operators (`&&`, `||`).
3. Use parentheses for clarity.
4. Avoid nested ternary operators.
5. Use `AtomicInteger` instead of `++` in concurrent code.
6. Use pattern matching `instanceof`.
7. Avoid relying on operator precedence.
8. Be careful with autoboxing.

---

# Real-World Scenarios

## Kafka Partition Selection

```java
partition = hash(key) % partitions;
```

---

## API Rate Limiter

```java
requestCount >= limit
```

---

## Circuit Breaker

```java
failures > threshold
```

---

## Load Balancer

```java
server = requestId % serverCount;
```

---

## Spring Security

```java
isAdmin || isOwner
```

---

# Revision Notes

* `==` compares primitives by value, objects by reference.
* `.equals()` compares object content.
* `&&` and `||` short-circuit.
* `&` and `|` do not short-circuit.
* `%` used heavily in partitioning/sharding.
* `++` is not thread-safe.
* `instanceof` supports pattern matching.
* Compound assignments perform implicit casting.
* Parentheses improve readability and safety.
* Bitwise operators are heavily used in frameworks and hashing.

---

# One-Page Cheat Sheet

| Operator Type | Operators       |
| ------------- | --------------- |
| Arithmetic    | + - * / %       |
| Unary         | ++ -- + - ! ~   |
| Relational    | == != > < >= <= |
| Logical       | && || !         |
| Bitwise       | & | ^ ~         |
| Shift         | << >> >>>       |
| Assignment    | = += -= *=      |
| Conditional   | ? :             |
| Type Check    | instanceof      |
| Cast          | (Type)          |

---

# 20 Flashcards

### 1

Q: Difference between `==` and `.equals()`?

A: `==` compares references (objects), `.equals()` compares content.

### 2

Q: What is short-circuiting?

A: Skipping evaluation when result is already known.

### 3

Q: Which operator prevents NPE checks?

A: `&&`

### 4

Q: Is `counter++` atomic?

A: No.

### 5

Q: Purpose of `%`?

A: Remainder operation; used in hashing and partitioning.

### 6

Q: Difference between `&` and `&&`?

A: `&` evaluates both sides, `&&` short-circuits.

### 7

Q: Difference between `|` and `||`?

A: `|` evaluates both sides, `||` short-circuits.

### 8

Q: What does `^` do?

A: XOR.

### 9

Q: Purpose of `>>>`?

A: Unsigned right shift.

### 10

Q: Safe string comparison?

A: `"CONST".equals(variable)`.

### 11–20

* What is operator precedence?
* What is autoboxing?
* Why use `AtomicInteger`?
* What is pattern matching?
* What is downcasting?
* What is upcasting?
* Why avoid nested ternary?
* Why use parentheses?
* How does HashMap use `>>>`?
* Why is `%` used in sharding?

---

# Mind Map

```text
Operators
├── Arithmetic
│   ├── +
│   ├── -
│   ├── *
│   ├── /
│   └── %
├── Unary
│   ├── ++
│   ├── --
│   ├── !
│   └── ~
├── Relational
├── Logical
├── Bitwise
├── Shift
├── Assignment
├── Ternary
├── instanceof
└── Casting
```

---

# Top 10 Key Takeaways

1. Operators map closely to JVM bytecode and CPU instructions.
2. `==` vs `.equals()` is a common source of bugs.
3. Short-circuit operators improve safety and performance.
4. `%` is foundational in distributed systems.
5. `++` is unsafe in concurrent environments.
6. Bitwise operators power hashing and permissions.
7. `instanceof` pattern matching simplifies code.
8. Operator precedence causes subtle production bugs.
9. Compound assignments perform implicit casts.
10. Senior engineers prioritize readability over clever operator tricks.

---

# Active Recall Questions

1. Why is `counter++` not thread-safe?
2. How does short-circuit evaluation prevent NPEs?
3. When should you use `&` instead of `&&`?
4. Why does `10 / 3` return `3`?
5. How does HashMap use shift operators?
6. Why is `%` important for sharding?
7. What problem does `instanceof` pattern matching solve?
8. What is the difference between signed and unsigned shifts?
9. Why can `Integer == Integer` be dangerous?
10. What are the JVM bytecode instructions for arithmetic operations?

---

# Hands-On Exercises (with Expected Outcome)

### Exercise 1: Safe User Validation

```java
User user = null;
```

Write a condition that safely checks:

* user is not null
* user is active

**Answer**

```java
if(user != null && user.isActive())
```

---

### Exercise 2: Build a Round-Robin Load Balancer

```java
int requestId = 105;
int servers = 4;
```

Find server.

**Answer**

```java
int server = requestId % servers;
```

Output:

```java
1
```

---

### Exercise 3: Implement Permission Flags

```java
READ = 1;
WRITE = 2;
DELETE = 4;
```

Grant READ + WRITE.

**Answer**

```java
int permission = READ | WRITE;
```

---

### Exercise 4: Compare Integer Objects Safely

```java
Integer a = 200;
Integer b = 200;
```

**Answer**

```java
a.equals(b)
```

---

### Exercise 5: Thread-Safe Counter

**Answer**

```java
AtomicInteger counter = new AtomicInteger();

counter.incrementAndGet();
```

This is the depth expected from a Senior Java Backend Engineer: not just knowing operators, but understanding how they influence JVM behavior, concurrency, Spring Boot applications, database access patterns, and distributed-system design.
