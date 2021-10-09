package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DishMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
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

    @GetMapping(value = "getAll")
    public List<DishDto> getAll(){
       return dishMapper.mapToDishDtoList(dishService.getAllDishes());
    }

    @GetMapping(value = "getAllGlutenFree")
    public List<DishDto> getAllGlutenFree(@RequestParam boolean isGlutenFree){
        return dishMapper.mapToDishDtoList(dishService.getAllGlutenFree(isGlutenFree));
    }

    @GetMapping(value = "getAllVegan")
    public List<DishDto> getAllVegan(@RequestParam boolean isVegan){
        return dishMapper.mapToDishDtoList(dishService.getAllVegan(isVegan));
    }

    @GetMapping(value = "getAllVegetarian")
    public List<DishDto> getAllVegetarian(@RequestParam boolean isVegetarian){
        return dishMapper.mapToDishDtoList(dishService.getAllVegetarian(isVegetarian));
    }

    @GetMapping(value = "getAllGlutenFreeAndVegan")
    public List<DishDto> getAllGlutenFreeAndVegan(@RequestParam boolean isGlutenFree,
                                                  @RequestParam boolean isVegan){
        return dishMapper.mapToDishDtoList(dishService.getAllGlutenFreeAndVegan(isGlutenFree, isVegan));
    }

    @GetMapping(value = "getAllGlutenFreeAndVegetarian")
    public List<DishDto> getAllGlutenFreeAndVegetarian(@RequestParam boolean isGlutenFree,
                                          @RequestParam boolean isVegetarian){
        return dishMapper.mapToDishDtoList(dishService.getAllGlutenFreeAndVegetarian(isGlutenFree, isVegetarian));
    }
    @GetMapping(value = "getAllVeganAndVegetarian")
    public List<DishDto> getAllVeganAndVegetarian(@RequestParam boolean isVegan,
                                                  @RequestParam boolean isVegetarian){
        return dishMapper.mapToDishDtoList(dishService.getAllVeganAndVegetarian(isVegan, isVegetarian));
    }

    @GetMapping(value = "getAllGlutenFreeAndVeganAndVegetarian")
    public List<DishDto> getAllGlutenFreeAndVeganAndVegetarian(@RequestParam boolean isGlutenFree,
                                                  @RequestParam boolean isVegan,
                                                  @RequestParam boolean isVegetarian){
        return dishMapper.mapToDishDtoList(dishService.getAllGlutenFreeAndVeganAndVegetarian(isGlutenFree, isVegan, isVegetarian));
    }


    @GetMapping(value = "get/{id}")
    public DishDto getDish(@PathVariable Long id) throws DishNotFoundException {
        return dishMapper.mapToDishDto(dishService.getDish(id)
                .orElseThrow(DishNotFoundException::new));
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishMapper.mapToDishDto(dishService.saveDish(dishMapper.mapToDish(dishDto)));
    }

    @PutMapping(value = "update/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto updateDish(@PathVariable Long id, @RequestBody DishDto dishDto){
        return dishMapper.mapToDishDto(dishService.saveDish(dishMapper.mapToDish(dishDto)));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteDish(@PathVariable Long id){
        dishService.deleteDish(id);
    }
}
