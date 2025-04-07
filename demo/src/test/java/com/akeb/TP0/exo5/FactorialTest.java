package com.akeb.TP0.exo5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.akeb.TP0.exo5.Factorial;

import org.junit.jupiter.api.DisplayName;

public class FactorialTest {

    @Test
    @DisplayName("Factorial of 0 should be 1")
    public void testFactorialOfZero() {
        assertEquals(1, Factorial.factorial(0));
    }

    @Test
    @DisplayName("Factorial of 1 should be 1")
    public void testFactorialOfOne() {
        assertEquals(1, Factorial.factorial(1));
    }

    @Test
    @DisplayName("Factorial of 5 should be 120")
    public void testFactorialOfFive() {
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    @DisplayName("Factorial of 10 should be 3628800")
    public void testFactorialOfTen() {
        assertEquals(3628800, Factorial.factorial(10));
    }

    @Test
    @DisplayName("Factorial of negative number should throw IllegalArgumentException")
    public void testFactorialOfNegativeNumber() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Factorial.factorial(-1)
        );
        
        assertEquals("n must be positive", exception.getMessage());
    }
}
