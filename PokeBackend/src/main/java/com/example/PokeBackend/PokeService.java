package com.example.PokeBackend;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Service
public class PokeService {
    private final RestTemplate restTemplate;
    private final String pokeApiUrl = "https://pokeapi.co/api/v2";

    public PokeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonData getPokemonByName(String name) {
        String url = pokeApiUrl + "/pokemon/" + name;
        ResponseEntity<PokemonData> responseEntity = restTemplate.getForEntity(url, PokemonData.class);
        if (responseEntity.getBody() != null) {
            responseEntity.getBody();
        }
        return responseEntity.getBody();
    }
/*
    public Mono<PokemonData> getPokemonByName(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/pokemon/{name}").build(name))
                .retrieve()
                .bodyToMono(PokemonData.class)
                .onErrorMap(error -> new RuntimeException("Failed to fetch Pokémon data from API: " + error.getMessage()));
    }

 */
}
