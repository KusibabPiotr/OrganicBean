package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DishMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.model.dtoo.DishDto;
import ka.piotr.organicbean.product.service.DishService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dish")
public class DishController {

    private DishService dishService;
    private DishMapper dishMapper;

    public DishController(DishService dishService, DishMapper dishMapper) {
        this.dishService = dishService;
        this.dishMapper = dishMapper;
    }

    @GetMapping(value = "getDishes")
    public List<DishDto> getDishes(){
       return dishMapper.mapToDishDtoList(dishService.getDishes());
    }

    @GetMapping(value = "getDish")
    public DishDto getDish(@RequestParam Long id) throws DishNotFoundException {
        return dishMapper.mapToDishDto(dishService.getDish(id)
                .orElseThrow(DishNotFoundException::new));
    }

    @PostMapping(value = "createDish",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@RequestBody DishDto dishDto){
        dishService.saveDish(dishMapper.mapToDish(dishDto));
    }

    @PutMapping(value = "updateDish",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto updateDish(@RequestBody DishDto dishDto){
        return dishMapper.mapToDishDto(dishService.saveDish(dishMapper.mapToDish(dishDto)));
    }

    @DeleteMapping(value = "deleteDish")
    public void deleteDish(@RequestParam Long id){
        dishService.deleteDish(id);
    }
}
