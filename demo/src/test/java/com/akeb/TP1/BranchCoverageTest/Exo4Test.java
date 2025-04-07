package com.akeb.TP1.BranchCoverageTest;

import com.akeb.TP1.exo4.QuadraticEquation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo4Test {

    private static final double DELTA = 1e-10;

    @Test
    @DisplayName("Test with a=0 - should throw IllegalArgumentException")
    public void testAEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquation.solve(0, 2, 1);
        });
    }
    
    @Test
    @DisplayName("Test with a!=0 - should not throw exception")
    public void testANotEqualsZero() {
        // Just verify no exception is thrown
        assertDoesNotThrow(() -> {
            QuadraticEquation.solve(1, 2, 1);
        });
    }
    
    @Test
    @DisplayName("Test with negative discriminant - should return null")
    public void testNegativeDiscriminant() {
        // For equation x^2 + x + 1 = 0, delta = 1 - 4 = -3
        assertNull(QuadraticEquation.solve(1, 1, 1));
    }
    
    @Test
    @DisplayName("Test with zero discriminant - should return one root")
    public void testZeroDiscriminant() {
        // For equation x^2 - 4x + 4 = 0, delta = 16 - 16 = 0
        double[] roots = QuadraticEquation.solve(1, -4, 4);
        assertEquals(1, roots.length);
        assertEquals(2.0, roots[0], DELTA);
    }
    
    @Test
    @DisplayName("Test with positive discriminant - should return two roots")
    public void testPositiveDiscriminant() {
        // For equation x^2 - 5x + 6 = 0, delta = 25 - 24 = 1
        double[] roots = QuadraticEquation.solve(1, -5, 6);
        assertEquals(2, roots.length);
        // Sort the roots to ensure consistent test results
        if (roots[0] < roots[1]) {
            double temp = roots[0];
            roots[0] = roots[1];
            roots[1] = temp;
        }
        assertEquals(3.0, roots[0], DELTA);
        assertEquals(2.0, roots[1], DELTA);
    }
}
