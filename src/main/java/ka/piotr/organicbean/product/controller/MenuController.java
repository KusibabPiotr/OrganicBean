package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.MenuMapper;
import ka.piotr.organicbean.product.exceptions.MenuNotFoundException;
import ka.piotr.organicbean.product.model.domain.Dish;
import ka.piotr.organicbean.product.model.domain.Drink;
import ka.piotr.organicbean.product.model.domain.Menu;
import ka.piotr.organicbean.product.model.domain.Product;
import ka.piotr.organicbean.product.model.dto.MenuDto;
import ka.piotr.organicbean.product.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @GetMapping("get")
    public MenuDto getMenu() throws MenuNotFoundException {
        return menuMapper.mapToMenuDto(menuService.getMenu().orElseThrow(MenuNotFoundException::new));
    }

    @PostMapping(value = "create",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@RequestBody MenuDto menuDto){
        menuService.saveMenu(menuMapper.mapToMenu(menuDto,null));
    }

    @PutMapping(value = "update/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public MenuDto updateMenu(@PathVariable Long id, @RequestBody MenuDto menuDto){
        return menuMapper.mapToMenuDto(menuService.saveMenu(menuMapper.mapToMenu(menuDto,id)));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);
    }
}
