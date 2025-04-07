package com.akeb.TP1.BranchCoverageTest;

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
    @DisplayName("Test with element found in the middle")
    public void testElementFoundMiddle() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(2, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test with element found at the beginning")
    public void testElementFoundBeginning() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(0, Exo3Correction.binarySearch(array, 1));
    }
    
    @Test
    @DisplayName("Test with element found at the end")
    public void testElementFoundEnd() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(4, Exo3Correction.binarySearch(array, 9));
    }
    
    @Test
    @DisplayName("Test with element less than all elements")
    public void testElementLessThanAll() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, Exo3Correction.binarySearch(array, 0));
    }
    
    @Test
    @DisplayName("Test with element greater than all elements")
    public void testElementGreaterThanAll() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, Exo3Correction.binarySearch(array, 10));
    }
    
    @Test
    @DisplayName("Test with element between elements")
    public void testElementBetween() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, Exo3Correction.binarySearch(array, 4));
    }
    
    @Test
    @DisplayName("Test with array of size 1 - element found")
    public void testSingleElementArrayFound() {
        int[] array = {5};
        assertEquals(0, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test with array of size 1 - element not found")
    public void testSingleElementArrayNotFound() {
        int[] array = {5};
        assertEquals(-1, Exo3Correction.binarySearch(array, 3));
    }
}
