package at.aau.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCore {

    @Test
    public void testPlayer() {
        Player player = new Player("One", 0xFF000000);
        assertEquals("One", player.getName());
        assertEquals(0, player.getAvailable());
    }

    @Test
    public void testCountry() {
        Country country = new Country("Alaska");
        assertEquals("Alaska", country.getName());
        assertEquals(0, country.getArmies());
    }

}
