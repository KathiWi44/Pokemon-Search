package com.example.PokeBackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/pokemon")
public class Controller {
    private PokeService pokemonService;
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(PokeService pokemonService){
        this.pokemonService = pokemonService;
    }

    /**
     * Handles GET requests for retrieving Pokemon data by name.
     * If the Pokemon is found, returns a ResponseEntity with HTTP status OK (200) and the Pokemon data.
     * If the Pokemon is not found, returns a ResponseEntity with HTTP status NOT_FOUND (404) and an error message.
     * If an exception occurs during the process, logs the error and returns a ResponseEntity
     * with HTTP status INTERNAL_SERVER_ERROR (500) and an error message.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return ResponseEntity containing either the Pokemon data or an error message based on the outcome.
     */
    @CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.GET})
    @GetMapping("/{name}")
    public ResponseEntity<?> getPokemonName(@PathVariable String name) {
        try {
            PokemonData pokemon = pokemonService.getPokemonByName(name.toLowerCase());
            if (pokemon != null) {
                return ResponseEntity.ok(pokemon);
            } else {
                String error = "Pokémon could not be found!";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
        } catch (Exception e) {
            logger.error("Failed to fetch Pokémon: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve Pokémon data");
        }
    }
}
