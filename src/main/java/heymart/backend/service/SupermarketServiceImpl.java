package heymart.backend.service;

import heymart.backend.models.Supermarket;
import heymart.backend.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    private final SupermarketRepository supermarketRepository;

    @Autowired
    public SupermarketServiceImpl(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    @Async
    @Override
    public CompletableFuture<Supermarket> findById(UUID id) {
       return CompletableFuture.completedFuture(supermarketRepository.findById(id).orElse(null));
    }

    @Async
    @Override
    public CompletableFuture<List<Supermarket>> findAll() {
       return CompletableFuture.completedFuture(supermarketRepository.findAll());
    }

    @Async
    @Override
    public CompletableFuture<Supermarket> save(Supermarket supermarket) {
        return CompletableFuture.completedFuture(supermarketRepository.save(supermarket));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteById(UUID id) {
        supermarketRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Void> editSupermarket(Supermarket editedSupermarket) {
        supermarketRepository.save(editedSupermarket);
        return CompletableFuture.completedFuture(null);
    }
}