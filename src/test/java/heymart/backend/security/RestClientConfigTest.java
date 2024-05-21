package heymart.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestClientConfigTest {

    @Autowired
    private RestClient restClient;

    @Test
    void restClient() {
        assertNotNull(restClient);
    }
}