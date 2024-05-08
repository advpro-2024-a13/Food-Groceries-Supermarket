package heymart.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private UUID productId;

    private String productName;
    private int productQuantity;
    private String productCategory;
    private String productDescription;
    private String productImagePath;
    private Long productPrice;
    private Long supermarketOwnerId;
}
