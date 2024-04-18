package com.example.PokeBackend;

import com.example.PokeBackend.PokeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
public class Controller {
    private final PokeService pokemonService;

    public Controller(PokeService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public Mono<ResponseEntity<PokemonData>> getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name)
                .map(pokemonData -> ResponseEntity.ok(pokemonData))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
