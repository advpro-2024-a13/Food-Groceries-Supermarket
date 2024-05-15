package heymart.backend.repository;

import heymart.backend.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface SupermarketRepository extends JpaRepository<Supermarket, UUID> {
    Supermarket save(Supermarket supermarket);
}