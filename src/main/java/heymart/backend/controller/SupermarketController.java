package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/supermarkets")
public class SupermarketController {

    private final SupermarketService supermarketService;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping
    public ResponseEntity<List<Supermarket>> getAllSupermarkets() throws ExecutionException, InterruptedException {
        List<Supermarket> supermarkets = supermarketService.findAll().get();
        return ResponseEntity.ok(supermarkets);
    }

    @PostMapping("/create")
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody Supermarket supermarket) throws ExecutionException, InterruptedException {
        Supermarket createdSupermarket = supermarketService.save(supermarket).get();
        return ResponseEntity.ok(createdSupermarket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermarket> getSupermarketById(@PathVariable Long id) throws ExecutionException, InterruptedException {
        Optional<Supermarket> supermarket = supermarketService.findBySupermarketId(id).get();
        return supermarket.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Supermarket> updateSupermarket(@PathVariable Long id, @RequestBody Supermarket updatedSupermarket) throws ExecutionException, InterruptedException {
        Optional<Supermarket> existingSupermarketOptional = supermarketService.findBySupermarketId(id).get();
        if (existingSupermarketOptional.isPresent()) {
            Supermarket existingSupermarket = existingSupermarketOptional.get();
            existingSupermarket.setName(updatedSupermarket.getName());
            existingSupermarket.setOwnerId(updatedSupermarket.getOwnerId());
            existingSupermarket.setProductIds(updatedSupermarket.getProductIds());
            Supermarket savedSupermarket = supermarketService.save(existingSupermarket).get();
            return ResponseEntity.ok(savedSupermarket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteSupermarket(@PathVariable Long id) {
        supermarketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
