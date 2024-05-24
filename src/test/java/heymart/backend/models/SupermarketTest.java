package heymart.backend.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketTest {
    Supermarket supermarket;
    private Long managerId;
    @BeforeEach
    void setUp() {
        managerId = 1L;
        this.supermarket = Supermarket.builder()
                        .supermarketId(123L)
                        .name("Supermarket ABC")
                        .ownerId(managerId)
                        .supermarketDescription("Warung ini menyediakan berbagai macam barang kebutuhan anda sehari - hari")
                        .supermarketImage("https://pbs.twimg.com/media/GKRGnu-WUAAszQP.jpg")
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
    void testSetName() {
        supermarket.setName("Supermarket XYZ");
        assertEquals("Supermarket XYZ", supermarket.getName());
    }
}