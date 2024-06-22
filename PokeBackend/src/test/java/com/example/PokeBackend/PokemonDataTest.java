package com.example.PokeBackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PokemonDataTest {

    private PokemonData pokemonData;

    @BeforeEach
    void setUp() {
        pokemonData = new PokemonData();
    }

    @Test
    void testId() {
        // Test setId and getId
        pokemonData.setId(1);
        assertEquals(1, pokemonData.getId());
    }

    @Test
    void testName() {
        // Test setName and getName
        pokemonData.setName("Pikachu");
        assertEquals("Pikachu", pokemonData.getName());
    }

    @Test
    void testHeight() {
        // Test setHeight and getHeight
        pokemonData.setHeight(10);
        assertEquals(10, pokemonData.getHeight());
    }

    @Test
    void testOrder() {
        // Test setOrder and getOrder
        pokemonData.setOrder(1);
        assertEquals(1, pokemonData.getOrder());
    }

    @Test
    void testWeight() {
        // Test setWeight and getWeight
        pokemonData.setWeight(60);
        assertEquals(60, pokemonData.getWeight());
    }

    @Test
    void testSprites() {
        // Test setSprites and getSprites
        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front.png");
        pokemonData.setSprites(sprites);
        assertEquals(sprites, pokemonData.getSprites());
    }

    @Test
    void testFrontDefault() {
        // Test setFrontDefault and getFrontDefault
        pokemonData.setFrontDefault("http://example.com/front.png");
        assertEquals("http://example.com/front.png", pokemonData.getFrontDefault());
    }
}
