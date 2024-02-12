package devmind.workshop_2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

        @JsonProperty("id")
        Long id;

        @JsonProperty("name")
        String name;

        @JsonProperty("base_experience")
        String baseExperience;

        @JsonProperty("weight")
        String weight;

        @JsonProperty("height")
        String height;
}
