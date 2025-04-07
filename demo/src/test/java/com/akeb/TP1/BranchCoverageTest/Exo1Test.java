package com.akeb.TP1.BranchCoverageTest;

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
        // This covers the 'if (s.charAt(i) != s.charAt(j))' branch evaluating to false
        assertTrue(Palindrome.isPalindrome("kayak"));
    }
    
    @Test
    @DisplayName("Test with non-palindrome")
    public void testNonPalindrome() {
        // This covers the 'if (s.charAt(i) != s.charAt(j))' branch evaluating to true
        assertFalse(Palindrome.isPalindrome("hello"));
    }
    
    @Test
    @DisplayName("Test with single character")
    public void testSingleCharacter() {
        // This covers the case where the loop condition 'i < j' is false immediately
        assertTrue(Palindrome.isPalindrome("a"));
    }
    
    @Test
    @DisplayName("Test with palindrome containing spaces and case differences")
    public void testPalindromeWithSpacesAndCase() {
        // Tests the string preprocessing (toLowerCase and removing spaces)
        assertTrue(Palindrome.isPalindrome("A man a plan a canal Panama"));
    }
}
