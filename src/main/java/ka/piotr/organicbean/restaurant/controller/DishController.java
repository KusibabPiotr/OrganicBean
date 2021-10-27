package ka.piotr.organicbean.restaurant.controller;

import ka.piotr.organicbean.restaurant.controller.mapper.DishMapper;
import ka.piotr.organicbean.restaurant.exceptions.DishNotFoundException;
import ka.piotr.organicbean.restaurant.exceptions.NoSuchAllergenTypeException;
import ka.piotr.organicbean.restaurant.model.dto.DishDto;
import ka.piotr.organicbean.restaurant.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<DishDto> getByParams(@RequestParam(value = "allergens",required = false) String params,
                                         @RequestParam(value = "name",required = false) String name,
                                         @RequestParam(value = "minKcal",defaultValue = "0") Integer minKcal,
                                         @RequestParam(value = "maxKcal",required = false) Integer maxKcal,
                                         @RequestParam(value = "minPrice",defaultValue = "0") Integer minPrice,
                                         @RequestParam(value = "maxPrice",required = false) Integer maxPrice)
            throws NoSuchAllergenTypeException {
        return DishMapper.mapToDishDtoList(
                dishService.getByParams(params,name,minKcal,maxKcal,minPrice,maxPrice));
    }

    @GetMapping(value = "/{dishId}")
    public DishDto getDish(@PathVariable Long dishId)
            throws DishNotFoundException {
        return DishMapper.mapToDishDto(dishService.getDish(dishId)
                .orElseThrow(DishNotFoundException::new));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return DishMapper.mapToDishDto(
                dishService.createDish(DishMapper.mapToDish(dishDto)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{dishId}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto updateDish(@PathVariable Long dishId,
                              @RequestBody DishDto dishDto)
            throws DishNotFoundException {
        return DishMapper.mapToDishDto(
                dishService.updateDish(DishMapper.mapToDish(dishDto),dishId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{dishId}")
    public void deleteDish(@PathVariable Long dishId)
            throws IllegalArgumentException{
        dishService.deleteDish(dishId);
    }
}
