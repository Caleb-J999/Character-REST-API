package com.caleb.characterapi.repository;

import com.caleb.characterapi.entity.PokemonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonCharacterRepository
        extends JpaRepository<PokemonCharacter, Long> {

    List<PokemonCharacter> findByPokemonTypeIgnoreCase(String pokemonType);

    List<PokemonCharacter> findByNameContainingIgnoreCase(String name);
}