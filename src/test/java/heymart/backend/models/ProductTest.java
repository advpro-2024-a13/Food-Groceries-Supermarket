package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.UUID;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        UUID id = UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93");
        this.product.setProductId(id);
        this.product.setProductName("Ayam Potong");
        this.product.setProductQuantity(100);
        this.product.setProductCategory("Bahan Makanan");
        this.product.setProductDescription("Ayam potong dengan berat satu kilogram.");
        this.product.setProductImagePath("https://sogood.id/wp-content/uploads/2018/08/FA_SG_Ayam_Utuh_Potong@10_1kg.png");
        this.product.setProductPrice(41900L);
    }

    @Test
    void testGetProductID() {
        UUID id = UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93");
        assertEquals(id,  this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Ayam Potong", this.product.getProductName());
    }

    @Test
    void testGetProductCategory() {
        assertEquals("Bahan Makanan", this.product.getProductCategory());
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Ayam potong dengan berat satu kilogram.",
                this.product.getProductDescription()
        );
    }

    @Test
    void testGetProductImagePath() {
        assertEquals("https://sogood.id/wp-content/uploads/2018/08/FA_SG_Ayam_Utuh_Potong@10_1kg.png", this.product.getImagePath());
    }

    @Test
    void testGetProductPrice() {
        assertEquals(41900, this.product.getProductPrice());
    }

    @Test
    void testSetProductID() {
        UUID id = UUID.fromString("1abccf56-925a-4a7e-bd67-9a02e8c0a983");
        this.product.setProductId(id);
        assertEquals(id,  this.product.getProductId());
    }

    @Test
    void testSetProductName() {
        String newProductName = "Bawang Goreng";
        this.product.setProductName(newProductName);
        assertEquals(newProductName, this.product.getProductName());
    }

    @Test
    void testSetProductCategory() {
        String newProductCategory = "Bumbu Dapur";
        this.product.setProductCategory(newProductCategory);
        assertEquals(newProductCategory, this.product.getProductCategory());
    }

    @Test
    void testSetProductDescription() {
        String newProductDescription = "Bumbu dapur 500 gram";
        this.product.setProductDescription(newProductDescription);
        assertEquals(newProductDescription,
                this.product.getProductDescription()
        );
    }

    @Test
    void testSetProductImagePath() {
        String newProductImagePath = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Shallot_bawang_merah.jpg/1200px-Shallot_bawang_merah.jpg";
        this.product.setProductImagePath(newProductImagePath);
        assertEquals(newProductImagePath, this.product.getProductImagePath());
    }

    @Test
    void testSetProductPrice() {
        Long newProductPrice = 30000L;
        this.product.setProductPrice(newProductPrice);
        assertEquals(newProductPrice, this.product.getProductPrice());
    }
}