package com.akeb.TP0.exo1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.DisplayName;

public class PersonTest {

    @Test
    @DisplayName("Test getFullName() returns correctly formatted full name")
    public void testGetFullName() {
        // Arrange
        Person person = new Person("John", "Doe", 30);
        
        // Act
        String fullName = person.getFullName();
        
        // Assert
        assertEquals("John Doe", fullName);
    }
    
    @Test
    @DisplayName("Test isAdult() returns true for person 18 years old")
    public void testIsAdultForExactly18() {
        // Arrange
        Person person = new Person("John", "Doe", 18);
        
        // Act & Assert
        assertTrue(person.isAdult());
    }
    
    @Test
    @DisplayName("Test isAdult() returns true for person over 18 years old")
    public void testIsAdultForOver18() {
        // Arrange
        Person person = new Person("John", "Doe", 25);
        
        // Act & Assert
        assertTrue(person.isAdult());
    }
    
    @Test
    @DisplayName("Test isAdult() returns false for person under 18 years old")
    public void testIsAdultForUnder18() {
        // Arrange
        Person person = new Person("John", "Doe", 17);
        
        // Act & Assert
        assertFalse(person.isAdult());
    }
    
    @Test
    @DisplayName("Test constructor correctly sets values")
    public void testConstructor() {
        // Arrange
        Person person = new Person("Jane", "Smith", 30);
        
        // Act & Assert
        assertEquals("Jane Smith", person.getFullName());
        assertTrue(person.isAdult());
    }
}
