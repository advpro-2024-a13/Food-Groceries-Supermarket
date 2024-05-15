package heymart.backend.service;

import heymart.backend.models.Supermarket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface SupermarketService {
    CompletableFuture<Supermarket> findById(UUID id);
    CompletableFuture<List<Supermarket>> findAll();
    CompletableFuture<Supermarket> save(Supermarket supermarket);
    CompletableFuture<Void> deleteById(UUID id);
    CompletableFuture<Void> editSupermarket(Supermarket editedSupermarket);
}