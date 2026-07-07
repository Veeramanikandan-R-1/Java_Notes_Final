# Methods in Java (Senior Backend Engineer Perspective)

Methods are the **behavioral units of a class**. Objects hold state (fields), while methods define what an object can do.

In backend systems, methods represent business operations:

```java
createOrder()
processPayment()
calculateTax()
findUserByEmail()
```

Good method design directly impacts:

* Readability
* Maintainability
* Testability
* Performance
* API usability

---

# 1. Fundamentals

## What is a Method?

A method is a named block of code that performs a specific task and can optionally:

* Accept input (parameters)
* Return output (return value)

```java
public int add(int a, int b) {
    return a + b;
}
```

Usage:

```java
int result = add(10, 20);
```

---

## Method Structure

```java
[access modifier] [non-access modifier] returnType methodName(parameters) {
    // body
}
```

Example:

```java
public static int add(int a, int b) {
    return a + b;
}
```

| Part       | Meaning            |
| ---------- | ------------------ |
| public     | Visibility         |
| static     | Class-level method |
| int        | Return type        |
| add        | Method name        |
| parameters | Inputs             |
| body       | Logic              |

---

## Method Signature

Method signature consists of:

```text
Method Name + Parameter Types
```

Example:

```java
void save(String name)
```

Signature:

```text
save(String)
```

Not included:

* Return type
* Access modifier
* Exceptions

Important because Java uses signatures for method overloading resolution.

---

# 2. Method Types

Understanding method categories helps design better APIs.

---

## Instance Methods

Belong to an object.

```java
class User {
    String name;

    String getName() {
        return name;
    }
}
```

Usage:

```java
User user = new User();
user.getName();
```

Use when behavior depends on object state.

---

## Static Methods

Belong to class.

```java
class MathUtil {

    static int square(int n) {
        return n * n;
    }
}
```

Usage:

```java
MathUtil.square(5);
```

Use when logic doesn't require object state.

Examples:

```java
Math.max()
Collections.sort()
Objects.requireNonNull()
```

---

## Abstract Methods

Declare behavior but not implementation.

```java
abstract class PaymentService {

    abstract void pay();
}
```

Implementation:

```java
class UpiPaymentService extends PaymentService {

    @Override
    void pay() {
        System.out.println("UPI Payment");
    }
}
```

Used for extensible designs.

Static methods belong to the **class**, not to an **instance**. Method overriding in Java only applies to **instance methods**, where a subclass provides a different implementation of a superclass method.

When a subclass declares a static method with the same signature as a superclass static method, it **hides** the superclass method rather than overrides it. Since no actual overriding occurs, using `@Override` on a static method causes a compilation error.

Example:

```java
class Parent {
    static void show() {}
}

class Child extends Parent {
    // @Override  // Compilation error
    static void show() {}
}
```

Here, `Child.show()` hides `Parent.show()` instead of overriding it.

---

## Default Methods (Java 8)

Interfaces can provide implementation.

```java
interface Logger {

    default void log() {
        System.out.println("Logging");
    }
}
```

Useful for backward compatibility.

---

# 3. Parameters and Return Types

Methods communicate through inputs and outputs.

---

## Parameters

```java
void greet(String name)
```

Input:

```java
greet("John");
```

---

## Return Values

```java
String getFullName() {
    return "John Doe";
}
```

---

## Multiple Inputs

```java
double calculateTax(double amount, double rate)
```

---

## Varargs

Accept variable number of arguments.

```java
int sum(int... numbers)
```

Usage:

```java
sum(1);
sum(1,2);
sum(1,2,3);
```

Internally becomes:

```java
int[]
```

---

# 4. Java Parameter Passing

A very common interview topic.

Java is **100% Pass By Value**.

---

## Primitive Example

```java
void change(int x) {
    x = 100;
}

int a = 10;
change(a);

System.out.println(a);
```

Output:

```text
10
```

Because copy of value is passed.

---

## Object Example

```java
void update(User user) {
    user.setName("John");
}
```

```java
User user = new User();
update(user);
```

Object changes.

Reason:

Java passes a copy of the reference value.

Both references point to same object.

---

# 5. Internal Working (Must Know)

You don't need JVM internals deeply, but these concepts explain method behavior.

---

## Method Invocation

When a method is called:

```java
service();
```

JVM creates a **Stack Frame**.

Stack frame stores:

* Parameters
* Local variables
* Return information

---

## Call Stack

Example:

```java
main()
  -> service()
       -> repository()
```

Stack:

```text
repository()
service()
main()
```

When repository finishes:

```text
service()
main()
```

This is why deep recursion causes:

```java
StackOverflowError
```

---

## Heap vs Stack

```java
User user = new User();
```

Reference:

```text
Stack
```

Object:

```text
Heap
```

Understanding this explains:

* Object mutation
* Pass-by-value
* Memory usage

---

# 6. Method Overloading

Same method name, different parameters.

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

Note: in below example float has to be mentioned explicitly or else it will assume as double

