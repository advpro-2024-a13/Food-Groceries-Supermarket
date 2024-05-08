package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SupermarketControllerTest {

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

        when(supermarketService.save(supermarket)).thenReturn(CompletableFuture.completedFuture(supermarket));

        try {
            ResponseEntity<Supermarket> result = supermarketController.createSupermarket(supermarket);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(supermarket, result.getBody());
            verify(supermarketService, times(1)).save(supermarket);
        } catch (InterruptedException | ExecutionException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetSupermarketById() {
        Supermarket supermarket = new Supermarket.Builder()
                .setId(1L)
                .setName("Supermarket ABC")
                .setOwnerId(1L)
                .setProductIds(Arrays.asList(1L, 2L, 3L))
                .build();

        when(supermarketService.findBySupermarketId(1L)).thenReturn(CompletableFuture.completedFuture(Optional.of(supermarket)));

        try{
            ResponseEntity<Supermarket> result = supermarketController.getSupermarketById(1L);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(supermarket, result.getBody());
            verify(supermarketService, times(1)).findBySupermarketId(1L);
        } catch (InterruptedException | ExecutionException e) {
            fail("Exception occurred: " + e.getMessage());
        }
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

        when(supermarketService.findAll()).thenReturn(CompletableFuture.completedFuture(supermarkets));

        try {
            ResponseEntity<List<Supermarket>> result = supermarketController.getAllSupermarkets();

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(supermarkets, result.getBody());
            verify(supermarketService, times(1)).findAll();
        } catch (InterruptedException | ExecutionException e) {
            fail("Exception occurred: " + e.getMessage());
        }
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

        when(supermarketService.findBySupermarketId(1L)).thenReturn(CompletableFuture.completedFuture(Optional.of(existingSupermarket)));
        when(supermarketService.save(any(Supermarket.class))).thenReturn(CompletableFuture.completedFuture(updatedSupermarket));

        try{
            ResponseEntity<Supermarket> result = supermarketController.updateSupermarket(1L, updatedSupermarket);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(updatedSupermarket, result.getBody());
            verify(supermarketService, times(1)).findBySupermarketId(1L);
            verify(supermarketService, times(1)).save(any(Supermarket.class));
        } catch (InterruptedException | ExecutionException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
