package com.akeb.TP1.ConditionCoverageTest;

import com.akeb.TP1.exo1.Palindrome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo1Test {

    @Test
    @DisplayName("Test with null input - should throw NullPointerException")
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            Palindrome.isPalindrome(null);
        });
    }
    
    @Test
    @DisplayName("Test with valid palindrome - even length")
    public void testValidPalindromeEven() {
        // Tests 'i < j' condition and 'charAt(i) != charAt(j)' condition
        assertTrue(Palindrome.isPalindrome("abba"));
    }
    
    @Test
    @DisplayName("Test with valid palindrome - odd length")
    public void testValidPalindromeOdd() {
        // Tests with different loop iterations
        assertTrue(Palindrome.isPalindrome("kayak"));
    }
    
    @Test
    @DisplayName("Test with non-palindrome - early difference")
    public void testNonPalindromeEarlyDiff() {
        // First characters differ - tests 'charAt(i) != charAt(j)' condition = true
        assertFalse(Palindrome.isPalindrome("hello"));
    }
    
    @Test
    @DisplayName("Test with non-palindrome - late difference")
    public void testNonPalindromeLatedDiff() {
        // Characters differ in the middle - tests deeper loop iterations
        assertFalse(Palindrome.isPalindrome("abcda"));
    }
    
    @Test
    @DisplayName("Test with single character")
    public void testSingleCharacter() {
        // This tests the 'i < j' condition evaluating to false immediately
        assertTrue(Palindrome.isPalindrome("a"));
    }
    
    @Test
    @DisplayName("Test with empty string")
    public void testEmptyString() {
        // Edge case - tests 'i < j' condition with an empty string
        assertTrue(Palindrome.isPalindrome(""));
    }
}
