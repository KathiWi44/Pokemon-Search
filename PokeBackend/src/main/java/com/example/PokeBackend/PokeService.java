package com.example.PokeBackend;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PokeService {
    private final WebClient webClient;
    private final String pokeApiUrl = "https://pokeapi.co/api/v2";

    public PokeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(pokeApiUrl).build();
    }

    public Mono<PokemonData> getPokemonByName(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/pokemon/{name}").build(name))
                .retrieve()
                .bodyToMono(PokemonData.class)
                .onErrorMap(error -> new RuntimeException("Failed to fetch Pokémon data from API: " + error.getMessage()));
    }
}
