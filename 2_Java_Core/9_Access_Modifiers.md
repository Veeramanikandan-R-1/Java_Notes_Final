# Java Access Modifiers

Access modifiers control **where a class, variable, method, or constructor can be accessed**.

There are 4 levels:

| Modifier    | Same Class | Same Package | Subclass (different package) | Other Package |
| ----------- | ---------- | ------------ | ---------------------------- | ------------- |
| `public`    | ✅          | ✅            | ✅                            | ✅             |
| `protected` | ✅          | ✅            | ✅*                           | ❌             |
| `default`   | ✅          | ✅            | ❌                            | ❌             |
| `private`   | ✅          | ❌            | ❌                            | ❌             |

> `default` means **no modifier is written**. It is also called **package-private**.

---

## 1. `public`

Accessible **from anywhere**.

```java
package com.myapp.model;

public class User {
    public String name = "John";

    public void display() {
        System.out.println(name);
    }
}
```

From another package:

```java
import com.myapp.model.User;

public class Test {
    public static void main(String[] args) {
        User user = new User();

        System.out.println(user.name);  // ✅
        user.display();                 // ✅
    }
}
```

**Use when:** Something should be accessible outside its package.

---

## 2. `private`

Accessible **only inside the same class**.

```java
public class User {

    private String password = "1234";

    private void validatePassword() {
        System.out.println("Validating...");
    }

    public void login() {
        validatePassword(); // ✅
        System.out.println(password); // ✅
    }
}
```

Outside the class:

```java
User user = new User();

user.password;          // ❌
user.validatePassword(); // ❌
```

This is heavily used for **encapsulation**.

Typical pattern:

```java
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

---

## 3. `default` / Package-Private

When you don't specify any modifier:

```java
class User {

    String name = "John";

    void display() {
        System.out.println(name);
    }
}
```

Accessible only **within the same package**.

```text
com.myapp
├── User.java
└── Test.java    ← can access User
```

But:

```text
com.otherapp
└── Test.java    ← cannot access User
```

```java
// No modifier
String name;

// No modifier
void display() { }
```

means **package-private**.

---

## 4. `protected`

Accessible:

1. Within the **same package**
2. From a **subclass**, even if the subclass is in another package

Example:

```java
package com.myapp.model;

public class User {

    protected String name = "John";
}
```

Same package:

```java
package com.myapp.model;

public class Test {

    public static void main(String[] args) {
        User user = new User();

        System.out.println(user.name); // ✅
    }
}
```

Different package + inheritance:

```java
package com.other;

import com.myapp.model.User;

public class Admin extends User {

    public void show() {
        System.out.println(name); // ✅
    }
}
```

But simply creating the object from another package doesn't give access:

```java
User user = new User();

System.out.println(user.name); // ❌
```

### Important `protected` point

In a **different package**, `protected` access is primarily through **inheritance**, not through an arbitrary `User` object.

---

# ⭐ Most Important Interview Summary

```text
public     → Everywhere
private    → Same class only
default    → Same package only
protected  → Same package + subclasses
```

### Real-world Java/Spring Boot usage

You'll commonly see:

```java
public class UserService {

    private UserRepository repository;

    public User getUser() {
        return repository.findById(1);
    }
}
```

Here:

* `public class` → other packages can use the class
* `private repository` → implementation detail hidden
* `public getUser()` → exposes the required functionality

**Rule of thumb:** Start with the **most restrictive access** (`private`) and expose things as `public`/`protected` only when necessary.
