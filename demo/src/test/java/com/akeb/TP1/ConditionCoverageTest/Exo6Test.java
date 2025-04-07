package com.akeb.TP1.ConditionCoverageTest;

import com.akeb.TP1.exo6.Exo6Correction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo6Test {

    @Test
    @DisplayName("Test condition n <= 1 (true with n < 1)")
    public void testNLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(0);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(-5);
        });
    }
    
    @Test
    @DisplayName("Test condition n <= 1 (true with n = 1)")
    public void testNEquals1() {
        assertThrows(IllegalArgumentException.class, () -> {
            Exo6Correction.fizzBuzz(1);
        });
    }
    
    @Test
    @DisplayName("Test condition n <= 1 (false)")
    public void testNGreaterThan1() {
        assertDoesNotThrow(() -> {
            Exo6Correction.fizzBuzz(2);
        });
    }
    
    @Test
    @DisplayName("Test condition n % 15 == 0 (true)")
    public void testDivisibleBy15() {
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(15));
        assertEquals("FizzBuzz", Exo6Correction.fizzBuzz(30));
    }
    
    @Test
    @DisplayName("Test condition n % 15 == 0 (false)")
    public void testNotDivisibleBy15() {
        assertNotEquals("FizzBuzz", Exo6Correction.fizzBuzz(16));
    }
    
    @Test
    @DisplayName("Test condition n % 3 == 0 (true)")
    public void testDivisibleBy3() {
        assertEquals("Fizz", Exo6Correction.fizzBuzz(3));
        assertEquals("Fizz", Exo6Correction.fizzBuzz(9));
    }
    
    @Test
    @DisplayName("Test condition n % 3 == 0 (false)")
    public void testNotDivisibleBy3() {
        assertNotEquals("Fizz", Exo6Correction.fizzBuzz(5));
    }
    
    @Test
    @DisplayName("Test condition n % 5 == 0 (true)")
    public void testDivisibleBy5() {
        assertEquals("Buzz", Exo6Correction.fizzBuzz(5));
        assertEquals("Buzz", Exo6Correction.fizzBuzz(10));
    }
    
    @Test
    @DisplayName("Test condition n % 5 == 0 (false)")
    public void testNotDivisibleBy5() {
        assertNotEquals("Buzz", Exo6Correction.fizzBuzz(7));
    }
    
    @Test
    @DisplayName("Test returning number as string")
    public void testReturnNumberAsString() {
        assertEquals("2", Exo6Correction.fizzBuzz(2));
        assertEquals("11", Exo6Correction.fizzBuzz(11));
    }
}
