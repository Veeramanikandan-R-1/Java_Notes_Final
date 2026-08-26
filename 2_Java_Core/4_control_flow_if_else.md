# Java Control Flow — `if-else` & `switch`

Control flow determines **which code executes and when**.

---

## 1. `if`

Executes code when a condition is `true`.

```java
int age = 25;

if (age >= 18) {
    System.out.println("Adult");
}
```

---

## 2. `if-else`

```java
int age = 15;

if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

Only **one block** executes.

---

## 3. `if-else if-else`

Used for multiple conditions.

```java
int marks = 75;

if (marks >= 90) {
    System.out.println("A");
} else if (marks >= 60) {
    System.out.println("B");
} else if (marks >= 40) {
    System.out.println("C");
} else {
    System.out.println("Fail");
}
```

Conditions are evaluated **top to bottom**. Once one condition is `true`, the remaining conditions are skipped.

---

## 4. Nested `if`

An `if` inside another `if`.

```java
if (age >= 18) {
    if (hasLicense) {
        System.out.println("Can drive");
    }
}
```

Avoid excessive nesting; combine conditions when appropriate:

```java
if (age >= 18 && hasLicense) {
    System.out.println("Can drive");
}
```

---

# 5. `switch`

Use `switch` when you compare **one value against multiple possible cases**.

```java
int day = 2;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;

    case 2:
        System.out.println("Tuesday");
        break;

    default:
        System.out.println("Invalid day");
}
```

### Why `break`?

Without `break`, execution continues into the next cases (**fall-through**).

```java
int x = 1;

switch (x) {
    case 1:
        System.out.println("One");
    case 2:
        System.out.println("Two");
}
```

Output:

```text
One
Two
```

Because there is no `break`.

---

# 6. `default`

Executes when no case matches.

```java
switch (status) {
    case 200:
        System.out.println("Success");
        break;
    case 404:
        System.out.println("Not Found");
        break;
    default:
        System.out.println("Other");
}
```

`default` is optional.

---

# 7. Modern Switch Expression ⭐

Modern Java supports switch expressions:

```java
int day = 2;

String result = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    default -> "Invalid";
};
```

Notice:

```text
case 1 -> ...
```

No `break` is required.

It can directly **return a value**.

---

### `yield`

For multi-line switch expression blocks:

```java
String result = switch (day) {
    case 1 -> "Monday";

    case 2 -> {
        System.out.println("Processing...");
        yield "Tuesday";
    }

    default -> "Invalid";
};
```

`yield` provides the value from that switch branch.

---

# `if-else` vs `switch`

| `if-else`                  | `switch`                          |   |                       |
| -------------------------- | --------------------------------- | - | --------------------- |
| Complex conditions         | Multiple fixed values             |   |                       |
| Supports `>`, `<`, `&&`, ` |                                   | ` | Mainly matching cases |
| Range checking             | Exact value matching              |   |                       |
| More flexible              | Cleaner for many discrete choices |   |                       |

Example:

```java
if (age >= 18)       // Good for range/condition
```

vs.

```java
switch (role) {      // Good for fixed values
    case "ADMIN" -> ...
    case "USER" -> ...
}
```

---

## 🔥 Interview Must-Know

Remember:

* `if` → single condition
* `else if` → multiple conditions
* `else` → fallback
* `switch` → multiple possible values
* Traditional `switch` needs `break` to avoid fall-through
* `default` handles unmatched cases
* Modern switch expressions use `->`
* Switch expressions can **return a value**
* `yield` returns a value from a multi-statement switch block
* Use **`if-else` for complex/range conditions** and **`switch` for fixed/discrete choices**.
