# Revision Notes

* String is immutable in Java.
* Use `StringBuilder` for repeated string modification.
* Use two pointers for palindrome and reversal problems.
* Use frequency counting for anagram and duplicate character problems.
* Use sliding window for substring problems.
* Use `Map<Character, Integer>` for general character frequency.
* Use `int[26]` only when input is limited to lowercase English letters.
* Clarify case sensitivity and whitespace rules.

---

# Cheat Sheet

| Problem Type | Pattern |
| ------------ | ------- |
| Palindrome | Two pointers |
| Anagram | Frequency count |
| Longest unique substring | Sliding window |
| Reverse string | StringBuilder |
| First non-repeat | LinkedHashMap / array |
| Normalize text | `strip`, case conversion |

---

# Interview Questions with Answers

### 1. Why avoid string concatenation in loops?

Because strings are immutable and repeated concatenation creates many temporary objects.

### 2. How do you check anagram?

Compare character frequencies of both strings.

### 3. When is sliding window useful for strings?

When the answer is based on a contiguous substring.

---

# Hands-on Exercises

### Exercise 1

Check palindrome.

**Answer**

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left++) != s.charAt(right--)) return false;
    }

    return true;
}
```

---

### Exercise 2

Check anagram for lowercase strings.

**Answer**

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

