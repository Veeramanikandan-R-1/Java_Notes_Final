# Java Methods — Method Design

Since you know JavaScript, Java methods are similar to JS functions, but **Java is statically typed**, so parameters and return types must be declared.

A method is a reusable block of code that performs a specific task.

```java
public int add(int a, int b) {
    return a + b;
}
```

---

## 1. Method Structure ⭐

```java
accessModifier returnType methodName(parameters) {
    // body
    return value;
}
```

Example:

```java
public int calculateTotal(int price, int quantity) {
    return price * quantity;
}
```

* `public` → access modifier
* `int` → return type
* `calculateTotal` → method name
* `int price, int quantity` → parameters
* `return` → returns result

---

## 2. `void` Methods

If a method doesn't return a value, use `void`.

```java
public void printUser(String name) {
    System.out.println(name);
}
```

No `return value` is required.

You can still use:

```java
return;
```

to exit early.

---

## 3. Parameters vs Arguments

```java
public int add(int a, int b) {  // parameters
    return a + b;
}

add(10, 20);                    // arguments
```

**Parameter** → variable defined in method declaration.

**Argument** → actual value passed during method call.

---

## 4. Method Naming ⭐

Use **camelCase** and meaningful names.

Good:

```java
calculateTotal()
findUserById()
createOrder()
validateUser()
```

Avoid:

```java
doStuff()
process()
method1()
```

A method should ideally perform **one clear responsibility**.

---

# 5. Return Type

Choose the return type based on what the method produces.

```java
public User findUser() {
    return user;
}
```

```java
public boolean isValid() {
    return true;
}
```

```java
public List<User> getUsers() {
    return users;
}
```

For Spring Boot, you'll commonly see:

```java
public User getUserById(Long id)
```

---

# 6. Method Overloading ⭐

Same method name but **different parameter lists**.

```java
public int add(int a, int b) {
    return a + b;
}

public int add(int a, int b, int c) {
    return a + b + c;
}
```

This is **compile-time polymorphism**.

You **cannot overload only by changing the return type**:

```java
int add(int a, int b)
double add(int a, int b) // ❌
```

---

# 7. Static vs Instance Methods

### Instance method

Requires an object:

```java
UserService service = new UserService();
service.getUser();
```

### Static method

Belongs to the class:

```java
Math.max(10, 20);
```

You call it using the class name.

```java
public static int add(int a, int b) {
    return a + b;
}
```

```java
Calculator.add(10, 20);
```

For Spring Boot, **most service methods are instance methods** because Spring manages service objects as beans.

---

# 8. Method Design Best Practices ⭐

### Keep methods small and focused

Bad:

```java
createUserAndValidateAndSendEmailAndSaveAudit();
```

Better:

```java
validateUser();
createUser();
sendWelcomeEmail();
saveAudit();
```

### Good method design

A good method should generally have:

* **One clear responsibility**
* Meaningful name
* Appropriate parameters
* Appropriate return type
* Minimal side effects
* Manageable complexity
* Proper access modifier

---

# 9. Java Pass-by-Value ⭐⭐⭐

This is an important interview topic.

**Java is always pass-by-value.**

For primitives:

```java
void change(int x) {
    x = 100;
}

int a = 10;
change(a);

System.out.println(a); // 10
```

For objects, Java passes a **copy of the reference value**:

```java
void change(User user) {
    user.setName("John");
}
```

The object's state can be changed, but the reference itself is still passed by value.

**Never say "Java passes objects by reference."**

Correct interview answer:

> Java is always pass-by-value. For objects, the value being passed is a copy of the reference.

---

# 🔥 Interview Must-Know

Remember:

```text
Method
├── Access modifier
├── Return type
├── Name
├── Parameters
└── Body
```

Most important:

* Method ≈ function in JavaScript
* Java methods require declared **parameter types and return type**
* `void` → no return value
* **Overloading** → same name, different parameter list
* Return type alone cannot differentiate overloaded methods
* `static` → belongs to class
* Instance method → belongs to object
* **Java is always pass-by-value**
* Prefer **small, single-responsibility methods**
* Use meaningful names like `findUserById()` rather than `process()`

For your Spring Boot path, **method design + OOP + interfaces + collections** are especially important because you'll use them heavily in **Controller → Service → Repository** architecture.
