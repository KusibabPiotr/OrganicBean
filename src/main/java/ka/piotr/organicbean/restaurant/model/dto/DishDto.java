package ka.piotr.organicbean.restaurant.model.dto;

import ka.piotr.organicbean.restaurant.model.DishType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
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


