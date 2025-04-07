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
