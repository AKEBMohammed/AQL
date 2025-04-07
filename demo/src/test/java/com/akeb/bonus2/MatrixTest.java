package com.akeb.bonus2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class MatrixTest {

    @Test
    @DisplayName("Test matrix constructor and initialization")
    public void testMatrixCreation() {
        Matrix matrix = new Matrix(3);
        
        // Test that all elements are initialized to 0
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, matrix.get(i, j));
            }
        }
    }
    
    @Test
    @DisplayName("Test set and get methods")
    public void testSetAndGet() {
        Matrix matrix = new Matrix(2);
        
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(1, 0, 3);
        matrix.set(1, 1, 4);
        
        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(0, 1));
        assertEquals(3, matrix.get(1, 0));
        assertEquals(4, matrix.get(1, 1));
    }
    
    @Test
    @DisplayName("Test add method")
    public void testAdd() {
        Matrix matrix1 = new Matrix(2);
        matrix1.set(0, 0, 1);
        matrix1.set(0, 1, 2);
        matrix1.set(1, 0, 3);
        matrix1.set(1, 1, 4);
        
        Matrix matrix2 = new Matrix(2);
        matrix2.set(0, 0, 5);
        matrix2.set(0, 1, 6);
        matrix2.set(1, 0, 7);
        matrix2.set(1, 1, 8);
        
        matrix1.add(matrix2);
        
        assertEquals(6, matrix1.get(0, 0));  // 1 + 5
        assertEquals(8, matrix1.get(0, 1));  // 2 + 6
        assertEquals(10, matrix1.get(1, 0)); // 3 + 7
        assertEquals(12, matrix1.get(1, 1)); // 4 + 8
    }
    
    @Test
    @DisplayName("Test add with null matrix")
    public void testAddWithNull() {
        Matrix matrix = new Matrix(2);
        
        assertThrows(NullPointerException.class, () -> {
            matrix.add(null);
        });
    }
    
    @Test
    @DisplayName("Test add with different size")
    public void testAddWithDifferentSize() {
        Matrix matrix1 = new Matrix(2);
        Matrix matrix2 = new Matrix(3);
        
        assertThrows(IllegalArgumentException.class, () -> {
            matrix1.add(matrix2);
        });
    }
    
    @Test
    @DisplayName("Test multiply method")
    public void testMultiply() {
        Matrix matrix1 = new Matrix(2);
        matrix1.set(0, 0, 1);
        matrix1.set(0, 1, 2);
        matrix1.set(1, 0, 3);
        matrix1.set(1, 1, 4);
        
        Matrix matrix2 = new Matrix(2);
        matrix2.set(0, 0, 5);
        matrix2.set(0, 1, 6);
        matrix2.set(1, 0, 7);
        matrix2.set(1, 1, 8);
        
        matrix1.multiply(matrix2);
        
        // [1 2] * [5 6] = [1*5+2*7, 1*6+2*8] = [19, 22]
        // [3 4]   [7 8]   [3*5+4*7, 3*6+4*8]   [43, 50]
        
        assertEquals(19, matrix1.get(0, 0)); // 1*5 + 2*7
        assertEquals(22, matrix1.get(0, 1)); // 1*6 + 2*8
        assertEquals(43, matrix1.get(1, 0)); // 3*5 + 4*7
        assertEquals(50, matrix1.get(1, 1)); // 3*6 + 4*8
    }
    
    @Test
    @DisplayName("Test multiply with null matrix")
    public void testMultiplyWithNull() {
        Matrix matrix = new Matrix(2);
        
        assertThrows(NullPointerException.class, () -> {
            matrix.multiply(null);
        });
    }
    
    @Test
    @DisplayName("Test multiply with different size")
    public void testMultiplyWithDifferentSize() {
        Matrix matrix1 = new Matrix(2);
        Matrix matrix2 = new Matrix(3);
        
        assertThrows(IllegalArgumentException.class, () -> {
            matrix1.multiply(matrix2);
        });
    }
    
    @Test
    @DisplayName("Test transpose method")
    public void testTranspose() {
        Matrix matrix = new Matrix(3);
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 0, 4);
        matrix.set(1, 1, 5);
        matrix.set(1, 2, 6);
        matrix.set(2, 0, 7);
        matrix.set(2, 1, 8);
        matrix.set(2, 2, 9);
        
        matrix.transpose();
        
        assertEquals(1, matrix.get(0, 0)); // Unchanged
        assertEquals(4, matrix.get(0, 1)); // Swapped from [1, 0]
        assertEquals(7, matrix.get(0, 2)); // Swapped from [2, 0]
        assertEquals(2, matrix.get(1, 0)); // Swapped from [0, 1]
        assertEquals(5, matrix.get(1, 1)); // Unchanged
        assertEquals(8, matrix.get(1, 2)); // Swapped from [2, 1]
        assertEquals(3, matrix.get(2, 0)); // Swapped from [0, 2]
        assertEquals(6, matrix.get(2, 1)); // Swapped from [1, 2]
        assertEquals(9, matrix.get(2, 2)); // Unchanged
    }
    
    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        Matrix matrix = new Matrix(2);
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(1, 0, 3);
        matrix.set(1, 1, 4);
        
        String expected = "[1, 2]\n[3, 4]\n";
        assertEquals(expected, matrix.toString());
    }
    
    @Test
    @DisplayName("Test index out of bounds in get and set")
    public void testIndexOutOfBounds() {
        Matrix matrix = new Matrix(2);
        
        // Testing array index out of bounds when accessing invalid indices
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.get(2, 0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.get(0, 2));
        
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.set(-1, 0, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.set(0, -1, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.set(2, 0, 1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.set(0, 2, 1));
    }
}
