package com.example.PokeBackend;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Mock
    private PokeService pokemonService;

    @Mock
    private Logger logger;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPokemonName_Valid() {
        // Arrange
        String name = "bulbasaur";
        PokemonData mockPokemon = new PokemonData();
        mockPokemon.setName(name);
        when(pokemonService.getPokemonByName(name.toLowerCase())).thenReturn(mockPokemon);

        // Act
        ResponseEntity<?> response = controller.getPokemonName(name);

        // Assert
        verify(pokemonService, times(1)).getPokemonByName(name.toLowerCase());
        verifyNoMoreInteractions(pokemonService);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPokemon, response.getBody());
    }

    @Test
    public void testGetPokemonName_Invalid() {
        // Arrange
        String name = "bikachu";
        when(pokemonService.getPokemonByName(name.toLowerCase())).thenReturn(null);

        // Act
        ResponseEntity<?> response = controller.getPokemonName(name);

        // Assert
        verify(pokemonService, times(1)).getPokemonByName(name.toLowerCase());
        verifyNoMoreInteractions(pokemonService);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Pokémon could not be found!", response.getBody());
    }
}
