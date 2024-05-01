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
        when(supermarketRepository.findById(1L)).thenReturn(Optional.of(supermarket));

        Optional<Supermarket> result = supermarketService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(supermarket, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        when(supermarketRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Supermarket> result = supermarketService.findById(1L);
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

        List<Supermarket> result = supermarketService.findAll();
        assertEquals(supermarkets, result);
    }

    @Test
    void testSave() {
        when(supermarketRepository.save(supermarket)).thenReturn(supermarket);

        Supermarket result = supermarketService.save(supermarket);
        assertEquals(supermarket, result);
    }

    @Test
    void testDeleteById() {
        supermarketService.deleteById(1L);
        verify(supermarketRepository, times(1)).deleteById(1L);
    }
}