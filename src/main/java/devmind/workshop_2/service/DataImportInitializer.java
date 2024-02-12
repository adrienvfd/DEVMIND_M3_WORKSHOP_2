package devmind.workshop_2.service;

import devmind.workshop_2.dto.PokemonLiteDto;
import devmind.workshop_2.entity.Pokemon;
import devmind.workshop_2.mapper.PokemonEnricher;
import devmind.workshop_2.mapper.PokemonMapper;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class DataImportInitializer {
    public static Set<Pokemon> pokemonList;

    public static void init() {
        String uri = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        List<PokemonLiteDto> pokemonLiteDtoList = PokemonMapper.toLiteDto(result);

        pokemonList = pokemonLiteDtoList.stream()
                .map(PokemonEnricher::enrichPokemon)
                .filter(Optional::isPresent)

                .map(Optional::get)
                .collect(toSet());
    }
}
