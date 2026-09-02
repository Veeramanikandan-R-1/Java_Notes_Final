## Java Packages

A **package** is a way to group related Java classes/interfaces. Think of it like a **folder + namespace**.

It helps with:

* Organizing code
* Avoiding class-name conflicts
* Controlling access (`public`, package-private)
* Managing large Spring Boot applications

---

### 1. Package Structure

Typical Spring Boot project:

```text
src/
└── main/
    └── java/
        └── com/
            └── myapp/
                └── shop/
                    ├── ShopApplication.java
                    ├── controller/
                    │   └── UserController.java
                    ├── service/
                    │   └── UserService.java
                    ├── repository/
                    │   └── UserRepository.java
                    └── model/
                        └── User.java
```

Sure. Here's the **simplest possible example**.

### 1. Create a package

Suppose we have this structure:

```text
src/
├── com/
│   └── myapp/
│       ├── model/
│       │   └── User.java
│       └── service/
│           └── UserService.java
```

**User.java**

```java
package com.myapp.model;

public class User {
    public String name = "John";
}
```

### 2. Use the class from another package

**UserService.java**

```java
package com.myapp.service;

import com.myapp.model.User;

public class UserService {

    public static void main(String[] args) {
        User user = new User();

        System.out.println(user.name);
    }
}
```

Output:

```text
John
```

### What is happening?

```text
User.java
   ↓
package com.myapp.model
   ↓
User belongs to model package

UserService.java
   ↓
import com.myapp.model.User
   ↓
Can use User class
```

So remember:

```java
package com.myapp.model;       // Which package does this class belong to?

import com.myapp.model.User;   // Which external class do I want to use?
```

**Simple analogy:**
Package = **folder/category**
Import = **bring a class from another folder so you can use it**.

---

Absolutely. Using the same example, here's the **complete process from creating files → compiling → running**.

### 1. Project structure

```text
myapp/
└── src/
    └── com/
        └── myapp/
            ├── model/
            │   └── User.java
            └── service/
                └── UserService.java
```

### 2. `User.java`

```java
package com.myapp.model;

public class User {
    public String name = "John";
}
```

### 3. `UserService.java`

```java
package com.myapp.service;

import com.myapp.model.User;

public class UserService {

    public static void main(String[] args) {
        User user = new User();

        System.out.println(user.name);
    }
}
```

---

## 4. Compile

Open terminal inside the **`myapp`** directory:

```bash
javac -d out src/com/myapp/model/User.java src/com/myapp/service/UserService.java
```

`-d out` means:

> Put the generated `.class` files inside the `out` directory.

You'll get:

```text
myapp/
├── src/
└── out/
    └── com/
        └── myapp/
            ├── model/
            │   └── User.class
            └── service/
                └── UserService.class
```

### 5. Run

```bash
java -cp out com.myapp.service.UserService
```

Output:

```text
John
```

### Why `com.myapp.service.UserService` instead of `UserService`?

Because Java needs the **fully qualified class name**:

```text
package + class name
      ↓
com.myapp.service.UserService
```

And:

```bash
-cp out
```

means:

> Look for compiled classes inside the `out` directory.

### ⭐ Remember

```bash
javac -d out <source files>   # Compile
java -cp out <package>.<Class> # Run
```

In real Spring Boot projects, **Maven/Gradle handles compilation and classpath management for you**, so you won't normally compile everything manually like this.

---

Each folder generally represents a package.

For example:

```java
package com.myapp.shop.service;

public class UserService {
    // ...
}
```



The package declaration should normally correspond to the directory structure:

```text
com/myapp/shop/service/UserService.java
```

### Package naming convention

Use lowercase and usually reverse-domain naming:

```java
com.company.project
com.google.maps
com.myapp.shop
```

---

## 2. Import

`import` allows you to use a class from another package without writing its fully qualified name.

Suppose:

```java
package com.myapp.model;

public class User {
    public String name;
}
```

Another class:

```java
package com.myapp.service;

import com.myapp.model.User;

public class UserService {

    User user = new User();
}
```

Without `import`:

```java
com.myapp.model.User user = new com.myapp.model.User();
```

So:

```java
import com.myapp.model.User;
```

is basically saying:

> "I want to use `User` from this package."

---

### Import multiple classes

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
```

You can use wildcard:

```java
import java.util.*;
```

This imports classes directly inside `java.util`.

**Best practice:** Explicit imports are generally preferred over `*`.

---

### Static Import

You can import static members:

```java
import static java.lang.Math.PI;
import static java.lang.Math.max;

public class Test {
    double x = PI;
    int result = max(10, 20);
}
```

Instead of:

```java
Math.PI
Math.max(10, 20)
```

Static imports are less common in normal application code.

---

## 3. Important: `java.lang` doesn't need import

Java automatically imports `java.lang.*`.

Therefore:

```java
String name = "John";
System.out.println(name);
```

works without:

```java
import java.lang.String;
import java.lang.System;
```

---

## 4. Same Package → No Import Required

```java
package com.myapp.service;

class UserService { }

class OrderService {
    UserService service = new UserService();
}
```

Because both classes are in the same package.

---

## 5. Package vs Folder — Important

A package **usually maps to a folder**, but package is fundamentally a **Java namespace**, not merely a physical folder.

For Spring Boot, however, you should follow the conventional matching structure:

```text
com/myapp/shop/controller
        ↓
package com.myapp.shop.controller;
```

### ⭐ Interview takeaway

> **Package organizes Java classes into namespaces, while `import` allows a class to refer to classes from other packages using their simple names.**

For Spring Boot, you'll frequently see:

```java
package com.company.app.controller;

import com.company.app.service.UserService;
import org.springframework.web.bind.annotation.RestController;
```

This package/import concept is fundamental before moving into **Spring Boot's Controller → Service → Repository package structure**.
