package heymart.backend.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SupermarketTest {

    private Supermarket supermarket;
    private User owner;
    private List<Long> productIds;

    @BeforeEach
    void setUp() {
        owner = new User(1L, "johndoe", "john@example.com");
        productIds = new ArrayList<>();
        productIds.add(1L);
        productIds.add(2L);
        supermarket = new Supermarket(1L, "Supermarket ABC", owner, productIds);
    }

    @Test
    void testGetId() {
        assertEquals(1L, supermarket.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Supermarket ABC", supermarket.getName());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, supermarket.getOwner());
    }

    @Test
    void testGetProductIds() {
        assertEquals(productIds, supermarket.getProductIds());
    }

    @Test
    void testSetName() {
        supermarket.setName("Supermarket XYZ");
        assertEquals("Supermarket XYZ", supermarket.getName());
    }

    @Test
    void testAddProductId() {
        Long newProductId = 3L;
        supermarket.addProductId(newProductId);
        assertEquals(3, supermarket.getProductIds().size());
        assertEquals(newProductId, supermarket.getProductIds().get(2));
    }

    @Test
    void testRemoveProductId() {
        supermarket.removeProductId(productIds.get(0));
        assertEquals(1, supermarket.getProductIds().size());
        assertEquals(productIds.get(1), supermarket.getProductIds().get(0));
    }
}