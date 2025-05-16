package com.akeb.TP3.exo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductApiClientImpl implements ProductApiClient {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public ProductApiClientImpl(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                // Drop table if exists
                stmt.execute("DROP TABLE IF EXISTS products");
                
                // Create table
                stmt.execute("CREATE TABLE products (" +
                        "product_id VARCHAR(50) PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "price DOUBLE, " +
                        "description TEXT)");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    @Override
    public Product getProduct(String productId) throws ApiException {
        try (Connection conn = getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE product_id = ?")) {
                stmt.setString(1, productId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Product(
                                rs.getString("product_id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("description")
                        );
                    } else {
                        throw new ApiException("Product not found with ID: " + productId, ApiException.ErrorType.NOT_FOUND);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiException("Connection error: " + e.getMessage(), ApiException.ErrorType.CONNECTION_ERROR);
        }
    }

    public void saveProduct(Product product) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO products (product_id, name, price, description) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, product.getProductId());
                stmt.setString(2, product.getName());
                stmt.setDouble(3, product.getPrice());
                stmt.setString(4, product.getDescription());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product", e);
        }
    }

    public void deleteProduct(String productId) {
        try (Connection conn = getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE product_id = ?")) {
                stmt.setString(1, productId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}