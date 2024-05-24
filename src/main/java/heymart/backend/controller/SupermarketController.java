package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/supermarket")
public class SupermarketController {


    private final SupermarketService supermarketService;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Supermarket>>> getAllSupermarkets() {
        return supermarketService.findAll().thenApply(ResponseEntity::ok);
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Supermarket>> createSupermarket(@RequestBody Supermarket supermarket) {
        return supermarketService.save(supermarket).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Supermarket>> getSupermarketById(@PathVariable UUID id) {
        return supermarketService.findById(id)
                .thenApply(supermarket -> {
                    if (supermarket != null) {
                        return ResponseEntity.ok(supermarket);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }

    @PutMapping("edit/{id}")
    public CompletableFuture<ResponseEntity<String>> editSupermarket(@PathVariable UUID id, @RequestBody Supermarket editedSupermarket) {
        return supermarketService.findById(id)
                .thenCompose(existingSupermarket -> {
                    if (existingSupermarket != null) {
                        editedSupermarket.setSupermarketId(id);
                        return supermarketService.save(editedSupermarket)
                                .thenApply(updatedSupermarket -> ResponseEntity.ok("Supermarket with ID " + id + " updated."));
                    } else {
                        return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Supermarket not found with ID " + id));
                    }
                });
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<ResponseEntity<String>> deleteSupermarket(@PathVariable UUID id) {
        return supermarketService.findById(id)
                .thenCompose(existingSupermarket -> {
                    if (existingSupermarket != null) {
                        return supermarketService.deleteById(id)
                                .thenApply(deleted -> ResponseEntity.ok("Product with ID " + id + " deleted."));
                    } else {
                        return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Product not found with ID " + id));
                    }
                });
    }
}
