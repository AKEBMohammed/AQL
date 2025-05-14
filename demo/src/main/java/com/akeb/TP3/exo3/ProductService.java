package com.akeb.TP3.exo3;

public class ProductService {
    private final ProductApiClient apiClient;

    public ProductService(ProductApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Product getProduct(String productId) throws ApiException {
        return apiClient.getProduct(productId);
    }
}
