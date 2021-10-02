package ka.piotr.organicbean.product.model.domain;

import ka.piotr.organicbean.product.model.domain.FoodType;
import ka.piotr.organicbean.product.model.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DishDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int kcal;
    private boolean glutenFree;
    private boolean vegan;
    private boolean vegetarian;
    private FoodType foodType;
    private Menu menu;
}
