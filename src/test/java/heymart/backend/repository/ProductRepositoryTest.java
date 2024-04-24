package heymart.backend.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import heymart.backend.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private List<Product> productData;

    @InjectMocks
    private ProductRepository productRepository;

    @Test
    public void testFindByProductIdFound() {
        UUID productId = UUID.randomUUID();
        Product product = new Product.Builder()
                .productId(productId)
                .build();
        when(productData.iterator()).thenReturn(new ArrayList<Product>(){{ add(product); }}.iterator());

        Product foundProduct = productRepository.findByProductId(productId);

        assertEquals(product, foundProduct);
    }

    @Test
    void testFindByIdEmptyProductRepository() {
        when(productData.iterator()).thenReturn(new ArrayList<Product>().iterator());

        Product foundProduct = productRepository.findByProductId(UUID.randomUUID());

        assertNull(foundProduct);
    }

    @Test
    public void testFindBySupermarketOwnerIdFound() {
        Long ownerId = 123L;
        Product product1 = new Product.Builder()
                .supermarketOwnerId(ownerId)
                .build();
        Product product2 = new Product.Builder()
                .supermarketOwnerId(ownerId)
                .build();
        List<Product> productList = new ArrayList<Product>() {{
            add(product1);
            add(product2);
        }};
        when(productData.iterator()).thenReturn(productList.iterator());

        List<Product> foundProducts = productRepository.findBySupermarketOwnerId(ownerId);

        assertEquals(2, foundProducts.size());
        assertTrue(foundProducts.contains(product1));
        assertTrue(foundProducts.contains(product2));
    }

    @Test
    public void testFindBySupermarketOwnerIdEmpty() {
        Long ownerId = 123L;
        when(productData.iterator()).thenReturn(new ArrayList<Product>().iterator());

        List<Product> foundProducts = productRepository.findBySupermarketOwnerId(ownerId);
        assertNotNull(foundProducts);
        assertTrue(foundProducts.isEmpty());
    }


    @Test
    public void testFindAllFound() {
        List<Product> productList = new ArrayList<>();
        when(productData.iterator()).thenReturn(productList.iterator());

        Iterator<Product> iterator = productRepository.findAll();

        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testFindAllNotFound() {
        when(productData.iterator()).thenReturn(null);

        Iterator<Product> iterator = productRepository.findAll();
        assertNull(iterator);
    }
}
