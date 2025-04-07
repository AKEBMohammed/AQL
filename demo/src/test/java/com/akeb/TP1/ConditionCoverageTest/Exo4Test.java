package com.akeb.TP1.ConditionCoverageTest;

import com.akeb.TP1.exo4.QuadraticEquation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Exo4Test {

    private static final double DELTA = 1e-10;

    @Test
    @DisplayName("Test condition a == 0 (true)")
    public void testAEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuadraticEquation.solve(0, 2, 1);
        });
    }
    
    @Test
    @DisplayName("Test condition a == 0 (false)")
    public void testANotEqualsZero() {
        // Will evaluate a == 0 as false
        assertDoesNotThrow(() -> {
            QuadraticEquation.solve(1, 2, 1);
        });
    }
    
    @Test
    @DisplayName("Test condition delta < 0 (true)")
    public void testDeltaLessThanZero() {
        // For equation x^2 + 2x + 2 = 0, delta = 4 - 8 = -4
        assertNull(QuadraticEquation.solve(1, 2, 2));
    }
    
    @Test
    @DisplayName("Test condition delta < 0 (false)")
    public void testDeltaNotLessThanZero() {
        // For equation x^2 - 6x + 9 = 0, delta = 36 - 36 = 0
        assertNotNull(QuadraticEquation.solve(1, -6, 9));
    }
    
    @Test
    @DisplayName("Test condition delta == 0 (true)")
    public void testDeltaEqualsZero() {
        // For equation x^2 - 6x + 9 = 0, delta = 36 - 36 = 0
        double[] roots = QuadraticEquation.solve(1, -6, 9);
        assertEquals(1, roots.length);
        assertEquals(3.0, roots[0], DELTA);
    }
    
    @Test
    @DisplayName("Test condition delta == 0 (false) with delta > 0")
    public void testDeltaGreaterThanZero() {
        // For equation x^2 - 7x + 12 = 0, delta = 49 - 48 = 1
        double[] roots = QuadraticEquation.solve(1, -7, 12);
        assertEquals(2, roots.length);
        // Sort the roots to ensure consistent test results
        if (roots[0] < roots[1]) {
            double temp = roots[0];
            roots[0] = roots[1];
            roots[1] = temp;
        }
        assertEquals(4.0, roots[0], DELTA);
        assertEquals(3.0, roots[1], DELTA);
    }
    
    @Test
    @DisplayName("Test with non-integer coefficients")
    public void testNonIntegerCoefficients() {
        // For equation 2x^2 - 5x + 2 = 0, delta = 25 - 16 = 9
        double[] roots = QuadraticEquation.solve(2, -5, 2);
        assertEquals(2, roots.length);
        assertEquals(2.0, roots[0], DELTA);
        assertEquals(0.5, roots[1], DELTA);
    }
}
