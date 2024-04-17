package heymart.backend.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supermarket {
    private Long id;
    private String name;
    private User owner;
    private List<Long> productIds;

    public Supermarket(Long id, String name, User owner, List<Long> productIds) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.productIds = productIds;
    }

    public void addProductId(Long productId) {
        productIds.add(productId);
    }

    public void removeProductId(Long productId) {
        productIds.remove(productId);
    }
}