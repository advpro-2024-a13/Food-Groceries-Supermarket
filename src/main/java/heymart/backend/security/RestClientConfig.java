package heymart.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class RestClientConfig {

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }
}
