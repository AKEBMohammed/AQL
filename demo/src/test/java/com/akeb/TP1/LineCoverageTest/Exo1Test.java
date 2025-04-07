package com.akeb.TP1.LineCoverageTest;

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
    @DisplayName("Test with valid palindrome")
    public void testValidPalindrome() {
        // This should cover the loop execution and true return
        assertTrue(Palindrome.isPalindrome("kayak"));
    }
    
    @Test
    @DisplayName("Test with non-palindrome")
    public void testNonPalindrome() {
        // This should cover the early return false inside the loop
        assertFalse(Palindrome.isPalindrome("hello"));
    }
    
    @Test
    @DisplayName("Test with empty string")
    public void testEmptyString() {
        // Edge case - empty string is considered a palindrome
        assertTrue(Palindrome.isPalindrome(""));
    }
}
