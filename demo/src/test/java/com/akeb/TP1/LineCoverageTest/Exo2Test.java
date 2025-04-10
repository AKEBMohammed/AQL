package com.akeb.TP1.LineCoverageTest;

import com.akeb.TP1.exo2.Exo2Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    @DisplayName("Test with null inputs - should throw NullPointerException")
    public void testNullInputs() {
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram(null, "test");
        });
        
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram("test", null);
        });
    }
    
    @Test
    @DisplayName("Test with different length strings")
    public void testDifferentLengthStrings() {
        // This covers the early return false when lengths differ
        assertFalse(Exo2Correction.isAnagram("hello", "hi"));
    }
    
    @Test
    @DisplayName("Test with valid anagrams")
    public void testValidAnagrams() {
        // This covers the character counting and final return true path
        assertTrue(Exo2Correction.isAnagram("chien", "niche"));
    }
    
    @Test
    @DisplayName("Test with non-anagrams of same length")
    public void testNonAnagrams() {
        // This covers the case where character counts differ
        assertFalse(Exo2Correction.isAnagram("hello", "world"));
    }
}
