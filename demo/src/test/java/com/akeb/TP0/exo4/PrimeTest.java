package com.akeb.TP0.exo4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.akeb.TP0.exo4.Prime;

public class PrimeTest {

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1})
    @DisplayName("Numbers less than 2 are not prime")
    public void testNonPositiveNumbers(int number) {
        assertFalse(Prime.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47})
    @DisplayName("Test known prime numbers")
    public void testKnownPrimeNumbers(int primeNumber) {
        assertTrue(Prime.isPrime(primeNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25})
    @DisplayName("Test known non-prime numbers")
    public void testKnownNonPrimeNumbers(int nonPrimeNumber) {
        assertFalse(Prime.isPrime(nonPrimeNumber));
    }

    @Test
    @DisplayName("Test smaller prime number 2")
    public void testSmallestPrime() {
        assertTrue(Prime.isPrime(2));
    }
    
    @Test
    @DisplayName("Test perfect squares (non-prime)")
    public void testPerfectSquares() {
        assertFalse(Prime.isPrime(4));
        assertFalse(Prime.isPrime(9));
        assertFalse(Prime.isPrime(16));
        assertFalse(Prime.isPrime(25));
    }

    @Test
    @DisplayName("Test performance with larger prime")
    public void testLargerPrime() {
        // Test with a larger prime number
        assertTrue(Prime.isPrime(997)); // 997 is prime
        
        // Time performance check for a larger prime
        long startTime = System.currentTimeMillis();
        boolean result = Prime.isPrime(7919); // 1000th prime number
        long endTime = System.currentTimeMillis();
        
        assertTrue(result);
        // Should be reasonably fast (under 100ms)
        assertTrue((endTime - startTime) < 100, 
            "Prime check for 7919 took too long: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("Test with large non-prime number")
    public void testLargeNonPrime() {
        // 999 = 3 * 333
        assertFalse(Prime.isPrime(999));
        
        // 1001 = 7 * 143 = 7 * 11 * 13
        assertFalse(Prime.isPrime(1001));
    }
}
