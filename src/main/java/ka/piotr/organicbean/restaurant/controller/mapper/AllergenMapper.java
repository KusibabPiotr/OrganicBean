package ka.piotr.organicbean.restaurant.controller.mapper;

import ka.piotr.organicbean.restaurant.model.domain.Allergen;
import ka.piotr.organicbean.restaurant.model.dto.AllergenDto;

import java.util.Set;
import java.util.stream.Collectors;

public class AllergenMapper {

    private AllergenMapper() {
    }

    public static AllergenDto mapToAllergenDto(final Allergen allergen){
        return AllergenDto.builder()
                .id(allergen.getId())
                .allergenType(allergen.getAllergenType())
                .build();
    }

    public static Allergen mapToAllergen(final AllergenDto allergenDto){
        return Allergen.builder()
                .id(allergenDto.getId())
                .allergenType(allergenDto.getAllergenType())
                .build();
    }

    public static Set<AllergenDto> mapToAllergenDtoSet(final Set<Allergen> allergens){
        return allergens.stream()
                .map(AllergenMapper::mapToAllergenDto)
                .collect(Collectors.toSet());
    }

    public static Set<Allergen> mapToAllergenSet(final Set<AllergenDto> allergens){
        return allergens.stream()
                .map(AllergenMapper::mapToAllergen)
                .collect(Collectors.toSet());
    }
}
