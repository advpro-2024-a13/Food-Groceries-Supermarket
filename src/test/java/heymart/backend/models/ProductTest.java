package heymart.backend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        this.product = Product.builder()
                .productId(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93"))
                .productName("Ayam Potong")
                .productQuantity(100)
                .productCategory("Bahan Makanan")
                .productDescription("Ayam potong dengan berat satu kilogram.")
                .productImagePath("https://sogood.id/wp-content/uploads/2018/08/FA_SG_Ayam_Utuh_Potong@10_1kg.png")
                .productPrice(41900L)
                .build();
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
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testGetProductCategory() {
        assertEquals("Bahan Makanan", this.product.getProductCategory());
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Ayam potong dengan berat satu kilogram.", this.product.getProductDescription());
    }

    @Test
    void testGetProductImagePath() {
        assertEquals("https://sogood.id/wp-content/uploads/2018/08/FA_SG_Ayam_Utuh_Potong@10_1kg.png", this.product.getProductImagePath());
    }

    @Test
    void testGetProductPrice() {
        assertEquals(41900L, this.product.getProductPrice());
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
    void testSetProductQuantity() {
        int newProductQuantity = 50;
        this.product.setProductQuantity(newProductQuantity);
        assertEquals(newProductQuantity, this.product.getProductQuantity());
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
        assertEquals(newProductDescription, this.product.getProductDescription());
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

    @Test
    void testNoArgsConstructor() {
        Product emptyProduct = new Product();
        assertEquals(null, emptyProduct.getProductId());
        assertEquals(null, emptyProduct.getProductName());
        assertEquals(0, emptyProduct.getProductQuantity());
        assertEquals(null, emptyProduct.getProductCategory());
        assertEquals(null, emptyProduct.getProductDescription());
        assertEquals(null, emptyProduct.getProductImagePath());
        assertEquals(null, emptyProduct.getProductPrice());
        assertEquals(null, emptyProduct.getSupermarketOwnerId());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String name = "Test Product";
        int quantity = 10;
        String category = "TestCategory";
        String description = "Test Description";
        String imagePath = "https://example.com/image.jpg";
        Long price = 1000L;
        Long ownerId = 12345L;

        Product allArgsProduct = new Product(id, name, quantity, category, description, imagePath, price, ownerId);

        assertEquals(id, allArgsProduct.getProductId());
        assertEquals(name, allArgsProduct.getProductName());
        assertEquals(quantity, allArgsProduct.getProductQuantity());
        assertEquals(category, allArgsProduct.getProductCategory());
        assertEquals(description, allArgsProduct.getProductDescription());
        assertEquals(imagePath, allArgsProduct.getProductImagePath());
        assertEquals(price, allArgsProduct.getProductPrice());
        assertEquals(ownerId, allArgsProduct.getSupermarketOwnerId());
    }

    @Test
    void testSetSupermarketOwnerId() {
        Long newOwnerId = 12345L;
        this.product.setSupermarketOwnerId(newOwnerId);
        assertEquals(newOwnerId, this.product.getSupermarketOwnerId());
    }

    @Test
    public void testProductBuilderToString() {
        // Given
        Product.ProductBuilder builder = Product.builder();

        // When
        String actualToString = builder.toString();

        // Then
        String expectedToString = "Product.ProductBuilder(" +
                "productId=null, productName=null, productQuantity=0, " +
                "productCategory=null, productDescription=null, " +
                "productImagePath=null, productPrice=null, supermarketOwnerId=null" +
                ")";
        assertEquals(expectedToString, actualToString);
    }

}