```java
public class Main {
    public static int add(int n1, int n2){
      return n1 + n2;
    }

    public static float add(float n1, float n2){
      return n1 + n2;
    }

    public static void main(String[] args) {
      System.out.println("m1 "+ Main.add(1,2));
      System.out.println("m2 "+Main.add(1.2f,2.2f));

    }
}
```

---

## Why Use It?

Improves API usability.

Example:

```java
List.of()
```

Has many overloaded versions.

---

## Rules

Valid:

```java
add(int,int)
add(double,double)
add(int,int,int)
```

Invalid:

```java
int add()
double add()
```

Only return type differs.

Compiler cannot distinguish.

---

# 7. Method Overriding

Child class provides new implementation.

```java
class Animal {

    void sound() {
        System.out.println("Animal");
    }
}
```

```java
class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```

---

## Runtime Polymorphism

```java
Animal animal = new Dog();

animal.sound();
```

Output:

```text
Bark
```

JVM decides at runtime.

---

## Why Important?

Most frameworks depend on it:

* Spring Beans
* Strategy Pattern
* Hibernate Proxies
* AOP

---

# 8. Important Method Modifiers

---

## final

Cannot override.

```java
final void validate() {
}
```

Use when behavior must remain fixed.

---

## static

Belongs to class.

```java
static void helper() {
}
```

---

## synchronized

Thread-safe execution.

```java
synchronized void updateBalance() {
}
```

Only one thread can execute at a time.

---

## native

Implemented outside Java.

```java
native void readMemory();
```

Used by JVM internals.

---

# 9. Generic Methods

Type-safe reusable methods.

```java
public <T> T identity(T value) {
    return value;
}
```

Usage:

```java
String s = identity("Hello");
Integer i = identity(10);
```

Widely used in collections and frameworks.

Note: this is also possible

```java

public class Main {
    public <T> T identity(T value){
      return value;
    }    
    public static void main(String[] args) {
      Main mainObj = new Main();
      int value1 = mainObj.identity(1);
      System.out.println("value1"+value1);
    }
}
```

### Generic in Java (Short Explanation)

**Generics** allow classes, interfaces, and methods to work with **different data types while maintaining compile-time type safety**.

Without Generics:

```java
List list = new ArrayList();
list.add("Java");

String value = (String) list.get(0); // Casting required
```

With Generics:

```java
List<String> list = new ArrayList<>();
list.add("Java");

String value = list.get(0); // No casting
```

### Why Generics?

1. **Type Safety** – Errors caught at compile time.
2. **No Explicit Casting** – Cleaner code.
3. **Code Reusability** – One implementation works for multiple types.

---

### Generic Class

```java
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
```

Usage:

```java
Box<String> box = new Box<>();
box.set("Java");
```

Note:

```java

public class Main<T> {
    private T value;

    public void set(T valueInp){
      value = valueInp;
    }

    public T get(){
      return value;
    }


    public static void main(String[] args) {
      Main<int> mainObj = new Main<>();
      mainObj.set(1);
      System.out.println("value1"+mainObj.get());
    }
}
```

This is not possible because **Java generics work only with reference types**, not primitive types.

`int` is a primitive type, so this line is invalid:

```java
Main<int> mainObj = new Main<>();
```

### Why?

Generics are implemented using **type erasure**. At runtime, `T` is treated as an `Object` (or its bound). Primitive types like `int`, `double`, `boolean` are **not objects**, so they cannot be used as generic type arguments.

### Use wrapper classes instead

```java
public class Main<T> {
    private T value;

    public void set(T valueInp) {
        value = valueInp;
    }

    public T get() {
        return value;
    }

    public static void main(String[] args) {
        Main<Integer> mainObj = new Main<>();
        mainObj.set(1); // autoboxing: int -> Integer
        System.out.println("value1 " + mainObj.get());
    }
}
```

Output:

```text
value1 1
```

### What happens behind the scenes?

```java
mainObj.set(1);
```

is automatically converted by the compiler to:

```java
mainObj.set(Integer.valueOf(1));
```

This is called **autoboxing**.

So:

| Primitive | Wrapper Class |
| --------- | ------------- |
| `int`     | `Integer`     |
| `double`  | `Double`      |
| `boolean` | `Boolean`     |
| `char`    | `Character`   |
| `long`    | `Long`        |

Generics require the wrapper class (`Integer`), not the primitive (`int`).


---

### Generic Method

```java
public <T> T identity(T value) {
    return value;
}
```

Usage:

```java
String s = identity("Hello");
Integer n = identity(100);
```

---

### Interview Definition

> Generics are a Java feature that enables parameterized types, allowing code to be reusable, type-safe, and free from unnecessary casting.

---

# 10. Method References

Cleaner Lambda syntax.

Instead of:

```java
list.forEach(item -> System.out.println(item));
```

Use:

```java
list.forEach(System.out::println);
```

Common in Streams API.

---

# Common Mistakes

### 1. Huge Methods

Bad:

```java
processOrder()
```

500 lines.

Problem:

