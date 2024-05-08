package heymart.backend.service;

import heymart.backend.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    void deleteProduct(UUID id);
    Product findByProductId(UUID id);
    List<Product> findBySupermarketOwnerId(Long ownerId);
    void editProduct(Product editedProduct);
    List<Product> findAllProducts();
}
