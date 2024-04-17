package heymart.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import heymart.backend.models.Product;
import heymart.backend.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setProductId(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93"));
        product.setProductName("Cilok Khas Bandung");
        product.setProductQuantity(1);
        product.setProductCategory("Makanan Matang");
        product.setProductDescription("Cilok enak dibuat dari Bandung dengan berat satu kilogram.");
        product.setProductImagePath("https://cdn0-production-images-kly.akamaized.net/OBneEUAQirmpXtpArNP01qdJofY=/0x284:903x793/1200x675/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3384331/original/092840500_1614053572-shutterstock_1791558536.jpg");
        product.setProductPrice(200000L);
        when(productRepository.findByProductId(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93"))).thenReturn(product);
    }

    @Test
    public void testFindByOwnerId() {
        Product foundProduct = productService.getProductById(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93"));

        verify(productRepository).findByProductId(UUID.fromString("d6e2c1fd-0dd4-4be2-b2fb-efdc1c3c2c93"));

        assertEquals(123L, foundProduct.getProductId());
        assertEquals("Cilok Khas Bandung", foundProduct.getProductName());
    }
}