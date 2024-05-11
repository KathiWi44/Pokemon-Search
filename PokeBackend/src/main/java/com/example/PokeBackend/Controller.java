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

@RestController
@RequestMapping("/pokemon")
public class Controller {

    @Autowired
    private PokeService pokemonService;

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    @GetMapping("/{name}")
    public ResponseEntity<?> getPokemonName(@PathVariable String name) {
        PokemonData pokemon = pokemonService.getPokemonByName(name.toLowerCase());
        if(pokemon != null){
            return ResponseEntity.ok(pokemon);
        }
        String error = "Pokemon could not be found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
