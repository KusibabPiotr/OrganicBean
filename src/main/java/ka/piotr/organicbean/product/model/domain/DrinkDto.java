package ka.piotr.organicbean.product.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DrinkDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int kcal;
    private Menu menu;

}
