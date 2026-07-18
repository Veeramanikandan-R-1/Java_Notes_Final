# Reflection in Java

### Subtopic: Reflection API, Class, Method, Field

---

# 1. Fundamentals

Reflection allows Java code to inspect and interact with classes, methods, fields, and constructors at runtime.

```java
Class<?> clazz = User.class;
```

It is powerful but should be used carefully.

---

# 2. Core Concepts

## Class Object

Every loaded class has a `Class` object.

```java
Class<User> clazz = User.class;
Class<?> runtimeClass = user.getClass();
Class<?> loaded = Class.forName("com.example.User");
```

## Inspect Fields

```java
Field[] fields = clazz.getDeclaredFields();
```

Access one field:

```java
Field field = clazz.getDeclaredField("email");
field.setAccessible(true);
Object value = field.get(user);
```

## Inspect Methods

```java
Method method = clazz.getDeclaredMethod("getEmail");
Object result = method.invoke(user);
```

## Inspect Constructors

```java
Constructor<User> constructor = clazz.getConstructor(String.class);
User user = constructor.newInstance("a@test.com");
```

## Annotations

Reflection is commonly used to read annotations.

```java
if (clazz.isAnnotationPresent(Entity.class)) {
    System.out.println("Entity class");
}
```

---

# 3. Internal Working

Reflection uses runtime metadata stored by the JVM.

Frameworks use it to:

* Create objects
* Inject dependencies
* Map JSON to objects
* Read annotations
* Call methods dynamically

Reflection can bypass normal access checks when `setAccessible(true)` is used.

---

# 4. Common Mistakes

* Using reflection when normal method calls are possible.
* Ignoring performance overhead.
* Breaking encapsulation with private field access.
* Not handling checked reflection exceptions.
* Making code fragile by depending on field names.
* Using reflection in hot paths without measurement.

---

# 5. Best Practices

* Prefer normal code over reflection.
* Use reflection mainly for frameworks, libraries, and generic tooling.
* Cache reflective lookups if repeated.
* Avoid private access unless necessary.
* Handle exceptions clearly.
* Be careful with security and module access.

---

# 6. Code Example

```java
import java.lang.reflect.Field;

class ObjectPrinter {
    static void printFields(Object object) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                System.out.println(field.getName() + "=" + field.get(object));
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Cannot access field", ex);
            }
        }
    }
}
```

---

# 7. Real-world Scenarios

* Spring dependency injection.
* Hibernate entity mapping.
* Jackson JSON serialization.
* Testing utilities.
* Annotation processors at runtime.
* Generic object mappers.

---

# Revision Notes

* Reflection inspects classes at runtime.
* `Class` represents runtime class metadata.
* `Field` represents class fields.
* `Method` represents methods.
* `Constructor` creates objects reflectively.
* Reflection can access annotations.
* Reflection can break encapsulation.
* Use carefully due to performance and safety concerns.

---

# Cheat Sheet

| Need | API |
| ---- | --- |
| Class metadata | `User.class` |
| Runtime class | `obj.getClass()` |
| Load class | `Class.forName()` |
| Fields | `getDeclaredFields()` |
| Method | `getDeclaredMethod()` |
| Invoke | `method.invoke(obj)` |
| Constructor | `getConstructor()` |
| Private access | `setAccessible(true)` |

---

# Interview Questions with Answers

### 1. What is reflection?

Runtime inspection and manipulation of classes, fields, methods, and constructors.

### 2. Why is reflection slower?

It involves dynamic lookup and access checks instead of direct compiled calls.

### 3. Where is reflection used?

Frameworks like Spring, Hibernate, and Jackson use it heavily.

---

# Hands-on Exercises

### Exercise 1

Print all declared field names of an object.

**Answer**

```java
public static void printFieldNames(Object object) {
    for (Field field : object.getClass().getDeclaredFields()) {
        System.out.println(field.getName());
    }
}
```

