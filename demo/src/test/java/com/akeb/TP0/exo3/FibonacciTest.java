package com.akeb.TP0.exo3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.akeb.TP0.exo3.Fibonacci;

public class FibonacciTest {

    @Test
    @DisplayName("Test fibonacci(0) returns 0")
    public void testFibonacciZero() {
        assertEquals(0, Fibonacci.fibonacci(0));
    }

    @Test
    @DisplayName("Test fibonacci(1) returns 1")
    public void testFibonacciOne() {
        assertEquals(1, Fibonacci.fibonacci(1));
    }

    @ParameterizedTest(name = "fibonacci({0}) = {1}")
    @CsvSource({
        "2, 1",
        "3, 2",
        "4, 3",
        "5, 5",
        "6, 8",
        "7, 13",
        "8, 21",
        "9, 34",
        "10, 55"
    })
    @DisplayName("Test fibonacci with various inputs")
    public void testFibonacciValues(int input, int expected) {
        assertEquals(expected, Fibonacci.fibonacci(input));
    }

    @Test
    @DisplayName("Test fibonacci throws exception for negative input")
    public void testFibonacciNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> Fibonacci.fibonacci(-1));
        assertEquals("n must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Test edge case with larger Fibonacci number")
    public void testLargerFibonacci() {
        // Testing a slightly larger value (but not too large to avoid long execution)
        assertEquals(610, Fibonacci.fibonacci(15));
    }

    @Test
    @DisplayName("Test performance for smaller inputs")
    public void testPerformanceSmallInput() {
        // Performance test for smaller input (should be fast)
        long startTime = System.currentTimeMillis();
        Fibonacci.fibonacci(10);
        long endTime = System.currentTimeMillis();
        
        // Should complete very quickly (less than 100ms)
        assertTrue((endTime - startTime) < 100, 
            "Calculation for n=10 took too long: " + (endTime - startTime) + "ms");
    }
}
