package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void subtractQuantity(UUID productId, int quantity) throws IllegalArgumentException {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int remainingQuantity = product.getProductQuantity() - quantity;
            if (remainingQuantity >= 0) {
                product.setProductQuantity(remainingQuantity);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Insufficient quantity for product with ID: " + productId);
            }
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
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
