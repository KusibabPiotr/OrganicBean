package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.MenuMapper;
import ka.piotr.organicbean.product.exceptions.MenuNotFoundException;
import ka.piotr.organicbean.product.model.dto.MenuDto;
import ka.piotr.organicbean.product.service.MenuService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/menu")
public class MenuController {

    private MenuService menuService;
    private MenuMapper menuMapper;

    public MenuController(MenuService menuService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    @GetMapping("getMenu")
    public MenuDto getMenu() throws MenuNotFoundException {
        return menuMapper.mapToMenuDto(menuService.getMenu().orElseThrow(MenuNotFoundException::new));
    }

    @PostMapping(value = "createMenu",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@RequestBody MenuDto menuDto){
        menuService.saveMenu(menuMapper.mapToMenu(menuDto));
    }

    @PutMapping(value = "updateMenu",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public MenuDto updateMenu(@RequestBody MenuDto menuDto){
        return menuMapper.mapToMenuDto(menuService.saveMenu(menuMapper.mapToMenu(menuDto)));
    }

    @DeleteMapping(value = "deleteMenu")
    public void deleteMenu(@RequestParam Long id){
        menuService.deleteMenu(id);
    }
}
