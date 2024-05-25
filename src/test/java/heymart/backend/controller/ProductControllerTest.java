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
import java.util.HashMap;
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

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    void testDeleteProductFound() {
        UUID productId = UUID.randomUUID();
        Product existingProduct = Product.builder()
                .productId(productId)
                .productName("Test Product")
                .productQuantity(10)
                .build();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(existingProduct);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product with ID " + productId + " deleted.", responseEntity.getBody());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testEditProductFound() {
        UUID productId = UUID.randomUUID();
        Product existingProduct = Product.builder()
                .productId(productId)
                .productName("Existing Product")
                .productQuantity(20)
                .build();
        Product updatedProduct = Product.builder()
                .productId(productId)
                .productName("Updated Product")
                .productQuantity(30)
                .build();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(existingProduct);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<String> responseEntity = productController.editProduct(updatedProduct);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product edited successfully.", responseEntity.getBody());
        verify(productService, times(1)).editProduct(updatedProduct);
    }

    @Test
    void testDeleteProductNotFound() {
        UUID productId = UUID.randomUUID();

        CompletableFuture<Product> future = CompletableFuture.completedFuture(null);
        when(productService.findByProductId(productId)).thenReturn(future);

        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

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

        ResponseEntity<String> responseEntity = productController.editProduct(product);

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

        ResponseEntity<Product> responseEntity = productController.findByProductId(productId).join();

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

        ResponseEntity<List<Product>> responseEntity = productController.findBySupermarketOwnerId(ownerId).join();

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

        ResponseEntity<List<Product>> responseEntity = productController.findAllProducts().join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
        verify(productService, times(1)).findAllProducts();
    }

    @Test
    void testSubtractProductQuantity_Success() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("productId", "3fa85f64-5717-4562-b3fc-2c963f66afa6");
        JSON.put("quantity", "10");

        ResponseEntity<String> response = productController.subtractProductQuantity(JSON);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product quantity subtracted successfully", response.getBody());
    }

    @Test
    void testSubtractProductQuantity_BadRequest_InvalidUUID() {
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("productId", "invalid-uuid-format");
        JSON.put("quantity", "10");

        ResponseEntity<String> response = productController.subtractProductQuantity(JSON);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid UUID string: invalid-uuid-format", response.getBody());
    }

    @Test
    void testSubtractProductQuantity_BadRequest_IllegalArgumentException() {
        UUID productId = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        HashMap<String, String> JSON = new HashMap<>();
        JSON.put("productId", productId.toString());
        JSON.put("quantity", "invalid-quantity-format");

        ResponseEntity<String> response = productController.subtractProductQuantity(JSON);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("For input string: invalid-quantity-format", response.getBody().replace("\"", ""));

    }
}