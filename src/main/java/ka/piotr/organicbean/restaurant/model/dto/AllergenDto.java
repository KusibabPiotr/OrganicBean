package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.model.AllergenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AllergenDto {

    private Long id;
    private AllergenType allergenType;

}
