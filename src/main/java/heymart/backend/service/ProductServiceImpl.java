package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Async
    @Override
    public CompletableFuture<Product> createProduct(Product product) {
        return CompletableFuture.completedFuture(productRepository.save(product));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteProduct(UUID id) {
        productRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Void> editProduct(Product product) {
        productRepository.save(product);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Product> findByProductId(UUID id) {
        return CompletableFuture.completedFuture(productRepository.findById(id).orElse(null));
    }

    @Async
    @Override
    public CompletableFuture<List<Product>> findBySupermarketOwnerId(Long ownerId) {
        return CompletableFuture.completedFuture(productRepository.findBySupermarketOwnerId(ownerId));
    }

    @Async
    @Override
    public CompletableFuture<List<Product>> findAllProducts() {
        return CompletableFuture.completedFuture(productRepository.findAll());
    }
}
