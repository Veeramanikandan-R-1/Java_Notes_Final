## Java Type Casting

**Type casting** means converting a value from one data type to another.

There are two main types:

1. **Implicit casting** → Java does it automatically
2. **Explicit casting** → You manually tell Java to convert

---

### 1. Implicit Casting — Widening

Converting a **smaller type → larger type**.

```text
byte → short → int → long → float → double
```

Java does this automatically because there is generally **no data loss**.

```java
int num = 100;
double value = num;   // implicit casting

System.out.println(value); // 100.0
```

Another example:

```java
int num = 10;
long value = num;

System.out.println(value); // 10
```

Conceptually:

```text
int (smaller)
   ↓
long (larger)
```

---

### 2. Explicit Casting — Narrowing

Converting a **larger type → smaller type**.

You must explicitly tell Java to do it:

```java
double value = 10.75;

int num = (int) value;

System.out.println(num); // 10
```

Syntax:

```java
targetType variable = (targetType) value;
```

Here:

```java
int num = (int) value;
```

The decimal part is **discarded**, not rounded.

```text
10.75 → 10
```

#### Another example

```java
long value = 1000;

int num = (int) value;

System.out.println(num); // 1000
```

---

### ⚠️ Important: Data Loss

Explicit casting can cause data loss.

```java
int num = 130;

byte b = (byte) num;

System.out.println(b); // -126
```

Why? `byte` can store only:

```text
-128 to 127
```

So narrowing conversions can produce unexpected values due to overflow.

---

### Primitive vs Object Casting

Don't confuse **primitive type casting** with **object/reference casting**.

For now, remember:

```java
// Primitive casting
int x = 10;
double y = x;          // implicit

double a = 10.5;
int b = (int) a;       // explicit
```

Later, when you learn **inheritance**, you'll encounter:

```java
Parent p = new Child();       // upcasting
Child c = (Child) p;          // downcasting
```

That's **reference type casting**, which is a separate but important concept.

### Interview Summary

| Type                     | Direction     | Manual? | Example        |
| ------------------------ | ------------- | ------- | -------------- |
| **Implicit / Widening**  | Small → Large | ❌       | `int → long`   |
| **Explicit / Narrowing** | Large → Small | ✅       | `double → int` |

**Easy rule:**

> **Widening is automatic; narrowing requires explicit casting.**
