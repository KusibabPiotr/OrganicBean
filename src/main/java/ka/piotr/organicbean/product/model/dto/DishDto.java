package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.DishType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class DishDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int kcal;
    private boolean glutenFree;
    private boolean vegan;
    private boolean vegetarian;
    private DishType dishType;
}


