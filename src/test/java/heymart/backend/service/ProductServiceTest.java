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
import java.util.concurrent.ExecutionException;
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
    void testCreate() throws ExecutionException, InterruptedException {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product).get();

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDelete() throws ExecutionException, InterruptedException {
        UUID id = UUID.randomUUID();

        productService.deleteProduct(id).get();

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void testEdit() throws ExecutionException, InterruptedException {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();

        productService.editProduct(product).get();

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindById() throws ExecutionException, InterruptedException {
        UUID id = UUID.randomUUID();
        Product product = Product.builder()
                .productId(id)
                .productName("Test Product")
                .productQuantity(10)
                .build();

        when(productRepository.findById(id)).thenReturn(java.util.Optional.of(product));

        Product foundProduct = productService.findByProductId(id).get();

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testFindBySupermarketOwnerId() throws ExecutionException, InterruptedException {
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

        when(productRepository.findBySupermarketOwnerId(ownerId)).thenReturn(productList);

        List<Product> result = productService.findBySupermarketOwnerId(ownerId).get();

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findBySupermarketOwnerId(ownerId);
    }

    @Test
    void testFindAllProduct() throws ExecutionException, InterruptedException {
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

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAllProducts().get();

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findAll();
    }
}