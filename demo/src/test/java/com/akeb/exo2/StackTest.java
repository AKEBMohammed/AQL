package com.akeb.exo2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    
    private Stack stack;
    
    @BeforeEach
    public void setUp() {
        stack = new Stack();
    }
    
    @Test
    @DisplayName("Stack should be empty when created")
    public void testNewStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
    
    @Test
    @DisplayName("Push adds element to the stack")
    public void testPush() {
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }
    
    @Test
    @DisplayName("Pop returns and removes the top element")
    public void testPop() {
        stack.push(1);
        stack.push(2);
        
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }
    
    @Test
    @DisplayName("Pop throws exception when stack is empty")
    public void testPopOnEmptyStack() {
        Exception exception = assertThrows(IllegalStateException.class, () -> stack.pop());
        assertEquals("Stack is empty", exception.getMessage());
    }
    
    @Test
    @DisplayName("Peek returns the top element without removing it")
    public void testPeek() {
        stack.push(1);
        stack.push(2);
        
        assertEquals(2, stack.peek());
        assertEquals(2, stack.size()); // Size should not change after peek
    }
    
    @Test
    @DisplayName("Peek throws exception when stack is empty")
    public void testPeekOnEmptyStack() {
        Exception exception = assertThrows(IllegalStateException.class, () -> stack.peek());
        assertEquals("Stack is empty", exception.getMessage());
    }
    
    @Test
    @DisplayName("Size returns the correct number of elements")
    public void testSize() {
        assertEquals(0, stack.size());
        
        stack.push(1);
        assertEquals(1, stack.size());
        
        stack.push(2);
        assertEquals(2, stack.size());
        
        stack.pop();
        assertEquals(1, stack.size());
    }
    
    @Test
    @DisplayName("IsEmpty returns true only when stack is empty")
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        
        stack.push(1);
        assertFalse(stack.isEmpty());
        
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    @DisplayName("Stack maintains LIFO (Last-In-First-Out) order")
    public void testLIFOOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
    
    @Test
    @DisplayName("Stack can expand capacity when needed")
    public void testExpandArray() {
        // Push more elements than the initial capacity (10)
        for (int i = 0; i < 15; i++) {
            stack.push(i);
        }
        
        assertEquals(15, stack.size());
        
        // Verify elements were stored correctly
        for (int i = 14; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
    }
}
