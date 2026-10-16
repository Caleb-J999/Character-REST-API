package com.caleb.characterapi.controller;

import com.caleb.characterapi.entity.PokemonCharacter;
import com.caleb.characterapi.service.PokemonCharacterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class PokemonCharacterController {

    private final PokemonCharacterService service;

    public PokemonCharacterController(PokemonCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<PokemonCharacter> getCharacters(
            @RequestParam(name = "pokemonType", required = false)
            String pokemonType,
            @RequestParam(name = "name", required = false)
            String name) {

        if (name != null) {
            return service.searchByName(name);
        }

        if (pokemonType != null) {
            return service.filterByType(pokemonType);
        }

        return service.getAllCharacters();
    }

    @GetMapping("/{id}")
    public PokemonCharacter getCharacterById(@PathVariable("id") long id) {
        return service.getCharacterById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonCharacter createCharacter(
            @Valid @RequestBody PokemonCharacter character) {

        return service.createCharacter(character);
    }

    @PutMapping("/{id}")
    public PokemonCharacter updateCharacter(
            @PathVariable("id") long id,
            @Valid @RequestBody PokemonCharacter character) {

        return service.updateCharacter(id, character);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable("id") long id) {
        service.deleteCharacter(id);
    }
}