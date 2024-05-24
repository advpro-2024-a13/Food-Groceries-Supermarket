package heymart.backend.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class SupermarketTest {
    Supermarket supermarket;
    private Long managerId;
    private List<UUID> productIds = new ArrayList<>();
    @BeforeEach
    void setUp() {
        managerId = 1L;
        productIds = new ArrayList<>(List.of(UUID.fromString("d144792b-40f5-42a8-b939-9df19231dba5")));
        this.supermarket = Supermarket.builder()
                        .supermarketId(123L)
                        .name("Supermarket ABC")
                        .ownerId(managerId)
                        .productIds(productIds)
                        .build();
    }

    @Test
    void testGetId() {
        Long id = 123L;
        assertEquals(id, supermarket.getSupermarketId());
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
        UUID newProductId = UUID.fromString("5c893611-f22d-4666-9d6f-06005cfdc31f");
        supermarket.addProductId(newProductId);
        assertTrue(supermarket.getProductIds().contains(newProductId));
    }

    @Test
    void testRemoveProductId() {
        UUID id = UUID.fromString("5c893611-f22d-4666-9d6f-06005cfdc31f");
        supermarket.addProductId(id);
        supermarket.removeProductId(id);
        assertFalse(supermarket.getProductIds().contains(id));
    }

    @Test
    void testAddProductIdAlreadyExists() {
        UUID existingProductId = UUID.fromString("d144792b-40f5-42a8-b939-9df19231dba5");
        supermarket.addProductId(existingProductId);
        assertEquals(1, supermarket.getProductIds().size());
    }
}