package heymart.backend.service;

import heymart.backend.models.Supermarket;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SupermarketService {
    CompletableFuture<Supermarket> findById(Long id);
    CompletableFuture<List<Supermarket>> findAll();
    Supermarket save(Supermarket supermarket);
    void deleteById(Long id);
    Supermarket findByOwnerId(long ownerId);
}