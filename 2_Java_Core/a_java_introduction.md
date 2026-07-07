# Java — Introduction (Senior Backend Engineer Perspective)

Before learning classes, collections, concurrency, Spring, microservices, or JVM internals, you must understand **what Java is, why it exists, and how it executes code**.

Think of Java not as a programming language alone, but as an **ecosystem for building portable, reliable, scalable applications**.

---

# 1. Fundamentals

## What is Java?

Java is a:

* High-level programming language
* Object-Oriented Programming (OOP) language
* Platform-independent language
* General-purpose language

Created by Sun Microsystems (1995), currently maintained by Oracle.

Example:

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

---

## Why Java Became Popular?

Before Java:

Programs were usually platform-specific.

A program compiled on Windows might not run on Linux.

Java introduced:

> Write Once, Run Anywhere (WORA)

Compile once:

```text
.java → .class
```

Run anywhere with JVM.

This made Java ideal for:

* Enterprise applications
* Banking systems
* Telecom systems
* E-commerce platforms
* Large-scale backend services

---

## Key Features of Java

### 1. Object-Oriented

Everything revolves around classes and objects.

```java
class User {
    String name;
}
```

Benefits:

* Reusability
* Maintainability
* Extensibility

---

### 2. Platform Independent

Java source code:

```text
User.java
```

Compiles into:

```text
User.class
```

(bytecode)

JVM executes bytecode on any platform.

---

### 3. Strongly Typed

Every variable has a defined type.

```java
int age = 25;
String name = "John";
```

Compiler catches many errors early.

---

### 4. Automatic Memory Management

Java provides Garbage Collection (GC).

Developer:

```java
User user = new User();
```

Java handles memory cleanup.

---

### 5. Multithreading Support

Built-in concurrency support.

```java
Thread thread = new Thread();
```

Critical for scalable backend systems.

---

# 2. Java Ecosystem

As a backend engineer, Java is more than language syntax.

The ecosystem includes:

```text
Java Language
     ↓
JDK
     ↓
JVM
     ↓
Libraries
     ↓
Frameworks
```

Examples:

* Spring Boot
* Hibernate
* Maven
* Gradle
* Kafka Clients
* JDBC

Most enterprise systems are built on this stack.

---

# 3. Java Editions

### Java SE (Standard Edition)

Core Java.

Contains:

* Collections
* Streams
* Concurrency
* IO
* Networking

Most interview questions come from here.

---

### Java EE / Jakarta EE

Enterprise APIs:

* Servlet
* JPA
* CDI

Today Spring dominates this space.

---

# 4. Internal Working (Must Know)

Senior engineers must understand how Java executes code.

Not JVM internals deeply, but enough to reason about performance and debugging.

---

## Step 1: Write Source Code

```java
System.out.println("Hello");
```

Stored as:

```text
Main.java
```

---

## Step 2: Compilation

Compiler:

```text
javac Main.java
```

Produces:

```text
Main.class
```

This contains bytecode.

---

## Step 3: JVM Loads Class

Class Loader loads:

```text
Main.class
```

into memory.

---

## Step 4: JVM Executes Bytecode

Execution Engine converts bytecode into machine instructions.

Program runs.

---

## Flow

```text
Java Source Code
       ↓
Compiler (javac)
       ↓
Bytecode (.class)
       ↓
Class Loader
       ↓
JVM
       ↓
Machine Code
       ↓
Execution
```

This flow is one of the most frequently asked Java interview topics.

---

# JDK vs JRE vs JVM

This is mandatory knowledge.

---

## JVM (Java Virtual Machine)

Runs Java bytecode.

Responsibilities:

* Execute code
* Manage memory
* Garbage collection

Think:

```text
JVM = Runtime Engine
```

---

## JRE (Java Runtime Environment)

Contains:

```text
JVM
+
Libraries
```

Used to run Java applications.

---

## JDK (Java Development Kit)

Contains:

```text
JRE
+
Compiler
+
Development Tools
```

Used to develop Java applications.

---

## Relationship

```text
JDK
 └── JRE
       └── JVM
```

Interview favorite.

---

# 5. Java Program Structure

