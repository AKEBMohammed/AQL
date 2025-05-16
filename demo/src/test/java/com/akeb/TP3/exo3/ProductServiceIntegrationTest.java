package com.akeb.TP3.exo3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class ProductServiceIntegrationTest {

    @Container
    public MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    private ProductApiClientImpl productApiClient;
    private ProductService productService;
    private static final String PRODUCT_ID = "prod-123";

    @BeforeEach
    public void setUp() {
        // Set up database connection using container's information
        productApiClient = new ProductApiClientImpl(
                mySQLContainer.getJdbcUrl(),
                mySQLContainer.getUsername(),
                mySQLContainer.getPassword()
        );

        // Initialize service with real API client
        productService = new ProductService(productApiClient);
    }

    @Test
    public void testGetProductSuccess() throws ApiException {
        // Arrange - Create a product in the database
        Product expectedProduct = new Product(PRODUCT_ID, "Test Product", 19.99, "A test product");
        productApiClient.saveProduct(expectedProduct);

        // Act
        Product result = productService.getProduct(PRODUCT_ID);

        // Assert
        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getProductId());
        assertEquals("Test Product", result.getName());
        assertEquals(19.99, result.getPrice());
        assertEquals("A test product", result.getDescription());
    }

    @Test
    public void testGetProductNotFound() {
        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.getProduct("nonexistent-id");
        });
        
        // Verify correct error type
        assertEquals(ApiException.ErrorType.NOT_FOUND, exception.getErrorType());
    }

    @Test
    public void testProductLifecycle() throws ApiException {
        // Create a new product
        String lifecycleProductId = "lifecycle-product";
        Product product = new Product(lifecycleProductId, "Lifecycle Product", 29.99, "For testing product lifecycle");
        
        // Save the product
        productApiClient.saveProduct(product);
        
        // Retrieve the product and verify it exists
        Product retrievedProduct = productService.getProduct(lifecycleProductId);
        assertNotNull(retrievedProduct);
        assertEquals(lifecycleProductId, retrievedProduct.getProductId());
        
        // Delete the product
        productApiClient.deleteProduct(lifecycleProductId);
        
        // Verify the product is deleted
        ApiException exception = assertThrows(ApiException.class, () -> {
            productService.getProduct(lifecycleProductId);
        });
        assertEquals(ApiException.ErrorType.NOT_FOUND, exception.getErrorType());
    }
}