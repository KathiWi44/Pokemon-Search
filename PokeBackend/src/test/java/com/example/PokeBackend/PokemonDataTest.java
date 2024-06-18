package com.example.PokeBackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDataTest {

    private PokemonData pokemonData;

    @BeforeEach
    void setUp() {
        pokemonData = new PokemonData();
    }

    @Test
    void testGetId() {
        pokemonData.setId(1);
        assertEquals(1, pokemonData.getId());
    }

    @Test
    void testSetId() {
        pokemonData.setId(1);
        assertEquals(1, pokemonData.getId());
    }

    @Test
    void testSetName() {
        pokemonData.setName("Pikachu");
        assertEquals("Pikachu", pokemonData.getName());
    }

    @Test
    void testGetName() {
        pokemonData.setName("Pikachu");
        assertEquals("Pikachu", pokemonData.getName());
    }

    @Test
    void testSetHeight() {
        pokemonData.setHeight(10);
        assertEquals(10, pokemonData.getHeight());
    }

    @Test
    void testGetHeight() {
        pokemonData.setHeight(10);
        assertEquals(10, pokemonData.getHeight());
    }

    @Test
    void testSetOrder() {
        pokemonData.setOrder(1);
        assertEquals(1, pokemonData.getOrder());
    }

    @Test
    void testGetOrder() {
        pokemonData.setOrder(1);
        assertEquals(1, pokemonData.getOrder());
    }

    @Test
    void testSetWeight() {
        pokemonData.setWeight(60);
        assertEquals(60, pokemonData.getWeight());
    }

    @Test
    void testGetWeight() {
        pokemonData.setWeight(60);
        assertEquals(60, pokemonData.getWeight());
    }

    @Test
    void testSetSprites() {
        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front.png");
        pokemonData.setSprites(sprites);
        assertEquals(sprites, pokemonData.getSprites());
    }

    @Test
    void testGetSprites() {
        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front.png");
        pokemonData.setSprites(sprites);
        assertEquals(sprites, pokemonData.getSprites());
    }

    @Test
    void testGetFrontDefault() {
        pokemonData.setFrontDefault("http://example.com/front.png");
        assertEquals("http://example.com/front.png", pokemonData.getFrontDefault());
    }

    @Test
    void testSetFrontDefault() {
        pokemonData.setFrontDefault("http://example.com/front.png");
        assertEquals("http://example.com/front.png", pokemonData.getFrontDefault());
    }


    /*
    @Test
    void testOnCreate() {
        PokemonData spyPokemonData = Mockito.spy(pokemonData);
        spyPokemonData.onCreate();
        assertNotNull(spyPokemonData.getCreatedAt());
    }

     */
}

