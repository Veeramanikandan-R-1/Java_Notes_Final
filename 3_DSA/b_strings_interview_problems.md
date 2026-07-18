# Strings Interview Problems

### Subtopic: Interview Problems

---

# 1. Fundamentals

String interview problems test character scanning, frequency counting, substring logic, and immutability awareness.

In Java, `String` is immutable.

```java
String s = "java";
```

Repeated modification should use `StringBuilder`.

---

# 2. Core Concepts

## Character Traversal

```java
for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
}
```

Use this for validation, counting, and parsing.

---

## Frequency Counting

For lowercase English letters:

```java
int[] freq = new int[26];

for (int i = 0; i < s.length(); i++) {
    freq[s.charAt(i) - 'a']++;
}
```

For general characters:

```java
Map<Character, Integer> freq = new HashMap<>();

for (char ch : s.toCharArray()) {
    freq.put(ch, freq.getOrDefault(ch, 0) + 1);
}
```

---

## Two Pointers

Useful for palindrome and reversal problems.

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        left++;
        right--;
    }

    return true;
}
```

---

## Sliding Window

Useful for substring problems.

```java
public static int longestUniqueSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0;
    int best = 0;

    for (int right = 0; right < s.length(); right++) {
        while (window.contains(s.charAt(right))) {
            window.remove(s.charAt(left++));
        }

        window.add(s.charAt(right));
        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

---

# 3. Internal Working

Because `String` is immutable, every modification creates a new object.

Bad:

```java
String result = "";
for (char ch : chars) {
    result += ch;
}
```

Good:

```java
StringBuilder sb = new StringBuilder();
for (char ch : chars) {
    sb.append(ch);
}
```

---

# 4. Common Mistakes

* Using `==` instead of `.equals()`.
* Repeated string concatenation in loops.
* Not handling case sensitivity.
* Forgetting whitespace.
* Assuming ASCII when input can be Unicode.
* Off-by-one errors in substring.
* Not normalizing input before comparison.

---

# 5. Best Practices

* Use `StringBuilder` for repeated appends.
* Use frequency arrays for constrained lowercase input.
* Use maps for general character sets.
* Normalize input if needed.
* Clarify whether comparison is case-sensitive.
* Use two pointers for palindrome/reversal.
* Use sliding window for substring optimization.

---

# 6. Code Examples

## Anagram Check

```java
public static boolean isAnagram(String a, String b) {
    if (a.length() != b.length()) return false;

    int[] freq = new int[26];

    for (int i = 0; i < a.length(); i++) {
        freq[a.charAt(i) - 'a']++;
        freq[b.charAt(i) - 'a']--;
    }

    for (int count : freq) {
        if (count != 0) return false;
    }

    return true;
}
```

---

## Reverse Words

```java
public static String reverseWords(String input) {
    String[] words = input.strip().split("\\s+");
    StringBuilder sb = new StringBuilder();

    for (int i = words.length - 1; i >= 0; i--) {
        if (sb.length() > 0) sb.append(' ');
        sb.append(words[i]);
    }

    return sb.toString();
}
```

---

# 7. Real-world Scenarios

* Input validation.
* Token parsing.
* Search/autocomplete.
* Log message processing.
* URL and file path parsing.
* Case-insensitive lookup.

---

# Revision Notes

* Strings are immutable.
* Use `StringBuilder` for repeated modification.
* Frequency arrays work for limited character sets.
* HashMap works for general frequency counting.
* Two pointers solve palindrome-style problems.
* Sliding window solves substring problems.
* Normalize case/space when required.

---

# Cheat Sheet

| Problem Type | Pattern |
| ------------ | ------- |
| Palindrome | Two pointers |
| Anagram | Frequency count |
| Longest substring | Sliding window |
| Reversal | StringBuilder / two pointers |
| First non-repeat | HashMap / array |
| Word parsing | split / scan |

---

# Interview Questions with Answers

### 1. Why is StringBuilder preferred in loops?

It mutates the same buffer instead of creating many temporary strings.

### 2. How to check anagram efficiently?

Count character frequencies and ensure both strings produce equal counts.

### 3. When use sliding window?

When solving contiguous substring problems with changing boundaries.

---

# Hands-on Exercises

### Exercise 1

Find first non-repeating character.

**Answer**

```java
public static char firstNonRepeating(String s) {
    Map<Character, Integer> freq = new LinkedHashMap<>();

    for (char ch : s.toCharArray()) {
        freq.put(ch, freq.getOrDefault(ch, 0) + 1);
    }

    for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
        if (entry.getValue() == 1) return entry.getKey();
    }

    return '\0';
}
```

