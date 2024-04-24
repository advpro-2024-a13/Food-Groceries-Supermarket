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

    private Supermarket(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.owner = builder.owner;
        this.productIds = builder.productIds;
    }

    public void addProductId(Long productId) {
        if (productIds == null) {
            productIds = new ArrayList<>();
        }
        productIds.add(productId);
    }

    public void removeProductId(Long productId) {
        if (productIds != null) {
            productIds.remove(productId);
        }
    }

    public static class Builder {
        private Long id;
        private String name;
        private User owner;
        private List<Long> productIds;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder setProductIds(List<Long> productIds) {
            this.productIds = productIds;
            return this;
        }

        public Supermarket build() {
            return new Supermarket(this);
        }
    }
}
