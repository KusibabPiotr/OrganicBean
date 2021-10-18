package ka.piotr.organicbean.restaurant.controller;

import ka.piotr.organicbean.restaurant.controller.mapper.DishMapper;
import ka.piotr.organicbean.restaurant.exceptions.DishNotFoundException;
import ka.piotr.organicbean.restaurant.exceptions.NoSuchAllergenTypeException;
import ka.piotr.organicbean.restaurant.model.dto.DishDto;
import ka.piotr.organicbean.restaurant.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final DishMapper dishMapper;

    @GetMapping(value = "get")
    public List<DishDto> getByParams(@RequestParam(value = "allergens",required = false) String params,
                                         @RequestParam(value = "name",required = false) String name,
                                         @RequestParam(value = "minKcal",defaultValue = "0") Integer minKcal,
                                         @RequestParam(value = "maxKcal",required = false) Integer maxKcal,
                                         @RequestParam(value = "minPrice",defaultValue = "0") Integer minPrice,
                                         @RequestParam(value = "maxPrice",required = false) Integer maxPrice)
            throws NoSuchAllergenTypeException {
        return dishMapper.mapToDishDtoList(dishService.getByParams(params,name,minKcal,maxKcal,minPrice,maxPrice));
    }

    @GetMapping(value = "get/{dishId}")
    public DishDto getDish(@PathVariable Long dishId) throws DishNotFoundException {
        return dishMapper.mapToDishDto(dishService.getDish(dishId)
                .orElseThrow(DishNotFoundException::new));
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishMapper.mapToDishDto(dishService.createDish(dishMapper.mapToDish(dishDto)));
    }

    @PutMapping(value = "update/{dishId}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto updateDish(@PathVariable Long dishId, @RequestBody DishDto dishDto){
        return dishMapper.mapToDishDto(dishService.createDish(dishMapper.mapToDish(dishDto,dishId)));
    }

    @DeleteMapping(value = "delete/{dishId}")
    public void deleteDish(@PathVariable Long dishId){
        dishService.deleteDish(dishId);
    }
}
