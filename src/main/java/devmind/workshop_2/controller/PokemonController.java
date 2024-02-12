package devmind.workshop_2.controller;

import devmind.workshop_2.entity.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static devmind.workshop_2.service.DataImportInitializer.pokemonList;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @RequestMapping("/")
    public Set<Pokemon> getAllPokemon() {
        return pokemonList;
    }

    @RequestMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public Pokemon updatePokemon(@RequestBody Pokemon pokemon) {
        if (pokemonList.stream().anyMatch(p -> p.getId().equals(pokemon.getId()))) {
            pokemonList.add(pokemon);
            return pokemon;
        } else {
            throw new RuntimeException("Pokemon not found");
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Pokemon updatePokemonNameById(@PathVariable Long id, @RequestParam("name") String name) {
        Optional<Pokemon> pokemon = pokemonList.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (pokemon.isPresent()) {
            Pokemon pokemonToUpdate = pokemon.get();
            pokemonToUpdate.setName(name);
            return pokemonToUpdate;
        } else {
            throw new RuntimeException("Pokemon not found");
        }
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        if (pokemonList.stream().anyMatch(p -> p.getId().equals(pokemon.getId()))) {
            throw new RuntimeException("Pokemon already exists");
        }
        pokemonList.add(pokemon);
        return pokemon;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public Pokemon createPokemon(@PathVariable Long id, @RequestParam("name") String name) {
        if (pokemonList.stream().anyMatch(p -> p.getId().equals(id))) {
            throw new RuntimeException("Pokemon already exists");
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setId(id);
        pokemon.setName(name);
        pokemonList.add(pokemon);
        return pokemon;
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public void deletePokemon(@RequestBody Pokemon pokemon) {
        if (!pokemonList.contains(pokemon)) {
            throw new RuntimeException("Pokemon not found");
        }
        pokemonList.remove(pokemon);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletePokemonById(@PathVariable Long id) {
        if (pokemonList.stream().noneMatch(p -> p.getId().equals(id))) {
            throw new RuntimeException("Pokemon not found");
        }
        pokemonList.removeIf(p -> p.getId().equals(id));
    }
}
