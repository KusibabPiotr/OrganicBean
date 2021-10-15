package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.AllergenType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AllergenDto {

    private Long id;
    private AllergenType allergenType;

}
