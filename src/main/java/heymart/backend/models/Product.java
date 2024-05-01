package heymart.backend.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID productId;
    private String productName;
    private int productQuantity;
    private String productCategory;
    private String productDescription;
    private String productImagePath;
    private Long productPrice;
    private Long supermarketOwnerId;

    private Product() {}

    public static class Builder {
        private UUID productId;
        private String productName;
        private int productQuantity;
        private String productCategory;
        private String productDescription;
        private String productImagePath;
        private Long productPrice;
        private Long supermarketOwnerId;

        public Builder() {}

        public Builder productId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Builder productCategory(String productCategory) {
            this.productCategory = productCategory;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder productImagePath(String productImagePath) {
            this.productImagePath = productImagePath;
            return this;
        }

        public Builder productPrice(Long productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder supermarketOwnerId(Long supermarketOwnerId) {
            this.supermarketOwnerId = supermarketOwnerId;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setProductQuantity(productQuantity);
            product.setProductCategory(productCategory);
            product.setProductDescription(productDescription);
            product.setProductImagePath(productImagePath);
            product.setProductPrice(productPrice);
            product.setSupermarketOwnerId(supermarketOwnerId);
            return product;
        }
    }
}
