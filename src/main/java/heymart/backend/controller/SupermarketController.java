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
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody Supermarket supermarket) {
        Supermarket createdSupermarket = supermarketService.save(supermarket);
        return ResponseEntity.ok(createdSupermarket);
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Supermarket>> getSupermarketById(@PathVariable Long id) {
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
    public ResponseEntity<String> editSupermarket(@PathVariable Long id, @RequestBody Supermarket editedSupermarket) {
        Supermarket existingSupermarket = supermarketService.findById(id).join();
        if (existingSupermarket != null) {
            editedSupermarket.setSupermarketId(id);
            supermarketService.save(editedSupermarket);
            return ResponseEntity.ok("Supermarket with ID " + id + " updated.");
        } else {
            return ResponseEntity.badRequest().body("Supermarket not found with ID " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSupermarket(@PathVariable Long id) {
        Supermarket existingSupermarket = supermarketService.findById(id).join();
        if (existingSupermarket != null) {
            supermarketService.deleteById(id);
            return ResponseEntity.ok("Supermarket with ID " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("Supermarket not found with ID " + id);
        }
    }
}
