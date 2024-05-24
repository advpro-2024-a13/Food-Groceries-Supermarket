package heymart.backend.controller;

import heymart.backend.models.Supermarket;
import heymart.backend.service.SupermarketServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupermarketControllerTest {

    @Mock
    private SupermarketServiceImpl supermarketService;

    @InjectMocks
    private SupermarketController supermarketController;

    @Test
    void testCreateSupermarket() {
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(123L)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(new ArrayList<>(List.of(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                        UUID.fromString("123e4567-e89b-12d3-a456-426614174001"),
                        UUID.fromString("123e4567-e89b-12d3-a456-426614174002"))))
                .build();

        when(supermarketService.save(any(Supermarket.class))).thenReturn(supermarket);

        ResponseEntity<Supermarket> responseEntity = supermarketController.createSupermarket(supermarket);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(supermarket, responseEntity.getBody());
        verify(supermarketService, times(1)).save(supermarket);
    }

    @Test
    public void testGetSupermarketById() {
        Long id = 123L;
        List<UUID> productId = new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()));
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(productId)
                .build();

        CompletableFuture<Supermarket> future = CompletableFuture.completedFuture(supermarket);
        when(supermarketService.findById(id)).thenReturn(future);

        ResponseEntity<?> responseEntity = supermarketController.getSupermarketById(id).join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(supermarketService, times(1)).findById(id);
    }

    @Test
    public void testGetAllSupermarkets() {
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
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID())))
                .build();
        supermarketList.add(supermarket2);

        CompletableFuture<List<Supermarket>> future = CompletableFuture.completedFuture(supermarketList);
        when(supermarketService.findAll()).thenReturn(future);

        ResponseEntity<?> responseEntity = supermarketController.getAllSupermarkets().join();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(supermarketList, responseEntity.getBody());
        verify(supermarketService, times(1)).findAll();
    }

    @Test
    public void testEditSupermarket() {
        Long id = 123L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();

        Supermarket editedSupermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket XYZ")
                .ownerId(2L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID())))
                .build();

            when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(supermarket));
            when(supermarketService.save(editedSupermarket)).thenReturn(editedSupermarket);
        ResponseEntity<String> responseEntity = supermarketController.editSupermarket(id, editedSupermarket);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(supermarketService, times(1)).findById(id);
        verify(supermarketService, times(1)).save(editedSupermarket);
    }

    @Test
    public void testEditSupermarketNotFound() {
        Long id = 123L;
        Supermarket editedSupermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket XYZ")
                .ownerId(2L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID())))
                .build();

        when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<String> responseEntity = supermarketController.editSupermarket(id, editedSupermarket);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Supermarket not found with ID " + id, responseEntity.getBody());
        verify(supermarketService, times(1)).findById(id);
        verify(supermarketService, times(0)).save(editedSupermarket);
    }

    @Test
    public void testDeleteSupermarket() {
        Long id = 123L;
        Supermarket supermarket = Supermarket.builder()
                .supermarketId(id)
                .name("Supermarket ABC")
                .ownerId(1L)
                .productIds(new ArrayList<>(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())))
                .build();

        CompletableFuture<Supermarket> future = CompletableFuture.completedFuture(supermarket);
        when(supermarketService.findById(id)).thenReturn(future);
        when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(supermarket));


        ResponseEntity<String> responseEntity = supermarketController.deleteSupermarket(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(supermarketService, times(1)).findById(id);
        verify(supermarketService, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteSupermarketNotFound() {
        Long id = 123L;

        // Simulate that the supermarket with the given ID does not exist
        when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<String> responseEntity = supermarketController.deleteSupermarket(id);

        // Assert that the response status is BAD_REQUEST and the correct message is returned
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Supermarket not found with ID " + id, responseEntity.getBody());
        verify(supermarketService, times(1)).findById(id);
        verify(supermarketService, times(0)).deleteById(id);
    }

    @Test
    public void testGetSupermarketByIdNotFound() {
        Long id = 123L;

        when(supermarketService.findById(id)).thenReturn(CompletableFuture.completedFuture(null));

        ResponseEntity<?> responseEntity = supermarketController.getSupermarketById(id).join();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(supermarketService, times(1)).findById(id);
    }
}
