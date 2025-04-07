package com.akeb.TP1.LineCoverageTest;

import com.akeb.TP1.exo3.Exo3Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo3Test {

    @Test
    @DisplayName("Test with null array - should throw NullPointerException")
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> {
            Exo3Correction.binarySearch(null, 5);
        });
    }
    
    @Test
    @DisplayName("Test with element found in the array")
    public void testElementFound() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(2, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test with element not found in the array")
    public void testElementNotFound() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, Exo3Correction.binarySearch(array, 4));
    }
    
    @Test
    @DisplayName("Test with empty array")
    public void testEmptyArray() {
        int[] array = {};
        assertEquals(-1, Exo3Correction.binarySearch(array, 5));
    }
}
