package ka.piotr.organicbean.product.controller.mapper;

import ka.piotr.organicbean.product.model.domain.Menu;
import ka.piotr.organicbean.product.model.dto.MenuDto;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuDto mapToMenuDto(final Menu menu){
        return new MenuDto(menu.getId(),
                menu.getDishList(),
                menu.getDrinkList(),
                menu.getProductList());
    }

    public Menu mapToMenu(final MenuDto menuDto){
        return new Menu(menuDto.getId(),
                menuDto.getDishList(),
                menuDto.getDrinkList(),
                menuDto.getProductList());
    }
}
