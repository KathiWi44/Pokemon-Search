package com.example.PokeBackend;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

@Service
public class PokeService {
    private final RestTemplate restTemplate;
    private final String pokeApiUrl = "https://pokeapi.co/api/v2";
    private final Logger logger = LoggerFactory.getLogger(PokeService.class);

    public PokeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "pokemonCache", key = "#name")
    public PokemonData getPokemonByName(String name) {
        String url = pokeApiUrl + "/pokemon/" + name;
        try {
            ResponseEntity<PokemonData> responseEntity = restTemplate.getForEntity(url, PokemonData.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("Failed to fetch Pokémon data from API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch Pokémon data from API: " + e.getMessage(), e);
        }
    }

}
