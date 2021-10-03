package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DrinkMapper;
import ka.piotr.organicbean.product.exceptions.DrinkNotFoundException;
import ka.piotr.organicbean.product.model.dto.DrinkDto;
import ka.piotr.organicbean.product.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/drink")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;
    private final DrinkMapper drinkMapper;

    @GetMapping(value = "get")
    public List<DrinkDto> getDrinks(){
        return drinkMapper.mapToDrinkDtoList(drinkService.getDrinks());
    }

    @GetMapping(value = "get/{id}")
    public DrinkDto getDrink(@PathVariable Long id) throws DrinkNotFoundException {
        return drinkMapper.mapToDrinkDto(drinkService.getDrink(id)
                .orElseThrow(DrinkNotFoundException::new));
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public DrinkDto createDrink(@RequestBody DrinkDto drinkDto){
        return drinkMapper.mapToDrinkDto(drinkService.saveDrink(drinkMapper.mapToDrink(drinkDto)));
    }

    @PutMapping(value = "update",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DrinkDto updateDrink(@RequestBody DrinkDto drinkDto){
        return drinkMapper.mapToDrinkDto(drinkService.saveDrink(drinkMapper.mapToDrink(drinkDto)));
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteDrink(@PathVariable Long id){
        drinkService.deleteDrink(id);
    }
}
