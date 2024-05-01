package heymart.backend.service;

import heymart.backend.models.Product;
import heymart.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

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
        Product product = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();
        when(productRepository.createProduct(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertEquals(product, createdProduct);
        verify(productRepository).createProduct(product);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        productService.deleteProduct(id);

        verify(productRepository).deleteProduct(id);
    }

    @Test
    void testEdit() {
        Product product = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();
        productService.editProduct(product);

        verify(productRepository).editProduct(product);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Product product = new Product.Builder()
                .productId(id)
                .productName("Test Product")
                .productQuantity(10)
                .build();
        when(productRepository.findByProductId(id)).thenReturn(product);

        Product foundProduct = productService.findByProductId(id);

        assertEquals(product, foundProduct);
        verify(productRepository).findByProductId(id);
    }

    @Test
    public void testFindBySupermarketOwnerId() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Kangkung")
                .productQuantity(1)
                .supermarketOwnerId(123L)
                .build();
        productList.add(product1);

        Product product2 = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Bayam")
                .productQuantity(2)
                .supermarketOwnerId(123L)
                .build();
        productList.add(product2);

        when(productRepository.findBySupermarketOwnerId(123L)).thenReturn(productList);

        List<Product> result = productService.findBySupermarketOwnerId(123L);

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findBySupermarketOwnerId(123L);
    }


    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Kangkung")
                .productQuantity(1)
                .build();
        productList.add(product1);

        Product product2 = new Product.Builder()
                .productId(UUID.randomUUID())
                .productName("Bayam")
                .productQuantity(2)
                .build();
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAllProducts();

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findAll();
    }
}