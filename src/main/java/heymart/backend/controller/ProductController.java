package heymart.backend.controller;

import heymart.backend.models.Product;
import heymart.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        Product existingProduct = productService.findByProductId(id).join();
        if (existingProduct != null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product with ID " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("Product not found with ID " + id);
        }
    }

    @PatchMapping("/subtractQuantity")
    public ResponseEntity<String> subtractProductQuantity(@RequestBody Map<String, String> json) {
        try {
            UUID productId = UUID.fromString(json.get("productId"));
            int productQuantity = Integer.parseInt(json.get("quantity"));
            productService.subtractQuantity(productId, productQuantity);
            return ResponseEntity.ok("Product quantity subtracted successfully");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PutMapping("/edit")
    public ResponseEntity<String> editProduct(@RequestBody Product product) {
        UUID productId = product.getProductId();
        Product existingProduct = productService.findByProductId(productId).join(); // Blocking until the CompletableFuture completes
        if (existingProduct != null) {
            productService.editProduct(product);
            return ResponseEntity.ok("Product edited successfully.");
        } else {
            return ResponseEntity.badRequest().body("Product not found with ID " + productId);
        }
    }

    @GetMapping("/findById/{id}")
    public CompletableFuture<ResponseEntity<Product>> findByProductId(@PathVariable UUID id) {
        return productService.findByProductId(id)
                .thenApply(product -> {
                    if (product != null) {
                        return ResponseEntity.ok(product);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }

    @GetMapping("/findByOwnerId/{ownerId}")
    public CompletableFuture<ResponseEntity<List<Product>>> findBySupermarketOwnerId(@PathVariable Long ownerId) {
        return productService.findBySupermarketOwnerId(ownerId)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/findAll")
    public CompletableFuture<ResponseEntity<List<Product>>> findAllProducts() {
        return productService.findAllProducts()
                .thenApply(ResponseEntity::ok);
    }
}
