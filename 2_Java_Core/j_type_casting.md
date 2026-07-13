# Type Casting in Java (Implicit & Explicit)

Type casting is the process of converting a value from one data type to another. Java supports both **automatic conversion (implicit)** and **manual conversion (explicit)**.

---

# 1. Fundamentals

## What is Type Casting?

Type casting changes the representation of a value so it can be stored in another data type.

Example:

```java
int age = 25;
double salary = age;   // int -> double
```

Here, `25` is converted to `25.0`.

---

## Why is Type Casting Needed?

Different APIs, libraries, databases, and calculations often use different data types.

Examples:

* Reading an integer but storing it as double
* Database `BIGINT` to Java `long`
* Integer division converted to floating-point
* Converting superclass references to subclass references

Without type casting, many assignments would not compile.

---

## Types of Casting

### 1. Implicit (Widening)

Performed automatically by Java.

Safe conversion.

```
byte
  ↓
short
  ↓
int
  ↓
long
  ↓
float
  ↓
double
```

---

### 2. Explicit (Narrowing)

Performed manually.

Possible data loss.

```
double
  ↓
float
  ↓
long
  ↓
int
  ↓
short
  ↓
byte
```

---

# 2. Cover All Concepts

## A. Implicit Type Casting (Widening)

Java automatically converts smaller primitive types into larger ones.

Example

```java
int num = 100;
long value = num;
```

Memory

```
int (32 bits)
100

↓

long (64 bits)
100
```

No information is lost.

---

Another example

```java
char ch = 'A';

int ascii = ch;

System.out.println(ascii);
```

Output

```
65
```

Java converts the Unicode value automatically.

---

### When does Java allow implicit casting?

Only when:

* No precision loss
* No overflow
* Destination type can represent all values

---

## B. Explicit Type Casting (Narrowing)

Converting a larger type into a smaller one.

Java requires the programmer to confirm the conversion.

Example

```java
double value = 15.75;

int number = (int) value;

System.out.println(number);
```

Output

```
15
```

Decimal part is removed.

---

Another example

```java
long salary = 50000;

int amount = (int) salary;
```

Works because the value fits inside an int.

---

Overflow example

```java
int value = 130;

byte b = (byte) value;

System.out.println(b);
```

Output

```
-126
```

Why?

Byte range:

```
-128 to 127
```

130 exceeds the range, so overflow occurs.

---

## Primitive Casting Rules

### Automatic

```java
byte → short
short → int
char → int
int → long
long → float
float → double
```

---

### Manual

```java
double → float
double → int
long → int
int → short
short → byte
```

---

## Type Promotion in Expressions

Java automatically promotes smaller types during arithmetic.

Example

```java
byte a = 10;
byte b = 20;

int result = a + b;
```

Result type is **int**, not byte.

Even though both operands are bytes.

Reason:

Arithmetic on `byte`, `short`, and `char` is always promoted to `int`.

---

Example

```java
byte x = 10;
byte y = 20;

byte z = (byte)(x + y);
```

Explicit cast is required.

---

## Casting in Division

Example

```java
int a = 5;
int b = 2;

System.out.println(a / b);
```

Output

```
2
```

Because integer division removes decimals.

To get decimal:

```java
double result = (double) a / b;
```

Output

```
2.5
```

---

## Casting with Characters

Characters are internally stored as Unicode integers.

Example

```java
char ch = 'A';

int ascii = ch;

char next = (char)(ch + 1);

System.out.println(next);
```

Output

```
B
```

---

## Object Type Casting

Type casting is also applicable to objects.

### Upcasting

Subclass → Superclass

Automatic.

```java
class Animal {}

class Dog extends Animal {}

Animal a = new Dog();
```

Safe.

---

### Downcasting

Superclass → Subclass

Manual.

```java
Animal a = new Dog();

Dog d = (Dog) a;
```

Requires explicit cast.

---

Invalid example

```java
Animal a = new Animal();

Dog d = (Dog) a;
```

Runtime

```
ClassCastException
```

Always verify before downcasting.

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
}
```

---

# 3. Internal Working

## How does implicit casting work?

Compiler inserts conversion instructions automatically.

Example

```java
int x = 100;

double y = x;
```

Conceptually becomes

```
Load int

↓

Convert to double

↓

Store double
```

---

## How does explicit casting work?

Compiler verifies the cast syntax.

At runtime:

* Higher bits may be discarded
* Decimal values truncated
* Overflow may occur
* Object compatibility checked

---

## Why arithmetic returns int?

CPU performs integer arithmetic efficiently.

Therefore Java promotes:

```
byte
short
char
```

to

```
int
```

before calculations.

---

# 4. Common Mistakes

### Mistake 1

Expecting decimal after integer division.

```java
int a = 5;
int b = 2;

double c = a / b;
```

Output

```
2.0
```

Correct

```java
double c = (double)a / b;
```

---

### Mistake 2

Ignoring overflow.

```java
int value = 1000;

