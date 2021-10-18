package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.model.AllergenType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AllergenDto {

    private Long id;
    private AllergenType allergenType;

}
