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

## Exercise 3: BinarySearch Class

### Bug Identified
The `BinarySearch.binarySearch()` method contains two bugs:

1. The loop condition `while (low < high)` is incorrect. It should be `while (low <= high)` to handle the case where the element might be at the last position (when low equals high).

2. The condition `array[mid] <= element` in the else-if statement is incorrect. It should be `array[mid] < element`. The current implementation could skip the target element if it's equal to the middle element but not at the middle index.

These bugs cause the method to:
- Miss elements at the position where low = high
- Potentially skip the target element due to incorrect comparison

The corrected implementation is available in `Exo3Correction.java`.

## Exercise 4: QuadraticEquation Class

### Bug Analysis
After thorough testing, no bugs were found in the `QuadraticEquation.solve()` method. The method correctly:

1. Checks if the coefficient 'a' is zero and throws an appropriate exception
2. Calculates the discriminant (delta) correctly
3. Returns null for negative discriminants (no real roots)
4. Returns a single root array for zero discriminant
5. Returns two-root array for positive discriminant
6. Uses the correct formula for calculating roots: (-b ± √delta)/(2a)

The implementation follows standard mathematical procedures for solving quadratic equations and handles all edge cases properly.

### Coverage Tests
All three coverage criteria (Line, Branch, and Condition) lead to similar test cases for this class, as the branching structure is simple and matches the conditions directly.

## Exercise 5: RomanNumeral Class

### Bug Identified
The `RomanNumeral.toRoman()` method contains two critical bugs:

1. The loop condition in the for loop is incorrect: `for (int i = 0; i <= symbols.length; i++)`. This will cause an `ArrayIndexOutOfBoundsException` on the last iteration because array indices are 0-based, and the valid range is from 0 to `symbols.length - 1`.

2. The condition in the while loop is `n > values[i]`, which should be `n >= values[i]`. This means that when `n` equals a value in the array, it won't be processed. For example, if `n = 5`, it would never add the symbol "V" to the result.

These bugs cause the method to:
- Throw an exception when accessing `symbols[symbols.length]`
- Incorrectly convert some numbers (e.g., exact values like 5, 10, etc. would be skipped and processed via smaller denominations)

The corrected implementation is available in `Exo5Correction.java`.