byte b = (byte)value;
```

Unexpected result.

---

### Mistake 3

Unsafe downcasting.

```java
Animal a = new Animal();

Dog d = (Dog)a;
```

Throws

```
ClassCastException
```

---

### Mistake 4

Forgetting arithmetic promotion.

```java
byte a = 10;
byte b = 20;

byte c = a + b;
```

Compilation error.

---

# 5. Best Practices

* Prefer implicit casting whenever possible.
* Avoid unnecessary casts.
* Use explicit casts only when you understand possible data loss.
* Validate object types using `instanceof` before downcasting.
* Never rely on overflow behavior.
* Cast before arithmetic if precision is required.

Example

```java
double average = (double) total / count;
```

Not

```java
double average = total / count;
```

---

# 6. Code Examples

## Example 1

```java
int age = 25;

double value = age;

System.out.println(value);
```

Output

```
25.0
```

---

## Example 2

```java
double price = 99.95;

int rounded = (int) price;

System.out.println(rounded);
```

Output

```
99
```

---

## Example 3

```java
char ch = 'A';

System.out.println((int) ch);
```

Output

```
65
```

---

## Example 4

```java
byte a = 10;
byte b = 20;

byte c = (byte)(a + b);

System.out.println(c);
```

Output

```
30
```

---

## Example 5

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
}
```

---

# 7. Real-world Scenarios

### Financial Applications

```java
double tax = 18.75;

int roundedTax = (int) tax;
```

Used when only whole numbers are required.

---

### Database Integration

Database

```
BIGINT
```

Java

```java
long id;
```

Sometimes converted to:

```java
int
```

after validating the range.

---

### REST APIs

JSON numbers may arrive as:

```java
double
```

Business logic may require:

```java
int
```

Explicit casting is needed after validation.

---

### Collections

```java
Object obj = "Java";

String value = (String) obj;
```

Object references often require casting when working with generic or legacy APIs.

---

# Revision Notes

* Type casting converts one type into another.
* Implicit casting is automatic and safe.
* Explicit casting is manual and may lose data.
* Integer division removes decimals.
* `byte`, `short`, and `char` are promoted to `int` during arithmetic.
* Downcasting requires explicit casting.
* Invalid downcasting causes `ClassCastException`.
* Use `instanceof` before downcasting.

---

# Cheat Sheet

| Conversion            | Automatic | Manual |
| --------------------- | --------- | ------ |
| byte → int            | ✅         | ❌      |
| int → long            | ✅         | ❌      |
| long → double         | ✅         | ❌      |
| double → int          | ❌         | ✅      |
| int → byte            | ❌         | ✅      |
| Superclass → Subclass | ❌         | ✅      |
| Subclass → Superclass | ✅         | ❌      |

**Remember:**

```
Small → Large = Automatic (Widening)

Large → Small = Manual (Narrowing)
```

Arithmetic promotion:

```
byte + byte → int
short + short → int
char + char → int
```

---

# Interview Questions with Answers

### 1. What is type casting in Java?

Converting a value from one data type to another, either automatically (implicit) or manually (explicit).

---

### 2. What is widening casting?

Automatic conversion from a smaller data type to a larger compatible data type without data loss.

Example:

```java
int x = 10;
long y = x;
```

---

### 3. What is narrowing casting?

Manual conversion from a larger data type to a smaller one, which may lose data or overflow.

Example:

```java
double d = 5.9;
int i = (int) d;
```

---

### 4. Why does `byte + byte` return `int`?

Java promotes `byte`, `short`, and `char` to `int` before performing arithmetic to simplify operations and align with CPU instruction efficiency.

---

### 5. What is the difference between upcasting and downcasting?

* **Upcasting:** Subclass → Superclass, automatic and safe.
* **Downcasting:** Superclass → Subclass, explicit and requires runtime type compatibility.

---

# Hands-on Exercises

### Exercise 1

Convert an `int` to `double` using implicit casting.

**Answer**

```java
int n = 10;
double d = n;
```

---

### Exercise 2

Convert a `double` to `int` and observe the output.

**Answer**

```java
double d = 45.89;
int n = (int) d;

System.out.println(n);
```

Output:

```
45
```

---

### Exercise 3

Write a program that calculates the average of two integers and returns the correct decimal value.

**Answer**

```java
int a = 5;
int b = 2;

double avg = (double) (a + b) / 2;

System.out.println(avg);
```

Output:

```
3.5
```

---

### Exercise 4

Demonstrate overflow by casting an `int` to `byte`.

**Answer**

```java
int value = 130;

byte b = (byte) value;

System.out.println(b);
```

Output:

```
-126
```

---

### Exercise 5

Create an `Animal` superclass and a `Dog` subclass. Perform safe downcasting using `instanceof`.

**Answer**

```java
class Animal {}

class Dog extends Animal {
    void bark() {
        System.out.println("Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();

        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.bark();
        }
    }
}
```

Output:

```
Woof!
```
