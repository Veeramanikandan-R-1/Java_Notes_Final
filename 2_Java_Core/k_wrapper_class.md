# Wrapper Classes in Java

### Subtopics: Integer, Double, Boolean, Character, Autoboxing, Unboxing

---

# 1. Fundamentals

## What are Wrapper Classes?

Java provides **wrapper classes** to represent primitive data types as objects.

| Primitive | Wrapper Class |
| --------- | ------------- |
| byte      | Byte          |
| short     | Short         |
| int       | Integer       |
| long      | Long          |
| float     | Float         |
| double    | Double        |
| char      | Character     |
| boolean   | Boolean       |

Example:

```java
int age = 25;            // primitive
Integer ageObj = 25;     // wrapper object
```

---

## Why were Wrapper Classes introduced?

Primitive types are **not objects**.

Because of this they cannot:

* Be stored in generic collections
* Have methods
* Represent null
* Be passed where an Object is required

Wrapper classes solve these limitations.

Example:

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(10);
```

This is impossible with:

```java
List<int> numbers;   // Compilation Error
```

---

## Primitive vs Wrapper

| Primitive             | Wrapper                       |
| --------------------- | ----------------------------- |
| Stores value directly | Stores value inside an object |
| Faster                | Slightly slower               |
| Cannot be null        | Can be null                   |
| No methods            | Rich utility methods          |
| Less memory           | More memory                   |
| Cannot use Generics   | Required for Generics         |

---

# 2. Wrapper Classes

---

# Integer

Represents an `int`.

```java
Integer number = 100;
```

Useful methods:

```java
Integer.parseInt("123");

Integer.valueOf("123");

Integer.max(5, 8);

Integer.min(5, 8);

Integer.compare(10, 20);

Integer.toBinaryString(10);
```

Example

```java
String input = "500";

int value = Integer.parseInt(input);

System.out.println(value + 10);
```

Output

```
510
```

---

# Double

Represents a `double`.

```java
Double salary = 50000.75;
```

Methods

```java
Double.parseDouble("12.5");

Double.compare(10.5, 20.5);

Double.isNaN(value);

Double.isInfinite(value);
```

Example

```java
double price = Double.parseDouble("99.99");
```

---

# Boolean

Represents boolean values.

```java
Boolean active = true;
```

Methods

```java
Boolean.parseBoolean("true");

Boolean.compare(true, false);
```

Example

```java
Boolean status = Boolean.parseBoolean("true");
```

---

# Character

Represents a character.

```java
Character ch = 'A';
```

Useful methods

```java
Character.isDigit()

Character.isLetter()

Character.isUpperCase()

Character.isLowerCase()

Character.toUpperCase()

Character.toLowerCase()

Character.isWhitespace()
```

Example

```java
char c = '9';

System.out.println(Character.isDigit(c));
```

Output

```
true
```

---

# Creating Wrapper Objects

### Using Constructor (Deprecated)

```java
Integer num = new Integer(10);   // Avoid
```

---

### Using valueOf()

```java
Integer num = Integer.valueOf(10);
```

Preferred because it can reuse cached objects.

---

### Using Autoboxing

```java
Integer num = 10;
```

Most common approach.

---

# 3. Autoboxing

## What is Autoboxing?

Automatic conversion of a primitive into its wrapper object.

Example

```java
int x = 10;

Integer obj = x;
```

Compiler converts it to

```java
Integer obj = Integer.valueOf(x);
```

---

## Why?

Makes Java code shorter and easier.

Without autoboxing:

```java
Integer age = Integer.valueOf(20);
```

With autoboxing:

```java
Integer age = 20;
```

---

## Where Autoboxing Happens

### Collections

```java
List<Integer> list = new ArrayList<>();

list.add(10);
```

Compiler

```java
list.add(Integer.valueOf(10));
```

---

### Method Parameters

```java
void print(Integer value) { }

print(100);
```

Compiler boxes automatically.

---

### Return Values

```java
Integer getAge() {
    return 25;
}
```

Compiler boxes automatically.

---

# 4. Unboxing

## What is Unboxing?

Automatic conversion from wrapper object to primitive.

Example

```java
Integer obj = 50;

int value = obj;
```

Compiler converts

```java
int value = obj.intValue();
```

---

## Example

```java
Integer a = 100;

int b = a + 20;

System.out.println(b);
```

Compiler performs

```
Integer → int

then

