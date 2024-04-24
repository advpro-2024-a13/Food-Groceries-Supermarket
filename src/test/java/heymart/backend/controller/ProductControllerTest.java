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
        Product product = new Product();
        UUID productId = UUID.randomUUID();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);

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
        Product product = new Product();
        UUID productId = UUID.randomUUID();
        product.setProductId(productId);
        product.setProductName("Updated Product");
        product.setProductQuantity(20);

        ResponseEntity<?> responseEntity = productController.editProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product edited successfully.", responseEntity.getBody());
        verify(productService, times(1)).editProduct(product);
    }

    @Test
    void testFindByProductId() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productService.findByProductId(productId)).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.findByProductId(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).findByProductId(productId);
    }

    @Test
    void testFindBySupermarketOwnerId() {
        Long ownerId = 123L;
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID());
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productList.add(product1);
        Product product2 = new Product();
        product2.setProductId(UUID.randomUUID());
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
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
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID());
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productList.add(product1);
        Product product2 = new Product();
        product2.setProductId(UUID.randomUUID());
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productList.add(product2);

        when(productService.findAllProducts()).thenReturn(productList);

        ResponseEntity<?> responseEntity = productController.findAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
        verify(productService, times(1)).findAllProducts();
    }
}
