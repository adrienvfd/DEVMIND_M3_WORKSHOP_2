package devmind.workshop_2.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import devmind.workshop_2.entity.Pokemon;
import devmind.workshop_2.dto.PokemonLiteDto;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class PokemonEnricher {

    public static Optional<Pokemon> enrichPokemon(PokemonLiteDto pokemonLite) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = pokemonLite.getUrl();
        String string = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();

        try {
            Pokemon pokemon = mapper.readValue(string, mapper.getTypeFactory().constructType(Pokemon.class));
            return Optional.of(pokemon);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

