package heymart.backend.service;

import heymart.backend.models.Supermarket;
import heymart.backend.repository.SupermarketRepository;
import heymart.backend.service.SupermarketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupermarketServiceImplTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    private SupermarketServiceImpl supermarketService;

    private Supermarket supermarket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supermarketService = new SupermarketServiceImpl(supermarketRepository);
        supermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();
    }

    @Test
    void testFindById() {
        when(supermarketRepository.findBySupermarketId(1L)).thenReturn(CompletableFuture.completedFuture(Optional.of(supermarket)));

        CompletableFuture<Optional<Supermarket>> futureResult = supermarketRepository.findBySupermarketId(1L);
        Optional<Supermarket> result = futureResult.join(); // this will block until the future is complete
        assertTrue(result.isPresent());
        assertEquals(supermarket, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        when(supermarketRepository.findBySupermarketId(1L)).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        CompletableFuture<Optional<Supermarket>> futureResult = supermarketService.findBySupermarketId(1L);
        Optional<Supermarket> result = futureResult.join(); // this will block until the future is complete
        assertFalse(result.isPresent());
    }

    @Test
    void testFindAll() {
        Supermarket supermarket2 = new Supermarket.Builder()
                .setId(2L)
                .setName("Supermarket XYZ")
                .setOwnerId(2L)
                .setProductIds(Arrays.asList(4L, 5L))
                .build();
        List<Supermarket> supermarkets = Arrays.asList(supermarket, supermarket2);
        when(supermarketRepository.findAll()).thenReturn(supermarkets);

        CompletableFuture<List<Supermarket>> futureResult = supermarketService.findAll();
        List<Supermarket> result = futureResult.join(); // this will block until the future is complete
        assertEquals(supermarkets, result);
    }

    @Test
    void testSave() {
        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        CompletableFuture<Supermarket> futureResult = supermarketService.save(supermarket);
        Supermarket result = futureResult.join(); // this will block until the future is complete
        assertEquals(supermarket, result);
    }

    @Test
    void testDeleteById() {
        supermarketService.deleteById(1L).join(); // this will block until the future is complete
        verify(supermarketRepository, times(1)).deleteById(1L);
    }
}