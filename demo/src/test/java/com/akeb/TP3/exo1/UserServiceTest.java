package com.akeb.TP3.exo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Create a mock for UserRepository
        userRepository = Mockito.mock(UserRepository.class);
        
        // Initialize UserService with the mocked repository
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById() {
        // Arrange
        long userId = 1L;
        User mockUser = new User(userId, "testUser", "test@example.com");
        
        // Configure mock to return a user when findUserById is called with userId
        when(userRepository.findUserById(userId)).thenReturn(mockUser);
        
        // Act
        User result = userService.getUserById(userId);
        
        // Assert
        assertEquals(mockUser, result, "The returned user should be the same as the mock user");
        verify(userRepository).findUserById(userId); // Verify that findUserById was called with the correct argument
    }
}