Example:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello");

    }
}
```

---

## Class

Blueprint.

```java
public class Main
```

Every Java application is organized into classes.

---

## Main Method

Entry point.

```java
public static void main(String[] args)
```

JVM starts execution here.

---

# 6. Object-Oriented Foundation

Java is fundamentally OOP.

Everything later builds upon this.

---

## Class

Blueprint.

```java
class User {
}
```

---

## Object

Instance of class.

```java
User user = new User();
```

---

## Encapsulation

Protect internal state.

```java
private String name;
```

Expose through methods.

---

## Inheritance

Reuse behavior.

```java
class Dog extends Animal
```

---

## Polymorphism

One interface, multiple implementations.

```java
Animal animal = new Dog();
```

---

## Abstraction

Expose what, hide how.

```java
interface PaymentService
```

Core design principle in enterprise applications.

---

# 7. Real-World Java Backend Usage

Java powers:

### Banking

* Transaction processing
* Fraud detection

### E-commerce

* Order management
* Payments

### Telecom

* Billing systems

### Cloud Platforms

* Spring Boot services
* Microservices

### Big Data

* Kafka
* Hadoop
* Spark

Reason:

* Stability
* Scalability
* Ecosystem maturity

---

# Common Mistakes

## Learning Framework Before Java

Bad path:

```text
Spring Boot
↓
Java Basics
```

Correct:

```text
Java
↓
Collections
↓
Concurrency
↓
JVM
↓
Spring
```

---

## Ignoring JVM Basics

Developers memorize syntax but don't understand:

* Memory
* GC
* Class loading

Leads to production debugging issues.

---

## Thinking Java = OOP Only

Modern Java includes:

* Functional programming
* Streams
* Lambdas
* Reactive paradigms

---

## Not Following Java Version Evolution

Important versions:

* Java 8 (Streams, Lambda)
* Java 11 (LTS)
* Java 17 (LTS)
* Java 21 (LTS)

Modern backend interviews expect Java 17+ awareness.

---

# Best Practices

## Write Readable Code

Bad:

```java
int x = 10;
```

Good:

```java
int customerAge = 10;
```

---

## Prefer Composition Over Inheritance

Often:

```java
HAS-A
```

is better than:

```java
IS-A
```

---

## Understand JVM Basics Early

Must know:

* JVM
* JDK
* JRE
* Heap
* Stack
* GC

Before moving into Spring.

---

## Keep Learning Modern Java

Know:

* Streams
* Optional
* Records
* Sealed Classes
* Virtual Threads (Java 21)

---

# Revision Notes

* Java is a platform-independent, object-oriented language.
* Source code compiles into bytecode.
* JVM executes bytecode.
* JDK = JRE + Development Tools.
* JRE = JVM + Libraries.
* Java supports OOP, concurrency, and automatic memory management.
* JVM provides portability.
* Main method is application entry point.
* Java ecosystem includes frameworks, libraries, and build tools.
* Modern Java development targets Java 17+ or Java 21+.

---

# Cheat Sheet

```text
Java Source (.java)
       ↓
javac
       ↓
Bytecode (.class)
       ↓
JVM
       ↓
Execution
```

```text
JDK
 └── JRE
       └── JVM
```

Core Features:

```text
OOP
Platform Independent
Strongly Typed
GC
Multithreading
Rich Ecosystem
```

---

# Interview Questions & Answers

### 1. What is Java?

A high-level, object-oriented, platform-independent programming language.

---

### 2. Why is Java platform independent?

Because Java compiles into bytecode executed by JVM on different platforms.

---

### 3. What is JVM?

Java Virtual Machine that executes bytecode and manages runtime operations.

---

### 4. Difference between JDK, JRE, and JVM?

```text
JDK = JRE + Tools
JRE = JVM + Libraries
JVM = Execution Engine
```

---

### 5. What is bytecode?

Platform-independent intermediate code generated by Java compiler.

---

### 6. What is WORA?

Write Once, Run Anywhere.

---

### 7. What is the entry point of a Java application?

```java
public static void main(String[] args)
```

---

### 8. Why is Java used in enterprise systems?

Because of portability, scalability, stability, and ecosystem maturity.

---

### 9. What are the four OOP pillars?

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction

---

### 10. Why should a backend engineer understand JVM basics?

To troubleshoot memory issues, performance problems, GC behavior, and production failures.

---

# Hands-On Exercises

## Exercise 1

Write a Java program that prints:

```text
Welcome to Java
```

### Solution

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Java");
    }
}
```

---

## Exercise 2

Create a `User` class with:

```text
name
age
```

Create an object and print values.

### Solution

```java
class User {

    String name;
    int age;
}

public class Main {

    public static void main(String[] args) {

        User user = new User();

        user.name = "John";
        user.age = 25;

        System.out.println(user.name);
        System.out.println(user.age);
    }
}
```

---

## Exercise 3

Explain the execution flow of:

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

### Answer

```text
1. Write source code
2. Compile using javac
3. Generate bytecode (.class)
4. JVM loads class
5. JVM executes bytecode
6. Output printed
```

---

## Senior Backend Engineer Takeaway

For senior interviews, "Java Introduction" is not about syntax. You should be able to explain:

* Why JVM exists
* How Java achieves portability
* JDK vs JRE vs JVM
* How code moves from source to execution
* Why Java dominates enterprise backend development

These fundamentals become the foundation for JVM, memory management, collections, concurrency, Spring Boot, and microservices.
