package com.akeb.TP1.LineCoverageTest;

import com.akeb.TP1.exo5.Exo5Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    @DisplayName("Test with invalid input (< 1) - should throw IllegalArgumentException")
    public void testInvalidInputLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(0);
        });
    }
    
    @Test
    @DisplayName("Test with invalid input (> 3999) - should throw IllegalArgumentException")
    public void testInvalidInputGreaterThan3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(4000);
        });
    }
    
    @Test
    @DisplayName("Test with valid simple inputs")
    public void testSimpleInputs() {
        assertEquals("I", Exo5Correction.toRoman(1));
        assertEquals("V", Exo5Correction.toRoman(5));
        assertEquals("X", Exo5Correction.toRoman(10));
        assertEquals("L", Exo5Correction.toRoman(50));
        assertEquals("C", Exo5Correction.toRoman(100));
        assertEquals("D", Exo5Correction.toRoman(500));
        assertEquals("M", Exo5Correction.toRoman(1000));
    }
    
    @Test
    @DisplayName("Test with subtractive notation inputs")
    public void testSubtractiveInputs() {
        assertEquals("IV", Exo5Correction.toRoman(4));
        assertEquals("IX", Exo5Correction.toRoman(9));
        assertEquals("XL", Exo5Correction.toRoman(40));
        assertEquals("XC", Exo5Correction.toRoman(90));
        assertEquals("CD", Exo5Correction.toRoman(400));
        assertEquals("CM", Exo5Correction.toRoman(900));
    }
    
    @Test
    @DisplayName("Test with complex numbers")
    public void testComplexNumbers() {
        assertEquals("MMXXI", Exo5Correction.toRoman(2021));
        assertEquals("MCMXCIX", Exo5Correction.toRoman(1999));
        assertEquals("MMCDXLIV", Exo5Correction.toRoman(2444));
    }
}
