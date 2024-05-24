package heymart.backend.models;

import heymart.backend.authority.SimpleGrantedAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "test@example.com", "Test", "User", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void testGetters() {
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("password", user.getPassword());
        assertEquals("ROLE_USER", user.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testUserDetailsMethods() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void testEqualsAndHashCode() {
        User sameUser = new User(1L, "test@example.com", "Test", "User", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        User differentUser = new User(2L, "test2@example.com", "Test2", "User2", "password2", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        assertEquals(user, sameUser);
        assertNotEquals(user, differentUser);

        assertEquals(user.hashCode(), sameUser.hashCode());
        assertNotEquals(user.hashCode(), differentUser.hashCode());
    }

    @Test
    void testEquals() {
        User sameUser = new User(1L, "test@example.com", "Test", "User", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        assertTrue(sameUser.equals(sameUser));
        assertFalse(sameUser.equals(null));
    }

    @Test
    void testUserMethods() {
        assertEquals("Test", user.firstName());
        assertEquals("User", user.lastName());
        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    void testIdAndEmailMethods() {
        assertEquals(1L, user.id());
        assertEquals("test@example.com", user.email());
    }

    @Test
    void setCreatedAt() {
        user.setCreatedAt(1L);
        assertEquals(1L, user.getCreatedAt());
    }

    @Test
    void setRole() {
        user.setRole("ROLE_USER");
        assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    void getAuthority() {
        assertEquals("ROLE_USER", user.getAuthority().iterator().next().getAuthority());
    }
}