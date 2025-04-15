package org.unicesumar.dao;

import org.unicesumar.entity.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);

    Product getProductById(long id);

    List<Product> getProducts();
}
