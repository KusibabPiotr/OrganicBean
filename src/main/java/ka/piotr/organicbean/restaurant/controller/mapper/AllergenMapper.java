package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Allergen;
import ka.piotr.organicbean.restaurant.model.dto.AllergenDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AllergenMapper {

    public AllergenDto mapToAllergenDto(final Allergen allergen){
        return new AllergenDto(allergen.getId(),
                allergen.getAllergenType());
    }

    public Allergen mapToAllergen(final AllergenDto allergenDto){
        return new Allergen(allergenDto.getId(),
                allergenDto.getAllergenType());
    }

    public Set<AllergenDto> mapToAllergenDtoSet(final Set<Allergen> allergens){
        return allergens.stream()
                .map(this::mapToAllergenDto)
                .collect(Collectors.toSet());
    }

    public Set<Allergen> mapToAllergenSet(final Set<AllergenDto> allergens){
        return allergens.stream()
                .map(this::mapToAllergen)
                .collect(Collectors.toSet());
    }
}
