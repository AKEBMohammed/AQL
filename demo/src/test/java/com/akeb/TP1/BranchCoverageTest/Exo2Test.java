package com.akeb.TP1.BranchCoverageTest;

import com.akeb.TP1.exo2.Exo2Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo2Test {

    @Test
    @DisplayName("Test with first string null")
    public void testFirstStringNull() {
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram(null, "test");
        });
    }
    
    @Test
    @DisplayName("Test with second string null")
    public void testSecondStringNull() {
        assertThrows(NullPointerException.class, () -> {
            Exo2Correction.isAnagram("test", null);
        });
    }
    
    @Test
    @DisplayName("Test with different length strings")
    public void testDifferentLengthStrings() {
        assertFalse(Exo2Correction.isAnagram("hello", "hi"));
    }
    
    @Test
    @DisplayName("Test with valid anagrams")
    public void testValidAnagrams() {
        assertTrue(Exo2Correction.isAnagram("chien", "niche"));
    }
    
    @Test
    @DisplayName("Test with non-anagrams of same length")
    public void testNonAnagrams() {
        assertFalse(Exo2Correction.isAnagram("hello", "world"));
    }
    
    @Test
    @DisplayName("Test with anagrams containing spaces and case differences")
    public void testAnagramsWithSpacesAndCase() {
        assertTrue(Exo2Correction.isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort"));
    }
    
    @Test
    @DisplayName("Test with empty strings")
    public void testEmptyStrings() {
        assertTrue(Exo2Correction.isAnagram("", ""));
    }
}
