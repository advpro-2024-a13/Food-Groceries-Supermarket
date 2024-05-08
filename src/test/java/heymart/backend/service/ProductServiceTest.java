package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();

        // Mock the behavior of productRepository.save()
        when(productRepository.save(product)).thenReturn(product);

        // Call the service method
        Product createdProduct = productService.createProduct(product);

        // Verify the result
        assertEquals(product, createdProduct);
        // Verify that productRepository.save() was called once with the specified product
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        // Call the service method
        productService.deleteProduct(id);

        // Verify that productRepository.deleteById() was called once with the specified ID
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void testEdit() {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();

        // Call the service method
        productService.editProduct(product);

        // Verify that productRepository.save() was called once with the specified product
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .productId(id)
                .productName("Test Product")
                .productQuantity(10)
                .build();

        // Mock the behavior of productRepository.findById()
        when(productRepository.findById(id)).thenReturn(java.util.Optional.of(product));

        // Call the service method
        Product foundProduct = productService.findByProductId(id);

        // Verify the result
        assertEquals(product, foundProduct);
        // Verify that productRepository.findById() was called once with the specified ID
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void testFindBySupermarketOwnerId() {
        Long ownerId = 123L;
        List<Product> productList = new ArrayList<>();

        Product product1 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Kangkung")
                .productQuantity(1)
                .supermarketOwnerId(ownerId)
                .build();
        productList.add(product1);

        Product product2 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Bayam")
                .productQuantity(2)
                .supermarketOwnerId(ownerId)
                .build();
        productList.add(product2);

        // Mock the behavior of productRepository.findBySupermarketOwnerId()
        when(productRepository.findBySupermarketOwnerId(ownerId)).thenReturn(productList);

        // Call the service method
        List<Product> result = productService.findBySupermarketOwnerId(ownerId);

        // Verify the result
        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        // Verify that productRepository.findBySupermarketOwnerId() was called once with the specified owner ID
        verify(productRepository, times(1)).findBySupermarketOwnerId(ownerId);
    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();

        Product product1 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Kangkung")
                .productQuantity(1)
                .build();
        productList.add(product1);

        Product product2 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Bayam")
                .productQuantity(2)
                .build();
        productList.add(product2);

        // Mock the behavior of productRepository.findAll()
        when(productRepository.findAll()).thenReturn(productList);

        // Call the service method
        List<Product> result = productService.findAllProducts();

        // Verify the result
        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        // Verify that productRepository.findAll() was called once
        verify(productRepository, times(1)).findAll();
    }
}
