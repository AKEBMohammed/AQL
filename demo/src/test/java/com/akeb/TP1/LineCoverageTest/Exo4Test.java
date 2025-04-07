package com.akeb.TP1.LineCoverageTest;

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
    @DisplayName("Test with negative discriminant - should return null")
    public void testNegativeDiscriminant() {
        // For equation x^2 + 2x + 5 = 0, delta = 4 - 20 = -16
        assertNull(QuadraticEquation.solve(1, 2, 5));
    }
    
    @Test
    @DisplayName("Test with zero discriminant - should return one root")
    public void testZeroDiscriminant() {
        // For equation x^2 - 2x + 1 = 0, delta = 4 - 4 = 0
        double[] roots = QuadraticEquation.solve(1, -2, 1);
        assertEquals(1, roots.length);
        assertEquals(1.0, roots[0], DELTA);
    }
    
    @Test
    @DisplayName("Test with positive discriminant - should return two roots")
    public void testPositiveDiscriminant() {
        // For equation x^2 - 3x + 2 = 0, delta = 9 - 8 = 1
        double[] roots = QuadraticEquation.solve(1, -3, 2);
        assertEquals(2, roots.length);
        assertEquals(2.0, roots[0], DELTA);
        assertEquals(1.0, roots[1], DELTA);
    }
}
