package heymart.backend.models;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

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
    private Long supermarketId;
    private String name;
    private Long ownerId;
    private String supermarketDescription;
    private String supermarketImage;

}
