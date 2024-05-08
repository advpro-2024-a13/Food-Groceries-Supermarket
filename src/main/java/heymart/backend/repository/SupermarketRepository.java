package heymart.backend.repository;

import heymart.backend.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    CompletableFuture<Optional<Supermarket>> findBySupermarketId(Long id);
    Supermarket save(Supermarket supermarket);
}