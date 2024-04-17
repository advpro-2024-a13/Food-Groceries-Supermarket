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


}
