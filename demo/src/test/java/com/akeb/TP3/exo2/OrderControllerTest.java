package com.akeb.TP3.exo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class OrderControllerTest {
    private OrderController orderController;
    private OrderService mockOrderService;
    private OrderDao mockOrderDao;

    @BeforeEach
    public void setUp() {
        // Create mocks for OrderService and OrderDao
        mockOrderDao = Mockito.mock(OrderDao.class);
        mockOrderService = Mockito.mock(OrderService.class);
        
        // Initialize OrderController with the mocked service
        orderController = new OrderController(mockOrderService);
    }

    @Test
    public void testCreateOrder() {
        // Arrange
        Order testOrder = new Order("John Doe", 299.99);
        
        // Act
        orderController.createOrder(testOrder);
        
        // Assert
        // Verify that OrderService.createOrder was called with the correct order object
        verify(mockOrderService).createOrder(testOrder);
    }
}
