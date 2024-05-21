package heymart.backend.service;

import heymart.backend.models.Product;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProductService {
    Product createProduct(Product product);
    void deleteProduct(UUID id);
    CompletableFuture<Product> findByProductId(UUID id);
    CompletableFuture<List<Product>> findBySupermarketOwnerId(Long ownerId);
    void editProduct(Product editedProduct);
    CompletableFuture<List<Product>> findAllProducts();
}
