package heymart.backend.service;

import heymart.backend.models.Supermarket;
import heymart.backend.repository.SupermarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupermarketServiceImplTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @InjectMocks
    private SupermarketServiceImpl supermarketService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() throws ExecutionException, InterruptedException{
        Long id = 123L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();

        when(supermarketRepository.findById(id)).thenReturn(Optional.of(supermarket));

        Supermarket result = supermarketService.findById(id).get();

        assertEquals(supermarket, result);
        verify(supermarketRepository, times(1)).findById(id);
    }


    @Test
    void testFindAll() {
        List<Supermarket> supermarketList = new ArrayList<>();
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(123L)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();
        supermarketList.add(supermarket);
        Supermarket supermarket2 = Supermarket.builder()
                .supermarketId(124L)
                .name("Supermarket XYZ")
                .ownerId(2L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();
        supermarketList.add(supermarket2);

        when(supermarketRepository.findAll()).thenReturn(supermarketList);

        CompletableFuture<List<Supermarket>> futureResult = supermarketService.findAll();
        List<Supermarket> result = futureResult.join();
        assertEquals(supermarketList, result);
        verify(supermarketRepository, times(1)).findAll();
    }

    @Test
    void testSave(){
        Supermarket supermarket = Supermarket.builder()
                .name("Test Supermarket")
                .ownerId(1L)
                .productIds(new ArrayList<>(List.of(UUID.randomUUID())))
                .build();

        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        Supermarket result = supermarketService.save(supermarket);
        assertEquals(supermarket, result);
        verify(supermarketRepository, times(1)).save(supermarket);
    }

    @Test
    void testDeleteById(){
        Long id = 123L;
        doNothing().when(supermarketRepository).deleteById(id);
        supermarketService.deleteById(id);
        verify(supermarketRepository, times(1)).deleteById(id);
    }
}