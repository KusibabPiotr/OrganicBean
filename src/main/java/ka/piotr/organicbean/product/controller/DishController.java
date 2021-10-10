package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DishMapper;
import ka.piotr.organicbean.product.exceptions.DishNotFoundException;
import ka.piotr.organicbean.product.model.dto.DishDto;
import ka.piotr.organicbean.product.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;
    private final DishMapper dishMapper;

    @GetMapping(value = "get")
    public List<DishDto> getAllByParams(@RequestParam(value = "allergens",required = false) String params) {
        System.out.println(params);
        return dishMapper.mapToDishDtoList(dishService.getAllByParams(params));
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
