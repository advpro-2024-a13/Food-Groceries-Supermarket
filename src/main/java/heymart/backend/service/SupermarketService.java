package heymart.backend.service;

import heymart.backend.models.Supermarket;

import java.util.List;
import java.util.Optional;

public interface SupermarketService {
    Optional<Supermarket> findById(Long id);
    List<Supermarket> findAll();
    Supermarket save(Supermarket supermarket);
    void deleteById(Long id);
}