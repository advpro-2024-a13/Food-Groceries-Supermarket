package heymart.backend.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HomepageControllerTest {

    @Test
    void testHomepage() {
        HomepageController homepageController = new HomepageController();

        String viewName = homepageController.homepage();

        assertEquals("homepage", viewName);
    }
}
