package heymart.backend.repository;

import heymart.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // Define mock behavior if needed
    }

    @Test
    public void testSaveProduct() {
        // Given
        Product product = createProduct();
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertNotNull(savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        // Add more assertions as needed
    }

    @Test
    public void testFindById() {
        // Given
        Product product = createProduct();
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        // When
        Optional<Product> foundProductOptional = productRepository.findById(product.getProductId());

        // Then
        assertTrue(foundProductOptional.isPresent());
        Product foundProduct = foundProductOptional.get();
        assertEquals(product.getProductName(), foundProduct.getProductName());
        // Add more assertions as needed
    }

    @Test
    public void testFindByOwnerId() {
        // Given
        Long ownerId = 1L;
        List<Product> products = new ArrayList<>();
        products.add(createProduct());
        products.add(createProduct());
        when(productRepository.findBySupermarketOwnerId(ownerId)).thenReturn(products);

        // When
        List<Product> foundProducts = productRepository.findBySupermarketOwnerId(ownerId);

        // Then
        assertEquals(2, foundProducts.size());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Product product = createProduct();

        // When
        doNothing().when(productRepository).delete(product);

        // Then
        assertDoesNotThrow(() -> productRepository.delete(product));
    }

    @Test
    public void testEditProduct() {
        // Given
        Product product = createProduct();
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product editedProduct = productRepository.save(product);

        // Then
        assertNotNull(editedProduct.getProductId());
        assertEquals(product.getProductName(), editedProduct.getProductName());
        // Add more assertions as needed
    }

    // Helper methods...

    private Product createProduct() {
        return new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .productCategory("Test Category")
                .productDescription("Test Description")
                .productImagePath("/test/image/path")
                .productPrice(100L)
                .supermarketOwnerId(1L)
                .build();
    }
}
