package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupemarketControllerTest {

    @Mock
    private SupermarketServiceImpl supermarketService;

    @InjectMocks
    private SupermarketController supermarketController;

    @Test
    public void testCreateSupermarket() {
        Supermarket supermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();

        when(supermarketService.save(supermarket)).thenReturn(supermarket);

        ResponseEntity<Supermarket> result = supermarketController.createSupermarket(supermarket);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(supermarket, result.getBody());
        verify(supermarketService, times(1)).save(supermarket);
    }

    @Test
    public void testGetSupermarketById() {
        Supermarket supermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();

        when(supermarketService.findById(1L)).thenReturn(Optional.of(supermarket));

        ResponseEntity<Supermarket> result = supermarketController.getSupermarketById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(supermarket, result.getBody());
        verify(supermarketService, times(1)).findById(1L);
    }

    @Test
    public void testGetAllSupermarkets() {
        Supermarket supermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();
        Supermarket supermarket2 = new Supermarket.Builder()
                .setId(2L)
                .setName("Supermarket XYZ")
                .setOwnerId(2L)
                .setProductIds(Arrays.asList(4L, 5L))
                .build();
        List<Supermarket> supermarkets = Arrays.asList(supermarket, supermarket2);

        when(supermarketService.findAll()).thenReturn(supermarkets);

        ResponseEntity<List<Supermarket>> result = supermarketController.getAllSupermarkets();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(supermarkets, result.getBody());
        verify(supermarketService, times(1)).findAll();
    }

    @Test
    public void testUpdateSupermarket() {
        Supermarket existingSupermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();

        Supermarket updatedSupermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket XYZ")
                .setOwnerId(2L)
                .setProductIds(Arrays.asList(4L, 5L))
                .build();
        when(supermarketService.findById(1L)).thenReturn(Optional.of(existingSupermarket));

        when(supermarketService.save(updatedSupermarket)).thenReturn(updatedSupermarket);

        ResponseEntity<Supermarket> result = supermarketController.updateSupermarket(1L, updatedSupermarket);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedSupermarket, result.getBody());
        verify(supermarketService, times(1)).findById(1L);
        verify(supermarketService, times(1)).save(updatedSupermarket);
    }
}
