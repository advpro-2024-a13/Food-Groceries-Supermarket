package heymart.backend.service;

import heymart.backend.models.Supermarket;
import heymart.backend.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class SupermarketServiceImpl implements SupermarketService {
    @Autowired
    private final SupermarketRepository supermarketRepository;

    public SupermarketServiceImpl(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    @Override
    public CompletableFuture<Optional<Supermarket>> findBySupermarketId(Long id) {
        try {
            return supermarketRepository.findBySupermarketId(id);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public CompletableFuture<List<Supermarket>> findAll() {
        try {
            return CompletableFuture.completedFuture(supermarketRepository.findAll());
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public CompletableFuture<Supermarket> save(Supermarket supermarket) {
        try {
            return CompletableFuture.completedFuture(supermarketRepository.save(supermarket));
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public CompletableFuture<Void> deleteById(Long id) {
        try {
            supermarketRepository.deleteById(id);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}