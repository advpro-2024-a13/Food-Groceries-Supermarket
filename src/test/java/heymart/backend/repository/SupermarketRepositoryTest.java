package heymart.backend.repository;

import heymart.backend.models.Supermarket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SupermarketRepositoryTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @Test
    void testFindById() {
        Long id = 123L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
//                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();

        when(supermarketRepository.findById(id)).thenReturn(Optional.of(supermarket));

        Optional<Supermarket> foundSupermarket = supermarketRepository.findById(id);
        assertEquals(supermarket, foundSupermarket.get());
        assertEquals(supermarket.getSupermarketId(), foundSupermarket.get().getSupermarketId());
    }

    @Test
    void testSave() {
        Long id = 124L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
//                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();

        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        Supermarket savedSupermarket = supermarketRepository.save(supermarket);
        assertEquals(supermarket, savedSupermarket);
        assertEquals(supermarket.getSupermarketId(), savedSupermarket.getSupermarketId());
    }
}
