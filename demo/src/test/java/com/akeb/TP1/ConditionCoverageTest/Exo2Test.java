package com.akeb.TP1.ConditionCoverageTest;

import com.akeb.TP1.exo2.Exo2Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    @DisplayName("Test with first string null only")
    public void testFirstStringNull() {
        // Tests the first part of the condition (s1 == null) being true
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram(null, "test");
        });
    }
    
    @Test
    @DisplayName("Test with second string null only")
    public void testSecondStringNull() {
        // Tests the second part of the condition (s2 == null) being true
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram("test", null);
        });
    }
    
    @Test
    @DisplayName("Test with both strings null")
    public void testBothStringsNull() {
        // Tests both parts of the condition being true
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram(null, null);
        });
    }
    
    @Test
    @DisplayName("Test with neither string null")
    public void testNeitherStringNull() {
        // Tests both parts of the condition being false
        assertTrue(Exo2Correction.isAnagram("abc", "cba"));
    }
    
    @Test
    @DisplayName("Test with different length strings")
    public void testDifferentLengthStrings() {
        assertFalse(Exo2Correction.isAnagram("hello", "hi"));
    }
    
    @Test
    @DisplayName("Test with same length non-anagrams")
    public void testSameLengthNonAnagrams() {
        assertFalse(Exo2Correction.isAnagram("hello", "world"));
    }
    
    @Test
    @DisplayName("Test with perfect anagrams")
    public void testPerfectAnagrams() {
        assertTrue(Exo2Correction.isAnagram("listen", "silent"));
    }
}
