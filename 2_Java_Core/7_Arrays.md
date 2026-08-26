# Java Arrays — Array Manipulation

Since you know JavaScript, Java arrays are conceptually similar, but there is one major difference:

> **Java arrays have a fixed size.**

```java
int[] numbers = {10, 20, 30, 40};
```

---

## 1. Access & Update

Java arrays use **zero-based indexing**, just like JavaScript.

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[0]); // 10

numbers[1] = 50;

System.out.println(numbers[1]); // 50
```

Invalid index:

```java
numbers[5]; // ArrayIndexOutOfBoundsException
```

---

## 2. Array Length

Use `.length` — **not a method**.

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers.length); // 3
```

Compare with JavaScript:

```javascript
numbers.length
```

Same concept.

---

## 3. Traversing an Array

### Traditional `for`

```java
int[] numbers = {10, 20, 30};

for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

### Enhanced `for` ⭐

```java
for (int number : numbers) {
    System.out.println(number);
}
```

Use enhanced `for` when you don't need the index.

---

# 4. `Arrays` Utility Class ⭐

Java provides `java.util.Arrays` for common array operations.

```java
import java.util.Arrays;
```

### Print array

Don't do:

```java
System.out.println(numbers);
```

Instead:

```java
System.out.println(Arrays.toString(numbers));
```

Output:

```text
[10, 20, 30]
```

---

### Sort

```java
int[] numbers = {30, 10, 20};

Arrays.sort(numbers);

System.out.println(Arrays.toString(numbers));
```

Output:

```text
[10, 20, 30]
```

---

### Search — `binarySearch`

Array must be sorted first:

```java
int[] numbers = {10, 20, 30, 40};

int index = Arrays.binarySearch(numbers, 30);

System.out.println(index); // 2
```

If the value isn't found, it returns a negative value.

---

### Copy Array

```java
int[] original = {10, 20, 30};

int[] copy = Arrays.copyOf(original, original.length);
```

You can also copy a range:

```java
int[] copy = Arrays.copyOfRange(original, 0, 2);
```

End index is **exclusive**.

---

### Fill

```java
int[] numbers = new int[5];

Arrays.fill(numbers, 10);
```

Result:

```text
[10, 10, 10, 10, 10]
```

---

### Compare Arrays

Don't use:

```java
numbers1 == numbers2
```

That compares references.

Use:

```java
Arrays.equals(numbers1, numbers2);
```

---

# 5. Copying Arrays — Important ⭐

This:

```java
int[] a = {1, 2, 3};
int[] b = a;
```

does **not create a new array**.

Both variables refer to the same array:

```text
a ──┐
    ├──> [1, 2, 3]
b ──┘
```

So:

```java
b[0] = 100;

System.out.println(a[0]); // 100
```

For an actual copy:

```java
int[] b = Arrays.copyOf(a, a.length);
```

---

# 6. Multidimensional Arrays

Java supports arrays of arrays:

```java
int[][] matrix = {
    {1, 2},
    {3, 4}
};

System.out.println(matrix[0][1]); // 2
```

Java also supports **jagged arrays**:

```java
int[][] data = {
    {1, 2, 3},
    {4},
    {5, 6}
};
```

Each inner array can have a different length.

---

# 7. Fixed Size ⭐

Once created, an array's size cannot change.

```java
int[] numbers = new int[3];
```

You cannot add a fourth element.

For dynamic collections, use:

```java
List<Integer> numbers = new ArrayList<>();
```

This will become very important when you learn **Collections**.

---

## 🔥 Interview Must-Know

Remember:

* Java arrays are **fixed-size**.
* Index starts at `0`.
* `array.length` → size; it's a field, not a method.
* `ArrayIndexOutOfBoundsException` → invalid index.
* `Arrays.sort()` → sorting.
* `Arrays.toString()` → readable output.
* `Arrays.equals()` → compare contents.
* `Arrays.copyOf()` → create a copy.
* `Arrays.binarySearch()` → search sorted array.
* `Arrays.fill()` → fill values.
* `b = a` → copies the **reference**, not the array.
* For dynamic size → use **`ArrayList` / `List`**.

### Java vs JavaScript

| Java                               | JavaScript            |
| ---------------------------------- | --------------------- |
| `int[]`                            | `Array`               |
| Fixed size                         | Dynamic               |
| `array.length`                     | `array.length`        |
| `Arrays.sort()`                    | `array.sort()`        |
| `Arrays.toString()`                | `array.toString()`    |
| `ArrayList` for dynamic collection | `Array` commonly used |

**For Spring Boot, don't spend too much time on arrays. Learn them well enough, then focus more on `List`, `Set`, `Map`, and Streams — these are much more common in backend Java code.**
