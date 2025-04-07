# TP1 - Testing and Bug Fixes

## Exercise 1: Palindrome Class

### Bug Identified
The `Palindrome.isPalindrome()` method contains a critical bug in the while loop index manipulation:

```java
while (i < j) {
    if (s.charAt(i) != s.charAt(j)) {
        return false;
    }
    j++;  // BUG: j should be decremented, not incremented
    i--;  // BUG: i should be incremented, not decremented
}
```

The indices `i` and `j` are supposed to move towards each other to check if characters at opposite ends match. However, the current implementation does the opposite:
- `j++` increments j (making it go beyond the string length)
- `i--` decrements i (making it go below 0)

This would cause an `ArrayIndexOutOfBoundsException` once executed.

### Coverage Tests
The three coverage criteria tests (Line, Branch, and Condition) would all encounter this bug, but the tests themselves wouldn't run successfully due to the ArrayIndexOutOfBoundsException.

The corrected implementation is available in `Exo1Correction.java`.

## Exercise 2: Anagram Class

### Bug Identified
The `Anagram.isAnagram()` method contains a critical bug in the loop that counts characters:

```java
for (int i = 0; i <= s1.length(); i++) {
    count[s1.charAt(i) - 'a']++;
    count[s2.charAt(i) - 'a']--;
}
```

The loop condition is `i <= s1.length()`, which will cause an `ArrayIndexOutOfBoundsException` on the last iteration because:
1. Array indices in Java are 0-based, so the valid range is 0 to length-1
2. On the last iteration, i = s1.length(), which is out of bounds
3. Accessing s1.charAt(s1.length()) or s2.charAt(s2.length()) throws an exception

The corrected implementation is available in `Exo2Correction.java`.
