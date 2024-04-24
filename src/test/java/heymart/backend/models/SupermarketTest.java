package heymart.backend.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SupermarketTest {
    private Supermarket supermarket;
    private Long managerId;
    private List<Long> productIds = new ArrayList<>();
    @BeforeEach
    void setUp() {
        managerId = 1L;
        supermarket = new Supermarket.Builder()
                        .setId(1L)
                        .setName("Supermarket ABC")
                        .setOwnerId(managerId)
                        .setProductIds(productIds)
                        .build();
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
    void testGetOwnerId() {
        assertEquals(1L, supermarket.getOwnerId());
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
        assertTrue(supermarket.getProductIds().contains(newProductId));
    }

    @Test
    void testRemoveProductId() {
        supermarket.addProductId(3L);
        supermarket.removeProductId(3L);
        assertFalse(supermarket.getProductIds().contains(3L));
    }
}