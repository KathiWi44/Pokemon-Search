package org.example.PokeBackend;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokemonMarshaller {
    private final ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger("Testing");

    public PokemonMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public PokemonData pokemonMarshaller(String pokemon) {
        try {
            return objectMapper.readValue(pokemon, PokemonData.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Json couldn't be transformed!");
            return null;
        }
    }
}
