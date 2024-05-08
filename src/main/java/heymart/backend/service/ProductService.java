package heymart.backend.service;

import heymart.backend.models.Product;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProductService {
    CompletableFuture<Product> createProduct(Product product);
    CompletableFuture<Void> deleteProduct(UUID id);
    CompletableFuture<Product> findByProductId(UUID id);
    CompletableFuture<List<Product>> findBySupermarketOwnerId(Long ownerId);
    CompletableFuture<Void> editProduct(Product editedProduct);
    CompletableFuture<List<Product>> findAllProducts();
}
