package com.akeb.TP1.BranchCoverageTest;

import com.akeb.TP1.exo6.Exo6Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    @DisplayName("Test n <= 1 branch (true) - should throw IllegalArgumentException")
    public void testNLessThanOrEqualTo1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(1);
        });
    }
    
    @Test
    @DisplayName("Test n <= 1 branch (false)")
    public void testNGreaterThan1() {
        assertDoesNotThrow(() -> {
            Exo6Correction.fizzBuzz(2);
        });
    }
    
    @Test
    @DisplayName("Test n % 15 == 0 branch (true)")
    public void testDivisibleBy15() {
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(15));
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(30));
    }
    
    @Test
    @DisplayName("Test n % 15 == 0 branch (false) and n % 3 == 0 branch (true)")
    public void testDivisibleBy3Only() {
        assertEquals("Fizz", Exo6Correction.fizzBuzz(3));
        assertEquals("Fizz", Exo6Correction.fizzBuzz(9));
    }
    
    @Test
    @DisplayName("Test n % 15 == 0 branch (false), n % 3 == 0 branch (false), and n % 5 == 0 branch (true)")
    public void testDivisibleBy5Only() {
        assertEquals("Buzz", Exo6Correction.fizzBuzz(5));
        assertEquals("Buzz", Exo6Correction.fizzBuzz(10));
    }
    
    @Test
    @DisplayName("Test all condition branches false - return the number as string")
    public void testNotDivisibleByAny() {
        assertEquals("2", Exo6Correction.fizzBuzz(2));
        assertEquals("11", Exo6Correction.fizzBuzz(11));
    }
}
