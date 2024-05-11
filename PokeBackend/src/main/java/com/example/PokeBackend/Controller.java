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

    @Autowired
    private PokeService pokemonService;
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve Pokémon data");
        }
    }

}
