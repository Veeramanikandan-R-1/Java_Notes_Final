# Java Loops — `for`, `while`, `do-while`

Since you already know JavaScript/React, Java loops will feel very familiar. The main syntax is almost the same.

Loops are used to **execute a block of code repeatedly**.

---

## 1. `for` Loop ⭐

Use when you generally **know how many times** you want to iterate.

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

Output:

```text
0
1
2
3
4
```

Structure:

```text
for (initialization; condition; update)
```

Example:

```java
for (int i = 0; i < users.size(); i++) {
    System.out.println(users.get(i));
}
```

---

## 2. Enhanced `for` Loop ⭐

Very commonly used with **arrays and collections**.

```java
String[] names = {"John", "Sam", "Raj"};

for (String name : names) {
    System.out.println(name);
}
```

Equivalent idea to JavaScript's:

```javascript
names.forEach(name => console.log(name));
```

But Java's enhanced `for` is especially convenient when you simply need each element.

---

## 3. `while` Loop

Use when the number of iterations **isn't necessarily known beforehand**.

```java
int i = 0;

while (i < 5) {
    System.out.println(i);
    i++;
}
```

Flow:

```text
condition
   ↓
 true → execute → update
   ↑              |
   └──────────────┘
   ↓
 false → exit
```

### Important

If the condition is initially `false`, the loop executes **zero times**.

```java
while (false) {
    // never executes
}
```

---

## 4. `do-while` Loop

Similar to `while`, but it **always executes at least once**.

```java
int i = 10;

do {
    System.out.println(i);
    i++;
} while (i < 5);
```

Output:

```text
10
```

Why?

Because the body executes **before** the condition is checked.

### Key difference ⭐

```text
while:
condition → body

do-while:
body → condition
```

---

# 5. `break`

Immediately **exits the loop**.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;
    }

    System.out.println(i);
}
```

Output:

```text
0
1
2
3
4
```

---

# 6. `continue`

Skips the **current iteration** and continues with the next one.

```java
for (int i = 0; i < 5; i++) {
    if (i == 2) {
        continue;
    }

    System.out.println(i);
}
```

Output:

```text
0
1
3
4
```

---

# `for` vs `while` vs `do-while`

| Loop       | When to use                 | Minimum execution |
| ---------- | --------------------------- | ----------------: |
| `for`      | Known/controlled iterations |                 0 |
| `while`    | Condition-based iteration   |                 0 |
| `do-while` | Must execute at least once  |             **1** |

### Spring Boot relevance

You'll frequently see loops when processing:

```java
for (User user : users) {
    // process user
}
```

However, in modern Java you'll also frequently use **Streams** for collection processing:

```java
users.stream()
     .filter(User::isActive)
     .forEach(System.out::println);
```

You don't need to replace every loop with streams; choose whichever is clearer.

---

## 🔥 Interview Must-Know

Remember these 5 points:

1. `for` → controlled/known iterations.
2. `while` → condition checked **before** execution.
3. `do-while` → condition checked **after** execution, so it runs at least once.
4. `break` → exits the loop completely.
5. `continue` → skips current iteration.

**Coming from JavaScript:** Java's `for`, `while`, `do-while`, `break`, and `continue` work conceptually almost identically. The important new thing for your Spring Boot journey is the **enhanced `for` loop** and later **Streams**.
