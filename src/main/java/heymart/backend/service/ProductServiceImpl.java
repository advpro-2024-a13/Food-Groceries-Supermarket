package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        productRepository.createProduct(product);
        return product;
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.editProduct(product);
    }

    @Override
    public Product findByProductId(UUID id) {
        return productRepository.findByProductId(id);
    }

    public List<Product> findBySupermarketOwnerId(Long ownerId) {
        return productRepository.findBySupermarketOwnerId(ownerId);
    }

    @Override
    public List<Product> findAllProducts() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return  allProduct;
    }
}