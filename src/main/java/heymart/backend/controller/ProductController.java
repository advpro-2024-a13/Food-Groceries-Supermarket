package heymart.backend.controller;

import heymart.backend.models.Product;
import heymart.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted.");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody Product product) {
        productService.editProduct(product);
        return ResponseEntity.ok("Product edited successfully.");
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByProductId(@PathVariable UUID id) {
        Product product = productService.findByProductId(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.badRequest().body("Product not found with ID " + id);
        }
    }

    @GetMapping("/findByOwnerId/{ownerId}")
    public ResponseEntity<?> findBySupermarketOwnerId(@PathVariable Long ownerId) {
        List<Product> products = productService.findBySupermarketOwnerId(ownerId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }
}