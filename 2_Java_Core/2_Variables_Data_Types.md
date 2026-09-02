# Java Basics — Variables & Data Types

## 1. Variables

A **variable** is a named memory location used to store a value.

```java
int age = 28;
String name = "John";
```

Here:

* `int` → data type
* `age` → variable
* `28` → value

### Variable declaration vs initialization

```java
int age;       // declaration
age = 28;      // initialization
```

Or together:

```java
int age = 28;
```

---

## 2. Java Data Types

Java has **2 categories**:

```text
Data Types
├── Primitive
└── Reference
```

### A. Primitive Types ⭐

There are **8 primitive types**:

| Type      |           Size | Example                  | Use              |
| --------- | -------------: | ------------------------ | ---------------- |
| `byte`    |          8-bit | `byte b = 10;`           | Small integers   |
| `short`   |         16-bit | `short s = 100;`         | Small integers   |
| `int`     |         32-bit | `int age = 28;`          | Most integers    |
| `long`    |         64-bit | `long id = 100L;`        | Large integers   |
| `float`   |         32-bit | `float x = 10.5f;`       | Decimal          |
| `double`  |         64-bit | `double price = 99.99;`  | Decimal, default |
| `char`    |         16-bit | `char grade = 'A';`      | Single character |
| `boolean` | JVM-dependent* | `boolean active = true;` | `true/false`     |

*Java language specifies `boolean` values, but doesn't define a fixed storage size.

### Important:

```java
long id = 100L;
float price = 10.5f;
double salary = 50000.50;
char grade = 'A';
boolean active = true;
```

`int`, `double`, and `boolean` are commonly used in application code.

---

## 3. Reference Types

Reference variables store a **reference to an object**, rather than the object value itself.

Examples:

```java
String name = "John";

User user = new User();

int[] numbers = {1, 2, 3};
```

Common reference types:

* `String`
* Arrays
* Classes
* Interfaces
* Enums
* Records

---

## 4. Primitive vs Reference ⭐

```java
int a = 10;              // primitive

String name = "John";    // reference
```

### Key difference

**Primitive:**

```text
variable → actual value
```

**Reference:**

```text
variable → reference → object
```

Reference variables can hold `null`:

```java
String name = null;
```

Primitive types cannot:

```java
int age = null; // ❌
```

---

## 5. `var` — Local Variable Type Inference

Java allows:

```java
var name = "John";
var age = 28;
```

The compiler determines the type:

```text
var name = "John" → String
var age = 28      → int
```

Important:

> `var` does **not** mean dynamically typed.

The type is still determined at **compile time**.

Also, `var` is mainly for **local variables**; you cannot use it for instance fields or method parameters.

---

## 6. Constants — `final`

Use `final` when a variable should not be reassigned:

```java
final double PI = 3.14159;
```

```java
PI = 4.0; // ❌ compilation error
```

For constants, conventionally use:

```java
static final double MAX_AMOUNT = 100000;
```

---

## 🔥 Interview Must-Know

Remember these:

1. Java has **8 primitive data types**.
2. Everything else such as `String`, arrays, and custom classes is a **reference type**.
3. Primitive variables hold values; reference variables hold references to objects.
4. Reference types can contain `null`; primitives cannot.
5. `int` is the default integer type.
6. `double` is the default floating-point type.
7. `var` provides **compile-time type inference**, not dynamic typing.
8. `final` prevents reassignment.
9. Java is **statically typed** — variable types are determined at compile time.


---

Q. similar to typeof in js here in java how it can be found?

Yes. Java doesn't have a direct `typeof` operator like JavaScript.

The approach depends on **what you want to check**.

### 1. Check the runtime type of an object → `getClass()`

Similar to:

```js
typeof value
```

In Java:

```java
Integer num = 10;

System.out.println(num.getClass());
```

Output:

```text
class java.lang.Integer
```

Get only the class name:

```java
System.out.println(num.getClass().getSimpleName());
```

Output:

```text
Integer
```

---

### 2. Check whether an object is a specific type → `instanceof`

This is very common:

```java
Object value = "Hello";

if (value instanceof String) {
    System.out.println("It's a String");
}
```

Another example:

```java
Object value = 100;

if (value instanceof Integer) {
    System.out.println("It's an Integer");
}
```

---

### 3. Primitive types

For primitives, you don't use `getClass()` directly:

```java
int num = 10;

// num.getClass(); ❌
```

You already know the type from the declaration:

```java
int num = 10;
double price = 10.5;
boolean active = true;
char grade = 'A';
```

If you need runtime type information, **boxing** can be used:

```java
int num = 10;

Object obj = num; // autoboxing: int → Integer

System.out.println(obj.getClass().getSimpleName());
```

Output:

```text
Integer
```

### Quick comparison

| JavaScript              | Java                                   |
| ----------------------- | -------------------------------------- |
| `typeof x`              | `x.getClass()`                         |
| `typeof x === "string"` | `x instanceof String`                  |
| `typeof x === "number"` | `x instanceof Integer` / `Double` etc. |

**Important:** Java is **statically typed**, so unlike JavaScript, you normally know a variable's type at compile time. Runtime checks like `getClass()` and `instanceof` are mainly useful with **objects, inheritance, and polymorphism**.