* Hard to understand
* Hard to test
* High coupling

---

### 2. Too Many Parameters

Bad:

```java
createUser(
  name,
  age,
  city,
  phone,
  email
);
```

Better:

```java
createUser(UserRequest request);
```

---

### 3. Hidden Side Effects

Bad:

```java
calculatePrice()
```

Also updates database.

Method name lies.

---

### 4. Returning null

Bad:

```java
User findUser()
```

Returns null.

Better:

```java
Optional<User>
```

---

### 5. Static Utility Abuse

Everything becomes static.

Results:

* Poor testing
* Tight coupling
* Difficult mocking

---

# Best Practices

## Single Responsibility

Bad:

```java
saveUserAndSendEmailAndGenerateReport()
```

Good:

```java
saveUser()
sendEmail()
generateReport()
```

---

## Business-Oriented Names

Bad:

```java
doWork()
```

Good:

```java
calculateInvoiceAmount()
```

---

## Keep Methods Focused

A method should answer one question:

> What single responsibility does this method have?

If multiple answers exist, split it.

---

## Validate Inputs Early

```java
Objects.requireNonNull(user);
```

Fail fast.

---

## Prefer Returning Results

Instead of modifying external state.

Bad:

```java
void calculate(Order order)
```

Good:

```java
BigDecimal calculate(Order order)
```

---

## Avoid Boolean Flags

Bad:

```java
process(true);
```

Good:

```java
processForAdmin();
processForCustomer();
```

---

# Real-World Backend Scenario

### Order Service

```java
public Order createOrder(OrderRequest request) {

    validateRequest(request);

    Order order = buildOrder(request);

    orderRepository.save(order);

    eventPublisher.publish(order);

    return order;
}
```

Notice:

Each method has one responsibility.

```java
validateRequest()
buildOrder()
save()
publish()
```

This makes code:

* Easy to test
* Easy to extend
* Easy to debug

---

# Revision Notes

* Method = reusable unit of behavior.
* Signature = name + parameter types.
* Java is always pass-by-value.
* Instance methods belong to objects.
* Static methods belong to classes.
* Overloading = compile-time polymorphism.
* Overriding = runtime polymorphism.
* JVM creates stack frames per method call.
* Objects live in heap, references in stack.
* Generic methods provide type safety.
* Small focused methods are easier to maintain.

---

# Cheat Sheet

```java
// Instance Method
void save()

// Static Method
static void save()

// Overloaded Methods
add(int a, int b)
add(double a, double b)

// Overridden Method
@Override
void process()

// Varargs
void print(String... values)

// Generic Method
<T> T get(T value)

// Final Method
final void validate()

// Method Reference
String::length

// Optional Return
Optional<User> findById(Long id)
```

---

# Interview Questions & Answers

### 1. What is a method signature?

Method name + parameter types.

---

### 2. Is return type part of method signature?

No.

---

### 3. Difference between overloading and overriding?

| Overloading          | Overriding               |
| -------------------- | ------------------------ |
| Compile time         | Runtime                  |
| Same method name     | Same signature           |
| Different parameters | Different implementation |

---

### 4. Is Java pass-by-reference?

No. Java is always pass-by-value.

---

### 5. Can static methods be overridden?

No. They are hidden, not overridden.

---

### 6. Why use `@Override`?

Compile-time safety and readability.

---

### 7. What causes `StackOverflowError`?

Excessive recursive method calls.

---

### 8. Why are small methods preferred?

Better readability, testing, and maintenance.

---

### 9. What is dynamic method dispatch?

Runtime selection of overridden methods based on actual object type.

---

### 10. Why are methods important in Spring?

Spring applies transactions, AOP, security, and proxies around method execution.

---

# Hands-On Exercises

## Exercise 1: Overloading

Create:

```java
add(int,int)
add(double,double)
add(int,int,int)
```

### Solution

```java
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

---

## Exercise 2: Overriding

Create `Vehicle`, `Car`, `Bike`.

Override:

```java
start()
```

### Solution

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle Start");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car Start");
    }
}
```

---

## Exercise 3: Generic Method

### Solution

```java
public class Util {

    public static <T> T identity(T value) {
        return value;
    }
}
```

Usage:

```java
String s = Util.identity("Hello");
Integer i = Util.identity(10);
```

---

## Exercise 4: Pass-by-Value

Predict output:

```java
class User {
    String name;
}

static void update(User user) {
    user.name = "John";
}
```

Output:

```text
John
```

Reason:

Copy of reference is passed, both references point to same object.

---

## Exercise 5: Refactoring

Refactor:

```java
processOrder()
```

into:

```java
validateOrder()
createOrder()
saveOrder()
publishOrderEvent()
```

Goal:

* Single Responsibility Principle
* Better testing
* Cleaner service layer

---

### Senior Backend Takeaway

For senior-level interviews, methods are not judged by syntax. They are judged by **API design, responsibility boundaries, side effects, extensibility, and maintainability**. A well-designed method should be **small, cohesive, predictable, and business-focused**.
