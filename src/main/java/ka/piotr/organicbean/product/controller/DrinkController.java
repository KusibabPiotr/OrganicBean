package ka.piotr.organicbean.product.controller;

import ka.piotr.organicbean.product.controller.mapper.DrinkMapper;
import ka.piotr.organicbean.product.exceptions.DrinkNotFoundException;
import ka.piotr.organicbean.product.model.domain.DrinkDto;
import ka.piotr.organicbean.product.service.DrinkService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/v1/drink")
public class DrinkController {

    private DrinkService drinkService;
    private DrinkMapper drinkMapper;

    public DrinkController(DrinkService drinkService, DrinkMapper drinkMapper) {
        this.drinkService = drinkService;
        this.drinkMapper = drinkMapper;
    }

    @GetMapping(value = "getDrinks")
    public List<DrinkDto> getDrinks(){
        return drinkMapper.mapToDrinkDtoList(drinkService.getDrinks());
    }

    @GetMapping(value = "getDrink")
    public DrinkDto getDrink(@RequestParam Long id) throws DrinkNotFoundException {
        return drinkMapper.mapToDrinkDto(drinkService.getDrink(id)
                .orElseThrow(DrinkNotFoundException::new));
    }

    @PostMapping(value = "createDrink",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDrink(@RequestBody DrinkDto drinkDto){
        drinkService.saveDrink(drinkMapper.mapToDrink(drinkDto));
    }

    @PutMapping(value = "updateDrink",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public DrinkDto updateDrink(@RequestBody DrinkDto drinkDto){
        return drinkMapper.mapToDrinkDto(drinkService.saveDrink(drinkMapper.mapToDrink(drinkDto)));
    }

    @DeleteMapping(value = "deleteDrink")
    public void deleteDrink(@RequestParam Long id){
        drinkService.deleteDrink(id);
    }
}
