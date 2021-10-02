package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.model.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MenuDto {

    private Long id;
    private List<Dish> dishList;
    private List<Drink> drinkList;
    private List<Product> productList;

}