addition
```

---

# Autoboxing + Unboxing Together

```java
Integer a = 10;

Integer b = 20;

Integer c = a + b;
```

Compiler performs

```
a.intValue()

b.intValue()

addition

Integer.valueOf(result)
```

---

# Wrapper Objects are Immutable

Wrapper objects cannot be modified.

Example

```java
Integer a = 10;

a = 20;
```

The original object isn't changed; a new `Integer` object represents `20` (or a cached instance is reused).

---

# Integer Caching

Java caches frequently used Integer objects.

Range:

```
-128 to 127
```

Example

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

Output

```
true
```

Outside cache

```java
Integer a = 500;
Integer b = 500;

System.out.println(a == b);
```

Output

```
false
```

Reason:

Different objects are created.

Always compare values using

```java
a.equals(b)
```

---

# Parsing Strings

Very common in backend applications.

```java
int age = Integer.parseInt("25");

double price = Double.parseDouble("99.95");

boolean active = Boolean.parseBoolean("true");
```

---

# Converting Primitive to String

```java
String s = String.valueOf(100);
```

---

# Converting Wrapper to Primitive

```java
Integer age = 25;

int value = age.intValue();
```

Usually unnecessary because of unboxing.

---

# Converting Primitive to Wrapper

```java
Integer.valueOf(10)
```

or

```java
Integer x = 10;
```

---

# 5. Internal Working

## How does Integer.valueOf() work?

Simplified

```java
Integer.valueOf(100)
```

Checks:

```
Is value between -128 and 127?

Yes
    Return cached object

No
    Create new Integer
```

Caching reduces object creation.

---

## How Autoboxing Works

Source code

```java
Integer age = 10;
```

Compiler generates approximately

```java
Integer age = Integer.valueOf(10);
```

---

## How Unboxing Works

Source

```java
int x = age;
```

Compiler generates

```java
int x = age.intValue();
```

---

# 6. Common Mistakes

## Mistake 1

Using `==` for wrappers

Wrong

```java
Integer a = 500;
Integer b = 500;

System.out.println(a == b);
```

Correct

```java
System.out.println(a.equals(b));
```

---

## Mistake 2

NullPointerException during unboxing

```java
Integer age = null;

int value = age;
```

Throws

```
NullPointerException
```

Because

```java
age.intValue()
```

is executed.

---

## Mistake 3

Using wrappers unnecessarily

Wrong

```java
Integer sum = 0;

for (...)
```

Prefer

```java
int sum = 0;
```

unless `null` or objects are required.

---

## Mistake 4

Using deprecated constructors

Wrong

```java
new Integer(10)
```

Correct

```java
Integer.valueOf(10)
```

or

```java
Integer x = 10;
```

---

# 7. Best Practices

✅ Use primitives whenever objects are not required.

✅ Use wrappers for Collections and Generics.

✅ Compare wrappers using `.equals()`.

✅ Handle null before unboxing.

```java
if (age != null) {
    int value = age;
}
```

✅ Prefer `valueOf()` over constructors.

✅ Avoid unnecessary boxing/unboxing in performance-critical code.

---

# 8. Real-world Scenarios

## Scenario 1: REST API

Incoming JSON

```json
{
  "age": 25
}
```

DTO

```java
class UserRequest {

    private Integer age;

}
```

`Integer` allows:

* value present
* value absent (`null`)

Using `int` would default to `0` or won't even allow to create int without initialization, making it impossible to distinguish "not provided" from an actual value.

---

## Scenario 2: Database

```java
@Column
private Integer salary;
```

Database NULL maps naturally to `Integer`.

---

## Scenario 3: Collections

```java
List<Integer> ids = List.of(1, 2, 3);
```

Collections require wrapper types because generics work only with objects.

---

## Scenario 4: Parsing Configuration

```java
String timeout = "30";

int seconds = Integer.parseInt(timeout);
```

Common in Spring Boot configuration processing.

---

## Scenario 5: Validation

```java
char c = input.charAt(0);

if (Character.isLetter(c)) {
    // Valid
}
```

---

# Code Example

```java
import java.util.*;

public class WrapperDemo {

