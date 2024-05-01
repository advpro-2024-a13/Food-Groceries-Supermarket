package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supermarkets")
public class SupermarketController {

    private final SupermarketService supermarketService;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping
    public ResponseEntity<List<Supermarket>> getAllSupermarkets() {
        List<Supermarket> supermarkets = supermarketService.findAll();
        return ResponseEntity.ok(supermarkets);
    }

    @PostMapping("/create")
    public ResponseEntity<Supermarket> createSupermarket(@RequestBody Supermarket supermarket) {
        Supermarket createdSupermarket = supermarketService.save(supermarket);
        return ResponseEntity.ok(createdSupermarket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermarket> getSupermarketById(@PathVariable Long id) {
        Optional<Supermarket> supermarket = supermarketService.findById(id);
        return supermarket.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Supermarket> updateSupermarket(@PathVariable Long id, @RequestBody Supermarket updatedSupermarket) {
        Optional<Supermarket> existingSupermarketOptional = supermarketService.findById(id);
        if (existingSupermarketOptional.isPresent()) {
            Supermarket existingSupermarket = existingSupermarketOptional.get();
            existingSupermarket.setName(updatedSupermarket.getName());
            existingSupermarket.setOwnerId(updatedSupermarket.getOwnerId());
            existingSupermarket.setProductIds(updatedSupermarket.getProductIds());
            Supermarket savedSupermarket = supermarketService.save(existingSupermarket);
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
