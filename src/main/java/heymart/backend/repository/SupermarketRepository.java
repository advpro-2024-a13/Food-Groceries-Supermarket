package heymart.backend.repository;

import heymart.backend.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    Optional<Supermarket> findById(Long id);
    Supermarket save(Supermarket supermarket);
}