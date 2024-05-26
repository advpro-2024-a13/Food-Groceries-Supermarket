package heymart.backend.repository;

import heymart.backend.models.Product;
import heymart.backend.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    Supermarket save(Supermarket supermarket);
    Supermarket findByOwnerId(Long ownerId);
}