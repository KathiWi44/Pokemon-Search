package com.example.PokeBackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class PokeServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PokeRepository repository;

    @InjectMocks
    private PokeService pokeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
        // Arrange
        String name = "bulbasaur";
        PokemonData mockPokemon = new PokemonData();
        mockPokemon.setName(name);

        when(repository.findByName(name)).thenReturn(mockPokemon);
        when(restTemplate.getForEntity(anyString(), eq(PokemonData.class)))
                .thenReturn(new ResponseEntity<>(mockPokemon, HttpStatus.OK));

        // Act
        if(pokeService!=null) {
            PokemonData result = pokeService.getPokemonByName(name);

            // Assert
            assertNotNull(result);
            assertEquals(name, result.getName());
        }
    }

    @Test
    public void testGetPokemonByName_ApiFailure() {
        String name = "charizard";
        when(repository.findByName(name)).thenReturn(null);
        when(restTemplate.getForEntity(anyString(), eq(PokemonData.class)))
                .thenThrow(new RuntimeException("API Error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            pokeService.getPokemonByName(name);
        });

        assertEquals("Failed to fetch Pokémon data from API: API Error", exception.getMessage());
    }

}


