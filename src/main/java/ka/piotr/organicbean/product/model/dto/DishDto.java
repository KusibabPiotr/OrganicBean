package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.AllergenType;
import ka.piotr.organicbean.product.model.DishType;
import ka.piotr.organicbean.product.model.domain.Allergen;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
public final class DishDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int kcal;
    private Set<AllergenDto> allergens;
    private DishType dishType;
}


