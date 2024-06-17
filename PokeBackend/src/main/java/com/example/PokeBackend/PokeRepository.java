package com.example.PokeBackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokeRepository extends JpaRepository<PokemonData, Long> {
    PokemonData findByName(String name);
}

