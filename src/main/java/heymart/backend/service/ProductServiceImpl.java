package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

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
    public Product findByProductId(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findBySupermarketOwnerId(Long ownerId) {
        return productRepository.findBySupermarketOwnerId(ownerId);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
