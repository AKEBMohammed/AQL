package com.akeb.TP1.ConditionCoverageTest;

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
    @DisplayName("Test with empty array (low < high is false immediately)")
    public void testEmptyArray() {
        int[] array = {};
        assertEquals(-1, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test array of size 1 - low = high case")
    public void testArraySizeOne() {
        int[] array = {5};
        assertEquals(0, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test array[mid] == element - true condition")
    public void testMidElementEquals() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(2, Exo3Correction.binarySearch(array, 5));
    }
    
    @Test
    @DisplayName("Test array[mid] < element - true condition")
    public void testMidElementLessThan() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(3, Exo3Correction.binarySearch(array, 7));
    }
    
    @Test
    @DisplayName("Test array[mid] > element - true condition")
    public void testMidElementGreaterThan() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(1, Exo3Correction.binarySearch(array, 3));
    }
    
    @Test
    @DisplayName("Test array[mid] == element but not at mid index")
    public void testElementEqualsButNotAtMid() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(4, Exo3Correction.binarySearch(array, 9));
    }
    
    @Test
    @DisplayName("Test element not in array")
    public void testElementNotInArray() {
        int[] array = {1, 3, 5, 7, 9};
        assertEquals(-1, Exo3Correction.binarySearch(array, 4));
    }
}
