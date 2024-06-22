package com.example.PokeBackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PokeRepository repository;

    @Mock
    private Logger logger;

    @InjectMocks
    private PokeService pokeService;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this); // Not needed with @ExtendWith(MockitoExtension.class)
    }

    @Test
    public void testGetPokemonByName_FetchFromRepository() {
        String name = "pikachu";
        PokemonData mockPokemon = new PokemonData();
        mockPokemon.setName(name);
        when(repository.findByName(name)).thenReturn(mockPokemon);

        PokemonData result = pokeService.getPokemonByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    public void testGetPokemonByName_FetchFromApi_Success() {
        String name = "bulbasaur";
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        // Mocking repository to return null, indicating the Pokémon is not found in the database
        when(repository.findByName(name)).thenReturn(null);

        // Creating a real PokemonData object with necessary properties
        PokemonData mockPokemon = new PokemonData();
        mockPokemon.setName(name);
        mockPokemon.setHeight(7);
        mockPokemon.setWeight(69);
        mockPokemon.setOrder(1);

        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front_default.png");
        mockPokemon.setSprites(sprites);

        // Mocking the behavior of restTemplate.getForEntity to return a ResponseEntity with the mock PokemonData
        when(restTemplate.getForEntity(eq(url), eq(PokemonData.class)))
                .thenReturn(new ResponseEntity<>(mockPokemon, HttpStatus.OK));

        // Calling the method under test
        PokemonData result = pokeService.getPokemonByName(name);

        // Assertions to verify the results
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals("http://example.com/front_default.png", result.getFrontDefault());
    }

    @Test
    public void testGetPokemonByName_ApiFailure() {
        String name = "charizard";
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        when(repository.findByName(name)).thenReturn(null);
        when(restTemplate.getForEntity(eq(url), eq(PokemonData.class)))
                .thenThrow(new RuntimeException("API Error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pokeService.getPokemonByName(name);
        });
    }
}
