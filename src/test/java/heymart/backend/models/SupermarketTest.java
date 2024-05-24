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

    @Test
    void testGetDescription() {
        assertEquals("Warung ini menyediakan berbagai macam barang kebutuhan anda sehari - hari", supermarket.getSupermarketDescription());
    }

    @Test
    void testSetDescription() {
        supermarket.setSupermarketDescription("Warung ini tempat praktik money laundering");
        assertEquals("Warung ini tempat praktik money laundering", supermarket.getSupermarketDescription());
    }

    @Test
    void testGetImage() {
        assertEquals("https://pbs.twimg.com/media/GKRGnu-WUAAszQP.jpg", supermarket.getSupermarketImage());
    }

    @Test
    void testSetImage() {
        supermarket.setSupermarketImage("https://tr.rbxcdn.com/732d895056cc76da9bb3b56bee353053/420/420/Hat/Png");
        assertEquals("https://tr.rbxcdn.com/732d895056cc76da9bb3b56bee353053/420/420/Hat/Png", supermarket.getSupermarketImage());
    }
}