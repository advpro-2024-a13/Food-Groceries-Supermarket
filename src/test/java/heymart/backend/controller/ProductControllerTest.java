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

        ResponseEntity<?> responseEntity = productController.createProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();

        ResponseEntity<?> responseEntity = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product with ID " + productId + " deleted.", responseEntity.getBody());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testEditProduct() {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("Updated Product")
                .productQuantity(20)
                .build();

        ResponseEntity<?> responseEntity = productController.editProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product edited successfully.", responseEntity.getBody());
        verify(productService, times(1)).editProduct(product);
    }

    @Test
    void testFindByProductIdFound() {
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .productId(productId)
                .productName("Test Product")
                .productQuantity(10)
                .build();

        when(productService.findByProductId(productId)).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.findByProductId(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).findByProductId(productId);
    }

    @Test
    void testFindByProductIdNotFound() {
        UUID productId = UUID.randomUUID();

        when(productService.findByProductId(productId)).thenReturn(null);

        ResponseEntity<?> responseEntity = productController.findByProductId(productId);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Product not found with ID " + productId, responseEntity.getBody());
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

        when(productService.findBySupermarketOwnerId(ownerId)).thenReturn(productList);

        ResponseEntity<?> responseEntity = productController.findBySupermarketOwnerId(ownerId);

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

        when(productService.findAllProducts()).thenReturn(productList);

        ResponseEntity<?> responseEntity = productController.findAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
        verify(productService, times(1)).findAllProducts();
    }
}