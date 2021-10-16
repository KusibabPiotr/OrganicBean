package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DishMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.exceptions.NoSuchAllergenTypeException;
import ka.piotr.organicbean.product.model.dto.DishDto;
import ka.piotr.organicbean.product.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final DishMapper dishMapper;

    @GetMapping(value = "get")
    public List<DishDto> getAllByParams(@RequestParam(value = "allergens",required = false) String params) {
        return dishMapper.mapToDishDtoList(dishService.getAllByParams(params));
    }

    @GetMapping(value = "get2")
    public List<DishDto> getAllByParams2(@RequestParam(value = "allergens",required = false) String params)
            throws NoSuchAllergenTypeException {
        return dishMapper.mapToDishDtoList(dishService.getAllByParams2(params));
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
