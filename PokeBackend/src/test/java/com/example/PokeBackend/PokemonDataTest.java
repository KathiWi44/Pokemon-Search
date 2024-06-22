package com.example.PokeBackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

//PokemonDataTest tests the getters and setters of PokemonData

class PokemonDataTest {

    private PokemonData pokemonData;

    @BeforeEach
    void setUp() {
        pokemonData = new PokemonData();
    }

    @Test
    void testId() {
        pokemonData.setId(1);
        assertEquals(1, pokemonData.getId());
    }

    @Test
    void testName() {
        pokemonData.setName("Pikachu");
        assertEquals("Pikachu", pokemonData.getName());
    }

    @Test
    void testHeight() {
        pokemonData.setHeight(10);
        assertEquals(10, pokemonData.getHeight());
    }

    @Test
    void testOrder() {
        pokemonData.setOrder(1);
        assertEquals(1, pokemonData.getOrder());
    }

    @Test
    void testWeight() {
        pokemonData.setWeight(60);
        assertEquals(60, pokemonData.getWeight());
    }

    @Test
    void testSprites() {
        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front.png");
        pokemonData.setSprites(sprites);
        assertEquals(sprites, pokemonData.getSprites());
    }

    @Test
    void testFrontDefault() {
        pokemonData.setFrontDefault("http://example.com/front.png");
        assertEquals("http://example.com/front.png", pokemonData.getFrontDefault());
    }
}
