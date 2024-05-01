package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supermarkets")
public class SupermarketController {

    private final SupermarketService supermarketService;

    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping
    public ResponseEntity<List<Supermarket>> getAllSupermarkets() {
        List<Supermarket> supermarkets = supermarketService.findAll();
        return ResponseEntity.ok(supermarkets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermarket> getSupermarketById(@PathVariable Long id) {
        Supermarket supermarket = supermarketService.findById(id);
        if (supermarket != null) {
            return ResponseEntity.ok(supermarket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody Supermarket supermarket) {
        Supermarket createdSupermarket = supermarketService.save(supermarket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupermarket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supermarket> updateSupermarket(@PathVariable Long id, @RequestBody Supermarket supermarket) {
        Supermarket updatedSupermarket = supermarketService.update(id, supermarket);
        if (updatedSupermarket != null) {
            return ResponseEntity.ok(updatedSupermarket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupermarket(@PathVariable Long id) {
        boolean deleted = supermarketService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}