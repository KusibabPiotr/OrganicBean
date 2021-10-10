package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DishMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.model.dto.DishDto;
import ka.piotr.organicbean.product.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final DishMapper dishMapper;

//  for user
    @GetMapping(value = "get")
    public List<DishDto> getAllByParams(@RequestParam(value = "allergens",required = false) String params) {
        return dishMapper.mapToDishDtoList(dishService.getAllByParams(params));
    }

//  for user??
    @GetMapping(value = "get/{dishId}")
    public DishDto getDish(@PathVariable Long dishId) throws DishNotFoundException {
        return dishMapper.mapToDishDto(dishService.getDish(dishId)
                .orElseThrow(DishNotFoundException::new));
    }
//  for admin
    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishMapper.mapToDishDto(dishService.createDish(dishMapper.mapToDish(dishDto)));
    }
//  for admin
    @PutMapping(value = "update/{dishId}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto updateDish(@PathVariable Long dishId, @RequestBody DishDto dishDto){
        return dishMapper.mapToDishDto(dishService.createDish(dishMapper.mapToDish(dishDto,dishId)));
    }
//  for admin
    @DeleteMapping(value = "delete/{dishId}")
    public void deleteDish(@PathVariable Long dishId){
        dishService.deleteDish(dishId);
    }
}
