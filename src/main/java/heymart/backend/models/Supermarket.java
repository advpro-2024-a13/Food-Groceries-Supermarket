package heymart.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supermarket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID supermarketId;
    private String name;
    private Long ownerId;
    @ElementCollection
    @Column(columnDefinition = "UUID")
    private List<UUID> productIds = new ArrayList<>();

    public void addProductId(UUID productId) {
        if (!productIds.contains(productId)) {
            productIds.add(productId);
        }
    }

    public void removeProductId(UUID productId) {
        productIds.remove(productId);
    }
}
