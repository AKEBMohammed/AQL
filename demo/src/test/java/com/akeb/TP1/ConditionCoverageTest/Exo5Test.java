package com.akeb.TP1.ConditionCoverageTest;

import com.akeb.TP1.exo5.Exo5Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    @DisplayName("Test condition n < 1 (true)")
    public void testNLessThan1True() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(0);
        });
    }
    
    @Test
    @DisplayName("Test condition n < 1 (false)")
    public void testNLessThan1False() {
        assertDoesNotThrow(() -> {
            Exo5Correction.toRoman(1);
        });
    }
    
    @Test
    @DisplayName("Test condition n > 3999 (true)")
    public void testNGreaterThan3999True() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(4000);
        });
    }
    
    @Test
    @DisplayName("Test condition n > 3999 (false)")
    public void testNGreaterThan3999False() {
        assertDoesNotThrow(() -> {
            Exo5Correction.toRoman(3999);
        });
    }
    
    @Test
    @DisplayName("Test combined condition (n < 1 || n > 3999) with different combinations")
    public void testCombinedCondition() {
        // Both false: valid input
        assertDoesNotThrow(() -> {
            Exo5Correction.toRoman(100);
        });
        
        // First true, second false: n < 1
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(0);
        });
        
        // First false, second true: n > 3999
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(4000);
        });
    }
    
    @Test
    @DisplayName("Test while loop condition (n >= values[i])")
    public void testWhileLoopCondition() {
        // Test cases for exact values in the array
        assertEquals("I", Exo5Correction.toRoman(1));
        assertEquals("IV", Exo5Correction.toRoman(4));
        assertEquals("V", Exo5Correction.toRoman(5));
        assertEquals("IX", Exo5Correction.toRoman(9));
        assertEquals("X", Exo5Correction.toRoman(10));
        
        // Test cases that require multiple iterations of the while loop
        assertEquals("III", Exo5Correction.toRoman(3));  // Uses 3 iterations of "I"
        assertEquals("XV", Exo5Correction.toRoman(15));  // Uses "X" then "V"
        assertEquals("MCMXCIX", Exo5Correction.toRoman(1999)); // Complex case
    }
    
    @Test
    @DisplayName("Test boundary values")
    public void testBoundaryValues() {
        // Boundaries of valid range
        assertEquals("I", Exo5Correction.toRoman(1));
        assertEquals("MMMCMXCIX", Exo5Correction.toRoman(3999));
    }
}
