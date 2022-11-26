package com.market.antonio.domain.repository;

import com.market.antonio.domain.Product;
import com.market.antonio.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save (Product product);

    Product update(Product product);
    void delete(int productId);

}
