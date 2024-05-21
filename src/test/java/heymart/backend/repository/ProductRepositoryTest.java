package heymart.backend.repository;

import heymart.backend.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void testSaveProduct() {
        Product product = createProduct();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
    }

    @Test
    void testFindById() {
        Product product = createProduct();
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        Optional<Product> foundProductOptional = productRepository.findById(product.getProductId());

        assertTrue(foundProductOptional.isPresent());
        Product foundProduct = foundProductOptional.get();
        assertEquals(product.getProductName(), foundProduct.getProductName());
    }

    @Test
    void testFindByOwnerId() {
        Long ownerId = 1L;
        List<Product> products = new ArrayList<>();
        products.add(createProduct());
        products.add(createProduct());
        when(productRepository.findBySupermarketOwnerId(ownerId)).thenReturn(products);

        List<Product> foundProducts = productRepository.findBySupermarketOwnerId(ownerId);

        assertEquals(2, foundProducts.size());
    }

    @Test
    void testDeleteProduct() {
        Product product = createProduct();

        doNothing().when(productRepository).delete(product);

        assertDoesNotThrow(() -> productRepository.delete(product));
    }

    @Test
    void testEditProduct() {
        Product product = createProduct();
        when(productRepository.save(product)).thenReturn(product);

        Product editedProduct = productRepository.save(product);

        assertNotNull(editedProduct.getProductId());
        assertEquals(product.getProductName(), editedProduct.getProductName());
    }

    private Product createProduct() {
        return Product.builder()
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