package heymart.backend.authority;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGrantedAuthorityTest {

    @Test
    void getAuthority() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        assertEquals("ROLE_USER", authority.getAuthority());
    }
}