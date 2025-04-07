package com.akeb.TP1.exo1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PalindromeTest {

    @Test
    @DisplayName("Simple palindromes should return true")
    public void testSimplePalindromes() {
        assertTrue(Palindrome.isPalindrome("kayak"));
        assertTrue(Palindrome.isPalindrome("radar"));
        assertTrue(Palindrome.isPalindrome("level"));
        assertTrue(Palindrome.isPalindrome("rotator"));
        assertTrue(Palindrome.isPalindrome("racecar"));
    }

    @Test
    @DisplayName("Palindromes with spaces should return true")
    public void testPalindromesWithSpaces() {
        assertTrue(Palindrome.isPalindrome("step on no pets"));
        assertTrue(Palindrome.isPalindrome("a man a plan a canal panama"));
        assertTrue(Palindrome.isPalindrome("never odd or even"));
    }

    @Test
    @DisplayName("Palindromes with mixed case should return true")
    public void testPalindromesWithMixedCase() {
        assertTrue(Palindrome.isPalindrome("Able was I ere I saw Elba"));
        assertTrue(Palindrome.isPalindrome("Madam Im Adam"));
        assertTrue(Palindrome.isPalindrome("A Santa at Nasa"));
    }

    @Test
    @DisplayName("Non-palindromes should return false")
    public void testNonPalindromes() {
        assertFalse(Palindrome.isPalindrome("hello"));
        assertFalse(Palindrome.isPalindrome("world"));
        assertFalse(Palindrome.isPalindrome("java"));
        assertFalse(Palindrome.isPalindrome("programming"));
    }

    @Test
    @DisplayName("Edge cases should be handled correctly")
    public void testEdgeCases() {
        assertTrue(Palindrome.isPalindrome(""));  // Empty string is a palindrome
        assertTrue(Palindrome.isPalindrome("a")); // Single character is a palindrome
        assertTrue(Palindrome.isPalindrome("  ")); // Just spaces is considered a palindrome
    }

    @Test
    @DisplayName("The example 'Esope reste ici et se repose' should be a palindrome")
    public void testFrenchExample() {
        assertTrue(Palindrome.isPalindrome("Esope reste ici et se repose"));
    }

    @Test
    @DisplayName("Null input should throw NullPointerException")
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            Palindrome.isPalindrome(null);
        });
    }

    @Test
    @DisplayName("Special characters should be considered")
    public void testWithSpecialCharacters() {
        assertFalse(Palindrome.isPalindrome("ab!a"));
        assertTrue(Palindrome.isPalindrome("a,b,a"));
    }
}
