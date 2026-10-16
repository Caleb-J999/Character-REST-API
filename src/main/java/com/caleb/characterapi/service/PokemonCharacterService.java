package com.caleb.characterapi.service;

import com.caleb.characterapi.entity.PokemonCharacter;
import com.caleb.characterapi.repository.PokemonCharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonCharacterService {

    private final PokemonCharacterRepository repository;

    public PokemonCharacterService(PokemonCharacterRepository repository) {
        this.repository = repository;
    }

    public List<PokemonCharacter> getAllCharacters() {
        return repository.findAll();
    }

    public PokemonCharacter getCharacterById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No pokemon found"));
    }

    public PokemonCharacter createCharacter(PokemonCharacter character) {
        character.setCharacterId(0);
        return repository.save(character);
    }

    public PokemonCharacter updateCharacter(
            long id, PokemonCharacter updatedCharacter) {

        PokemonCharacter existingCharacter = getCharacterById(id);

        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setDescription(updatedCharacter.getDescription());
        existingCharacter.setPokemonType(updatedCharacter.getPokemonType());
        existingCharacter.setRegion(updatedCharacter.getRegion());

        return repository.save(existingCharacter);
    }

    public void deleteCharacter(long id) {
        PokemonCharacter existingCharacter = getCharacterById(id);
        repository.delete(existingCharacter);
    }

    public List<PokemonCharacter> filterByType(String pokemonType) {
        return repository.findByPokemonTypeIgnoreCase(pokemonType);
    }

    public List<PokemonCharacter> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}