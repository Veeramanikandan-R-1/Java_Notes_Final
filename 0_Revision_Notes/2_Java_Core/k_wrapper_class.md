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