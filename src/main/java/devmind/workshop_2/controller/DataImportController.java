package devmind.workshop_2.controller;

import devmind.workshop_2.dto.PokemonLiteDto;
import devmind.workshop_2.entity.Pokemon;
import devmind.workshop_2.mapper.PokemonEnricher;
import devmind.workshop_2.mapper.PokemonMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/import")
public class DataImportController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private List<Pokemon> importData() {
        String uri = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        List<PokemonLiteDto> pokemonLiteDtoList = PokemonMapper.toLiteDto(result);

        return pokemonLiteDtoList.stream()
                .map(PokemonEnricher::enrichPokemon)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
