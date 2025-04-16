package org.unicesumar.dao.impl;

import org.unicesumar.dao.ProductDao;
import org.unicesumar.entity.Product;
import org.unicesumar.exceptions.ECommerceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private String query;

    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addProduct(Product product) {
        query = "INSERT INTO products (name, price) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product successfully inserted!");
            }

        } catch (SQLException e) {
            throw new ECommerceException("Error inserting product", e.getMessage());
        }
    }

    @Override
    public Product getProductById(long id) {
        query = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3));
                }
            }
        } catch (SQLException e) {
            throw new ECommerceException("Error fetching product by ID", e.toString());
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        query = "SELECT * FROM products";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            throw new ECommerceException("Error fetching products", e.toString());
        }

        return products;
    }
}
