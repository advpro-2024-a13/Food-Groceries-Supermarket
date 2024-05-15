package heymart.backend.controller;

import heymart.backend.models.Product;
import heymart.backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void testCreateProduct() {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .productQuantity(10)
                .build();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(product);
        when(productService.createProduct(any(Product.class))).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.createProduct(product).join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    void testDeleteProductNotFound() {
        UUID productId = UUID.randomUUID();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(null);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.deleteProduct(productId).join();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Product not found with ID " + productId, responseEntity.getBody());
        verify(productService, never()).deleteProduct(productId);
    }

    @Test
    void testEditProductNotFound() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .productId(productId)
                .productName("Updated Product")
                .productQuantity(20)
                .build();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(null);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.editProduct(product).join();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Product not found with ID " + productId, responseEntity.getBody());
        verify(productService, never()).editProduct(product);
    }

    @Test
    void testFindByProductIdFound() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .productId(productId)
                .productName("Test Product")
                .productQuantity(10)
                .build();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(product);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.findByProductId(productId).join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).findByProductId(productId);
    }

    @Test
    void testFindByProductIdNotFound() {
        UUID productId = UUID.randomUUID();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(null);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.findByProductId(productId).join();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(productService, times(1)).findByProductId(productId);
    }

    @Test
    void testFindBySupermarketOwnerId() {
        Long ownerId = 123L;
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Product 1")
                .productQuantity(10)
                .build();
        productList.add(product1);
        Product product2 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Product 2")
                .productQuantity(20)
                .build();
        productList.add(product2);

        CompletableFuture<List<Product>> future = CompletableFuture.completedFuture(productList);
        when(productService.findBySupermarketOwnerId(ownerId)).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.findBySupermarketOwnerId(ownerId).join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
        verify(productService, times(1)).findBySupermarketOwnerId(ownerId);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Product 1")
                .productQuantity(10)
                .build();
        productList.add(product1);
        Product product2 = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Product 2")
                .productQuantity(20)
                .build();
        productList.add(product2);

        CompletableFuture<List<Product>> future = CompletableFuture.completedFuture(productList);
        when(productService.findAllProducts()).thenReturn(future);

        ResponseEntity<?> responseEntity = productController.findAllProducts().join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
        verify(productService, times(1)).findAllProducts();
    }
}