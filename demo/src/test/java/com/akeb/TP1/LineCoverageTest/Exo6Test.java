package com.akeb.TP1.LineCoverageTest;

import com.akeb.TP1.exo6.Exo6Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    @DisplayName("Test with invalid input (n <= 1) - should throw IllegalArgumentException")
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(1);
        });
    }
    
    @Test
    @DisplayName("Test with number divisible by both 3 and 5")
    public void testFizzBuzz() {
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(15));
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(30));
    }
    
    @Test
    @DisplayName("Test with number divisible by 3 only")
    public void testFizz() {
        assertEquals("Fizz", Exo6Correction.fizzBuzz(3));
        assertEquals("Fizz", Exo6Correction.fizzBuzz(9));
    }
    
    @Test
    @DisplayName("Test with number divisible by 5 only")
    public void testBuzz() {
        assertEquals("Buzz", Exo6Correction.fizzBuzz(5));
        assertEquals("Buzz", Exo6Correction.fizzBuzz(20));
    }
    
    @Test
    @DisplayName("Test with number not divisible by 3 or 5")
    public void testNumber() {
        assertEquals("2", Exo6Correction.fizzBuzz(2));
        assertEquals("11", Exo6Correction.fizzBuzz(11));
    }
}
