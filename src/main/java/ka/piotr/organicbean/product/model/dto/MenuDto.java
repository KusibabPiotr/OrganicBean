package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.model.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public final class MenuDto {

    private List<Dish> dishList;
    private List<Drink> drinkList;
    private List<Product> productList;

}
