package ka.piotr.organicbean.product.model.dto;

import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.model.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public final class MenuDto {

    private Long id;
    private List<Dish> dishList;
    private List<Drink> drinkList;
    private List<Product> productList;

//    private static MenuDto menuDto = null;
//
//    private MenuDto(){}
//
//    private MenuDto(List<Dish> dishList, List<Drink> drinkList, List<Product> productList) {
//        this.dishList = dishList;
//        this.drinkList = drinkList;
//        this.productList = productList;
//    }
//
//    public static MenuDto getInstance() {
//        if (menuDto == null) {
//            menuDto = new MenuDto(new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//        }
//        return menuDto;
//    }

}
