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