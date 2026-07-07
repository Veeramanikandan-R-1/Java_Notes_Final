# Revision Notes

```text
for       -> Known iterations

while     -> Unknown iterations

do-while  -> Execute at least once

for-each  -> Traverse collections

break     -> Exit loop

continue  -> Skip iteration

Nested loops increase complexity

for-each internally uses Iterator

Always check loop boundaries

Avoid infinite loops

Think about Big-O complexity
```

---

# Cheat Sheet

```java
// For Loop
for(int i = 0; i < n; i++) {
}

// While Loop
while(condition) {
}

// Do While
do {
} while(condition);

// For Each
for(String s : list) {
}

// Break
break;

// Continue
continue;
```

---

# Hands-On Exercises

## 1. Print Numbers 1 to 100

**Solution**

```java
for(int i = 1; i <= 100; i++) {
    System.out.println(i);
}
```

---

## 2. Sum Numbers 1 to N

Input:

```java
N = 5
```

Output:

```text
15
```

**Solution**

```java
int sum = 0;

for(int i = 1; i <= 5; i++) {
    sum += i;
}

System.out.println(sum);
```

---

## 3. Count Even Numbers

**Solution**

```java
for(int i = 1; i <= 100; i++) {

    if(i % 2 == 0) {
        System.out.println(i);
    }

}
```

---

## 4. Find Maximum Element

**Solution**

```java
int[] arr = {4, 8, 2, 10, 3};

int max = arr[0];

for(int num : arr) {

    if(num > max) {
        max = num;
    }

}

System.out.println(max);
```

---

## 5. Count Occurrences

Input:

```java
{1,2,3,2,2,4}
```

Count occurrences of 2.

**Solution**

```java
int count = 0;

for(int num : arr) {

    if(num == 2) {
        count++;
    }

}
```

---

## 6. Remove Invalid Users

Given:

```java
List<User>
```

Create another list containing only active users.

**Solution**

```java
List<User> activeUsers = new ArrayList<>();

for(User user : users) {

    if(user.isActive()) {
        activeUsers.add(user);
    }

}
```

This exercise resembles actual Spring Boot service-layer code.