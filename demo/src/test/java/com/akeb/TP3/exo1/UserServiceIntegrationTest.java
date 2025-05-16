package com.akeb.TP3.exo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class UserServiceIntegrationTest {

    @Container
    public MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private UserRepositoryImpl userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Set up database connection using container's information
        userRepository = new UserRepositoryImpl(
                mySQLContainer.getJdbcUrl(),
                mySQLContainer.getUsername(),
                mySQLContainer.getPassword()
        );
        
        // Initialize UserService with the real repository
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById_UserExists() {
        // Arrange - Create a test user in the database
        long userId = 1L;
        User testUser = new User(userId, "testUser", "test@example.com");
        userRepository.saveUser(testUser);
        
        // Act
        User result = userService.getUserById(userId);
        
        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }
    
    @Test
    public void testGetUserById_UserDoesNotExist() {
        // Act
        User result = userService.getUserById(999L);
        
        // Assert
        assertNull(result);
    }
    
    @Test
    public void testUserLifecycle() {
        // Create a new user
        long userId = 2L;
        User user = new User(userId, "lifecycleUser", "lifecycle@example.com");
        
        // Save the user
        userRepository.saveUser(user);
        
        // Retrieve the user and verify it exists
        User retrievedUser = userService.getUserById(userId);
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
        
        // Delete the user
        userRepository.deleteUser(userId);
        
        // Verify the user is deleted
        User deletedUser = userService.getUserById(userId);
        assertNull(deletedUser);
    }
}