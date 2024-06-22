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

    /**
     * Test case for getPokemonByName when the Pokemon is fetched successfully from the repository.
     * Verifies that the method retrieves the Pokemon from the repository without calling the API.
     */
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

    /**
     * Test case for getPokemonByName when the Pokemon is fetched successfully from the API.
     * Verifies that the method calls the API to fetch the Pokemon data when not found in the repository,
     * saves it to the repository, and returns the fetched Pokemon data.
     */
    @Test
    public void testGetPokemonByName_FetchFromApi_Success() {
        String name = "bulbasaur";
        String url = "https://pokeapi.co/api/v2/pokemon/" + name;

        when(repository.findByName(name)).thenReturn(null);

        PokemonData mockPokemon = new PokemonData();
        mockPokemon.setName(name);
        mockPokemon.setHeight(7);
        mockPokemon.setWeight(69);
        mockPokemon.setOrder(1);

        LinkedHashMap<String, String> sprites = new LinkedHashMap<>();
        sprites.put("front_default", "http://example.com/front_default.png");
        mockPokemon.setSprites(sprites);

        when(restTemplate.getForEntity(eq(url), eq(PokemonData.class)))
                .thenReturn(new ResponseEntity<>(mockPokemon, HttpStatus.OK));

        PokemonData result = pokeService.getPokemonByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals("http://example.com/front_default.png", result.getFrontDefault());
    }

    /**
     * Test case for getPokemonByName when the API call fails.
     * Verifies that the method throws a RuntimeException and logs the error message.
     */
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
