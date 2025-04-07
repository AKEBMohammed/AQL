package com.akeb.TP1.BranchCoverageTest;

import com.akeb.TP1.exo5.Exo5Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo5Test {

    @Test
    @DisplayName("Test n < 1 branch - should throw IllegalArgumentException")
    public void testNumberLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(-10);
        });
    }
    
    @Test
    @DisplayName("Test n > 3999 branch - should throw IllegalArgumentException")
    public void testNumberGreaterThan3999() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(4000);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Exo5Correction.toRoman(10000);
        });
    }
    
    @Test
    @DisplayName("Test valid range branch - should not throw exception")
    public void testValidRange() {
        assertDoesNotThrow(() -> {
            Exo5Correction.toRoman(1);
            Exo5Correction.toRoman(3999);
            Exo5Correction.toRoman(100);
        });
    }
    
    @Test
    @DisplayName("Test numbers that use each symbol")
    public void testAllSymbols() {
        assertEquals("I", Exo5Correction.toRoman(1));
        assertEquals("IV", Exo5Correction.toRoman(4));
        assertEquals("V", Exo5Correction.toRoman(5));
        assertEquals("IX", Exo5Correction.toRoman(9));
        assertEquals("X", Exo5Correction.toRoman(10));
        assertEquals("XL", Exo5Correction.toRoman(40));
        assertEquals("L", Exo5Correction.toRoman(50));
        assertEquals("XC", Exo5Correction.toRoman(90));
        assertEquals("C", Exo5Correction.toRoman(100));
        assertEquals("CD", Exo5Correction.toRoman(400));
        assertEquals("D", Exo5Correction.toRoman(500));
        assertEquals("CM", Exo5Correction.toRoman(900));
        assertEquals("M", Exo5Correction.toRoman(1000));
    }
    
    @Test
    @DisplayName("Test inner while loop executed multiple times")
    public void testRepeatedSymbols() {
        // Tests cases where symbols repeat (2-3 times)
        assertEquals("II", Exo5Correction.toRoman(2));
        assertEquals("III", Exo5Correction.toRoman(3));
        assertEquals("XX", Exo5Correction.toRoman(20));
        assertEquals("XXX", Exo5Correction.toRoman(30));
        assertEquals("CC", Exo5Correction.toRoman(200));
        assertEquals("CCC", Exo5Correction.toRoman(300));
        assertEquals("MM", Exo5Correction.toRoman(2000));
        assertEquals("MMM", Exo5Correction.toRoman(3000));
    }
    
    @Test
    @DisplayName("Test complex cases")
    public void testComplexCases() {
        assertEquals("MCMXCIV", Exo5Correction.toRoman(1994));
        assertEquals("MMCDXLIV", Exo5Correction.toRoman(2444));
        assertEquals("MMCMXCIX", Exo5Correction.toRoman(2999));
    }
}
