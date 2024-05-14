package heymart.backend.repository;

import heymart.backend.controller.ProductController;
import heymart.backend.models.Product;
import heymart.backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Test
    void testCreateProduct() {
        Product product = new Product();
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.createProduct(any(Product.class))).thenReturn(CompletableFuture.completedFuture(product));
        ProductController controller = new ProductController(productService);
        ResponseEntity<Product> response = controller.createProduct(new Product()).join();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.findByProductId(id)).thenReturn(CompletableFuture.completedFuture(new Product()));
        when(productService.deleteProduct(id)).thenReturn(CompletableFuture.completedFuture(null));
        ProductController controller = new ProductController(productService);
        ResponseEntity<String> response = controller.deleteProduct(id).join();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("deleted"));
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID());
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.findByProductId(product.getProductId())).thenReturn(CompletableFuture.completedFuture(product));
        when(productService.editProduct(any(Product.class))).thenReturn(CompletableFuture.completedFuture(null));
        ProductController controller = new ProductController(productService);
        ResponseEntity<String> response = controller.editProduct(product).join();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("edited"));
    }

    @Test
    void testFindByProductId() {
        UUID id = UUID.randomUUID();
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.findByProductId(id)).thenReturn(CompletableFuture.completedFuture(null)); // Assuming product not found
        ProductController controller = new ProductController(productService);
        ResponseEntity<Product> response = controller.findByProductId(id).join();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testFindBySupermarketOwnerId() {
        Long ownerId = 123L;
        List<Product> products = Collections.emptyList();
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.findBySupermarketOwnerId(ownerId)).thenReturn(CompletableFuture.completedFuture(products));
        ProductController controller = new ProductController(productService);
        ResponseEntity<List<Product>> response = controller.findBySupermarketOwnerId(ownerId).join();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testFindAllProducts() {
        List<Product> products = Collections.emptyList();
        ProductService productService = Mockito.mock(ProductService.class);
        when(productService.findAllProducts()).thenReturn(CompletableFuture.completedFuture(products));
        ProductController controller = new ProductController(productService);
        ResponseEntity<List<Product>> response = controller.findAllProducts().join();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}
