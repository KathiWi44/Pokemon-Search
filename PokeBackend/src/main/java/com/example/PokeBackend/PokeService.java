package com.example.PokeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PokeService {
    private final RestTemplate restTemplate;
    private final PokeRepository repository;
    private final String pokeApiUrl = "https://pokeapi.co/api/v2";
    private final Logger logger = LoggerFactory.getLogger(PokeService.class);

    /**
     * @param restTemplate The RestTemplate instance used to make HTTP requests to the Pokemon V2 API.
     * @param repository The PokeRepository instance used to access and store Pokemon data in the database.
     */
    @Autowired
    public PokeService(RestTemplate restTemplate, PokeRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    /**
     * Retrieves Pokemon data by name.
     * If the Pokemon is found in the local database, retrieves and returns it.
     * If not found, fetches the Pokemon data from the external API,
     * saves it to the database, and returns the fetched Pokemon data.
     *
     * @param name The name of the Pokemon to retrieve.
     * @return PokemonData object containing the retrieved Pokemon's data.
     */
    public PokemonData getPokemonByName(String name) {
        PokemonData pokemon = repository.findByName(name);
        if (pokemon != null) {
            logger.info("Fetching Pokémon {} from pokemon database...", name);
            return pokemon;
        }

        String url = pokeApiUrl + "/pokemon/" + name;

        ResponseEntity<PokemonData> responseEntity = restTemplate.getForEntity(url, PokemonData.class);
        PokemonData fetchedPokemon = responseEntity.getBody();

        if (fetchedPokemon != null) {
            logger.info("Fetched Pokémon {} from API. Saving to database...", name);
            fetchedPokemon.setFrontDefault(fetchedPokemon.getSprites().get("front_default").toString());
            repository.save(fetchedPokemon);
        }
        return fetchedPokemon;
    }
}
