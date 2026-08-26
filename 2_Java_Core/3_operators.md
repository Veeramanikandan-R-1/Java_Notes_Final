# Java Operators — Arithmetic & Logical

Operators are symbols used to perform operations on values/variables.

---

## 1. Arithmetic Operators

Used for mathematical calculations.

| Operator | Meaning           | Example         |
| -------- | ----------------- | --------------- |
| `+`      | Addition          | `10 + 5` → `15` |
| `-`      | Subtraction       | `10 - 5` → `5`  |
| `*`      | Multiplication    | `10 * 5` → `50` |
| `/`      | Division          | `10 / 5` → `2`  |
| `%`      | Modulus/remainder | `10 % 3` → `1`  |

### Important: Integer division ⭐

```java
int result = 10 / 3;
System.out.println(result); // 3
```

Because both operands are integers, the decimal part is discarded.

```java
double result = 10.0 / 3;
System.out.println(result); // 3.333...
```

### `+` with Strings

`+` also performs **String concatenation**:

```java
String name = "John";
System.out.println("Hello " + name);
// Hello John
```

---

## 2. Increment / Decrement

```java
int x = 10;

x++;  // 11
x--;  // 10
```

### Pre vs Post ⭐

```java
int x = 10;

int a = x++;  // a = 10, x = 11
int b = ++x;  // x incremented first, then assigned
```

Remember:

```text
x++ → use first, increment later
++x → increment first, use later
```

---

# 3. Logical Operators ⭐

Used mainly with boolean expressions.

### AND `&&`

Returns `true` only when **both** conditions are true.

```java
int age = 25;

age >= 18 && age <= 60
// true
```

Truth table:

```text
true  && true  → true
true  && false → false
false && true  → false
false && false → false
```

---

### OR `||`

Returns `true` when **at least one** condition is true.

```java
boolean isAdmin = false;
boolean isManager = true;

isAdmin || isManager
// true
```

```text
true  || false → true
false || true  → true
false || false → false
```

---

### NOT `!`

Reverses the boolean value.

```java
boolean loggedIn = true;

!loggedIn
// false
```

```text
!true  → false
!false → true
```

---

# 4. Short-Circuit Evaluation ⭐⭐⭐

This is **very important for interviews**.

### `&&`

If the first condition is `false`, Java **doesn't evaluate the second condition**.

```java
if (user != null && user.isActive()) {
    // ...
}
```

If `user == null`, Java doesn't execute:

```java
user.isActive()
```

This prevents a `NullPointerException`.

### `||`

If the first condition is `true`, Java doesn't evaluate the second condition.

```java
if (isAdmin || isManager()) {
    // ...
}
```

If `isAdmin == true`, `isManager()` isn't called.

---

## `&&` vs `&`

Don't confuse:

```java
&&   // logical AND + short-circuit
&    // bitwise AND (also works with booleans without short-circuit)
```

Similarly:

```java
||   // logical OR + short-circuit
|    // bitwise OR
```

For normal boolean conditions, use **`&&` and `||`**.

---

## 🔥 Interview Must-Know

```text
Arithmetic:
+  -  *  /  %

Logical:
&&  → AND
||  → OR
!   → NOT
```

Most important concepts:

* `10 / 3` with integers → `3`
* `%` gives remainder
* `++x` vs `x++`
* `&&` and `||` use **short-circuit evaluation**
* `&&` stops when first condition is `false`
* `||` stops when first condition is `true`
* `&&` / `||` are logical operators; `&` / `|` are bitwise operators.
