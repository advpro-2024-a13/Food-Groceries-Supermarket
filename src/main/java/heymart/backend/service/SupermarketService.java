package heymart.backend.service;

import heymart.backend.models.Supermarket;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SupermarketService {
    CompletableFuture<Optional<Supermarket>> findBySupermarketId(Long id);
    CompletableFuture<List<Supermarket>> findAll();
    CompletableFuture<Supermarket> save(Supermarket supermarket);
    CompletableFuture<Void> deleteById(Long id);
}