    public static void main(String[] args) {

        Integer age = 25;                 // Autoboxing

        int primitive = age;              // Unboxing

        List<Integer> list = new ArrayList<>();

        list.add(age);

        int value = Integer.parseInt("100");

        System.out.println(value);

        Character c = 'A';

        System.out.println(Character.isUpperCase(c));

        Double d = Double.parseDouble("12.5");

        System.out.println(d);

        Boolean active = Boolean.parseBoolean("true");

        System.out.println(active);
    }
}
```

---

# Revision Notes

* Wrapper classes convert primitives into objects.
* Needed for Collections, Generics, and nullable values.
* Autoboxing = Primitive → Wrapper.
* Unboxing = Wrapper → Primitive.
* Wrapper classes are immutable.
* Integer caches values from `-128` to `127`.
* Use `.equals()` for value comparison, not `==`.
* Prefer `valueOf()` or autoboxing over constructors.
* Beware of `NullPointerException` during unboxing.

---

# Cheat Sheet

| Operation          | Code                                      |
| ------------------ | ----------------------------------------- |
| int → Integer      | `Integer.valueOf(10)` / `Integer x = 10;` |
| Integer → int      | `x.intValue()` / `int a = x;`             |
| String → int       | `Integer.parseInt("10")`                  |
| String → double    | `Double.parseDouble("1.5")`               |
| String → boolean   | `Boolean.parseBoolean("true")`            |
| int → String       | `String.valueOf(10)`                      |
| Compare wrappers   | `a.equals(b)`                             |
| Compare primitives | `a == b`                                  |
| Check digit        | `Character.isDigit(c)`                    |
| Check letter       | `Character.isLetter(c)`                   |

---

# Interview Questions with Answers

### 1. Why do wrapper classes exist?

To represent primitive values as objects so they can be used with generics, collections, APIs expecting `Object`, and to allow `null` values.

---

### 2. Difference between `parseInt()` and `valueOf()`?

`parseInt()` returns a primitive `int`.

```java
int x = Integer.parseInt("10");
```

`valueOf()` returns an `Integer` object.

```java
Integer x = Integer.valueOf("10");
```

---

### 3. What is Autoboxing?

Automatic conversion from a primitive to its wrapper object by the compiler.

---

### 4. What is Unboxing?

Automatic conversion from a wrapper object to its primitive value.

---

### 5. Why can wrappers cause `NullPointerException`?

Because unboxing a `null` wrapper calls methods like `intValue()` on `null`.

---

### 6. Why should we avoid `==` for Integer comparison?

`==` compares object references, not values. Use `.equals()` for value comparison.

---

### 7. Why are wrapper classes immutable?

Immutability makes them thread-safe, simplifies caching (such as `Integer` cache), and prevents unexpected changes after creation.

---

### 8. When should you use `Integer` instead of `int`?

When a value can be `null`, when working with collections or generics, or when an API requires an object.

---

# Hands-on Exercises

### Exercise 1

Convert the following `String` values into primitives:

```java
"100"
"99.99"
"true"
```

**Answer**

```java
int a = Integer.parseInt("100");
double b = Double.parseDouble("99.99");
boolean c = Boolean.parseBoolean("true");
```

---

### Exercise 2

Convert primitives into wrapper objects.

```java
int age = 25;
double salary = 5000.5;
char grade = 'A';
```

**Answer**

```java
Integer ageObj = age;
Double salaryObj = salary;
Character gradeObj = grade;
```

---

### Exercise 3

Fix the bug.

```java
Integer a = 500;
Integer b = 500;

if (a == b)
    System.out.println("Equal");
```

**Answer**

```java
if (a.equals(b))
    System.out.println("Equal");
```

---

### Exercise 4

What happens?

```java
Integer age = null;

int value = age;
```

**Answer**

Throws `NullPointerException` because unboxing invokes `age.intValue()` on a `null` reference.

---

### Exercise 5

Write a method that accepts a list of numeric strings and returns their sum.

**Answer**

```java
public static int sum(List<String> numbers) {
    int sum = 0;

    for (String num : numbers) {
        sum += Integer.parseInt(num);
    }

    return sum;
}
```

---

### Exercise 6

Validate a password to ensure it contains at least one digit and one uppercase letter using `Character` methods.

**Answer**

```java
public static boolean isValid(String password) {
    boolean hasDigit = false;
    boolean hasUpper = false;

    for (char c : password.toCharArray()) {
        if (Character.isDigit(c)) {
            hasDigit = true;
        }
        if (Character.isUpperCase(c)) {
            hasUpper = true;
        }
    }

    return hasDigit && hasUpper;
}
```

This demonstrates practical use of wrapper utility methods commonly seen in input validation and backend applications.
