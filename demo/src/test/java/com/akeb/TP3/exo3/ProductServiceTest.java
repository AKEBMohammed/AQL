package com.akeb.TP3.exo3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    private ProductApiClient mockApiClient;
    private ProductService productService;
    private final String PRODUCT_ID = "prod-123";

    @BeforeEach
    public void setUp() {
        // Create mock for ProductApiClient
        mockApiClient = mock(ProductApiClient.class);
        
        // Initialize ProductService with the mock
        productService = new ProductService(mockApiClient);
    }

    @Test
    public void testGetProductSuccess() throws ApiException {
        // Arrange
        Product mockProduct = new Product(PRODUCT_ID, "Test Product", 19.99, "A test product");
        when(mockApiClient.getProduct(PRODUCT_ID)).thenReturn(mockProduct);
        
        // Act
        Product result = productService.getProduct(PRODUCT_ID);
        
        // Assert
        assertNotNull(result);
        assertEquals(PRODUCT_ID, result.getProductId());
        assertEquals("Test Product", result.getName());
        verify(mockApiClient).getProduct(PRODUCT_ID);
    }

    @Test
    public void testGetProductNotFound() throws ApiException {
        // Arrange
        try {
            when(mockApiClient.getProduct(PRODUCT_ID))
                .thenThrow(new ApiException("Product not found", ApiException.ErrorType.NOT_FOUND));
            
            // Act
            productService.getProduct(PRODUCT_ID);
            fail("Should have thrown ApiException");
        } catch (ApiException e) {
            // Assert
            assertEquals(ApiException.ErrorType.NOT_FOUND, e.getErrorType());
            verify(mockApiClient).getProduct(PRODUCT_ID);
        }
    }
    
    @Test
    public void testGetProductInvalidFormat() throws ApiException {
        // Arrange
        try {
            when(mockApiClient.getProduct(PRODUCT_ID))
                .thenThrow(new ApiException("Invalid data format", ApiException.ErrorType.INVALID_FORMAT));
            
            // Act
            productService.getProduct(PRODUCT_ID);
            fail("Should have thrown ApiException");
        } catch (ApiException e) {
            // Assert
            assertEquals(ApiException.ErrorType.INVALID_FORMAT, e.getErrorType());
            verify(mockApiClient).getProduct(PRODUCT_ID);
        }
    }
    
    @Test
    public void testGetProductConnectionError() throws ApiException {
        // Arrange
        try {
            when(mockApiClient.getProduct(PRODUCT_ID))
                .thenThrow(new ApiException("Connection failed", ApiException.ErrorType.CONNECTION_ERROR));
            
            // Act
            productService.getProduct(PRODUCT_ID);
            fail("Should have thrown ApiException");
        } catch (ApiException e) {
            // Assert
            assertEquals(ApiException.ErrorType.CONNECTION_ERROR, e.getErrorType());
            verify(mockApiClient).getProduct(PRODUCT_ID);
        }
    }
}
